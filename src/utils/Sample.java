package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

//import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class Sample {
	
	File uncList, sampleFile;
	String clientNum, s, line;
	Label label;
	ArrayList<String> clientList = new ArrayList<String>();
	ArrayList<String> sampleClient = new ArrayList<String>();
	ProgressIndicator pi = new ProgressIndicator();
	//s=null;
	
	public Sample(File uncList, Label label, ProgressIndicator pi) {
		super();
		s=null;
		this.uncList = uncList;
		this.label = label;
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(uncList),"Cp1251"));
			while((s = br.readLine())!=null){
				clientList.add(s);
			}
			br.close();
		} catch (/*UnsupportedEncodingException | FileNotFoundException |*/ IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.pi=pi;
	}
	
	public Sample(String clientNum, Label label, ProgressIndicator pi) {
		super();
		this.clientNum = clientNum;
		this.label = label;
		clientList.add(clientNum);
		this.pi=pi;
	}
	
	public void createSampleFile(File chsCustDir){
		sampleFile = new File(chsCustDir.getParent(), "file.csv");
		label.setText(sampleFile.getAbsolutePath());
		try {
			if (sampleFile.exists()){
				sampleFile.delete();
				sampleFile.createNewFile();
				//System.out.println(sampleFile.getAbsolutePath()+"created");
			}else{
				sampleFile.createNewFile();
				//System.out.println(sampleFile.getAbsolutePath()+"created");
			}
			doSample(chsCustDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {
//			doSample(chsCustDir);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void doSample(File folder) throws IOException{
		
		String str = null;
		 
		 File[] folderEntries = folder.listFiles();
		 
		 for (File entry : folderEntries)
		 {
			 if (entry.isDirectory())
			 {
				 doSample(entry);
				 continue;
			 }
			 str = entry.getName();
			 if (str.endsWith(".csv")){
				 
					 BufferedReader brFile = new BufferedReader(new InputStreamReader(new FileInputStream(entry)));
					 while((line=brFile.readLine())!=null){
						 for(String s : clientList){
						 if (line.contains("	"+s+"	")){
							 sampleClient.add(line);
//							 System.out.println(s);
//							 System.out.println(str);
							 //clientList.remove(clientList.indexOf(s));
							// System.out.println(line);
						 }//else{continue;}
//						 String m=("*["+s+"]*");
//						 Pattern p = Pattern.compile(m);
//						 Matcher mtchr = p.matcher(line);
//						 if(mtchr.matches()){
//							 System.out.println(line);
//						 }else{continue;}
					 }
					 
				 }
					 brFile.close();
			 }//else{continue;}
			 
		 }
		writeSample();
	}
	
	public void writeSample(){
		BufferedWriter bufFile = null;
		 try {
			bufFile = new BufferedWriter(new FileWriter(sampleFile));
					//new BufferedReader(new InputStreamReader(new FileInputStream(sampleFile)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 for (String l : sampleClient){
			 try {
				bufFile.write(l+"\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
		 try {
			bufFile.flush();
			bufFile.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pi.setProgress(1);
	}
	
}

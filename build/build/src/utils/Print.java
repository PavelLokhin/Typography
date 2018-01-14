package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Print {

	private static final String FOOTER = "%%EOF";
	ArrayList<File> newFiles = new ArrayList<File>();
	//String END_OF_FILE = "%%EOF";
	//InputStream inH = null;
	//InputStream inF = null;
	//ArrayList<String> headerTmp = new ArrayList();
	//ArrayList<File> fileTmp = new ArrayList<File>();
	
	public void startPrnt(List<File> files, File header, File prntDir){
	
//		BufferedReader brHeader=null;
//		try {
//			brHeader = new BufferedReader(new InputStreamReader(new FileInputStream(header),"Cp1251"));
//		} catch (UnsupportedEncodingException | FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ArrayList<String> f1 = new ArrayList<String>();
		String s;
		BufferedReader brHeader=null;
		
		for(int a=0; a<files.size(); a++){
					
			try {
				brHeader = new BufferedReader(new InputStreamReader(new FileInputStream(header),"Cp1251"));
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				while ((s=brHeader.readLine())!=null){
				f1.add(s);
				//System.out.println(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			BufferedReader brFile=null;
			try {
				//Get file with 'a' num
				brFile = new BufferedReader(new InputStreamReader(new FileInputStream(files.get(a)),"Cp1251"));
			} catch (UnsupportedEncodingException | FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				while ((s=brFile.readLine())!=null){
					f1.add(s);
					//System.out.println(s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			f1.add(FOOTER);
			BufferedWriter bw = null;
			try {
				bw = new BufferedWriter(new FileWriter(files.get(a)));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i=0; i<f1.size(); i++){
				try {
					bw.write(f1.get(i)+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		try {
			brHeader.close();
			brFile.close();
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f1.clear();
				
	}
		//copyFiles(files, prntDir);		
	}
	
	private static void copyFiles(List<File>newFiles, File dest){	
		InputStream in =null;
		OutputStream os = null;
		byte[] buffer = null;
		try{
			os=new FileOutputStream(dest);
			buffer = new byte[1024];
		}catch(IOException e){e.printStackTrace();}
		int length;
	for (File f:newFiles){
		try{
			in = new FileInputStream(f);
			while ((length = in.read(buffer)) > 0) {
				os.write(buffer, 0, length);
	       }
		}catch(IOException e){e.printStackTrace();}
	}
	try{
	in.close();
	os.close();
	}catch(IOException e){e.printStackTrace();}
	}
//			for (File f : newFiles){
//				try{
//					in = new FileInputStream(f);
//					Files.copy(in, dest, StandardCopyOption.REPLACE_EXISTING);
//					//Files.copy(in, dest.getAbsolutePath(), StandardCopyOption.REPLACE_EXISTING);
//				}catch(IOException e){e.printStackTrace();}
//				
//			}
//			try {
//				in.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
	
	
}

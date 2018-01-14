package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.ArrayList;
//import java.net.URL;
import java.util.List;
//import java.util.ResourceBundle;

import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utils.Editor;
import utils.Sample;

//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;

public class Controller{
	/*UNC - Unique Number of Client*/
	Stage primaryStage;
	String clientNum;
	File file;
	File header;
	File dir;
	File prntDir;
	File chsCustDir, uncList, sampleFile;
	List<File> files;
	@FXML
	Button btnR = new Button();
	@FXML
	Button choosePrinter = new Button();
	@FXML
	Button chsFile = new Button();
	@FXML
	Button chsFolder = new Button();
	@FXML
	ProgressIndicator editProg = new ProgressIndicator();
	@FXML
	ProgressIndicator headerProg = new ProgressIndicator();
	@FXML
	ProgressIndicator findIndicator = new ProgressIndicator();
	@FXML
	TextArea filesList;
	@FXML
	TextField txtField1 = new TextField();
	@FXML
	TextField txtField2 = new TextField();
	@FXML
	TextField psHeader;
	@FXML
	TextField printer;
	@FXML
	TextField folder;
	@FXML
	TextField unc;
	@FXML
	Label label;
		
	public void chooseFolder(){
		DirectoryChooser directoryChooser = new DirectoryChooser();
		dir = directoryChooser.showDialog(primaryStage);
		//Choose dir with lists
		if (dir != null && btnR.isFocused()==true){
			txtField1.setText(dir.getAbsolutePath());
			editProg.setProgress(0);
		//Choose Printer	
		}else if (dir !=null && choosePrinter.isFocused()){
			printer.setText(dir.getAbsolutePath());
			prntDir = dir;
		//Choose dir files for sample	
		}else if (dir !=null && chsFolder.isFocused()){
			findIndicator.setProgress(0);
			folder.setText(dir.getAbsolutePath());
			chsCustDir=dir;
		}
	 }
	//Choose Files for Printing
	public void filesChooser(){
		headerProg.setProgress(0);
		FileChooser fileChooser = new FileChooser();
		filesList.clear();
		files = fileChooser.showOpenMultipleDialog(primaryStage);
		printLog(filesList, files);
		
	}
	//Start Printing
	public void startPrinting() throws FileNotFoundException{
		Print prnt = new Print();
		File s = new File("C:/");
		if (prntDir == null){prntDir=s;}
		prnt.startPrnt(files, header, prntDir);
		headerProg.setProgress(1);
	}
	//Editing lists
	public void runEditor(){
		//editProg.setProgress(0);
		//editProg = new ProgressIndicator();
		Editor edit = new Editor();
		file = new File(dir.getAbsolutePath());
		try {
			edit.edit(file);
			editProg.setProgress(1);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//System.out.println("Done");
		//editProg.setProgress(0);
	}
	//Choose PostScript Header
	public void chooseHeader(){
		FileChooser fileChooser = new FileChooser();
		header = fileChooser.showOpenDialog(primaryStage);
		psHeader.setText(header.getName());
	}
	//Show List of Files for Printing
	public void printLog(TextArea textArea, List<File> files){
		if (files == null || files.isEmpty()){
			return;
		}
		for (File f : files){
			textArea.appendText(f.getAbsolutePath()+"\n");
		}
	}
	//Choose list of clients
	public void chooseUNC(){
		FileChooser fileChooser = new FileChooser();
		if (chsFile.isFocused()){
			// && fileChooser.showOpenDialog(primaryStage)!=null
			uncList = fileChooser.showOpenDialog(primaryStage);
			unc.setText(uncList.getName());
		}else{
			clientNum=unc.getText();
		}
	}
	
	public void makeSample(){
		//System.out.println(clientNum);
		//findIndicator.setProgress(0);
		if (uncList!=null){
			Sample sample = new Sample(uncList, label, findIndicator);
			sample.createSampleFile(chsCustDir);
			//System.out.println("hfuewhfuw");
		}else if(clientNum!=null){
			Sample sample = new Sample(clientNum, label, findIndicator);
			sample.createSampleFile(chsCustDir);
			//System.out.println("hfuewhfuw");
		}
		//findIndicator.setProgress(1);
	}
	//	Button button = new Button();
	//	System.out.println(txtField1.getId());
		//txtField1.setText("1231231");
	//	txtField2.setText("Hello");
}
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		chooseFolder();
//	}



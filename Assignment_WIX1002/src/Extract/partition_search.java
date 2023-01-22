package Extract;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

class partition_search{
	String toFile;
	String keyword;
	int n;
	
	partition_search(String toFile,String keyword,int n){
		this.toFile=toFile;
		this.keyword=keyword;
		this.n=n;
	}
	public void search_func() throws Exception{
    	ArrayList<String> list=new ArrayList<>();
    	Scanner sc=new Scanner(new FileInputStream("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/partition_file.txt"));
    	
    	String entered= JOptionPane.showInputDialog("Enter "+ this.keyword);
    	boolean found=false;
    	
    	while (sc.hasNextLine()) {
            String line = sc.nextLine();
            Pattern pattern = Pattern.compile("\\[(.*?)\\].*JobId=(\\d+).*NodeList=(\\w+).*#CPUs=(\\d+).*Partition=(.*)");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            String search=matcher.group(this.n);
            if (search.equals(entered)) {
            	list.add(line);
            	found=true;
            }
        }

    	if (!(found)) {JOptionPane.showMessageDialog(null, "Invalid "+keyword, "Error",JOptionPane.WARNING_MESSAGE);return;}
    	
    	PrintWriter writer =new PrintWriter(toFile);
    	writer.println("┌"+"─".repeat(25)+"┬"+"─".repeat(25)+"┬"+"─".repeat(25)+"┬"+"─".repeat(26)+"┬"+"─".repeat(25)+"┐");
 		writer.printf("│%-24s │%-24s │%-24s │ %-24s │ %-24s│\n", "Time", "JobId", "Node List","CPU","Partition");
 		writer.println("│"+"─".repeat(25)+"┼"+"─".repeat(25)+"┼"+"─".repeat(25)+"┼"+"─".repeat(26)+"┼"+"─".repeat(25)+"│");
    	for (String line1 :list) {
            Pattern pattern = Pattern.compile("\\[(.*?)\\].*JobId=(\\d+).*NodeList=(\\w+).*#CPUs=(\\d+).*Partition=(.*)");
            Matcher matcher = pattern.matcher(line1);
            matcher.find();
            writer.printf("│%-24s │%-24s │%-24s │ %-24s │ %-24s│\n",matcher.group(1),matcher.group(2),matcher.group(3),matcher.group(4),matcher.group(5));
    	}
    	writer.println("└"+"─".repeat(25)+"┴"+"─".repeat(25)+"┴"+"─".repeat(25)+"┴"+"─".repeat(26)+"┴"+"─".repeat(25)+"┘");
        writer.close();
    	new OpenFile().showFile(toFile);
    }
}
   


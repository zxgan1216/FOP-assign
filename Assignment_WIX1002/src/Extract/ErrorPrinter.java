package Extract;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.*;

import javax.swing.JOptionPane;

public class ErrorPrinter {
	private final String SOURCE;
	
	public ErrorPrinter(String source) {
		this.SOURCE = source;
	}
	
	public void printAll(String toFile) throws IOException {
		OpenFile open=new OpenFile();
		this.printTableBy(toFile, ",");
		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(toFile, true)))) {
			
			Error_counter counter = new Error_counter(this.SOURCE);
			writer.println("Errors count: " + counter.countAllLines());
			open.showFile(toFile);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void printUser(String toFile, String user) {
		OpenFile open=new OpenFile();
		this.printTableBy(toFile, user);
		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(toFile, true)))) {
			
			Error_counter counter = new Error_counter(this.SOURCE);
			writer.printf("%s's Long: %s\n", user, counter.countIfContainsAll(user, "long"));
			writer.printf("%s's Normal: %s\n", user, counter.countIfContainsAll(user, "normal"));
			open.showFile(toFile);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void printError(String toFile, String error) {
		OpenFile open=new OpenFile();
		this.printTableBy(toFile,error);
		try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(toFile, true)))) {
			
			Error_counter counter = new Error_counter(this.SOURCE);
			writer.println("Error: " + counter.countIfContain(error));
			open.showFile(toFile);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public StringBuilder getLines(String... keywords) throws IOException {
		StringBuilder tmp = new StringBuilder();
		
		try(Stream<String> lines = Files.lines(Paths.get(this.SOURCE))) {
			lines.filter(line -> Arrays.stream(keywords).allMatch(line::contains))
				 .forEach(line -> tmp.append(line).append("\n"));
		}
		
		return tmp;
	}
	
	public void printTableBy(String toFile, String... keywords) {
		try (PrintWriter writer = new PrintWriter(toFile)) {
			StringBuilder tmp = this.getLines(keywords);
			
			if (tmp.length() == 0) {
				JOptionPane.showMessageDialog(null, "INVALID CODE", "Error",JOptionPane.WARNING_MESSAGE);
				return ;
			}
			
			// Header
			writer.println("┌"+"─".repeat(16)+"┬"+"─".repeat(22)+"┬"+"─".repeat(16)+"┐");
			writer.printf("│%-15s │ %-20s │ %-15s│\n", "Association", "Username", "Error type");
			writer.println("│"+"─".repeat(16)+"┼"+"─".repeat(22)+"┼"+"─".repeat(16)+"│");
			
			// Content
			for(String line : tmp.toString().split("\n")) {
				String[] info = line.split(",");
				writer.printf("│%-15s │ %-20s │ %-15s│\n", info[0], info[2], info[4]);
			}
				
			// Footer
			writer.println("└"+"─".repeat(16)+"┴"+"─".repeat(22)+"┴"+"─".repeat(16)+"┘");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
			
	}
}

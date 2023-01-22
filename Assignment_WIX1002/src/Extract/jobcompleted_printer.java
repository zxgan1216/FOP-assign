package Extract;

import java.io.PrintWriter;
import java.util.ArrayList;

class jobcompleted_printer{
	public void printTable(String toFile,ArrayList<String> ID,ArrayList<String> jobCompleted,ArrayList<String> jobSubmitted) throws Exception {
		jobcompleted_data ori_data=new jobcompleted_data();
		ori_data.keepData();
		try (PrintWriter writer = new PrintWriter(toFile)) {
			
			
			writer.println("┌"+"─".repeat(10)+"┬"+"─".repeat(30)+"┬"+"─".repeat(30)+"┐");
			writer.printf("│%-9s │ %-28s │ %-29s│\n", "ID", "Job Submitted(time)", "Job Completed(time)");
			writer.println("│"+"─".repeat(10)+"┼"+"─".repeat(30)+"┼"+"─".repeat(30)+"│");
			
			// Content
			for (int i=0;i<ID.size();i++) {
				writer.printf("│%-9s │ %-28s │ %-29s│\n", ID.get(i), jobSubmitted.get(i), jobCompleted.get(i));
			}
				
			// Footer
			writer.println("└"+"─".repeat(10)+"┴"+"─".repeat(30)+"┴"+"─".repeat(30)+"┘");
			writer.printf("Total work = %d",ori_data.getCount());
			
			writer.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
			
	}
}

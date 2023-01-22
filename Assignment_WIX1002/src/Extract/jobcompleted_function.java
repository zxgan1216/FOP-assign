package Extract;

import java.io.PrintWriter;
import java.util.LinkedHashMap;

import javax.swing.JOptionPane;

class jobcompleted_function{
    
	public void calAvg() throws Exception {
		jobcompleted_data ori_data=new jobcompleted_data();
		ori_data.keepData();
		jobcompleted_calculator data=new jobcompleted_calculator();
		data.calTime();
		String []message = {"Total work = "+ori_data.ID.size(),"Total execution time = "+Double.toString(data.getTotalTime()/1000)+" seconds","Average execution time = "+Double.toString(data.average/1000)+" seconds"};
		JOptionPane.showMessageDialog(null,message,"Average exceution time",JOptionPane.PLAIN_MESSAGE);
	}
	
	public void printList() throws Exception {
		jobcompleted_data ori_data=new jobcompleted_data();
		ori_data.keepData();
		OpenFile open=new OpenFile();
		String file="/Users/zxgan/eclipse-workspace/WIX1002/src/Extract/List job and time.txt";
		new jobcompleted_printer().printTable(file, ori_data.ID, ori_data.jobCompleted, ori_data.jobSubmitted);
		open.showFile(file);
	}
	
	 
	 
	 public void findId() throws Exception {
		 jobcompleted_data ori_data=new jobcompleted_data();
		 ori_data.keepData();
		 String file="/Users/zxgan/eclipse-workspace/WIX1002/src/Extract/List job and time by ID.txt";
		 PrintWriter writer =new PrintWriter(file);
		 String id_enter=JOptionPane.showInputDialog("Enter ID");
		 boolean find=false;
		 
		 for (int i=0;i<ori_data.ID.size();i++) {
			 if (id_enter.equals(ori_data.ID.get(i))) {
				 find=true;
				 break;
			 }
		 }
		 
		 if(find) {
			 OpenFile open=new OpenFile();
			 jobcompleted_calculator data=new jobcompleted_calculator();
			 writer.println("┌"+"─".repeat(10)+"┬"+"─".repeat(30)+"┬"+"─".repeat(30)+"┐");
			 writer.printf("│%-9s │ %-28s │ %-29s│\n", "ID", "Job Submitted(time)", "Job Completed(time)");
			 writer.println("│"+"─".repeat(10)+"┼"+"─".repeat(30)+"┼"+"─".repeat(30)+"│");
			 for (int i=0;i<ori_data.ID.size();i++) {
				 if (id_enter.equals(ori_data.ID.get(i))) {
					 writer.printf("│%-9s │ %-28s │ %-29s│\n", ori_data.ID.get(i), ori_data.jobSubmitted.get(i), ori_data.jobCompleted.get(i));
					 writer.print("└"+"─".repeat(10)+"┴"+"─".repeat(30)+"┴"+"─".repeat(30)+"┘");
					 double time = data.getTimeDifference(ori_data.jobSubmitted.get(i),ori_data.jobCompleted.get(i));
					 writer.printf("\nTime for job %s is %.2f seconds",ori_data.ID.get(i),time);
					 
				 }
			 }
			 open.showFile(file);
		 }
		 else JOptionPane.showMessageDialog(null, "INVALID JOB ID", "Error",JOptionPane.WARNING_MESSAGE);
		 
		 writer.close();
	 }
	 
	 public void time_chart() throws Exception {
			int count_1min=0;
			int count_30min=0;
			int count_1hour=0;
			int count_12hour=0;
			int count_24hour=0;
			int count_more_24hour=0;
			jobcompleted_data ori_data=new jobcompleted_data();
			ori_data.keepData();
			LinkedHashMap<String, Integer> time_division = new LinkedHashMap<String, Integer>();
			for (int i=0;i<ori_data.jobCompleted.size();i++) {
				double time = new jobcompleted_calculator().getTimeDifference(ori_data.jobSubmitted.get(i),ori_data.jobCompleted.get(i));
				time/=1000;
				if (time<=60) {
					count_1min++;
				}
				else if (time<=1800) {
					count_30min++;
				}
				else if (time<=3600) {
					count_1hour++;
				}
				else if (time<=43200) {
					count_12hour++;
				}
				else if (time<=86400) {
					count_24hour++;
				}
				else {
					count_more_24hour++;
				}
			}
			
			time_division.put("less than 1 minute", count_1min);
			time_division.put("less than 30 minutes", count_30min);
			time_division.put("less than 1 hour", count_1hour);
			time_division.put("less than 12 hour", count_12hour);
			time_division.put("less than 24 hour", count_24hour);
			time_division.put("more than 24 hour", count_more_24hour);
			
			String file="/Users/zxgan/eclipse-workspace/WIX1002/src/Extract/List job and time (time chart).txt";
			PrintWriter writer =new PrintWriter(file);
			writer.println("Count for less than 1 minutes : "+count_1min);
			writer.println("Count for less than 30 minutes : "+count_30min);
			writer.println("Count for less than 1 hour : "+count_1hour);
			writer.println("Count for less than 12 hour : "+count_12hour);
			writer.println("Count for less than 24 hour : "+count_24hour);
			writer.println("Count for more than 24 hour : "+count_more_24hour);
			
			new OpenFile().showFile(file);
			writer.close();
			
			PieChart3D pie=new PieChart3D("Execution time","Execution time",time_division);
			pie.pack();
			pie.setVisible(true);
		}
	 
}

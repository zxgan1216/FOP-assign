package Extract;

import javax.swing.JOptionPane;

public class JobCompleted_main{
	public static void main(String [] args) throws Exception {
		
		jobcompleted_function func=new jobcompleted_function();
		
		while (true) {
	        String[] responds= {"Back","Display average execution time","Display average time division ","Search job id", "Display list"};
			int ans=JOptionPane.showOptionDialog(null,"Choose a function", "Find job completed",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,responds, null);
			switch (ans) {
				case 4 -> {
					func.printList();
				}
				case 3 ->{
					func.findId();
				}
				case 2-> {
					func.time_chart();
				}
				case 1 ->{
					func.calAvg();
				}
				case -1 ->{ //x
					System.exit(0);
				}
				case 0->{ //back case
					new Main_program().main(null);
				}
			}
		}
	}

}



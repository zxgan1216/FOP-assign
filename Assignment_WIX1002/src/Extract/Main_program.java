package Extract;

import javax.swing.JOptionPane;

public class Main_program {
public static void main(String [] args) throws Exception {
		
		while (true) {
	        String[] responds= {"cancel","Aeverage Execution Time","Error","Partition", "Job"};
			int ans=JOptionPane.showOptionDialog(null,"Choose a function", "UM HPC",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,responds, null);
			switch (ans) {
				case 4 -> {
					new job_main().main(null);
				}
				case 3 ->{
					new Partition_main().main(null);
				}
				case 2-> {
					new Error_main().main(null);
				}
				case 1 ->{
					new JobCompleted_main().main(null);
				}
				case -1 ->{ //x
					System.exit(0);
				}
				case 0->{ //back case
					System.exit(0);
				}
			}
		}
}
}

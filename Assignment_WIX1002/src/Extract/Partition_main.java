package Extract;

import javax.swing.JOptionPane;

public class Partition_main {
	public static void main(String [] args) throws Exception {
		while (true) {
	        String[] responds= {"Back","Display", "Search"};
			int ans=JOptionPane.showOptionDialog(null,"Choose a function", "Find Partition",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,responds, null);
			switch (ans) {
				case 2-> {
					new Partition_search_main().partition_search();
				}
				case 1 ->{
					new Partition_display().partition_display();
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

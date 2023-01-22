package Extract;

import javax.swing.JOptionPane;

public class Partition_display {
	public void partition_display() throws Exception {
		Partition_calculator cal=new Partition_calculator();
		while (true) {
	        String[] responds= {"Back","DisplayCPU","Display Node", "Display partition"};
			int ans=JOptionPane.showOptionDialog(null,"Choose a function", "Find job completed",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,responds, null);
			switch (ans) {
				case 3 ->{
					cal.PartitionList();
				}
				case 2-> {
					cal.NodeList();
				}
				case 1 ->{
					cal.CPUList();
				}
				case -1 ->{ //x
					System.exit(0);
				}
				case 0->{ //back case
					new Partition_main().main(null);
				}
			}
		}
	}
}

package Extract;


import javax.swing.JOptionPane;

class Partition_search_main{
	public void partition_search() throws Exception {
		Partition_calculator cal=new Partition_calculator();
		while (true) {
	        String[] responds= {"Back","Search Partition","Search CPU","Search Node", "Search JobId"};
			int ans=JOptionPane.showOptionDialog(null,"Choose a function", "Find job completed",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,responds, null);
			switch (ans) {
				case 4 -> {
					cal.searchJobId();
				}
				case 3 ->{
					cal.searchNode();
				}
				case 2-> {
					cal.searchCPU();
				}
				case 1 ->{
					cal.searchPartition();
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





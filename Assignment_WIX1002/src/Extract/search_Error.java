package Extract;

import javax.swing.JOptionPane;

class search_Error{
	public static void main(String[] args) throws Exception {
		findError find = new findError();
	    while (true) {
	        String[] responds= {"Back","Find Error by day","Find error by month", "Find error by user"};
	  		int ans=JOptionPane.showOptionDialog(null,"Choose a function", "Find job completed",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,responds, null);
	  		switch (ans) {
	  			case 3 ->{
	  				find.searchUser(); 
	  			}
	  			case 2-> {
	  				find.searchMonth();
	  			}
	  			case 1 ->{
	  				find.searchDay();
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

package Extract;

import javax.swing.JOptionPane;


class DisplayError {
  public void display_error() throws Exception {
      findError find = new findError();
      
      while (true) {
	        String[] responds= {"Back","Display Month Error","Display User Error", "Display Error Type"};
			int ans=JOptionPane.showOptionDialog(null,"Choose a function", "Find Error",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,responds, null);
			switch (ans) {
				case 3 ->{
					find.displayAllTypeError(); 
				}
				case 2-> {
					find.displayUserError();
				}
				case 1 ->{
					find.displayMonthError();
				}
				case -1 ->{ //x
					System.exit(0);
				}
				case 0->{ //back case
					new Error_main().main(null);
				}
			}
		}
    }
    }
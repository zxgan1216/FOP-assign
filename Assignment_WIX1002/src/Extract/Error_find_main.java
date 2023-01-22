package Extract;

import javax.swing.JOptionPane;

public class Error_find_main{
    public void find_error() throws Exception {
    	ErrorPrinter printer= new ErrorPrinter("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/Error_Association_log.txt");
        while (true) {
        String[] responds= {"Back","Search error by type","Search error by association", "Print all error"};
		int ans=JOptionPane.showOptionDialog(null,"Choose a function", "Find Error",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,responds, null);
		
		switch (ans) {
		case 3 -> {
			printer.printAll("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/All_error_list.txt");
		}
		case 2 ->{
			String ID=JOptionPane.showInputDialog("Enter ID");
			printer.printUser("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/Error_search_by_user.txt", ID);
		}
		case 1-> {
			String errorType=JOptionPane.showInputDialog("Enter type of error");
			printer.printError("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/Error_search_by_type.txt", errorType);
		}
		case -1 ->{
			System.exit(0);
		}
		case 0->{ //back case
			new Error_main().main(null);
		}
		}
        }
        
        //System.out.println(new ErrorPrinter("C:\\Users\\User\\Desktop\\Error_Association_log.crash").getLines(","));
    }
    
}

   

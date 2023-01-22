package Extract;

import javax.swing.*;
import java.awt.*;
public class job_main {
    public static void main(String[] args) throws Exception {
    	job_promptUserInput pui = new job_promptUserInput();
    	job_chooseMonth cm = new job_chooseMonth();

        while (true) {
            String[] responds = {"back", "Display Pie Chart", "User Input", "Choose Month"};
            int ans = JOptionPane.showOptionDialog(null, "Choose a function", "Find data in desired time", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, responds, null);
            switch (ans) {
                case 3 -> {//choose month open respective file
                    cm.userinputmonth();
                    job_searching q2 = new job_searching(cm.getStartDate(), cm.getEndDate());
                    q2.choice();
                    q2.searching();
                    JOptionPane.showMessageDialog(null, q2.toString(), "Job",JOptionPane.PLAIN_MESSAGE);
                }
                case 2 -> {//User input month , open respective file
                    pui.PromptInputDate();
                    job_searching q3 = new job_searching(pui.getStartDate(), pui.getEndDate());
                    q3.choice();
                    q3.searching();
                    JOptionPane.showMessageDialog(null, q3.toString(), "Job",JOptionPane.PLAIN_MESSAGE);
                }
                case 1 -> { //Display Pie Chart, open all "WEXITSTATUS" der files (4 files)
                    JOptionPane.showMessageDialog(null, "Number of job done = 1052", "Job",JOptionPane.PLAIN_MESSAGE);
                    job_piechart3D pie = new job_piechart3D("Pie Chart ", "Completed jobs based on WEXITSTATUS");
                    pie.getdata(687, 269,12,84);
                    pie.presentdata();
                    pie.pie();
                }
                case -1 -> {
                    System.exit(0);
                }
                case 0->{
                	new Main_program().main(null);
                }

            }
        }
    }
}

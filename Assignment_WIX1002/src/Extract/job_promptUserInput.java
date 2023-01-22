package Extract;

import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class job_promptUserInput {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    void PromptInputDate() {
        
    	JOptionPane.showMessageDialog(null, "Enter your desired start date and end date in the format of \\\\\\\"yyyy-MM-dd'T'HH:mm:ss.SSS\\\\", "Input data",JOptionPane.PLAIN_MESSAGE);

        String start_date=JOptionPane.showInputDialog("Start Time: ");
        String[] startinput = start_date.split("-");
        int[] x = new int[startinput.length];
        for (int i = 0; i < startinput.length; i++)
            x[i] = Integer.parseInt(startinput[i]);

        String end_date=JOptionPane.showInputDialog("End Time: ");
        String[] endinput = end_date.split("-");
        int[] y = new int[endinput.length];
        for (int i = 0; i < endinput.length; i++)
            y[i] = Integer.parseInt(endinput[i]);

        startDate = LocalDateTime.of(x[0], x[1], x[2], x[3], x[4], x[5], x[6]);
        endDate = LocalDateTime.of(y[0], y[1], y[2], y[3], y[4], y[5], y[6]);
    }
    public LocalDateTime getStartDate(){
        return startDate;
    }
    public LocalDateTime getEndDate(){
        return endDate;
    }
}
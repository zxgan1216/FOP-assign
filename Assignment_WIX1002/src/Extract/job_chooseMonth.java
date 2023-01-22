package Extract;


import java.time.LocalDateTime;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class job_chooseMonth {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    void userinputmonth() {
    	String month_entered=JOptionPane.showInputDialog("Enter month to be display");
    	int month=Integer.parseInt(month_entered);

        switch (month) {
            case 6 -> {
                startDate = LocalDateTime.of(2022, 6, 1, 0, 0, 0, 0);
                endDate = LocalDateTime.of(2022, 6, 30, 23, 59, 0, 0);
            }
            case 7 -> {
                startDate = LocalDateTime.of(2022, 7, 1, 0, 0, 0, 0);
                endDate = LocalDateTime.of(2022, 7, 31, 23, 59, 0, 0);
            }
            case 8 -> {
                startDate = LocalDateTime.of(2022, 8, 1, 0, 0, 0, 0);
                endDate = LocalDateTime.of(2022, 8, 31, 23, 59, 0, 0);
            }
            case 9 -> {
                startDate = LocalDateTime.of(2022, 9, 1, 0, 0, 0, 0);
                endDate = LocalDateTime.of(2022, 9, 30, 23, 59, 0, 0);
            }
            case 10 -> {
                startDate = LocalDateTime.of(2022, 10, 1, 0, 0, 0, 0);
                endDate = LocalDateTime.of(2022, 10, 31, 23, 59, 0, 0);
            }
            case 11 -> {
                startDate = LocalDateTime.of(2022, 11, 1, 0, 0, 0, 0);
                endDate = LocalDateTime.of(2022, 11, 30, 23, 59, 0, 0);
            }
            case 12 -> {
                startDate = LocalDateTime.of(2022, 12, 1, 0, 0, 0, 0);
                endDate = LocalDateTime.of(2022, 12, 31, 23, 59, 0, 0);
            }
            default -> {
            	JOptionPane.showMessageDialog(null, "Month out range", "Error",JOptionPane.WARNING_MESSAGE);
                userinputmonth();
            }
        }
    }

    public LocalDateTime getStartDate(){
        return startDate;
    }
    public LocalDateTime getEndDate(){
        return endDate;
    }
}
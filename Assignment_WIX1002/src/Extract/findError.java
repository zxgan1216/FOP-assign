package Extract;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

class findError {
    List<Error> list = getErrorList();
    HashMap<String, Integer> userCount = new HashMap<>(); // total errors count by users
    HashMap<String, Integer> errorCounts1 = new HashMap<>(); // long errors count by users
    HashMap<String, Integer> errorCounts2 = new HashMap<>(); // normal errors count by users
    HashMap<String, Integer> errorCounts3 = new HashMap<>(); // extended errors count by users
    HashMap<String, Integer> monthCount = new HashMap<>(); // total errors count by months
    HashMap<String, Integer> errorCounts4 = new HashMap<>(); // long errors count by months
    HashMap<String, Integer> errorCounts5 = new HashMap<>(); // normal errors count by months
    HashMap<String, Integer> errorCounts6 = new HashMap<>(); // extended errors count by months
    HashMap<String, Integer> allErrorCount = new HashMap<>();
    String errorType;
    int i;
    List<Error> info = new ArrayList<>();

    public List<Error> getErrorList() {
        ArrayList<Error> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new FileInputStream("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/errorUser.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Pattern pattern = Pattern.compile("\\[(.+)] error: This association (\\d+)\\(account='(\\w+)', user='(.+)', partition='\\((.+)\\)'\\) does not have access to qos (\\w+)");// w(A-Za-z0-9)
                Matcher matcher = pattern.matcher(line);
                matcher.find();
                String time = matcher.group(1);
                String association = matcher.group(2);
                String account = matcher.group(3);
                String user = matcher.group(4);
                String partition = matcher.group(5);
                String errorType = matcher.group(6);
                Error error = new Error(LocalDateTime.parse(time), Integer.parseInt(association), account,
                        user, partition, errorType);
                list.add(error);
            }
            sc.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void displayList() {
        int i = 1;
        System.out.printf("%-15s%-27s%-22s%-20s%-17s%-20s%-20s\n", "No.", "Date and Time", "Association", "Account", "User", "Partition", "Error");
        for (Error error : list) {
            System.out.printf("%-10d%-36s%-19s%-16s%-22s%-18s%-15s\n", i, error.time(), error.association(), error.account(), error.user(), error.partition(), error.errorType());
            i++;
        }
        System.out.println();
    }

    public void getUserError() {
        ArrayList<String> users = new ArrayList<>();

        for (Error error : list) {
            users.add(error.user());
        }
        for (String user : users) {
            if (userCount.containsKey(user)) {
                userCount.put(user, userCount.get(user) + 1);
            } else {
                userCount.put(user, 1);
            }
        }

        for (Error error : list) {
            if (error.errorType().equalsIgnoreCase("long")) {
                Integer count = errorCounts1.getOrDefault(error.user(), 0);
                errorCounts1.put(error.user(), count + 1);
            } else if (error.errorType().equalsIgnoreCase("normal")) {
                Integer count = errorCounts2.getOrDefault(error.user(), 0);
                errorCounts2.put(error.user(), count + 1);
            } else if (error.errorType().equalsIgnoreCase("extended")) {
                Integer count = errorCounts3.getOrDefault(error.user(), 0);
                errorCounts3.put(error.user(), count + 1);
            }
        }
    }

    public void getMonthError() {
        ArrayList<String> months = new ArrayList<>();

        for (Error error : list) {
            months.add(error.time().toString().substring(5, 7));
        }
        //System.out.println(months);
        for (String month : months) {
            if (monthCount.containsKey(month)) {
                monthCount.put(month, monthCount.get(month) + 1);
            } else {
                monthCount.put(month, 1);
            }
        }
        //System.out.println(errorCountByMonth);

        for (Error error : list) {
            String month = error.time().toString().substring(5, 7);
            if (error.errorType().equalsIgnoreCase("long")) {
                Integer count = errorCounts4.getOrDefault(month, 0);
                errorCounts4.put(month, count + 1);
            } else if (error.errorType().equalsIgnoreCase("normal")) {
                Integer count = errorCounts5.getOrDefault(month, 0);
                errorCounts5.put(month, count + 1);
            } else if (error.errorType().equalsIgnoreCase("extended")) {
                Integer count = errorCounts6.getOrDefault(month, 0);
                errorCounts6.put(month, count + 1);
            }
        }
    }

    public void displayUserError() throws Exception {
        getUserError();
        String file="/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/User_error.txt";
        PrintWriter writer =new PrintWriter(file);
        writer.println("Number of jobs causing errors and the corresponding users");
        writer.println("┌"+"─".repeat(25)+"┬"+"─".repeat(25)+"┬"+"─".repeat(25)+"┬"+"─".repeat(26)+"┬"+"─".repeat(25)+"┐");
		writer.printf("│%-24s │%-24s │%-24s │ %-24s │ %-24s│\n", "User", "Total error", "Error Count (long)","Error Count (normal)","Error Count (extended)");
		writer.println("│"+"─".repeat(25)+"┼"+"─".repeat(25)+"┼"+"─".repeat(25)+"┼"+"─".repeat(26)+"┼"+"─".repeat(25)+"│");
        for (String user : userCount.keySet()) {
            writer.printf("│%-24s │%-24s │%-24s │ %-24s │ %-24s│\n", user, userCount.get(user),
                    errorCounts1.get(user) == null ? 0 : errorCounts1.get(user),
                    errorCounts2.get(user) == null ? 0 : errorCounts2.get(user),
                    errorCounts3.get(user) == null ? 0 : errorCounts3.get(user));
        }
        writer.println("└"+"─".repeat(25)+"┴"+"─".repeat(25)+"┴"+"─".repeat(25)+"┴"+"─".repeat(26)+"┴"+"─".repeat(25)+"┘");
        writer.close();
        new OpenFile().showFile(file);
        PieChart3D pie=new PieChart3D("Error","User Error (count)",userCount);
		pie.pack();
		pie.setVisible(true); 
		PieChart3D pie2=new PieChart3D("Error","User Error normal(count)",errorCounts1);
		pie2.pack();
		pie2.setVisible(true); 
		PieChart3D pie3=new PieChart3D("Error","User Error long(count)",errorCounts2);
		pie3.pack();
		pie3.setVisible(true); 
		PieChart3D pie4=new PieChart3D("Error","User Error extended(count)",errorCounts3);
		pie4.pack();
		pie4.setVisible(true); 
        
    }

    public void displayMonthError() throws Exception {
        getMonthError();
        String file="/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/Month_error.txt";
        PrintWriter writer =new PrintWriter(file);
        writer.println("Number of jobs causing errors and the corresponding months");
        writer.println("┌"+"─".repeat(25)+"┬"+"─".repeat(25)+"┬"+"─".repeat(25)+"┬"+"─".repeat(26)+"┬"+"─".repeat(25)+"┐");
		writer.printf("│%-24s │%-24s │%-24s │ %-24s │ %-24s│\n", "Month", "Total error", "Error Count (long)","Error Count (normal)","Error Count (extended)");
		writer.println("│"+"─".repeat(25)+"┼"+"─".repeat(25)+"┼"+"─".repeat(25)+"┼"+"─".repeat(26)+"┼"+"─".repeat(25)+"│");
        for (String month : monthCount.keySet()) {
            writer.printf("│%-24s │%-24s │%-24s │ %-24s │ %-24s│\n", month, monthCount.get(month),
                    errorCounts4.get(month) == null ? 0 : errorCounts4.get(month),
                    errorCounts5.get(month) == null ? 0 : errorCounts5.get(month),
                    errorCounts6.get(month) == null ? 0 : errorCounts6.get(month));
        }
        writer.println("└"+"─".repeat(25)+"┴"+"─".repeat(25)+"┴"+"─".repeat(25)+"┴"+"─".repeat(26)+"┴"+"─".repeat(25)+"┘");
        writer.close();
        new OpenFile().showFile(file);
        PieChart3D pie=new PieChart3D("Error","Month Error (count)",monthCount);
		pie.pack();
		pie.setVisible(true); 
		PieChart3D pie2=new PieChart3D("Error","Month Error normal(count)",errorCounts4);
		pie2.pack();
		pie2.setVisible(true); 
		PieChart3D pie3=new PieChart3D("Error","Month Error long(count)",errorCounts5);
		pie3.pack();
		pie3.setVisible(true); 
		PieChart3D pie4=new PieChart3D("Error","Month Error extended(count)",errorCounts6);
		pie4.pack();
		pie4.setVisible(true); 
    }

    public void getAllTypeError() {
        try {
            Scanner input = new Scanner(new FileInputStream("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/extracted_log.txt"));
            i = 0;
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.contains("error")) {
                    i++;
                    String errorType = extractErrorType(line);
                    if (allErrorCount.containsKey(errorType))
                        allErrorCount.put(errorType, allErrorCount.get(errorType) + 1);
                    else
                        allErrorCount.put(errorType, 1);
                }
            }

            input.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractErrorType(String error) {
        if (error.contains("does not have access to qos"))
            errorType = "QoS Access Error";
        else if (error.contains("Invalid qos"))
            errorType = "Invalid QoS Error";
        else if (error.contains("Security violation"))
            errorType = "Security Violation Error";
        else if (error.contains("_find_node_record"))
            errorType = "Node Lookup Error";
        else if (error.contains("node_name2bitmap"))
            errorType = "Invalid Node Error";
        else if (error.contains("not responding"))
            errorType = "Node Not Responding Error";
        else if (error.contains("Job/step already completing or completed"))
            errorType = "Job/Step Completion Error";
        else if (error.contains("_slurm_rpc_requeue"))
            errorType = "Job Requeue Error";
        else if (error.contains("Registered PENDING"))
            errorType = "Job Pending Error";
        else if (error.contains("Socket timed out"))
            errorType = "Socket Timeout Error";
        else if (error.contains("Prolog launch failure"))
            errorType = "Prolog Launch Error";
        else if (error.contains("validate_node_specs"))
            errorType = "Node Validation Error";
        else if (error.contains("Invalid job id specified"))
            errorType = "Invalid Job ID Error";
        else if (error.contains("Zero Bytes were transmitted or received"))
            errorType = "Zero Bytes Transmission Error";
        else if (error.contains("slurm_msg_sendto: address:"))
            errorType = "Slurm Message Sending Error";
        else if (error.contains("slurmd error running"))
            errorType = "Job Termination Error";
        else if (error.contains("_handle_assoc_tres_run_secs"))
            errorType = "TRES Underflow Error";
        else if (error.contains("Configured cpu count change"))
            errorType = "CPU Configuration Error";
        else if (error.contains("_slurm_rpc_reconfigure_controller"))
            errorType = "Controller Configuration Error";
        else if (error.contains("have a different slurm.conf"))
            errorType = "Slurm Configuration Error";
        else if (error.contains("valid_job_resources"))
            errorType = "Invalid Job Resources Error";
        else if (error.contains("Aborting JobId"))
            errorType = "Aborted Job Error";
        else if (error.contains("dealloc"))
            errorType = "GRES Underflow Error";
        else if (error.contains("Could not find configured group training"))
            errorType = "Group Configuration Error";
        else if (error.contains("User") && error.contains("not found"))
            errorType = "User Not Found Error";
        else if (error.contains("Attempt to modify priority"))
            errorType = "Job Priority Error";
        else if (error.contains("epilog error"))
            errorType = "Epilog Execution Error";

        return errorType;
    }

    public void displayAllTypeError() throws Exception {
        getAllTypeError();
        String file="/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/Error_type.txt";
        PrintWriter writer =new PrintWriter(file);
        writer.println("┌"+"─".repeat(40)+"┬"+"─".repeat(10)+"┐");
		writer.printf("│%-39s │ %-9s│\n", "Type of Error", "Count");
		writer.println("│"+"─".repeat(40)+"┼"+"─".repeat(10)+"│");
        for (String type : allErrorCount.keySet()) {
        	writer.printf("│%-39s │ %-9s│\n", type, allErrorCount.get(type));
        }
        writer.println("└"+"─".repeat(40)+"┴"+"─".repeat(10)+"┘");
        writer.println("\nTotal type of errors: " + allErrorCount.size());
        writer.println("Total number of errors: " + i);
        writer.close();
        
        new OpenFile().showFile(file);
        PieChart3D pie=new PieChart3D("Error","Error type (count)",allErrorCount);
		pie.pack();
		pie.setVisible(true); 
    }

    public void searchUser() throws Exception {
        String name =JOptionPane.showInputDialog("Enter Username : ");
        String file="/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/Search_User.txt";
        PrintWriter writer =new PrintWriter(file);
        boolean found=false;
        for (Error error : list) {
            if (error.user().equals(name))
            	found=true;
        }
        if (!(found))JOptionPane.showMessageDialog(null, "Invalid code", "Error",JOptionPane.WARNING_MESSAGE);
        for (Error error : list) {
            if (error.user().equals(name))
                writer.println(error);
        }
        writer.close();
        if (found) new OpenFile().showFile(file);
    }

    public void searchMonth() throws Exception {
    	String file="/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/Search_month.txt";
    	PrintWriter writer =new PrintWriter(file);
        String month = JOptionPane.showInputDialog("Enter month");
        boolean found=false;
        for (Error error : list) {
            if (error.time().getMonthValue() == Integer.parseInt(month))
            	found=true;
        }
        if (!(found))JOptionPane.showMessageDialog(null, "Invalid code", "Error",JOptionPane.WARNING_MESSAGE);
        for (Error error : list) {
            if (error.time().getMonthValue() == Integer.parseInt(month)) {
            	writer.println(error);
                info.add(error);
            }
        }
        writer.close();
        if (found) new OpenFile().showFile(file);
    }

    public void searchDay() throws Exception{
    	String file="/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/Search_day.txt";
    	PrintWriter writer =new PrintWriter(file);
        String day= JOptionPane.showInputDialog("Enter day");
        boolean found=false;
        for (Error error : list) {
            if (error.time().getDayOfMonth() == Integer.parseInt(day))
            	found=true;
        }
        if (!(found))JOptionPane.showMessageDialog(null, "Invalid code", "Error",JOptionPane.WARNING_MESSAGE);
        for (Error error : list) {
            if (error.time().getDayOfMonth() == Integer.parseInt(day)) {
                writer.println(error);
            }
        }
        writer.close();
        if(found) new OpenFile().showFile(file);
    }
}
        record Error(LocalDateTime time, int association, String account,
                     String user, String partition, String errorType) {}

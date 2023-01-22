package Extract;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
public class job_searching {
    Scanner sc = new Scanner(System.in);
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    String read;
    private int output=4;
    String choice;
    int jobCreated = 0;
    int jobEnded = 0;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int stat0 = 0;
    private int stat1 = 0;
    private int stat2 = 0;
    private int otherstat = 0;
    private int status;
    private PrintWriter printjc;
    private PrintWriter printjd0;
    private PrintWriter printjd2;
    private PrintWriter printjdother;
    private PrintWriter printjd;
    private PrintWriter printjd1;
    {
        try {
            printjc = new PrintWriter(new FileOutputStream("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/JobCreated.txt"));
            printjd0 = new PrintWriter(new FileOutputStream("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/WEXITSTATUS(0).txt"));
            printjd1 = new PrintWriter(new FileOutputStream("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/WEXITSTATUS(1).txt"));
            printjd2 = new PrintWriter(new FileOutputStream("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/WEXITSTATUS(2).txt"));
            printjdother = new PrintWriter(new FileOutputStream("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/WEXITSTATUS(other).txt"));
            printjd = new PrintWriter(new FileOutputStream("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/JobDone.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public job_searching(LocalDateTime startDate, LocalDateTime endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }
    Pattern p1 = Pattern.compile("(\\[\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\]) _slurm_rpc_submit_batch_job: JobId=(\\d+) InitPrio=(\\d+) usec=(\\d+)");
    Pattern p2 = Pattern.compile("(\\[\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\]) _job_complete: JobId=(\\d+) WEXITSTATUS (\\d+)");
    Pattern p3 = Pattern.compile("(\\[\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}\\]) _job_complete: JobId=(\\d+) done");

    public void choice() throws Exception {
    	String[] line= {"back","Number of jobs create","Number of jobs completed with WEXITSTATUS","Number of jobs done"};
    	output=JOptionPane.showOptionDialog(null,"Choose a function", "Job",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,null,line, null);
        switch (output) {
        case 3->{
        	printjc.printf("Timestamp %20s JobId %9s InitPrio %10s usec %10s\n", "", "", "", "");
        }
        
        case 2->{
        	String[] line1= {"WEXITSTATUS other than 0,1,2 (other)","WEXITSTATUS 2 (2)","WEXITSTATUS 1 (1)","WEXITSTATUS 0 (0)"};
        	choice=JOptionPane.showInputDialog(line1);
        	printjd0.printf("Timestamp %20s JobId %10s\n", "", "");
            printjd1.printf("Timestamp %20s JobId %10s\n", "", "");
            printjd2.printf("Timestamp %20s JobId %10s\n", "", "");
            printjdother.printf("Timestamp %20s JobId %10s\n", "", "");
        }
        case 1->{
        	printjd.printf("Timestamp %20s JobId %10s\n", "", "");
        }
        
        case 0->{
        	new job_main().main(null);
        }
        case -1->{
        	System.exit(0);
        }
        }
    }
    public void choice(int def){
        output = def;
        printjd0.printf("Timestamp %20s JobId %10s\n", "", "");
        printjd1.printf("Timestamp %20s JobId %10s\n", "", "");
        printjd2.printf("Timestamp %20s JobId %10s\n", "", "");
        printjdother.printf("Timestamp %20s JobId %10s\n", "", "");
    }
    public void searching(){
       try {
            BufferedReader input = new BufferedReader(new FileReader("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/extracted_log.txt"));
           //reading from log file provided by dr liew

            while ((read = input.readLine()) != null) {
                Matcher m1 = p1.matcher(read);
                Matcher m2 = p2.matcher(read);
                Matcher m3 = p3.matcher(read);
                String timestampString = read.substring(1, 24);
                LocalDateTime timestamp = LocalDateTime.parse(timestampString, format);
                if (timestamp.isAfter(startDate) && timestamp.isBefore(endDate)) {
                    switch (output) {
                        case 3 -> {
                            if (m1.find()) {
                                jobCreated++;
                                printjc.printf("%10s %10s %18s %15s \n",m1.group(1),  m1.group(2), m1.group(3), m1.group(4));
                            }
                        }
                        case 2 -> {
                            if (m2.find()) {
                                status = Integer.parseInt(m2.group(3));
                                switch (choice) {
                                    case "0" -> {
                                        if (status == 0){
                                            stat0++;
                                            printjd0.printf("%10s %10s\n",m2.group(1) ,m2.group(2));
                                        }
                                    }
                                    case "1" -> {
                                        if (status == 1){
                                            stat1++;
                                            printjd1.printf("%10s %10s\n",m2.group(1) ,m2.group(2));
                                        }
                                    }
                                    case "2" -> {
                                        if (status == 2){
                                            stat2++;
                                            printjd2.printf("%10s %10s\n",m2.group(1) ,m2.group(2));
                                        }
                                    }
                                    case "other" -> {
                                        if (Integer.parseInt(m2.group(3)) > 2) {
                                            otherstat++;
                                            printjdother.printf("%10s %10s\n",m2.group(1) ,m2.group(2));
                                        }
                                    }
                                }
                            }
                        }
                        case 1 -> {
                            if (m3.find()) {
                                jobEnded++;
                                printjd.printf("%10s %10s\n",m3.group(1) ,m3.group(2));
                            }
                        }
                        case 4 ->{
                            if (m2.find()) {
                                status = Integer.parseInt(m2.group(3));
                                if (status == 0) {
                                    stat0++;
                                    printjd0.printf("%10s %10s\n",m2.group(1) ,m2.group(2));
                                } else if (status == 1) {
                                    stat1++;
                                    printjd1.printf("%10s %10s\n",m2.group(1) ,m2.group(2));
                                } else if (status == 2) {
                                    stat2++;
                                    printjd2.printf("%10s %10s\n",m2.group(1) ,m2.group(2));
                                } else if (Integer.parseInt(m2.group(3)) > 2) {
                                    otherstat++;
                                    printjdother.printf("%10s %10s\n",m2.group(1) ,m2.group(2));
                                }
                            }
                        }
                    }
                }
            }
            input.close();
           printjc.close();
           printjd0.close();
           printjd1.close();
           printjd2.close();
           printjdother.close();
           printjd.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getStat0(){
        return this.stat0;
    }
    public int getStat1(){
        return this.stat1;
    }
    public int getStat2(){
        return this.stat2;
    }
    public int getOtherstat(){
        return this.otherstat;
    }

    public String toString(){
        String message;
        if(output==3)
             message = ("Number of jobs done within " + startDate + " - " + endDate + " is " + jobCreated + " jobs.");
        else if((output==2) && choice.equals("0"))
             message = ("Number of jobs done with WEXITSTATUS 0 is " + stat0 + " jobs");
        else if((output==2) && choice.equals("1"))
             message = ("Number of jobs done with WEXITSTATUS 1 is " + stat1 + " jobs");
        else if((output==2) && choice.equals("2"))
             message = ("Number of jobs done with WEXITSTATUS 2 is " + stat2 + " jobs");
        else if((output==2) && choice.equals("other"))
             message = ("Number of jobs done with WEXITSTATUS other than 0,1 and 2 is " + otherstat + " jobs");
        else if((output==1))
             message = ("Number of jobs created within " + startDate + " - " + endDate + " is " + jobEnded + " jobs.");
        else
            message = ("Number of jobs done with WEXITSTATUS 0,1,2 and other than it are " + stat0 + ", " + stat1 + ", " + stat2 + " and " + otherstat +" respectively.");
        return message;
    }
}
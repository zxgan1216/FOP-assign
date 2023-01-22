package Extract;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class jobcompleted_data{
	ArrayList<String> ID=new ArrayList<>();
	ArrayList<String> jobCompleted=new ArrayList<>();
	ArrayList<String> jobSubmitted=new ArrayList<>();
	private int count;

    public void keepData() throws Exception{
    	BufferedReader br = new BufferedReader(new FileReader("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/test.txt"));
    	String input;
    	while((input = br.readLine()) != null){
    		String pattern = "(\\d+)\\s+\\[(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\]\\s+\\[(\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\]";
    		Pattern r = Pattern.compile(pattern);
    		Matcher m = r.matcher(input);
    		
    		if (m.find()) {
    			String id =m.group(1);
    			String start = m.group(2);
    			String end = m.group(3);
    			ID.add(id);
    			jobSubmitted.add(start);
    			jobCompleted.add(end);
    			count = getCount() + 1;
    			}
    		}
    	br.close();
    	}

	public int getCount() {
		return count;
	}
}


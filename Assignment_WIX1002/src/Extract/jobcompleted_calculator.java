package Extract;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class jobcompleted_calculator{
	double total_time;
	double average;
	
	public void calTime() throws Exception {
		jobcompleted_data data=new jobcompleted_data();
		data.keepData();
		for (int i=0;i<data.ID.size();i++) {
			double time = getTimeDifference(data.jobSubmitted.get(i),data.jobCompleted.get(i));
			total_time+=time;
		}
		average=total_time/data.ID.size();
	}
	
	public double getTimeDifference(String time1String, String time2String) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime time1 = LocalDateTime.parse(time1String, formatter);
        LocalDateTime time2 = LocalDateTime.parse(time2String, formatter);
        return Duration.between(time1, time2).toMillis();
    }
	
	public double getAverage() {
		return this.average;
	}
	
	public double getTotalTime() {
		return this.total_time;
	}
}
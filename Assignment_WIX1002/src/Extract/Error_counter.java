package Extract;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Stream;

public class Error_counter extends Counter {
	
	Error_counter(String source) {
		super(source);
	}
	
	public int countAllLines() {
		try(Stream<String> lines = this.getLines()){ 
	        return (int) lines.count(); 
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	public int countIfContainsAll(String... keywords) {
	    Set<String> keywordSet = Set.of(keywords); 
	    try(Stream<String> lines = this.getLines()) { 
	        return (int) lines
	                .filter(line -> keywordSet.stream().allMatch(line::contains)) 
	                .count(); 
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return 0;
	}
	
	public int countIfContain(String keyword) {
		return countIfContainsAll(keyword);
	}
}

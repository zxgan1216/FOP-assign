package Extract;

import java.io.*;
import java.util.regex.*;
import java.util.stream.*;
import java.util.HashMap;

public class RegexErrorCounter extends Error_counter {
	private final Pattern PATTERN;
	
	public RegexErrorCounter(String source, String regex) {
		super(source);
		this.PATTERN = Pattern.compile(regex);
	}

	public HashMap<String, Integer> countMatcherGroup(int key) throws Exception {
		final HashMap<String, Integer> MAP = new HashMap<>();
		
		try(Stream<String> lines = this.getLines()) {
			lines.forEach(line -> {
				Matcher matcher = this.PATTERN.matcher(line);
				if (matcher.matches()) {
					MAP.put(matcher.group(key), MAP.getOrDefault(matcher.group(key), 0) + 1);
				}
			});
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		return MAP;
	}
}

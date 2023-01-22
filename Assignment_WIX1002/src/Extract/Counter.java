package Extract;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.*;
import java.io.*;

public abstract class Counter {
	protected final String SOURCE;
	
	Counter(String source) {
		this.SOURCE = source;
	}
	
	public Stream<String> getLines() throws IOException {
		return Files.lines(Paths.get(this.SOURCE));
	}
}

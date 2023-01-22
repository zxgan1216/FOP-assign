package Extract;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

class Partition_printer{
	String keyword;
	String toFile;
	int i=0;
	HashMap<String, Integer> list_map;
	
	Partition_printer(String keyword,String toFile,HashMap<String,Integer> list_map){
		this.keyword=keyword;
		this.toFile=toFile;
		this.list_map=list_map;
	}
	
	public void print() throws Exception {
        PrintWriter writer =new PrintWriter(toFile);
        writer.println(keyword+" list : ");
        writer.println("┌"+"─".repeat(40)+"┬"+"─".repeat(10)+"┐");
		writer.printf("│%-39s │ %-9s│\n", keyword, "Count");
		writer.println("│"+"─".repeat(40)+"┼"+"─".repeat(10)+"│");
		for(Map.Entry<String, Integer> entry : list_map.entrySet()) {
        	list_map.put(entry.getKey(), entry.getValue());
        	writer.printf("│%-39s │ %-9s│\n",entry.getKey(), entry.getValue());
        	i+=entry.getValue();
        }
		writer.println("└"+"─".repeat(40)+"┴"+"─".repeat(10)+"┘");
		writer.printf("Number of type of %s = %d\n",keyword,list_map.size());
		writer.printf("Total number of %s = %d",keyword,i);
        PieChart3D pie=new PieChart3D("Partition",keyword+"_count",list_map);
		pie.pack();
		pie.setVisible(true); 
		writer.close();
		new OpenFile().showFile(toFile);
	}
}


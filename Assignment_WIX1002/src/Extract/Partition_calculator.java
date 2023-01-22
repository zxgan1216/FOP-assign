package Extract;

import java.util.HashMap;

class Partition_calculator{
	
	final String DEFAULT = "/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/extracted_log.txt";
    final String REGEX = "\\[.*\\].*Allocate JobId=(.*) NodeList=(.*) #CPUs=(.*) Partition=(.*)";
    RegexErrorCounter counter = new RegexErrorCounter(DEFAULT, REGEX);
    
    public void NodeList() throws Exception {
    	String file="/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/partition_nodelist.txt";
        HashMap<String, Integer> Nodelist_map = counter.countMatcherGroup(2);
        Partition_printer printer=new Partition_printer("Node", file, Nodelist_map);
        printer.print();
    }
    
    public void CPUList() throws Exception {
    	String file="/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/partition_CPUlist.txt";
        HashMap<String, Integer> CPUlist_map = counter.countMatcherGroup(3);
        Partition_printer printer=new Partition_printer("CPU", file, CPUlist_map);
        printer.print();
    }
    
    public void PartitionList() throws Exception {
    	String file="/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/partition_partitonlist.txt";
        HashMap<String, Integer> Partitionlist_map = counter.countMatcherGroup(4);
        Partition_printer printer=new Partition_printer("Partition", file, Partitionlist_map);
        printer.print();
    }
    
    public void searchNode() throws Exception{
    	partition_search node_search=new partition_search("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/partition_search Node.txt","Node",3);
    	node_search.search_func();
    }
    
    public void searchJobId() throws Exception{
    	partition_search node_search=new partition_search("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/partition_search JobId.txt","JobId",2);
    	node_search.search_func();
    }
    
    public void searchCPU() throws Exception{
    	partition_search node_search=new partition_search("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/partition_search_CPU.txt","CPU",4);
    	node_search.search_func();
    }
    
    public void searchPartition() throws Exception{
    	partition_search node_search=new partition_search("/Users/zxgan/eclipse-workspace/Assignment_WIX1002/src/Extract/partition_search_CPU.txt","GPU",5);
    	node_search.search_func();
    }
    
}

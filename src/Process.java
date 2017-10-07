public class Process implements Comparable<Process>{
    public PCB Pcb_data;
    public String code; // = CPU-I/O burst Sequence;
    double timeCreated;
    int priority;
    String state;
    public int[] codeA;
    int count;
    
    //to do: other variables help you computing the latency, response
    public Process(PCB process) {
    //set PCB data, code and others
    	this.code = process.getCode();
    	this.Pcb_data = process;
    	this.timeCreated = System.currentTimeMillis();
    //set state as "NEW";
    	this.state = process.getState();
    	this.priority = process.getPriority();
    	codeA = new int[code.length()];
    	for(int i = 0;i<code.length();i++){
            codeA[i] = Integer.parseInt((code.substring(i,i+1)));
        }
    }

    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public int getBurstNum(){
        return codeA[count];
    }
    
    public void nextBurstNum(){
    	count++;
    }
    
    public int getCount(){
    	return count;
    }
    
    public void setBurstNum(int i){
    	codeA[count] = i; 
    }

    public int[] getCodeA() {
        return codeA;
    }
    
    public double getTimeCreated() {
        return timeCreated;
    }

    public String getCode() {
        return code;
    }
    
    public int getPriority(){
    	return priority;
    }

    public PCB getPcb_data() {
        return Pcb_data;
    }

	@Override
	public int compareTo(Process o) {
		// TODO Auto-generated method stub
		int comparePriority = o.getPriority();
		//ascending return this.priority - comparePriority; 
		//descending
		return comparePriority - this.priority;
	}
    
}

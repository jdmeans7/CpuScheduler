import java.util.Random;

public class CPU implements Runnable{
    public boolean BusyOrNot;
    public boolean done = false;
    public int PC; //Your CPU only has one register PC
    public int timeslice;
    private String threadName;
    private Thread t;
    
    Process currentProcess;
    int burstNum;
    Pair returnPair;
    String currentState;
    int choice;
    
    public CPU(int settimeslice, String thread, int userChoice) {
        timeslice = settimeslice;
        BusyOrNot = false;
        threadName = thread;
        choice = userChoice;
    }
    //ToDo: pair method, will need to create new class that contains an int and a string to return both

    public void setCurretProcess(Process p){
    	currentProcess = p;
    }
    
    public Process getCurretProcess(){
    	return currentProcess;
    }
    
    public Pair currentReturnPair(){
    	return new Pair(burstNum, currentState);
    }
    
    public void execute(){
    	if (choice == 2){
            if (currentProcess.getBurstNum() <= timeslice){
            	for (int i = 0;i < currentProcess.getBurstNum(); i++){
                	bubbleSort();
                }
            	currentProcess.nextBurstNum();
            	burstNum = currentProcess.getBurstNum();
        		currentState = "wait";
            }else if (currentProcess.getBurstNum() > timeslice){
            	for (int i = 0;i < timeslice; i++){
                	bubbleSort();
                }
            	//these print statements test if the round robin is working correctly
            	System.out.println("1. " + currentProcess.getBurstNum());
            	currentProcess.setBurstNum(currentProcess.getBurstNum()-timeslice);
            	System.out.println("2. " + currentProcess.getBurstNum());
        		burstNum = currentProcess.getBurstNum();
        		currentState = "ready";
            }

            if (currentProcess.getCount()+3 > currentProcess.getCode().length()){
            	burstNum = currentProcess.getBurstNum();
        		currentState = "terminated";
            }
    	}else{
    		for (int i = 0;i < currentProcess.getBurstNum(); i++){
    			bubbleSort();
    		}
    		if (currentProcess.getCount()+3 > currentProcess.getCode().length()){
    			burstNum = currentProcess.getBurstNum();
        		currentState = "terminated";
    		}
    		else{
    			currentProcess.nextBurstNum();
    			burstNum = currentProcess.getBurstNum();
        		currentState = "wait";
    		}
    	}
    }
    
    
    @Override
	public void run() {
    	if(!done){	
    		try {
    			execute();
    		}catch(Exception e){
    		}
    	}
    }    
    
    public boolean isCPUBusy() {
        return BusyOrNot;
    }
    
    public void setCPUBusy(boolean bool){
    	BusyOrNot = bool;
    }
    
    public void setDone(boolean bool){
    	done = bool;
    }
    
    public void bubbleSort() {
        Random rand = new Random();
        int[] arr = new int[1000];
        for(int i = 0;i<arr.length;i++){
            arr[i] = rand.nextInt(100);
        }
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap temp and arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
	
	 public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
}

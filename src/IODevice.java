import java.util.ArrayList;
import java.util.Random;

public class IODevice implements Runnable {
	
	Process currentProcess;
	String currentState;
	private String threadName;
    private Thread t;
	
    public IODevice(ArrayList<Process> Wait_Queue, String thread) {
    	threadName = thread;
    }

    public boolean BusyOrNot;
    int burstAmount;
    
    public void setCurretProcess(Process p){
    	currentProcess = p;
    }
    
    public String getCurrentState(){
    	return currentState;
    }
    
    public boolean isIOBusy(){
    	return BusyOrNot;
    }
    
    public void execute(){
    	burstAmount = currentProcess.getBurstNum();
        for(int i = 0; i<burstAmount;i++){ //p.getioburstnumber
            bubbleSort();
        }
        currentProcess.nextBurstNum();
        //CallBubble Sort()for IO_bursttimes and then return “ready”
        currentState = "ready";
    }
    
    //Always pick one process from Wait_Queue to execute
    @Override
	public void run() {
    	try{
	        execute();
    	}catch(Exception e){
    	}
    }

    public void bubbleSort() {
    	BusyOrNot = true;
        Random rand = new Random();
        int[] arr = new int[10];
        for(int i = 0;i<arr.length;i++){
            arr[i] = rand.nextInt(1000);
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
    	BusyOrNot = false;
    }

    public void start () {
	      System.out.println("Starting " +  threadName );
	      if (t == null) {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }
}

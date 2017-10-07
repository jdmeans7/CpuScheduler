import java.util.ArrayList;
import java.util.Random;

public class IODevice {
	
    public IODevice(ArrayList<Process> Wait_Queue) {}

    public boolean BusyOrNot;
    int burstAmount;
    
    //Always pick one process from Wait_Queue to execute
    public String execute(Process p) {
        BusyOrNot = true;
        burstAmount = p.getBurstNum();
        for(int i = 0; i<burstAmount;i++){ //p.getioburstnumber
            bubbleSort();
        }
        p.nextBurstNum();
        //CallBubble Sort()for IO_bursttimes and then return “ready”
        return "ready";
    }

    public void bubbleSort() {
        Random rand = new Random();
        int[] arr = new int[10];
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
}

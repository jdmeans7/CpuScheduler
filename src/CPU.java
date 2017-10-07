import java.util.Random;

public class CPU {
    public boolean BusyOrNot;
    public int PC; //Your CPU only has one register PC
    public int timeslice;

    public CPU(int settimeslice) {
        timeslice = settimeslice;
        BusyOrNot = false;
    }
    //ToDo: pair method, will need to create new class that contains an int and a string to return both

    public Pair execute(Process p) {
        BusyOrNot = true;
        for (int i = 0;i < p.getBurstNum(); i++){
        	bubbleSort();
        }
        if (p.getCount()+3 > p.getCode().length()){
        	return (new Pair(p.getBurstNum(), "terminated"));
        }
    	else{
        	p.nextBurstNum();
        	return (new Pair(p.getBurstNum(), "wait"));
        }
    }    
    
    public Pair executeRoundRobin(Process p) {
        BusyOrNot = true;
        if (p.getBurstNum() <= timeslice){
        	for (int i = 0;i < p.getBurstNum(); i++){
            	bubbleSort();
            }
        }else if (p.getBurstNum() > timeslice){
        	for (int i = 0;i < timeslice; i++){
            	bubbleSort();
            }
        	//these print statements test if the round robin is working correctly
        	//System.out.println("1. " + p.getBurstNum());
        	p.setBurstNum(p.getBurstNum()-timeslice);
        	//System.out.println("2. " + p.getBurstNum());
    		return (new Pair(p.getBurstNum(), "ready"));
        }

        if (p.getCount()+3 > p.getCode().length()){
        	return (new Pair(p.getBurstNum(), "terminated"));
        }else{
        	p.nextBurstNum();
        	return (new Pair(p.getBurstNum(), "wait"));
        }
    }    
    /*
        read the CPU burst number, say #,from the position PositionOfNextInstructionToExecute of P.
        Repeat calling Bubble Sort() for # times and then continue.
        If the code runs out, return (PositionOfNextInstructionToExecute, “terminated”), then OS put it back to the terminated queue.
        If the slice of time (restricted number of calling Bubble sort() for a process each time) runs out, return (PositionOfNextInstructionToExecute+1, “ready”), then OS put it back to the ready queue.
        Otherwise, return (PositionOfNextInstructionToExecute+1, “wait”)(namely, P has an I/O request and then OS remove it from the ready queue and sent it to I/O queue)
    */
    
    
    public boolean isCPUBusy() {
        return BusyOrNot;
    }
    
    public void setCPUBusy(boolean bool){
    	BusyOrNot = bool;
    }
    
    public void bubbleSort() {
        Random rand = new Random();
        int[] arr = new int[10000];
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

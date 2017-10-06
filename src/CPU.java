import java.util.Random;

public class CPU {
    public boolean BusyOrNot;
    public int PC; //Your CPU only has one register PC
    public int timeslice;

    public CPU(int settimeslice) {
        timeslice = settimeslice;
        BusyOrNot = false;
    }

    //ToDo: execute method
    public Pair execute(Process p) {
        BusyOrNot = true;
        int time = (int)System.currentTimeMillis();
        for(int i = 0;i<p.getBurstNum();i++){
            bubbleSort();
            if(((int)System.currentTimeMillis()-time)>timeslice){ //if time taken is greater than the time alloted, sets the burst number to the original burst num minus the amount of times bubble sort was run and returns the process to the ready queue
                p.setBurstNum(p.getBurstNum()-(i+1));
                return (new Pair(p.getPcb_data().getNext(),"ready"));
            }
        }
        if((p.getPcb_data().getNext()+1) > p.getCodeA().length) return (new Pair(p.getPcb_data().getNext(), "terminated")); //If there are no more burst numbers to be read, puts process in terminated queue.
        else return (new Pair((p.getPcb_data().getNext()+1),"wait"));
        /*
        read the CPU burst number, say #,from the position PositionOfNextInstructionToExecute of P.
        Repeat calling Bubble Sort() for # times and then continue.

        If the code runs out, return (PositionOfNextInstructionToExecute, “terminated”), then OS put it back to the terminated queue.

        If the slice of time (restricted number of calling Bubble sort() for a process each time) runs out,
        return (PositionOfNextInstructionToExecute, “ready”), then OS put it back to the ready queue,
        subtracting the amount of times bubbles sort was done from the burst number.

        Otherwise, return (PositionOfNextInstructionToExecute+1, “wait”)(namely, P has an I/O request and then OS remove it
        from the ready queue and sent it to I/O queue)
    */

    }
    public boolean isCPUBusy() {
        return BusyOrNot;
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

import java.util.ArrayList;

public class IODevice {
    public IODevice(ArrayList<Process> Wait_Queue) {
    }

    public boolean BusyOrNot;

    //Always pick one process from Wait_Queue to exeute
    public String execute(int IO_burst) {
        BusyOrNot = true;
        //CallBubble Sort()for IO_bursttimes and then return “ready”;
        return "ready";
    }
    public void bubbleSort(){}
}

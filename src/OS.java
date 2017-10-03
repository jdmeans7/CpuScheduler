import java.util.ArrayList;

public class OS {
    public CPU cpu;
    public IODevice io;
    public boolean isCPUAvailable;
    //public ProcessTable process_Table;
    public ArrayList<Process> New_Queue;
    public ArrayList<Process> Ready_Queue;
    public ArrayList<Process> Wait_Queue;
    public ArrayList<Process> Terminated_Queue;
    //Read the txt input file, for each line,create a process and record its arrival time
    //Put each process in New_Q queue initially then put them in Ready_Q
    //Always check whether the CPU is idle or not; if yes, use your scheduler algorithm to select a process from the Ready_Queue for CPU execution
    //According to the return value of CPU execute(), put the process into the corresponding queue.
    //Record the time of every operation for computing your latency and response
}

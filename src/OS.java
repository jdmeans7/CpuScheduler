import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class OS {
    public static void main(String[] args) {
        CPU cpu;
        IODevice io;
        boolean isCPUAvailable;
        //public ProcessTable process_Table;
        ArrayList<PCB> New_Queue = new ArrayList<>();
        ArrayList<Process> Ready_Queue;
        ArrayList<Process> Wait_Queue;
        ArrayList<Process> Terminated_Queue;

        File f = new File("test");
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            Scanner scan = new Scanner(bf);
            while(scan.hasNextLine()) {
                String[] line = scan.nextLine().split(" ");
                New_Queue.add(new PCB(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]),line[3]));
            }
        } catch (Exception e){}
        //ToDo: OS class
        //Read the txt input file, for each line,create a process and record its arrival time
        //Put each process in New_Q queue initially then put them in Ready_Q
        //Always check whether the CPU is idle or not; if yes, use your scheduler algorithm to select a process from the Ready_Queue for CPU execution
        //According to the return value of CPU execute(), put the process into the corresponding queue.
        //Record the time of every operation for computing your latency and response
    }
}

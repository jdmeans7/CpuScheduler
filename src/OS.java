import com.sun.org.apache.regexp.internal.RE;

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
        ArrayList<Process> Ready_Queue = new ArrayList<>();
        ArrayList<Process> Wait_Queue;
        ArrayList<Process> Terminated_Queue;

        //Read text file and convert lines to PCB objects, add those PCB objects to New_Queue
        File f = new File("C:\\Users\\jason\\IdeaProjects\\CpuScheduler\\src\\test");
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            Scanner scan = new Scanner(bf);
            while(scan.hasNextLine()) {
                String[] line = scan.nextLine().split(" ");
                New_Queue.add(new PCB(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]),line[3]));
            }
        } catch (Exception e){}

        //Convert PCB objects to Process objects and add those objects to Reader_Queue
        for(int i = 0; i<New_Queue.size();i++){
            Ready_Queue.add(new Process(New_Queue.get(i)));
        }

        //Give user choice of sorting algorithm
        Scanner uScan = new Scanner(System.in);
        boolean done = false;
        while(!done) {
            System.out.println("Which sorting algorithm should be used?");
            System.out.println("1. FCFS");
            System.out.println("2. Round Robin");
            System.out.println("3. Last one");
            int choice = uScan.nextInt();
            if (choice == 1) {
                done = true;
                //ToDo: code for FCFS alg
            } else if (choice == 2) {
                done = true;
                //ToDo: Code for RR alg
            } else if (choice == 3) {
                done = true;
                //ToDo: Code for last alg
            } else System.out.println("Invalid input");
        }
        //ToDo: OS class
        //Read the txt input file, for each line,create a process and record its arrival time
        //Put each process in New_Q queue initially then put them in Ready_Q
        //Always check whether the CPU is idle or not; if yes, use your scheduler algorithm to select a process from the Ready_Queue for CPU execution
        //According to the return value of CPU execute(), put the process into the corresponding queue.
        //Record the time of every operation for computing your latency and response
    }
}

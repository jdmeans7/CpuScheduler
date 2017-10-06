import com.sun.org.apache.regexp.internal.RE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class OS {
    public static void main(String[] args) {
        CPU cpu = new CPU(5);
        IODevice io = new IODevice();
        boolean isCPUAvailable;
        //public ProcessTable process_Table;
        ArrayList<PCB> New_Queue = new ArrayList<>();
        ArrayList<Process> Ready_Queue = new ArrayList<>();
        ArrayList<Process> Wait_Queue = new ArrayList<>();
        ArrayList<Process> Terminated_Queue = new ArrayList<>();

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

        /*test of string to i[] of code
        int[] a = Ready_Queue.get(0).getCodeA();
        for(int i = 0;i<a.length;i++){
            System.out.println(a[i]);
        }
        */

        //Give user choice of sorting algorithm
        Scanner uScan = new Scanner(System.in);
        boolean done = false;
        while(!done) {
            System.out.println("Which sorting algorithm should be used?");
            System.out.println("1. FCFS");
            System.out.println("2. Round Robin");
            System.out.println("3. Static Priority");
            int choice = Integer.parseInt(uScan.nextLine());

            if (choice == 1) {
                done = true;
                /*ToDo: code for FCFS alg:
                 Runnable processes are kept in a first-in, first-out  ready  queue.
                 FCFS  is  non-preemptive;  once  a  process  begins running on a CPU,
                 it will continue running until it either complete or blocks for I/O.
                  */
                boolean finished = false;
                int count = 0;
                while(!finished){
                    Process p = Ready_Queue.remove(count);
                    Pair pair = cpu.execute(p);
                    if(pair.getState() == "wait"){
                        p.getPcb_data().setNext(p.getPcb_data().getNext()+1);
                        Wait_Queue.add(p);
                    }
                    else if(pair.getState() == "ready"){
                        Ready_Queue.add(p);
                    }
                }
            }

            else if (choice == 2) {
                done = true;
                /*ToDo: Code for RR alg:
                Similar   to   FCFS,   except   preemptive.   Each   process   is
                assigned a time slice when it is scheduled. At the end of the time slice, if the
                process  is  still  running,  the  process  is  preempted,  and  moved  to  the  tail  of
                the ready queue.
                */
            }

            else if (choice == 3) {
                done = true;
                /*ToDo: Code for static priority alg:
                 The processes with the highest priorities always get the CPU.
                 Lower-priority  processes  may  be  preempted  if  a  process  with  a  higher  priority becomes runnable.
                 */
            } else System.out.println("Invalid input");
        }


        //ToDo: OS class
        //Read the txt input file, for each line,create a process and record its arrival time
        //Put each process in New_Q queue initially then put them in Ready_Q
        //Always check whether the CPU is idle or not; if yes, use your scheduler algorithm to select a process from the Ready_Queue for CPU execution
        //According to the return value of CPU execute(), put the process into the corresponding queue.
        //Record the time of every operation for computing your latency and response
        //Thread for CPU and IO classes to run in parallel
    }
}

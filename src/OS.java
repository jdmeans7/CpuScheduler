import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class OS {
    public static void main(String[] args) {
       // CPU cpu = new CPU(5);
        //CPU cpu = new CPU(5, "CPUthread");
        //public ProcessTable process_Table;
        
    	ArrayList<PCB> New_Queue = new ArrayList<>();
        ArrayList<Process> Ready_Queue = new ArrayList<>();
        ArrayList<Process> Wait_Queue = new ArrayList<>();
        ArrayList<Process> Terminated_Queue = new ArrayList<>();
        IODevice io = new IODevice(Wait_Queue, "IOthread");
        int choice = 0;

        File f = new File("C:\\Users\\amayz\\workspace\\CpuScheduler\\test.txt");
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            Scanner scan = new Scanner(bf);
            while(scan.hasNextLine()) {
                String[] line = scan.nextLine().split(" ");
                New_Queue.add(new PCB(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]),line[3]));
            }
        } catch (Exception e){}
        
        
        for (int i = 0; i < New_Queue.size(); i++){
        	Process p = new Process(New_Queue.get(i));  //create the processes based on the PCB in the new queue
        	Ready_Queue.add(p);  //add these processes to the ready queue
        }
        
        Scanner uScan = new Scanner(System.in);
        boolean done = false;
        while(!done) {
            System.out.println("Which sorting algorithm should be used?");
            System.out.println("1. FCFS");
            System.out.println("2. Round Robin");
            System.out.println("3. Static Priority"); 
            choice = uScan.nextInt();
            CPU cpu = new CPU(5, "CPUthread", choice);
            if (choice == 1) {
        		cpu.start();
        		io.start();
                boolean valueInReadyQueue = true;
                cpu.setDone(false);
                while (valueInReadyQueue == true){
                	if (Ready_Queue.size() == 0){
            			valueInReadyQueue = false;
            			cpu.setDone(true);
            			System.out.println("First Come First Serve Algorithm Finished.");
            		}else{
                		Pair pairReturn;
                		Process firstValueInReady = Ready_Queue.get(0);
                		cpu.setCurretProcess(firstValueInReady);
                		cpu.run();
                		pairReturn = cpu.currentReturnPair();
                		String stateCPU = pairReturn.getState();
                		
                		if (stateCPU.equalsIgnoreCase("wait")){
                			Ready_Queue.remove(firstValueInReady);
                			Wait_Queue.add(firstValueInReady);
                			io.setCurretProcess(firstValueInReady);
                			System.out.println(Wait_Queue.get(0).getPriority());
                			io.run();
                			String waitState = io.getCurrentState();
                			if (waitState.equalsIgnoreCase("ready")){
                				Ready_Queue.add(Wait_Queue.get(0));
                				Wait_Queue.remove(0);
                				valueInReadyQueue = true;
                			}
                		}
                		
                		if (stateCPU.equalsIgnoreCase("terminated")){
                			Terminated_Queue.add(firstValueInReady);
                			Ready_Queue.remove(0);
                		}
                		if (stateCPU.equalsIgnoreCase("ready")){
                			valueInReadyQueue = true;
                		}
                	}
                }
                done = true;
            }else if (choice == 2) {
            	cpu.start();
        		io.start();
            	 boolean valueInReadyQueue = true;
     			cpu.setDone(false);
                 while (valueInReadyQueue == true){
                 	if (Ready_Queue.size() == 0){
             			valueInReadyQueue = false;
            			cpu.setDone(true);
             			System.out.println("Round Robin Algorithm Finished.");
             		}else //if(cpu.BusyOrNot == false)
             			{
                 		Pair pairReturn;
                 		Process firstValueInReady = Ready_Queue.get(0);
                 		cpu.setCurretProcess(firstValueInReady);
                		cpu.run();
                		pairReturn = cpu.currentReturnPair();
                		String stateCPU = pairReturn.getState();
                 		
                 		if (stateCPU.equalsIgnoreCase("wait")){
                 			Ready_Queue.remove(firstValueInReady);
                 			Wait_Queue.add(firstValueInReady);
                 			io.setCurretProcess(firstValueInReady);
                			System.out.println(Wait_Queue.get(0).getPriority());
                			io.run();
                			String waitState = io.getCurrentState();
                 			if (waitState.equalsIgnoreCase("ready")){
                 				Ready_Queue.add(Wait_Queue.get(0));
                 				Wait_Queue.remove(0);
                 				valueInReadyQueue = true;
                 			}
                 		}
                 		
                 		if (stateCPU.equalsIgnoreCase("terminated")){
                 			Terminated_Queue.add(firstValueInReady);
                 			Ready_Queue.remove(0);
                 		}
                 		if (stateCPU.equalsIgnoreCase("ready")){
                 			Ready_Queue.remove(0);
                 			Ready_Queue.add(firstValueInReady);
                 			valueInReadyQueue = true;
                 		}
                 	}
                 }
                 done = true;
                //ToDo: Code for RR alg
            } else if (choice == 3) {
            	cpu.start();
        		io.start();
                Collections.sort(Ready_Queue);
                boolean valueInReadyQueue = true;
    			cpu.setDone(false);
                while (valueInReadyQueue == true){
                	if (Ready_Queue.size() == 0){
            			valueInReadyQueue = false;
            			cpu.setDone(true);
            			System.out.println("Static Priority Algorithm Finished.");
            		}else //if(cpu.BusyOrNot == false)
            			{
                		Pair pairReturn;
                		Process firstValueInReady = Ready_Queue.get(0);
                		cpu.setCurretProcess(firstValueInReady);
                		cpu.run();
                		pairReturn = cpu.currentReturnPair();
                		String stateCPU = pairReturn.getState();
                		
                		if (stateCPU.equalsIgnoreCase("wait")){
                			Ready_Queue.remove(firstValueInReady);
                			Wait_Queue.add(firstValueInReady);
                			io.setCurretProcess(firstValueInReady);
                			System.out.println(Wait_Queue.get(0).getPriority());
                			io.run();
                			String waitState = io.getCurrentState();
                			if (waitState.equalsIgnoreCase("ready")){
                				Ready_Queue.add(Wait_Queue.get(0));
                				Wait_Queue.remove(0);
                				Collections.sort(Ready_Queue);
                				valueInReadyQueue = true;
                			}
                		}
                		
                		if (stateCPU.equalsIgnoreCase("terminated")){
                			Terminated_Queue.add(firstValueInReady);
                			Ready_Queue.remove(0);
                		}
                		if (stateCPU.equalsIgnoreCase("ready")){
                			valueInReadyQueue = true;
                		}
                	}
                }
                done = true;
                
                //TEST PRINTS ---SORT WORKS BASED ON PRIORITY---
                //System.out.println(Terminated_Queue.get(0).getPriority());
                //System.out.println(Terminated_Queue.get(1).getPriority());
                //System.out.println(Terminated_Queue.get(2).getPriority());
                
                
            } else System.out.println("Invalid input");
        }       
        
        //TEST PRINTS ---SORT WORKS BASED ON PRIORITY---
        //System.out.println(Ready_Queue.get(0).getPriority());
        //System.out.println(Ready_Queue.get(1).getPriority());
        //System.out.println(Ready_Queue.get(2).getPriority());
        
        //ToDo: OS class
        //Read the txt input file, for each line,create a process and record its arrival time
        //Put each process in New_Q queue initially then put them in Ready_Q
        //Always check whether the CPU is idle or not; if yes, use your scheduler algorithm to select a process from the Ready_Queue for CPU execution
        //According to the return value of CPU execute(), put the process into the corresponding queue.
        //Record the time of every operation for computing your latency and response
    }
}

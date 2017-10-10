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
        
    	ArrayList<Long> latency = new ArrayList<>();
    	ArrayList<Long> resTimes = new ArrayList<>();
    	ArrayList<PCB> New_Queue = new ArrayList<>();
        ArrayList<Process> Ready_Queue = new ArrayList<>();
        ArrayList<Process> Wait_Queue = new ArrayList<>();
        ArrayList<Process> Terminated_Queue = new ArrayList<>();
        IODevice io = new IODevice(Wait_Queue, "IOthread");
        int choice = 0;
        int totalProcesses;
        
        Scanner uScan = new Scanner(System.in);
        System.out.println("Enter file name: ");
        String filename = uScan.nextLine();
        File f = new File(filename); //"C:\\Users\\amayz\\workspace\\CpuScheduler\\test.txt"
        try {
            BufferedReader bf = new BufferedReader(new FileReader(f));
            Scanner scan = new Scanner(bf);
            int check = 0;
            while(scan.hasNextLine()) {
            	if(check == 0) {
			scan.nextLine();
			check++;
		}
		else {
			String[] line = scan.nextLine().split(",");
			New_Queue.add(new PCB(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]), line[3]));
		}
            }
        } catch (Exception e){}
        
        
        for (int i = 0; i < New_Queue.size(); i++){
        	Process p = new Process(New_Queue.get(i));  //create the processes based on the PCB in the new queue
        	Ready_Queue.add(p);  //add these processes to the ready queue
        }
        
        totalProcesses = Ready_Queue.size();
        
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
                			//System.out.println(Wait_Queue.get(0).getPriority());
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
                			latency.add(System.currentTimeMillis()-firstValueInReady.getStartTime());
                			resTimes.add(firstValueInReady.getResTime() - firstValueInReady.getStartTime());
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
                			//System.out.println(Wait_Queue.get(0).getPriority());
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
                 			latency.add(System.currentTimeMillis()-firstValueInReady.getStartTime());
                			resTimes.add(firstValueInReady.getResTime() - firstValueInReady.getStartTime());
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
                			//System.out.println(Wait_Queue.get(0).getPriority());
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
                			latency.add(System.currentTimeMillis()-firstValueInReady.getStartTime());
                			resTimes.add(firstValueInReady.getResTime() - firstValueInReady.getStartTime());
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
            int lsum = 0;
            int rsum = 0;
            long lmin;
            long rmin;
            System.out.println("\n******* REPORT *******\n");
            for(int i = 0; i<latency.size(); i++){
            	lsum += latency.get(i);
            	System.out.println("-----" + "Process " + i+1 + "-----");
            	System.out.println("Latency: " + latency.get(i));
            	rsum += resTimes.get(i);
            	System.out.println("Response Time: " + resTimes.get(i));
            }
            Collections.sort(latency);
            Collections.sort(resTimes);
            lmin = latency.get(0);
            rmin = resTimes.get(0);
            long lmax = latency.get(latency.size()-1);
            long rmax = resTimes.get(resTimes.size()-1);
            double lmean = lsum/latency.size();
            double rmean = rsum/resTimes.size();
            double lDev = standDev(latency,lmean);
            double rDev = standDev(resTimes,rmean);
            
            System.out.println("------- LATENCY -------");
            System.out.println("Latency min: " + lmin);
            System.out.println("Latency avg: " + lmean);
            System.out.println("Latency max: " + lmax);
            System.out.println("Latency std dev: " + lDev);

            System.out.println("\n------- RESPONSE TIME -------");
            System.out.println("Response Time min: " + rmin);
            System.out.println("Response Time avg: " + rmean);
            System.out.println("Response Time max: " + rmax);
            System.out.println("Response Time std dev: " + rDev);
            
            System.out.println("\n------- THROUGHPUT -------");
            System.out.println("Throughput: " + (lsum+rsum)/totalProcesses);
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
    
    public static double standDev(ArrayList<Long> array1, double mean){
         
         //ArrayList<Long> deviations = new ArrayList<>();
         double dsum = 0;
         for (int i=0;i<array1.size();i++)
         {
        	double a = (double)array1.get(i)-mean;
         	//deviations.add(a*a);
         	dsum += (a*a);
         }
         double b = (1/(double)array1.size());
         dsum = dsum*b;
         return Math.sqrt(dsum);
         
    }
}

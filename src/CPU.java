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

    /*
        read the CPU burst number, say #,from the position PositionOfNextInstructionToExecute of P.
        Repeat calling Bubble Sort() for # times and then continue.
        If the code runs out, return (PositionOfNextInstructionToExecute, “terminated”), then OS put it back to the terminated queue.
        If the slice of time (restricted number of calling Bubble sort() for a process each time) runs out, return (PositionOfNextInstructionToExecute+1, “ready”), then OS put it back to the ready queue.
        Otherwise, return (PositionOfNextInstructionToExecute+1, “wait”)(namely, P has an I/O request and then OS remove it from the ready queue and sent it to I/O queue)
    */

    return new Pair(0,"a");
    }
    public boolean isCPUBusy() {
        return BusyOrNot;
    }

}

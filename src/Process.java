public class Process {
    public PCB Pcb_data;
    public String code; // = CPU-I/O burst Sequence;
    //ToDo: other variables help you computing the latency, response
    public Process(PCB p) {
        this.Pcb_data = p;
        code = p.getCode();
        //ToDo: Set PCB data, code and others. Set state as "NEW";
    }

    public PCB getPcb_data() {
        return Pcb_data;
    }

    public String getCode() {
        return code;
    }
}

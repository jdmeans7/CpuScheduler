public class Process {
    public PCB Pcb_data;
    public String code; // = CPU-I/O burst Sequence;
    public int[] codeA;
    //ToDo: other variables help you computing the latency, response
    public Process(PCB p) {
        this.Pcb_data = p;
        code = p.getCode();
        codeA = new int[code.length()];
        for(int i = 0;i<code.length();i++){
            codeA[i] = Integer.parseInt((code.substring(i,i+1)));
        }
    }

    public int getBurstNum(){
        return codeA[Pcb_data.getNext()];
    }

    public int[] getCodeA() {
        return codeA;
    }

    public PCB getPcb_data() {
        return Pcb_data;
    }

    public String getCode() {
        return code;
    }
}

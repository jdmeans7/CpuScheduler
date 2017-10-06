public class PCB {
    //for example: Process_id, Arrive_time, state, PositionOfNextInstructionToExecute(PC value)and so on
    String id;
    double arriveTime;
    int next;
    String state;
    int arriveOrder;
    String code;
    int priority;
    public PCB(String id,int arriveOrder,int priority, String code){
        this.id = id;
        this.arriveOrder = arriveOrder;
        this.priority = priority;
        this.code = code;
        state = "new";
        arriveTime = System.currentTimeMillis();
        next = 0;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public double getArriveTime() {
        return arriveTime;
    }

    public String getId() {
        return id;
    }

    public int getNext() {
        return next;
    }

    public int getArriveOrder() {
        return arriveOrder;
    }

    public int getPriority() {
        return priority;
    }

    public String getCode() {
        return code;
    }
}
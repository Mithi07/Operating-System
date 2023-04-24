public class ProcessNPP implements Comparable<ProcessNPP>{
    
    private static int lastProcessID = 0;

    private int processID, arrivalTime, burstTime, priority;
    private int finishTime, turnaroundTime, waitingTime;

    ///////////////////////////////////////////////

    public ProcessNPP() {}

    public ProcessNPP(int arrivalTime, int burstTime, int priority) {
        this.processID = lastProcessID++;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    ///////////////////////////////////////////////

    
    public void setFinishTime(int i) {
        finishTime = i;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setTurnaroundTime(int i) {
        turnaroundTime = i;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void setWaitingTime(int i) {
        waitingTime = i;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    ///////////////////////////////////////////////

    public int getProcessID(){
        return processID;
    }

    public static int getLastID(){
        return lastProcessID;
    }

    public int getArrivalTime(){
        return arrivalTime;
    }

    public int getBurstTime(){
        return burstTime;
    }

    public int getPriority(){
        return priority;
    }

    @Override
    public String toString() {
        return "P" + this.processID + ":\nArrival time (" + this.arrivalTime + ")\nBurst time (" + this.burstTime +")\nPriority (" + priority +")\nFinish time ("+finishTime+")\nTurnaround time ("+turnaroundTime+")\nWaiting time("+waitingTime+")";
    }

    @Override
    public int compareTo(ProcessNPP p) {
        if (this.getArrivalTime() > p.getArrivalTime()) {
            return 1;
        }

        else if (this.getArrivalTime() == p.getArrivalTime()) {
            if ( getPriority() > p.getPriority() ) {
                return 1;
            }
            
            else if ( getPriority() < p.getPriority()) {
                return -1;
            }

            else {
                return 0;
            }
        }

        else {
            return -1;
        }
    }
}

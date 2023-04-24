import java.util.*;
import java.text.DecimalFormat;

public class NPP {
    public static void main(String [] args) {
        
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the number of processes needed: ");
        int totalProcesses = input.nextInt();
        ArrayList<ProcessNPP> processList = new ArrayList<>();
        for (int i = 0; i < totalProcesses; i++) {
            System.out.println("Please enter arrival time, burst time, and priority for P"+ i +": ");
            processList.add(new ProcessNPP(input.nextInt(), input.nextInt(), input.nextInt()));
        }
        input.close();

        Collections.sort(processList);

        ////////////////////////////////////////////////////
        ProcessNPP currentProcess;
        ProcessNPP previousProcess;

        for (int i = 0; i < processList.size(); i++) {
            currentProcess = processList.get(i);
            if (i == 0) {
                currentProcess.setFinishTime(currentProcess.getArrivalTime() + currentProcess.getBurstTime());
            }
            else if (i >= 1){
                previousProcess = processList.get(i-1);
                currentProcess.setFinishTime(currentProcess.getBurstTime() + previousProcess.getFinishTime());
            }
        }

        for (int i = 0; i < processList.size(); i++) {
            currentProcess = processList.get(i);
            if (i == 0) {
                currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getArrivalTime());
            }
            else if (i >= 1){
                previousProcess = processList.get(i-1);
                currentProcess.setTurnaroundTime(currentProcess.getFinishTime() - currentProcess.getArrivalTime());
            }
        }

        for (int i = 0; i < processList.size(); i++) {
            currentProcess = processList.get(i);
            if (i == 0) {
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());
            }
            else if (i >= 1){
                previousProcess = processList.get(i-1);
                currentProcess.setWaitingTime(currentProcess.getTurnaroundTime() - currentProcess.getBurstTime());
            }
        }
        ////////////////////////////////////////////////////

        double avgWaitingTime, avgTurnaroundTime, avgFinishTime;
        double sumWaitTime = 0, sumTurnaroundTime = 0, sumFinishTime = 0;
        for (int i = 0; i < processList.size(); i++) {
            sumWaitTime += processList.get(i).getWaitingTime();
            sumTurnaroundTime += processList.get(i).getTurnaroundTime();
            sumFinishTime += processList.get(i).getFinishTime();
        }
        avgWaitingTime = sumWaitTime/processList.size();
        avgTurnaroundTime = sumTurnaroundTime / processList.size();
        avgFinishTime = sumFinishTime / processList.size();


        // for (Process i : processList) { //test
        //     System.out.println("-------------------------------------------------");
        //     System.out.println(i);
        //     System.out.println("-------------------------------------------------");
        // }

        // System.out.println("\n\nAvg wait time:" + avgWaitingTime);
        // System.out.println("Avg turnaround time: " + avgTurnaroundTime);
        DecimalFormat numFormat = new DecimalFormat("00");
        System.out.println("                                           Non-Preemptive Priority Scheduling                                            ");
        System.out.println("+---------+------------------+----------------+--------------+-----------------+---------------------+------------------+");
        System.out.println("|   PID   |   Arrival Time   |   Burst Time   |   Priority   |   Finish Time   |   Turnaround Time   |   Waiting Time   |");
        System.out.println("+---------+------------------+----------------+--------------+-----------------+---------------------+------------------+");
        for (int i = 0; i < processList.size(); i++) {
            System.out.println("|    P"+processList.get(i).getProcessID()+"   |        "+numFormat.format(processList.get(i).getArrivalTime())+"        |       "+numFormat.format(processList.get(i).getBurstTime())+"       |      "+numFormat.format(processList.get(i).getPriority())+"      |        "+numFormat.format(processList.get(i).getFinishTime())+"       |          "+numFormat.format(processList.get(i).getTurnaroundTime())+"         |        "+numFormat.format(processList.get(i).getWaitingTime())+"        |");
        }
        System.out.println("+---------+------------------+----------------+--------------+-----------------+---------------------+------------------+");
        
        //put avg and total times here !!!!!!!!!
        System.out.println("\nTotal finish time: "+processList.get(processList.size()-1).getFinishTime()+". \n");
        System.out.println("Total turnaround time: "+ sumTurnaroundTime+". ");
        System.out.println("Total waiting time: "+sumWaitTime+". ");
        System.out.println("Average waiting time: "+avgWaitingTime+". \n\n");
        System.out.println("Gantt Chart: ");

        StringBuilder row1 = new StringBuilder("+");
        StringBuilder row2 = new StringBuilder("|");
        StringBuilder row3 = new StringBuilder("+");
        StringBuilder row4 = new StringBuilder(String.valueOf(processList.get(0).getArrivalTime()));


        for (int i = 0; i < processList.size(); i++) {
            row1.append("----------+");
        }

        for (int i = 0; i < processList.size()-1; i++) {
            row2.append("     P"+processList.get(i).getProcessID()+"   |");
        }
        row2.append("     P" + processList.get(processList.size()-1).getProcessID()+"   |");

        for (int i = 0; i < processList.size(); i++) {
            row3.append("----------+");
        }

        for (int i = 0; i < processList.size(); i++) {
            row4.append("         "+numFormat.format(processList.get(i).getFinishTime()));
        }

        System.out.println(row1);;
        System.out.println(row2);;
        System.out.println(row3);;
        System.out.println(row4);;

    }

}

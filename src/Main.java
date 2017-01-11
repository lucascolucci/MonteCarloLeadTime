import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lucascolucci on 12/2/16.
 */
public class Main {
    public static void main(String [ ] args)
    {
        List<String> columnNames = new ArrayList<String>();
        List<Integer> wipLimits = new ArrayList<Integer>();
        List<List<Integer>> leadTimeCandidatesPerColumn = new ArrayList<List<Integer>>();
        List<String> namesOfColumnsAffectedByGlobalWIPLimit = new ArrayList<String>();
        Integer globalWIPLimit = 5;
        initializeVariables(columnNames, wipLimits, leadTimeCandidatesPerColumn, namesOfColumnsAffectedByGlobalWIPLimit, globalWIPLimit);

        List<Integer> leadTimes = new ArrayList<Integer>();

        for(int i = 0; i < 2500; i++){
            Simulator simulator = new Simulator(columnNames, wipLimits, leadTimeCandidatesPerColumn, namesOfColumnsAffectedByGlobalWIPLimit, globalWIPLimit);
            simulator.initializeBoard();
            //simulator.printBoard();
            leadTimes.add(simulator.run());
        }

        printArrayLineByLine(leadTimes);
        //System.out.println(average(leadTimes));
    }

    private static void printArrayLineByLine(List<Integer> values){
        for(Integer value : values){
            System.out.println(value);
        }
    }

    private static Integer average(List<Integer> list){
        int sum = 0;
        for(Integer i : list){
            sum += i;
        }
        return sum / list.size();
    }


    private static void initializeVariables(List<String> columnNames, List<Integer> wipLimits, List<List<Integer>> leadTimeCandidatesPerColumn, List<String> namesOfColumnsAffectedByGlobalWIPLimit, Integer globalWIPLimit) {
        columnNames.add("----BACKLOG----");
        columnNames.add("---GROOMING----");
        columnNames.add(" -READY TO DEV-");
        columnNames.add(" --DEVELOPING--");
        columnNames.add("--CODE REVIEW--");
        columnNames.add("-READY TO TEST-");
        columnNames.add("----TESTING----");
        columnNames.add("-----DONE------");


        //namesOfColumnsAffectedByGlobalWIPLimit.add("---GROOMING----");
        namesOfColumnsAffectedByGlobalWIPLimit.add(" -READY TO DEV-");
        namesOfColumnsAffectedByGlobalWIPLimit.add(" --DEVELOPING--");
        namesOfColumnsAffectedByGlobalWIPLimit.add("--CODE REVIEW--");
        namesOfColumnsAffectedByGlobalWIPLimit.add("-READY TO TEST-");
        namesOfColumnsAffectedByGlobalWIPLimit.add("----TESTING----");

        wipLimits.add(12);
        wipLimits.add(5);
        wipLimits.add(5);
        wipLimits.add(5);
        wipLimits.add(5);
        wipLimits.add(5);
        wipLimits.add(5);
        wipLimits.add(12);

        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0)));
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(6,10,6,0,2,3,2,19,0,0,0,4,3,1,4,0,0,1,0)));//,0,4,3,4,1,3,20,0,25,35,10,1,0,8,0,0,0,0,1,1,0)));
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0,1,2,1,0,6,2,2,1,1,3,1,0,0,1,2,1,12,4)));//,0,17,14,24,18,14,2,5,2,8,10,17,22,0,11,23,0,1,0,1,0)));
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(1,2,0,0,0,0,1,2,2,4,0,2,5,0,0,1,3,2,2)));//,1,5,0,1,1,2,3,0,5,4,3,1,3,5,0,6,0,4,0,1,0)));
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(1,2,1,2,2,2,1,1,0,0,1,1,4,2,2,2,1,4,4)));//,3,3,1,0,8,1,5,0,6,2,4,1,0,13,1,1,1,1,1,2,1)));
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,2,1,1,3,3,1,1,0,1,2,2)));//,0,0,0,2,1,0,1,0,1,1,2,0,1,0,9,0,0,1,0,0,0)));
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(2,0,0,2,3,3,2,4,1,0,1,2,4,0,1,3,6,0,0)));//,0,2,3,1,1,0,8,1,2,1,1,0,10,0,0,0,0,0,0,0,0)));
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0)));

    }
}
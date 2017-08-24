import com.sun.jdi.LongValue;

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
        Integer globalWIPLimit = 9;
        initializeVariables(columnNames, wipLimits, leadTimeCandidatesPerColumn, namesOfColumnsAffectedByGlobalWIPLimit, globalWIPLimit);
        List<Integer> leadTimes = new ArrayList<Integer>();

        for(int i = 0; i < 5000; i++){
            Simulator simulator = new Simulator(columnNames, wipLimits, leadTimeCandidatesPerColumn, namesOfColumnsAffectedByGlobalWIPLimit, globalWIPLimit);
            simulator.initializeBoard();
            //simulator.printBoard();
            leadTimes.add(simulator.run());
        }

        printArrayLineByLine(leadTimes);
        System.out.println("Average LT is: " + average(leadTimes));
    }

    private static void printArrayLineByLine(List<Integer> values){
        for(Integer value : values){
            System.out.println(value);
        }
    }

    private static Double average(List<Integer> list){
        Double sum = 0.0;
        for(Integer i : list){
            sum += i;
        }
        return sum / list.size();
    }


    private static void initializeVariables(List<String> columnNames, List<Integer> wipLimits, List<List<Integer>> leadTimeCandidatesPerColumn, List<String> namesOfColumnsAffectedByGlobalWIPLimit, Integer globalWIPLimit) {
        columnNames.add("----BACKLOG----");
        columnNames.add(" --DEVELOPING--");
        columnNames.add("--CODE REVIEW--");
        columnNames.add("-READY TO TEST-");
        columnNames.add("----TESTING----");
        columnNames.add("-----DONE------");

        // Columns that will be affected by the global wip limit
        namesOfColumnsAffectedByGlobalWIPLimit.add(" --DEVELOPING--");
        namesOfColumnsAffectedByGlobalWIPLimit.add("--CODE REVIEW--");
        namesOfColumnsAffectedByGlobalWIPLimit.add("-READY TO TEST-");
        namesOfColumnsAffectedByGlobalWIPLimit.add("----TESTING----");

        // Number of Items still on our backlog
        wipLimits.add(87);
        // Max number of items allowed at Developing
        wipLimits.add(9);
        // Max number of items allowed at Developing
        wipLimits.add(9);
        // Max number of items allowed at Developing
        wipLimits.add(9);
        // Max number of items allowed at Developing
        wipLimits.add(9);
        // Same number as the backlog
        wipLimits.add(87);

        // Backlog
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0)));
        // Developing
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 1, 1, 1, 3, 0, 1, 0, 3, 2, 1, 1, 0, 1, 1, 4, 0, 0, 0, 0, 1, 5, 1, 1, 1, 2, 0, 0, 2, 0, 0, 1, 0, 0, 0, 0, 19, 0)));
        // Code Review
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 3, 0, 0, 1, 0, 0, 2, 3, 0, 4, 1, 2, 3, 3, 2, 1, 7, 3, 1, 1, 3, 0, 1, 1, 1, 2, 2, 6, 3, 2, 9, 0, 9)));
        // Ready to Test
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 6, 2, 0, 3, 5, 2, 0, 5, 1, 2, 0, 4, 0, 0, 0, 0, 2, 0, 2, 5, 0, 0, 1, 11, 14, 14)));
        // Testing
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 2, 1, 1, 3, 0, 0, 2, 1, 0, 3, 0, 0, 0, 0, 0, 2, 4, 4, 0, 2)));
        // Done
        leadTimeCandidatesPerColumn.add(new ArrayList<Integer>(Arrays.asList(0)));

    }
}
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lucascolucci on 12/1/16.
 */
public class Simulator {
    private List<String> columnNames;
    private List<Integer> wipLimits;
    private List<List<Integer>> leadTimeCandidatesPerColumn;
    private List<String> namesOfColumnsAffectedByGlobalWIPLimit;
    private int globalWIPLimit;
    private Board board;

    public Simulator(List<String> columnNames, List<Integer> wipLimits, List<List<Integer>> leadTimeCandidatesPerColumn, List<String> namesOfColumnsAffectedByGlobalWIPLimit, int globalWIPLimit) {
        this.columnNames = columnNames;
        this.wipLimits = wipLimits;
        this.leadTimeCandidatesPerColumn = leadTimeCandidatesPerColumn;
        this.namesOfColumnsAffectedByGlobalWIPLimit = namesOfColumnsAffectedByGlobalWIPLimit;
        this.globalWIPLimit = globalWIPLimit;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public List<Integer> getWipLimits() {
        return wipLimits;
    }

    public void setWipLimits(List<Integer> wipLimits) {
        this.wipLimits = wipLimits;
    }

    public void initializeBoard(){
        board = new Board();

        for(int i = 0; i < columnNames.size(); i++){
            Column column = new Column(columnNames.get(i), wipLimits.get(i), leadTimeCandidatesPerColumn.get(i));
            board.addColumn(column);
        }

        for(int i = 0; i < wipLimits.get(0); i++) {
            Card card = new Card();
            board.getColumns().get(0).addCard(card);
        }
    }

    public void printBoard() {
        List<Column> columns = board.getColumns();
        int maxSize = -1;
        for(Column c : columns){
            if(c.getCards().size()>maxSize)
                maxSize = c.getCards().size();
        }

        for(int i = -2; i < maxSize; i++){
            for(Column column : columns){
                if(i == -2)
                    System.out.print(column.getName() + "   ");
                else if(i == -1) {
                    if(columns.indexOf(column) == 0)
                        System.out.print("      (" + column.getWipLimit() + ")");
                    else
                        System.out.print("               (" + column.getWipLimit() + ")");
                }
                else if(i >= column.getCards().size())
                    System.out.print("                  ");
                else
                    column.getCards().get(i).printCard();
            }
            System.out.println();
        }
    }

    public int run(){
        int totalDays = 0;
        for(int day = 0; !board.allDone(); day++){
            for(int index = board.getColumns().size()-2; index >=0; index--){
                Column column = board.getColumns().get(index);
                if(index == 0 && board.numberOfCardsInColumns(namesOfColumnsAffectedByGlobalWIPLimit) >= globalWIPLimit){
                    continue;
                }


                List<Card> cardsToBeRemoved = new ArrayList<Card>();
                for(Card card : column.getCards()){
                    if(card.getTimeLeft() == 0 && index > 0){
                        card.setTimeLeft(simulateColumnLeadTime(column.getLeadTimeCandidates()));
                    }
                    else {
                        if(index > 0) {
                            card.incrementTotalLeadTime();
                            card.decreaseTimeLeft();
                        }
                        Column nextColumn = board.getColumns().get(index+1);
                        if (card.getTimeLeft() == 0 && nextColumn.hasSpace()) {
                            nextColumn.addCard(card);
                            cardsToBeRemoved.add(card);
                        }
                    }
                }
                column.getCards().removeAll(cardsToBeRemoved);
                totalDays = day;
            }
            //printBoard();
        }
        //System.out.println("Total Elapsed Time - " + totalDays);
        return totalDays;
    }

    private int simulateColumnLeadTime(List<Integer> leadTimeCandidates) {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(leadTimeCandidates.size());
        Integer item = leadTimeCandidates.get(index);
        return item;
    }


}

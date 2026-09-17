/**
 * STUDENT FILE
 *
 * Name: ______________________________
 * AI Code Name: ______________________
 *
 * Strategy Description:
 * Replace this comment with a short explanation of the strategy your AI uses.
 * Your final strategy must be fundamentally different from the sample AIs.
 */
public class MyAI extends CellAI {

    @Override
    public String getAIName() {
        return "MyAI - CHANGE ME";
    }

    @Override
    public Location select(Grid grid) {
        /*
         * Replace this starter strategy.
         *
         * Helpful information:
         *   getID()                     -> your cell ID
         *   grid.getRows()              -> number of rows
         *   grid.getCols()              -> number of columns
         *   grid.getCell(r, c)          -> -1 if dead, otherwise an AI ID
         *   GridFunctions.getNeighbors  -> number of living neighbors
         *   GridFunctions.mostCommonNeighbor -> most common neighboring AI
         *   randomInt(bound)            -> reproducible random integer
         */

        int numMyCells = 0;
        int numOtherCells = 0;
        for (int r = 0; r < grid.getRows(); r++) {
            for (int c = 0; c < grid.getCols(); c++) {
                int cellID = grid.getCell(r, c);
                if (cellID == getID()) {
                    numMyCells++;
                } else if (cellID != -1) {
                    numOtherCells++;
                }
            }
        }


        return new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
    }

    public static Location defend(Grid grid, int myID) {
        Location bestLocation = null;
        for (int r = 0 ; r < grid.getRows(); r++){
            for (int c = 0 ; c < grid.getCols(); c++){
                if (grid.getCell(r, c) == -1){
                    int neighbors = GridFunctions.getNeighbors(c, r, grid);
                    if (neighbors == 2){
                        int mostCommon = GridFunctions.mostCommonNeighbor(c, r, grid);
                        if (mostCommon == myID){
                            bestLocation = new Location(r, c);                                                                         
                        }
                    }
                    }
                }
            }
            return bestLocation;
        }

        /*
        This method is a sample implementation of an attack strategy. It looks for empty cells that have exactly 2 neighbors, 
        and if the most common neighbor is not the AI's own ID, it selects that location as a potential attack move.
        */
        public static Location attack(Grid grid, int myID) {
            int kills = 0;
            Location bestLocation = null;
            for (int r = 0 ; r < grid.getRows(); r++){
                for (int c = 0 ; c < grid.getCols(); c++){
                    if (grid.getCell(r, c) == -1){
                        int neighbors = GridFunctions.getNeighbors(c, r, grid);
                        if (neighbors == 2){
                            int mostCommon = GridFunctions.mostCommonNeighbor(c, r, grid);
                            if (mostCommon != myID && mostCommon != -1 && kills < 2){
                                bestLocation = new Location(r, c);
                                kills = 2;                                                                         
                            }
                        }
                    }
                    
                    
                    if (grid.getCell(r, c) != myID && grid.getCell(r, c) != -1 && GridFunctions.getNeighbors(c, r, grid) == 3){
                        Location free = null;
                        if (grid.getCell(r-1, c) == -1){
                            free = new Location(r-1, c);
                        } else if (grid.getCell(r+1, c) == -1){
                            free = new Location(r+1, c);
                        } else if (grid.getCell(r, c-1) == -1){
                            free = new Location(r, c-1);
                        } else if (grid.getCell(r, c+1) == -1){
                            free = new Location(r, c+1);
                        }
                        if(kills < 4 && free != null){
                            bestLocation = free;
                            kills = 4;
                        }
                    }
                        
                        
                }
            }
            return bestLocation;
        }

}

/**
 * STUDENT FILE
 *
 * Name: ______________________________
 * AI Code Name: ______________________
 *
 * Strategy Description:
 * First Strat commented out
 * Second in progress
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
        
        for(int r = 0; r < grid.getRows(); r++){
            for(int c = 0; c < grid.getCols(); c++){
                int[][] temp = new int[7][7];
                for(int i = 0; i < 7; i++){
                    int row = -3;
                    for(int j = 0; j < 7; j++){
                        int col = -3;
                        temp[i][j] = grid.getCell(r + row, c + col);
                        col++;
                    }
                    row++;
                }

            }
        }

        

        /*Location choice = null;
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

        if (numMyCells < numOtherCells){
            choice = defend(grid, getID());
        }
        else {
            choice = attack(grid, getID());
        }

        return new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
        */


    }

    
    public static void updateGrid(Grid grid){
            boolean[][] newSociety = new boolean[grid.getRows()][grid.getCols()];
            for(int r = 0; r < newSociety.length; r++) {
                for(int c = 0; c < newSociety[0].length; c++){
                    int numNeighbors = neighborCount(r, c);
                    if (society[r][c]) {
                        if(numNeighbors >= 2 && numNeighbors <= 3){
                            newSociety[r][c] = true;
                        }
                        else{
                            newSociety[r][c] = false;
                        }

                    }
                    else {
                        if(numNeighbors == 3){
                            newSociety[r][c] = true;
                        }
                        else{
                            newSociety[r][c] = false;
                        }
                    }

                    }
                }
            
            society = newSociety;
        }
    
    
    
    
    
    
    
    
    
    /* 
    public static Location defend(Grid grid, int myID) {
        Location bestLocation = new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
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
        /* 
        public static Location attack(Grid grid, int myID) {
            int kills = 0;
            Location bestLocation = new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
            for (int r = grid.getRows() - 1 ; r >= 0; r--){
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
                    
                    
                    if (otherIsAlive(grid, r, c, myID) && GridFunctions.getNeighbors(c, r, grid) == 3){
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
                      
                    
                    if(otherIsAlive(grid, r, c, myID)){
                        if(otherIsAlive(grid, r - 1 , c, myID) && otherIsAlive(grid, r - 2, c, myID) && 
                        otherIsAlive(grid, r + 4, c, myID) && otherIsAlive(grid, r + 5, c, myID) && otherIsAlive(grid, r + 6, c, myID) &&
                        otherIsAlive(grid, r + 2, c - 2, myID) && otherIsAlive(grid, r + 2, c - 3, myID) && otherIsAlive(grid, r + 2, c - 4, myID)
                        && otherIsAlive(grid, r + 2, c + 2, myID) && otherIsAlive(grid, r + 2, c + 3, myID) && otherIsAlive(grid, r + 2, c + 4, myID)){
                            if(kills < 12){
                                bestLocation = new Location(r + 2, c);
                                kills = 12;
                            }

                        }
                    }
                        
                }
            }
            return bestLocation;
        }

        public static boolean otherIsAlive(Grid grid, int r, int c, int myID){
            if(grid.getCell(r, c) != -1 && grid.getCell(r, c) != myID){
                return true;
            }
            return false;
        }
        */

}

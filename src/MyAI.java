/**
 * STUDENT FILE
 *
 * Name: Ava Peiser
 * AI Code Name: GA3
 *
 * Strategy Description:
 * My strategy involves evaluating the potential score of each possible move by simulating the grid updates and comparing the scores. 
 * I choose the move that maximizes the difference between the score after the move and the score before the move.
 * First, I create a temporary 7x7 grid around each cell to simulate the local environment.
 * Then, I update this temporary grid three times to allow the effects of the move to show.
 * Finally, I calculate the score difference between the original and updated grids to determine the best move.
 */
public class MyAI extends CellAI {

    @Override
    public String getAIName() {
        return "GA3";
    }

    @SuppressWarnings("null")
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

        
        Location choice = new Location(0, 0);
        int maxScore = 0;
        for(int r = 0; r < grid.getRows(); r++){
            for(int c = 0; c < grid.getCols(); c++){
                int[][] temp = new int[7][7];
                int row = -3;
                
                for(int i = 0; i < 7; i++){
                    int col = -3;
                    for(int j = 0; j < 7; j++){
                        if(r + row >= 0 && r + row < grid.getRows() && c + col >= 0 && c + col < grid.getCols()){
                            temp[i][j] = grid.getCell(r + row, c + col);
                        }
                        else{
                            // This is where we fill in the missing cells with null values. This will prevent the array out of bounds exception when we update the grid.
                            // however, this will cause a null pointer exception when we try to update the grid. 
                            // We will need to fix this by checking if the cell is null before we update the grid.
                            temp[i][j] = -1;
                        }
                        col++;
                    }
                    row++;
                }
                Grid tempGrid = new Grid(temp);
                tempGrid = updateGrid(tempGrid);
                tempGrid = updateGrid(tempGrid);
                tempGrid = updateGrid(tempGrid);
                int scoreSame = scoreKeeper(tempGrid, getID());
                tempGrid = new Grid(temp);
                
                if(grid.getCell(r, c) == -1){
                    temp[3][3] = getID();
                    tempGrid = new Grid(temp);
                }
                else{
                    temp[3][3] = -1;
                    tempGrid = new Grid(temp);
                }
                tempGrid = updateGrid(tempGrid);
                tempGrid = updateGrid(tempGrid);
                tempGrid = updateGrid(tempGrid);
                int scoreChanged = scoreKeeper(tempGrid, getID());
                int score = scoreChanged - scoreSame;
                if(score > maxScore){
                    maxScore = score;
                    if(r >= 0 && r < grid.getRows() && c >= 0 && c < grid.getCols() ){
                        choice = new Location(r, c);
                    }
                }
            }
        }
        System.out.println("MyAI: " + choice);
        return choice;

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

    public static Grid updateGrid(Grid grid){
            int[][] newSociety = new int[grid.getRows()][grid.getCols()];
            for(int r = 0; r < newSociety.length; r++) {
                for(int c = 0; c < newSociety[0].length; c++){
                    int numNeighbors =  GridFunctions.getNeighbors(r, c, grid);
                    if (grid.getCell(r, c) != -1) {
                        if(numNeighbors >= 2 && numNeighbors <= 3){
                            newSociety[r][c] = grid.getCell(r, c);
                        }
                        else{
                            newSociety[r][c] = -1;
                        }
                    } else {
                        if(numNeighbors == 3){
                            newSociety[r][c] = GridFunctions.mostCommonNeighbor(r, c, grid);
                        }
                        else{
                            newSociety[r][c] = -1;
                        }
                    }
                }
            
        }
        return new Grid(newSociety);
    }

        public static int scoreKeeper(Grid grid, int myID){
            int score = 0;
            for(int r = 0; r < grid.getRows(); r++){
                for(int c = 0; c < grid.getCols(); c++){
                    if(grid.getCell(r, c) == myID){
                        score++;
                    }
                    if(grid.getCell(r, c) != -1 && grid.getCell(r, c) != myID){
                        score--;
                    }
                }
            }
            return score;
        }
    
    
    
    
    
    
    
    
    
    /* 
    public static Location defend(Grid grid, int myID) {
        Location bestLocation = new Location(randomInt(grid.getRows()), randomInt(grid.getCols()));
        for (int r = 0 ; r < grid.getRows(); r++){
            for (int c = 0 ; c < grid.getCols(); c++){
                if (grid.getCell(r, c) == -1){
                    int neighbors = GridFunctions.getNeighbors(r, c, grid);
                    if (neighbors == 2){
                        int mostCommon = GridFunctions.mostCommonNeighbor(r, c, grid);
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

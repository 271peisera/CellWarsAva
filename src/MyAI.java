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
    
        public static boolean otherIsAlive(Grid grid, int r, int c, int myID){
            if(grid.getCell(r, c) != -1 && grid.getCell(r, c) != myID){
                return true;
            }
            return false;
        }
        */

}

package com.github.paulranshaw.amazingproject;

/**
 * Class containing methods to handle maze generation
 */
public class MazeGen {
    // Values to be stored in 2D array based on what each cell represents
    static int room = 1;
    static int wall = 0;
    static int[][] maze;

    // Is this needed?
    /**
     * Method for creating maze from given params
     *
     * @param columns as columns for the maze
     * @param rows as the rows for the maze
     * @return populated 2D maze array
     */
    public static int[][] getMaze(int rows, int columns) {
        // Possibly redundant?
        int[][] freshMaze = createMaze(rows, columns);
        return freshMaze;
    }

    /**
     * Method for creating maze from given params
     *
     * @param columns as columns for the maze
     * @param rows as the rows for the maze
     * @return populated 2D maze array
     */
    public static int[][] createMaze(int rows, int columns) {
        maze = new int[rows][columns];
        // Counters for walls and rooms
        int emptyCounter = 0;
        int wallCounter = 0;
        // Positions of the walls between rooms
        int[] wallRow = new int[(rows * columns) / 2];
        int[] wallCol = new int[(rows * columns) / 2];
        // Set whole maze to be walls initially
        for (int i=0; i < rows; i++){
            for (int j=0; j < columns; j++){
                maze[i][j] = wall;
            }
        }
        // Make a grid of empty rooms with walls between and give each room a unique negative ID using emptyCounter
        for (int i=1; i < rows - 1; i=i + 2){
            for (int j=1; j < columns - 1; j=j + 2){
                emptyCounter = emptyCounter + 1;
                maze[i][j] = -emptyCounter;
                // Record info about the walls to the right of and below this room
                if (i < rows - 2){
                    wallRow[wallCounter] = i + 1;
                    wallCol[wallCounter] = j;
                    wallCounter = wallCounter + 1;
                }
                if (j < columns - 2){
                    wallRow[wallCounter] = i;
                    wallCol[wallCounter] = j + 1;
                    wallCounter = wallCounter + 1;
                }
            }
        }
        // Remove walls randomly unless doing so would create a loop
        for (int i=wallCounter - 1; i > 0; i--){
            int randomInt = (int)(Math.random() * i);
            removeWall(wallRow[randomInt], wallCol[randomInt]);
            wallRow[randomInt] = wallRow[i];
            wallCol[randomInt] = wallCol[i];
            }
        // Replace negative values in the maze with empty
        for (int i=1; i < rows - 1; i++){
            for (int j=1; j < columns - 1; j++){
                if (maze[i][j] < 0){
                    maze[i][j] = room;
                }
            }
        }
        return maze;
    }

    /**
     * Method for removing walls from populated maze
     *
     * @param row as number of rows
     * @param col as number of columns
     */
    static synchronized void removeWall(int row, int col){
        // Checks if the wall is horizontal and that rooms either side of the wall don't have the same code
        if (row % 2 == 1 && maze[row][col - 1] != maze[row][col + 1]){
            // Replaces all wall codes with the new one and sets wall as part of the room
            swapRoomCodes(row, col - 1, maze[row][col - 1], maze[row][col+1]);
            maze[row][col] = maze[row][col+1];
        }
        // Same as above but for vertical rooms
        else if (row%2==0 && maze[row-1][col] != maze[row+1][col]){
            swapRoomCodes(row-1,col, maze[row-1][col], maze[row+1][col]);
            maze[row][col] = maze[row+1][col];
        }
    }

    /**
     * Recursive function that changes all room codes toReplace into replaceWith
     *
     * @param row as row
     * @param col as column
     * @param toReplace as toReplace
     * @param replaceWith as ReplaceWith
     */
    private static void swapRoomCodes(int row, int col, int toReplace, int replaceWith) {
        if (maze[row][col] == toReplace){
            maze[row][col] = replaceWith;
            swapRoomCodes(row + 1,col, toReplace, replaceWith);
            swapRoomCodes(row - 1,col, toReplace, replaceWith);
            swapRoomCodes(row, col + 1, toReplace, replaceWith);
            swapRoomCodes(row, col - 1, toReplace, replaceWith);
        }
    }
}

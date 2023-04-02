package com.github.paulranshaw.amazingproject;

public class MazeGen {
    //values to be stored in 2d array based on what each cell represents
    static int wall = 0;
    static int room = 1;
    //temp variables for size, will want user to input size and pass as argument
    static int rows = 41;
    static int columns = 51;
    static int[][] maze;


    public static int[][] getMaze() {
        int[][] freshMaze = createMaze();
        return freshMaze;
    }

    public static int[][] createMaze() {

        maze = new int[rows][columns];
        //counters for walls and rooms
        int emptyCounter = 0;
        int wallCounter = 0;
        //positions of the walls between rooms
        int[] wallRow = new int[(rows*columns)/2];
        int[] wallCol = new int[(rows*columns)/2];
        //set whole maze to be walls initially
        for (int i=0;i<rows;i++){
            for (int j=0;j<columns;j++){
                maze[i][j] = wall;
            }
        }

        //make a grid of empty rooms with walls between and give each room a unique negative ID using emptyCounter
        for (int i=1;i<rows-1;i=i+2){
            for (int j=1;j<columns-1;j=j+2){
                emptyCounter = emptyCounter+1;
                maze[i][j] = -emptyCounter;
                //record info about the walls to the right of and below this room
                if (i<rows-2){
                    wallRow[wallCounter] = i+1;
                    wallCol[wallCounter] = j;
                    wallCounter = wallCounter+1;
                }
                if (j<columns-2){
                    wallRow[wallCounter] = i;
                    wallCol[wallCounter] = j+1;
                    wallCounter = wallCounter+1;
                }
            }
        }

        //remove walls randomly unless doing so would create a loop
        for (int i=wallCounter-1;i>0;i--){
            int randomInt = (int)(Math.random()*i);
            removeWall(wallRow[randomInt],wallCol[randomInt]);
            wallRow[randomInt] = wallRow[i];
            wallCol[randomInt] = wallCol[i];
            }
        //replace negative values in the maze with empty
        for (int i=1;i<rows-1;i++){
            for (int j=1;j<columns-1;j++){
                if (maze[i][j]<0){
                    maze[i][j] = room;
                }
            }
        }



        return maze;
    }

    static synchronized void removeWall(int row, int col){
        //checks if the wall is horizontal and that rooms either side of the wall don't have the same code
        if (row%2==1 && maze[row][col-1] != maze[row][col+1]){
            //replaces all wall codes with the new one and sets wall as part of the room
            swapRoomCodes(row,col-1,maze[row][col-1],maze[row][col+1]);
            maze[row][col] = maze[row][col+1];
        }
        //same as above but for vertical rooms
        else if (row%2==0 && maze[row-1][col] != maze[row+1][col]){
            swapRoomCodes(row-1,col,maze[row-1][col],maze[row+1][col]);
            maze[row][col] = maze[row+1][col];
        }
    }

    private static void swapRoomCodes(int row, int col, int toReplace, int replaceWith){
        //recursive function that changes all room codes toReplace into replaceWith
        if (maze[row][col]==toReplace){
            maze[row][col] = replaceWith;
            swapRoomCodes(row+1,col,toReplace,replaceWith);
            swapRoomCodes(row-1,col,toReplace,replaceWith);
            swapRoomCodes(row,col+1,toReplace,replaceWith);
            swapRoomCodes(row,col-1,toReplace,replaceWith);
        }
    }
}

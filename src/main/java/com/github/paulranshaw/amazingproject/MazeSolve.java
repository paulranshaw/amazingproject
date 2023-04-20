package com.github.paulranshaw.amazingproject;
import com.github.paulranshaw.amazingproject.commands.Solve;

import static java.lang.Thread.sleep;

public class MazeSolve {
    static int[][] maze;
    static int rows;
    static int cols;
    static int ROOM = 1;
    static int VISITED = 2;
    static int PATH = 3;

    public static int[][] solveMaze(int[][] passedMaze, int row, int col) {
        maze = passedMaze;
        rows = row;
        cols = col;
        solveRecurse(1,1);
        try {
            sleep(5000);
        }catch (InterruptedException e){
        }
        Solve.maze = maze;
        Solve.row = rows;
        Solve.col = cols;
        return maze;
    }
    public static boolean solveRecurse(int row, int col){
        if (maze[row][col] == ROOM) {
            maze[row][col] = PATH; // add this cell to the path
            if (row == rows - 2 && col == cols - 2)
                return true; // path has reached goal

            if (solveRecurse(row - 1, col) || // try to solve maze by extending path
                    solveRecurse(row, col - 1) || // in each possible direction
                    solveRecurse(row + 1, col) ||
                    solveRecurse(row, col + 1))
                return true;
            // maze can't be solved from this cell, so backtrack out of the cell
            maze[row][col] = VISITED; // mark cell as having been visited


        }
        return false;
    }
}

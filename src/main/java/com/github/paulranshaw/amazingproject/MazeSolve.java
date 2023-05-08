package com.github.paulranshaw.amazingproject;

import com.github.paulranshaw.amazingproject.commands.Solve;
import static java.lang.Thread.sleep;

/*
 * README NOTICE
 *
 * The following reference has been used as guidance for completing the mathematical
 * aspect of maze manipulation, any interaction with Minecraft itself and Forge we have
 * implemented from scratch ourselves.
 *
 * OpenGenus IQ: Computing Expertise & Legacy. (2023). Maze Generator and Solver in Java. [online] Available at: https://iq.opengenus.org/maze-generator-in-java/ [Accessed 20 Apr. 2023].
 */

public class MazeSolve {
    static int[][] maze;
    static int rows;
    static int cols;
    static int PATH = 3;
    static int ROOM = 1;
    static int VISITED = 2;

    /**
     *
     * @param passedMaze as the given maze
     * @param row as number of rows
     * @param col as number of columns
     * @return maze
     */
    public static int[][] solveMaze(int[][] passedMaze, int row, int col) {
        maze = passedMaze;
        rows = row;
        cols = col;
        solveRecurse(1,1);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Solve.maze = maze;
        Solve.row = rows;
        Solve.col = cols;
        return maze;
    }

    /**
     * Method to recursively create path to goal state for generated maze structure
     *
     * @param row as number of rows of generated maze
     * @param col as number of columns of generated maze
     * @return populated maze array
     */
    public static boolean solveRecurse(int row, int col){
        if (maze[row][col] == ROOM) {
            maze[row][col] = PATH;
            if (row == rows - 2 && col == cols - 2)
                return true;
            if (solveRecurse(row - 1, col) ||
                    solveRecurse(row, col - 1) ||
                    solveRecurse(row + 1, col) ||
                    solveRecurse(row, col + 1))
                return true;
            maze[row][col] = VISITED;
        }
        return false;
    }
}

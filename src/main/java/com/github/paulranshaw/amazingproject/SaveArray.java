package com.github.paulranshaw.amazingproject;

import net.minecraft.util.math.BlockPos;

public class SaveArray {
    public int[][] saveArray;
    public int rows;
    public int cols;
    public BlockPos pos;
    public SaveArray (int[][] mazeArray,int row, int col, BlockPos posn){
        saveArray=mazeArray;
        rows = row;
        cols = col;
        pos = posn;
    }
}

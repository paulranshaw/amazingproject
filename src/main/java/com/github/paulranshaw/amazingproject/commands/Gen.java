package com.github.paulranshaw.amazingproject.commands;

import com.github.paulranshaw.amazingproject.MazeGen;
import com.github.paulranshaw.amazingproject.MazeSolve;
import joptsimple.internal.Strings;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/**
 * Class for /gen command functionality
 */
public class Gen extends CommandBase {
    /**
     * Returns the name of the command
     *
     * @return command name
     */
    @Override
    public String getName() {
        return "gen";
    }

    /**
     * Returns lang usage description for this command
     *
     * @param sender as instance that has tried executing the command
     * @return null as lang not yet set
     */
    @Override
    public String getUsage(ICommandSender sender) {
        return null;
    }

    /**
     * Returns the permission level of a user required to execute the command
     *
     * @return permission level 0 to make the command explicitly usable by all users
     */
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    /**
     * Makes permission of the user to be true
     * @param server
     * @param sender
     * @return allows command to be usable by all users
     */
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender){
        return true;
    }
    /**
     * Executes command functionality, in this case placing a block at
     * a preset position for testing purposes
     *
     * @param server as the game server
     * @param sender as the instance that has tried executing the command
     * @param args as any arguments which may have been passed into the command
     */


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args){
        // Take rows and columns arguments for use within generation
        try{
            int rows = Integer.parseInt(args[0]);
            int columns = Integer.parseInt(args[1]);

            // Try and catch to go here

            // Validate as being natural and params being odd
            if ((rows > 0 && columns > 0) && (rows % 2 != 0 && columns % 2 != 0)) {
        /* Set the position of the block to be placed, this position is slightly east of the players location
        so that the maze doesn't generate on top of them
         */
                BlockPos pos = sender.getPosition();
                pos = pos.add(3, 0, 0);
                // Check that the command sender is a player and not other instance
                if (sender instanceof EntityPlayer) {
                    // Grab the world which the player is currently within
                    World world = sender.getEntityWorld();
                    // Generate maze from MazeGen class, pass rows and columns from args
                    int[][] maze = MazeGen.createMaze(rows, columns);
            /* Iterates across the maze, changing the block state at that position to stone
            if it is a wall, or empty if it is a room. Maze is currently 2 blocks high.
             */
                    for (int i = 0; i < rows; i++) {
                        sender.sendMessage(new TextComponentString("Generating: "+MakeProgressBar((float) (i+1),(float) rows)));
                        for (int j = 0; j < columns; j++) {
                            if (maze[i][j] == 0) {
                                // Set as stone brick as wall
                                world.setBlockState(pos.add(i, 0, j), Blocks.STONEBRICK.getDefaultState());
                                world.setBlockState(pos.add(i, 1, j), Blocks.STONEBRICK.getDefaultState());
                            } else if (maze[i][j] == 1) {
                                // Set as air block so room can be explored
                                world.setBlockState(pos.add(i, 0, j), Blocks.AIR.getDefaultState());
                                world.setBlockState(pos.add(i, 1, j), Blocks.AIR.getDefaultState());
                            }
                            // Adds a floor to the maze
                            world.setBlockState(pos.add(i, -1, j), Blocks.STONE.getDefaultState());
                        }
                    }
                    // opens the start of the maze
                    world.setBlockState(pos.add(0, 1, 1), Blocks.AIR.getDefaultState());
                    world.setBlockState(pos.add(0, 0, 1), Blocks.AIR.getDefaultState());

                    // opens the end of the maze
                    world.setBlockState(pos.add(rows-1,1, columns-2), Blocks.AIR.getDefaultState());
                    world.setBlockState(pos.add(rows-1, 0, columns-2), Blocks.AIR.getDefaultState());

                    //adds short tunnel to the end of the maze
                    for(int i = 0;i<3;i++){
                        world.setBlockState(pos.add(rows+i, 1, columns-1), Blocks.STONEBRICK.getDefaultState());
                        world.setBlockState(pos.add(rows+i, 0, columns-1), Blocks.STONEBRICK.getDefaultState());
                        world.setBlockState(pos.add(rows+i, 1, columns-3), Blocks.STONEBRICK.getDefaultState());
                        world.setBlockState(pos.add(rows+i, 0, columns-3), Blocks.STONEBRICK.getDefaultState());
                        world.setBlockState(pos.add(rows+i, -1, columns-2), Blocks.STONE.getDefaultState());
                    }

                    //builds small prize room
                    BlockPos tempPos = pos.add(rows+3,0,columns-4);
                    for(int i = 0;i<=5;i++){
                        for(int j = 0;j<=5;j++){
                            world.setBlockState(tempPos.add(i, -1, j), Blocks.STONE.getDefaultState());
                            if (i==0||i==5||j==0||j==5){
                                world.setBlockState(tempPos.add(i, 0, j), Blocks.STONEBRICK.getDefaultState());
                                world.setBlockState(tempPos.add(i, 1, j), Blocks.STONEBRICK.getDefaultState());
                            }
                            if ((i==1||i==4)&&(j==1||j==4)){
                                world.setBlockState(tempPos.add(i, 0, j), Blocks.GOLD_BLOCK.getDefaultState());
                                world.setBlockState(tempPos.add(i, 1, j), Blocks.GOLD_BLOCK.getDefaultState());
                            }
                        }
                    }
                    //opens the prize room
                    world.setBlockState(tempPos.add(0,1, 2), Blocks.AIR.getDefaultState());
                    world.setBlockState(tempPos.add(0, 0, 2), Blocks.AIR.getDefaultState());


                    // calls the MazeSolve class to solve the maze and stores maze position in Solve class
                    int[][] solvedMaze = MazeSolve.solveMaze(maze,rows,columns);
                    Solve.playerPos = pos;

                }
            }
            else{
                sender.sendMessage(new TextComponentString("This command needs to be in format /gen x y where x and y are odd numbers"));
            }
        }
        catch (Exception e){
            sender.sendMessage(new TextComponentString("This command needs to be in format /gen x y where x and y are odd numbers"));

        }
    }
    public String MakeProgressBar(float current,float max){
        float percent=current/max;
        int progressbar=(int) (100*percent);

        return "§2"+Strings.repeat('|',progressbar)+"§7"+Strings.repeat('|',100-progressbar);
    }
}

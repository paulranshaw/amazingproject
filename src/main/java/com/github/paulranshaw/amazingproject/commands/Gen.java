package com.github.paulranshaw.amazingproject.commands;

import com.github.paulranshaw.amazingproject.MazeGen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
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
     * Executes command functionality, in this case placing a block at
     * a preset position for testing purposes
     *
     * @param server as the game server
     * @param sender as the instance that has tried executing the command
     * @param args as any arguments which may have been passed into the command
     */
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        // Take rows and columns arguments for use within generation
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
            }
        }
    }
}

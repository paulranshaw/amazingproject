package com.github.paulranshaw.amazingproject.commands;

import joptsimple.internal.Strings;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/*
 * README NOTICE
 *
 * The following reference has been used as guidance for completing the mathematical
 * aspect of maze manipulation, any interaction with Minecraft itself and Forge we have
 * implemented from scratch ourselves.
 *
 * OpenGenus IQ: Computing Expertise & Legacy. (2023). Maze Generator and Solver in Java. [online] Available at: https://iq.opengenus.org/maze-generator-in-java/ [Accessed 20 Apr. 2023].
 */

/**
 * Class for /solve command functionality
 */
public class Solve extends CommandBase {
    public static BlockPos playerPos;
    public static int[][] maze;
    public static int col;
    public static int row;

    /**
     * Returns the name of the command
     *
     * @return command name
     */
    @Override
    public String getName() {
        return "solve";
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
     *
     * @param server as server instance the sender is within
     * @param sender as sender executing the command
     * @return allows command to be usable by all users
     */
    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    /**
     * Executes command functionality, in this case iterating through the maze array which contains a path
     * to goal state and then printing this path into the game world
     *
     * @param server as the game server
     * @param sender as the instance that has tried executing the command
     * @param args as any arguments which may have been passed into the command
     */
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args){
        int[][] maze1 = maze;
        int rows = row;
        int cols = col;
        World world = sender.getEntityWorld();
        for (int i = 0; i < rows; i++) {
            sender.sendMessage(new TextComponentString("Solving: "+makeProgressBar((float) (i+1),(float) rows)));
            for (int j = 0; j < cols; j++) {
                if (maze1[i][j] == 3) {
                    // Place Redstone onto floor of maze to show the user path to goal state
                    world.setBlockState(playerPos.add(i, 0, j), Blocks.REDSTONE_WIRE.getDefaultState());
                }
            }
        }
    }

    /**
     * Method to output progress as string in chat whilst generation ongoing
     *
     * @param current as current item
     * @param max as goal item
     * @return string progress bar
     */
    public String makeProgressBar(float current, float max) {
        float percent = current / max;
        int progressbar = (int) (100 * percent);
        return "§2" + Strings.repeat('|', progressbar) + "§7" + Strings.repeat('|', 100 - progressbar);
    }
}

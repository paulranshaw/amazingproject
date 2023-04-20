package com.github.paulranshaw.amazingproject.commands;

import com.github.paulranshaw.amazingproject.MazeGen;
import com.github.paulranshaw.amazingproject.SaveArray;
import joptsimple.internal.Strings;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import java.io.*;
import java.util.ArrayList;
import net.minecraftforge.fml.client.config.GuiConfigEntries;

/**
 * Class for /gen command functionality
 */
public class Solve extends CommandBase {

    SaveArray savedObject;
    public static int[][] maze;
    public static int col;
    public static int row;
    public static BlockPos playerPos;
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
        int[][] maze1 = maze;
        int rows = row;
        int cols = col;
        World world = sender.getEntityWorld();

        for (int i = 0; i < rows; i++) {
            sender.sendMessage(new TextComponentString("Generating: "+MakeProgressBar((float) (i+1),(float) rows)));
            for (int j = 0; j < cols; j++) {
                if (maze1[i][j] == 3) {
                    // Set as stone brick as wall
                    world.setBlockState(playerPos.add(i, 0, j), Blocks.REDSTONE_WIRE.getDefaultState());
                }
            }
        }

    }
    public String MakeProgressBar(float current,float max){
        float percent=current/max;
        int progressbar=(int) (100*percent);

        return "§2"+Strings.repeat('|',progressbar)+"§7"+Strings.repeat('|',100-progressbar);
    }
}

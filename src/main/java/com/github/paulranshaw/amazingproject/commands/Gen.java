package com.github.paulranshaw.amazingproject.commands;

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
        /* Set the position of the block to be placed, this position is preset for a specific
         * world which the command has been tested within instead of near to the player in
         * any world
         */
        BlockPos pos = new BlockPos(-128, 70, 260);
        // Check that the command sender is a player and not other instance
        if (sender instanceof EntityPlayer) {
            // Grab the world which the player is currently within
            World world = sender.getEntityWorld();
            /* Change the block state of the block at the BlockPos to be the following
               which can have additional properties
             */
            world.setBlockState(pos, Blocks.BEDROCK.getDefaultState());
        }
    }
}

package com.github.paulranshaw.amazingproject;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = AMazingProject.MODID, name = AMazingProject.NAME, version = AMazingProject.VERSION)
public class AMazingProject
{
    public static final String MODID = "amazingproject";
    public static final String NAME = "AMazingProject";
    public static final String VERSION = "1.0";
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }
}

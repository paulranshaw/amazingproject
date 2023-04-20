package com.github.paulranshaw.amazingproject;

import com.github.paulranshaw.amazingproject.commands.Gen;
import com.github.paulranshaw.amazingproject.commands.Solve;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
// import org.apache.logging.log4j.Logger;


/**
 * Main class for handling the loading of the modification
 * and registering of commands
 */
// Define modification details
@Mod(modid = AMazingProject.MODID, name = AMazingProject.NAME, version = AMazingProject.VERSION)
public class AMazingProject {
    public static final String MODID = "amazingproject";
    public static final String NAME = "AMazingProject";
    public static final String VERSION = "1.0";
//    private static Logger logger;

    /**
     * Event handler to be called to handle
     * code to be executed before FML initialization
     *
     * @param event as the event that has occurred
     */
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
//        // Set logger which can be used to output into logs
//        logger = event.getModLog();
    }

    /**
     * Event handler to be called once FML has
     * initialized
     *
     * @param event as the event that has occurred
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    /**
     * Event handler to be called once the server has
     * started
     *
     * @param event as the event that has occurred
     */
    @EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        // Register gen command to enable usage
        event.registerServerCommand(new Gen());
        event.registerServerCommand(new Solve());
    }
}

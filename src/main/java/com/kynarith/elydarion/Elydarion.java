package com.kynarith.elydarion;

import com.kynarith.elydarion.block.ModBlocks;
import com.kynarith.elydarion.item.ModItems;
import com.kynarith.elydarion.util.ElydarionConfig;
import com.kynarith.elydarion.util.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Elydarion.MOD_ID)
public class Elydarion
{
    // Define MOD_ID to use all of this mod
    public static final String MOD_ID = "elydarion";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroup ELYDARION_BLOCKS = new ItemGroup("elydarionBlocks") {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModBlocks.COPPER_ORE.get());
        }
    };

    public static final ItemGroup ELYDARION_ITEMS = new ItemGroup("elydarionItems") {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.SILVER_COIN.get());
        }
    };

    public static final ItemGroup ELYDARION_COMBAT = new ItemGroup("elydarionCombat") {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(ModItems.COPPER_SWORD.get());
        }
    };

    public static final ItemGroup ELYDARION_TOOLS = new ItemGroup("elydarionTools") {
        @Override
        public ItemStack createIcon() { return new ItemStack(ModItems.COPPER_AXE.get()); }
    };

    public Elydarion()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ElydarionConfig.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ElydarionConfig.SERVER_CONFIG);

        //Register items and blocks
        Registration.register();
        ModItems.register();
        ModBlocks.register();

        //Call differents events to minecraft
        //MinecraftForge.EVENT_BUS.register(new ModEvents());

        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ElydarionConfig.LoadConfigFile(ElydarionConfig.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("elydarion-client.toml").toString());
        ElydarionConfig.LoadConfigFile(ElydarionConfig.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("elydarion-server.toml").toString());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}

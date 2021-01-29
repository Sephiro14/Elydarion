package com.kynarith.elydarion;

import com.kynarith.elydarion.block.ModBlocks;
import com.kynarith.elydarion.block.ModFuilds;
import com.kynarith.elydarion.item.ModItems;
import com.kynarith.elydarion.proxies.ClientProxy;
import com.kynarith.elydarion.proxies.IProxy;
import com.kynarith.elydarion.proxies.ServerProxy;
import com.kynarith.elydarion.util.ElydarionConfig;
import com.kynarith.elydarion.util.Registration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
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

    public static IProxy proxy;

    public Elydarion()
    {
        proxy = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> ServerProxy::new);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        registerModAdditions();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        registerConfigs();

        proxy.init();

        loadConfigs();

    }

    private void registerConfigs()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ElydarionConfig.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ElydarionConfig.SERVER_CONFIG);
    }

    private void loadConfigs()
    {
        ElydarionConfig.LoadConfigFile(ElydarionConfig.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("elydarion-client.toml").toString());
        ElydarionConfig.LoadConfigFile(ElydarionConfig.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("elydarion-server.toml").toString());
    }

    private void registerModAdditions()
    {
        //init the registration of our additions
        Registration.init();

        //register items, blocks & etc... added by the mod
        ModItems.register();
        ModBlocks.register();
        ModFuilds.register();

        //Call differents events to minecraft
        //MinecraftForge.EVENT_BUS.register(new ModEvents());
    }
}

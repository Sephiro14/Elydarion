package com.kynarith.elydarion.proxies;

import com.kynarith.elydarion.Elydarion;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Elydarion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientProxy implements IProxy
{

    @Override
    public void init()
    {
        //RenderItems & Crops
        //RenderTypeLookup.setRenderLayer(ModBlocks.EXAMPLE_CROP.get(), RenderType.getCutout());

    }

    @Override
    public World getClientWorld()
    {
        return Minecraft.getInstance().world;
    }
}

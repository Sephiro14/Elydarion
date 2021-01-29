package com.kynarith.elydarion.block;

import com.kynarith.elydarion.Elydarion;
import com.kynarith.elydarion.item.ModItems;
import com.kynarith.elydarion.util.Registration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;

public class ModFuilds
{
    public static final ResourceLocation MILK_STILL_RL = new ResourceLocation(Elydarion.MOD_ID, "block/milk_still");
    public static final ResourceLocation MILK_FLOWING_RL = new ResourceLocation(Elydarion.MOD_ID, "block/milk_flowing");
    public static final ResourceLocation MILK_OVERLAY_RL = new ResourceLocation(Elydarion.MOD_ID, "block/milk_overlay");

    public static final RegistryObject<FlowingFluid> MILK_FLUID = Registration.FLUIDS.register("milk_fluid", () -> new ForgeFlowingFluid.Source(ModFuilds.MILK_PROPERTIES));
    public static final RegistryObject<FlowingFluid> MILK_FLOWING = Registration.FLUIDS.register("milk_flowing", () -> new ForgeFlowingFluid.Flowing(ModFuilds.MILK_PROPERTIES));
    public static final ForgeFlowingFluid.Properties MILK_PROPERTIES = new ForgeFlowingFluid.Properties(() -> MILK_FLUID.get(), () -> MILK_FLOWING.get(), FluidAttributes.builder(MILK_STILL_RL, MILK_FLOWING_RL)
            .density(15)
            .luminosity(2)
            .rarity(Rarity.RARE)
            .sound(SoundEvents.ITEM_HONEY_BOTTLE_DRINK)
            .overlay(MILK_OVERLAY_RL)
            .viscosity(5))
            .slopeFindDistance(3)
            .levelDecreasePerBlock(3)
            .block(() -> ModFuilds.MILK_BLOCK.get()).bucket(() -> ModItems.MILK_BUCKET.get());

    public static final RegistryObject<FlowingFluidBlock> MILK_BLOCK = Registration.BLOCKS.register("milk",
            () -> new FlowingFluidBlock(() -> ModFuilds.MILK_FLUID.get(),
                    AbstractBlock.Properties.create(Material.WATER).doesNotBlockMovement().hardnessAndResistance(100f).noDrops()));

    public static void register()
    {

    }
}

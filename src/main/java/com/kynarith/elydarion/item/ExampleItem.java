package com.kynarith.elydarion.item;

import com.kynarith.elydarion.Elydarion;
import com.kynarith.elydarion.util.KeyboardUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

//Example for Food Items
public class ExampleItem extends Item
{
    public ExampleItem()
    {
        super(new Properties().group(Elydarion.ELYDARION_ITEMS)
                .food(new Food.Builder()
                        .hunger(5)
                        .saturation(1.5f)
                        .effect(() -> new EffectInstance(Effects.GLOWING, 300, 1), 0.5f) // 0.5f => 50%
                        .build()));
    }

    //Transform item to own Fuel
    @Override
    public int getBurnTime(ItemStack itemStack)
    {
        return 600;
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag)
    {
        if(KeyboardUtil.isHoldingShift())
        {
            tooltip.add(new StringTextComponent("Je suis un exemple"));
        }
        else
        {
            tooltip.add(new StringTextComponent("Hold" + "\u00A7e" + " SHIFT " + "\u00A77" + "pour plus d'information !"));
        }

        super.addInformation(stack, world, tooltip, flag);
    }
}

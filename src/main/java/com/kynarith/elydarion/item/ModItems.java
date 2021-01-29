package com.kynarith.elydarion.item;

import com.kynarith.elydarion.Elydarion;
import com.kynarith.elydarion.block.ModBlocks;
import com.kynarith.elydarion.block.ModFuilds;
import com.kynarith.elydarion.util.Registration;
import net.minecraft.client.audio.Sound;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

public class ModItems
{
    /* COINS */
    public static final RegistryObject<Item> COPPER_COIN = Registration.ITEMS.register("copper_coin",
            () -> new Item(new Item.Properties().group(Elydarion.ELYDARION_ITEMS).maxStackSize(64)));

    public static final RegistryObject<Item> SILVER_COIN = Registration.ITEMS.register("silver_coin",
            () -> new Item(new Item.Properties().group(Elydarion.ELYDARION_ITEMS).maxStackSize(64)));

    public static final RegistryObject<Item> GOLD_COIN = Registration.ITEMS.register("gold_coin",
            () -> new Item(new Item.Properties().group(Elydarion.ELYDARION_ITEMS).maxStackSize(64)));


    /* INGOT */
    public static final RegistryObject<Item> COPPER_INGOT = Registration.ITEMS.register("copper_ingot",
            () -> new Item(new Item.Properties().group(Elydarion.ELYDARION_ITEMS).maxStackSize(64)));


    /* EXAMPLE ITEM */
    //Item create before, we just call ExampleItem with his custom parameters
    public static final RegistryObject<Item> EXAMPLE_ITEM = Registration.ITEMS.register(("example_item"),
            () -> new ExampleItem());

    /* TOOLS */
    public static final RegistryObject<Item> COPPER_SHOVEL = Registration.ITEMS.register("copper_shovel",
            () -> new ShovelItem(ModItemTier.COPPER, 2f, -3f, new Item.Properties()
                    .defaultMaxDamage(200)
                    .addToolType(ToolType.SHOVEL, 2)
                    .group(Elydarion.ELYDARION_TOOLS)));

    public static final RegistryObject<Item> COPPER_PICKAXE = Registration.ITEMS.register("copper_pickaxe",
            () -> new ShovelItem(ModItemTier.COPPER, 1.5f, -2.8f, new Item.Properties()
                    .defaultMaxDamage(200)
                    .addToolType(ToolType.PICKAXE, 2)
                    .group(Elydarion.ELYDARION_TOOLS)));

    public static final RegistryObject<Item> COPPER_AXE = Registration.ITEMS.register("copper_axe",
            () -> new ShovelItem(ModItemTier.COPPER, 7f, -3.2f, new Item.Properties()
                    .defaultMaxDamage(200)
                    .addToolType(ToolType.AXE, 2)
                    .group(Elydarion.ELYDARION_TOOLS)));

    public static final RegistryObject<Item> COPPER_HOE = Registration.ITEMS.register("copper_hoe",
            () -> new ShovelItem(ModItemTier.COPPER, -1f, -1.5f, new Item.Properties()
                    .defaultMaxDamage(200)
                    .addToolType(ToolType.HOE, 2)
                    .group(Elydarion.ELYDARION_TOOLS)));

    /* WEAPONS */
    public static final RegistryObject<Item> COPPER_SWORD = Registration.ITEMS.register("copper_sword",
            () -> new SwordItem(ModItemTier.COPPER, 3, -2.2f, new Item.Properties()
                    .defaultMaxDamage(150)
                    .group(Elydarion.ELYDARION_COMBAT)));

    /* ARMOR */
    public static final RegistryObject<Item> COPPER_HELMET = Registration.ITEMS.register("copper_helmet",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.HEAD, new Item.Properties().group(Elydarion.ELYDARION_COMBAT)));

    public static final RegistryObject<Item> COPPER_CHESTPLATE = Registration.ITEMS.register("copper_chestplate",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.CHEST, new Item.Properties().group(Elydarion.ELYDARION_COMBAT)));

    public static final RegistryObject<Item> COPPER_LEGGINGS = Registration.ITEMS.register("copper_leggings",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.LEGS, new Item.Properties().group(Elydarion.ELYDARION_COMBAT)));

    public static final RegistryObject<Item> COPPER_BOOTS = Registration.ITEMS.register("copper_boots",
            () -> new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.FEET, new Item.Properties().group(Elydarion.ELYDARION_COMBAT)));

    public static final RegistryObject<Item> MILK_BUCKET = Registration.ITEMS.register("milk_bucket",
            () -> new BucketItem(ModFuilds.MILK_FLUID::get, new Item.Properties().group(Elydarion.ELYDARION_ITEMS).maxStackSize(1)));

    public static void register ()
    {

    }

    public enum ModArmorMaterial implements IArmorMaterial
    {
        COPPER(50, new int[] { 2, 7, 5, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON,
                Ingredient.fromStacks(new ItemStack(ModItems.COPPER_INGOT.get())), Elydarion.MOD_ID + ":copper", 0, 0.1f);

        private final int durability;
        private final int[] damageReductionAmountArray; //Reduction damage to each pieces, {Helmet, Chestplate, Leggins, Boots}
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final Ingredient repairMaterial;
        private final String name;
        private final float toughness;
        private final float knockbackResistance;

        ModArmorMaterial(int durability, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, Ingredient repairMaterial, String name, float toughness, float knockbackResistance) {
            this.durability = durability;
            this.damageReductionAmountArray = damageReductionAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.repairMaterial = repairMaterial;
            this.name = name;
            this.toughness = toughness;
            this.knockbackResistance = knockbackResistance;
        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return durability;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return damageReductionAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public float getToughness() {
            return toughness;
        }

        @Override
        public float getKnockbackResistance() {
            return knockbackResistance;
        }
    }

    public enum ModItemTier implements IItemTier
    {
        COPPER(2, 200, 2.5f, 1f, 15, Ingredient.fromStacks(new ItemStack(ModItems.COPPER_INGOT.get())));

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final Ingredient repairMaterial;

        ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Ingredient repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = repairMaterial;
        }

        @Override
        public int getMaxUses() {
            return maxUses;
        }

        @Override
        public float getEfficiency() {
            return efficiency;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial;
        }
    }
}

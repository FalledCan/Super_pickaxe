package com.github.falledcan.super_pickaxe;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class Super_Pickaxe extends JavaPlugin {

    public static ItemStack item1;
    public static ItemStack item2;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Events(),this);
        addTool();
        addCraft();

    }

    private void addTool() {
        item1 = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName("§b§lSuperPickaxe(3×3)");
        ArrayList<String> lore1 = new ArrayList<String>();
        lore1.add("§6このピッケルは3×3で掘れます。");
        meta1.setLore(lore1);
        item1.setItemMeta(meta1);
        NBTItem nbtItem1 = new NBTItem(item1);
        nbtItem1.setInteger("Range",1);
        nbtItem1.applyNBT(item1);

        item2 = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta2 = item2.getItemMeta();
        meta2.setDisplayName("§b§lSuperPickaxe(5×5)");
        ArrayList<String> lore2 = new ArrayList<String>();
        lore2.add("§6このピッケルは5×5で掘れます。");
        meta2.setLore(lore2);
        item2.setItemMeta(meta2);
        NBTItem nbtItem2 = new NBTItem(item2);
        nbtItem2.setInteger("Range",2);
        nbtItem2.applyNBT(item2);
    }

    private void addCraft() {
        NamespacedKey key1 = new NamespacedKey(this,"SuperPickaxe3");
        ShapedRecipe recipe1 = new ShapedRecipe(key1,item1);
        recipe1.shape("ABA","BCB","ABA");
        recipe1.setIngredient('A',Material.IRON_BLOCK);
        recipe1.setIngredient('B',Material.GOLD_BLOCK);
        recipe1.setIngredient('C',Material.NETHERITE_PICKAXE);
        Bukkit.addRecipe(recipe1);

        NamespacedKey key2 = new  NamespacedKey(this,"SuperPickaxe5");
        ShapedRecipe recipe2 = new ShapedRecipe(key2,item2);
        recipe2.shape("ABA","BCB","ABA");
        recipe2.setIngredient('A',Material.NETHERITE_INGOT);
        recipe2.setIngredient('B',Material.DIAMOND_BLOCK);
        recipe2.setIngredient('C',Material.NETHERITE_PICKAXE);
        Bukkit.addRecipe(recipe2);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

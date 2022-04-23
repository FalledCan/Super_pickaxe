package com.github.falledcan.super_pickaxe;

import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class Events implements Listener {


    private final HashMap<Player, Boolean> a = new HashMap<>();
    private final HashMap<Player, String> b = new HashMap<>();


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();
        NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
        if(e.getAction() == Action.LEFT_CLICK_BLOCK && nbtItem.getInteger("Range") != null){
            b.put(e.getPlayer(), e.getBlockFace().toString());
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        NBTItem nbtItem = new NBTItem(player.getInventory().getItemInMainHand());
        if(nbtItem.getInteger("Range") == null)
           return;
       Block block = e.getBlock();
       if(a.get(player) != null)
           return;
       a.put(player, true);
       int r = nbtItem.getInteger("Range");
       switch (b.get(player)){
           case "UP":
           case "DOWN":
               UpDownBreak(block ,player, r);
               break;
           case "NORTH":
           case "SOUTH":
               ZBreak(block, player, r);
               break;
           case "EAST":
           case "WEST":
               XBreak(block, player, r);
               break;
       }
       a.remove(player);
    }

    private void UpDownBreak(Block block,Player player,int r) {
        Location loc = block.getLocation();
        World world = block.getWorld();
        int XB = loc.getBlockX();
        int YB = loc.getBlockY();
        int ZB = loc.getBlockZ();
        for (int x = -r; x <= r; x++) {
            for (int z = -r; z <= r; z++) {
                Block blocks = world.getBlockAt(XB + x , YB , ZB + z );
                if (blocks.getType() != Material.AIR) {
                    player.breakBlock(blocks);
                }
            }
        }
    }

    private void XBreak(Block block,Player player,int r) {
        Location loc = block.getLocation();
        World world = block.getWorld();
        int XB = loc.getBlockX();
        int YB = loc.getBlockY();
        int ZB = loc.getBlockZ();
        for (int y = -r; y <= r; y++) {
            for (int z = -r; z <= r; z++) {
                Block blocks = world.getBlockAt(XB  , YB + y , ZB + z );
                if (blocks.getType() != Material.AIR) {
                    player.breakBlock(blocks);
                }
            }
        }
    }

    private void ZBreak(Block block,Player player,int r) {
        Location loc = block.getLocation();
        World world = block.getWorld();
        int XB = loc.getBlockX();
        int YB = loc.getBlockY();
        int ZB = loc.getBlockZ();
        for (int x = -r; x <= r; x++) {
            for (int y = -r; y <= r; y++) {
                Block blocks = world.getBlockAt(XB + x , YB + y , ZB );
                if (blocks.getType() != Material.AIR) {
                    player.breakBlock(blocks);
                }
            }
        }
    }


}

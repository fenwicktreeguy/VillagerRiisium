package ChestChecker;

import Main.MainRunner;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.NBTTagList;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Map;

public class ChestCheck implements Listener{


    public static String voucherLore = "Riithium Ore";

    public static boolean hasEnchant(ItemStack it, Enchantment e) {
        if(it.containsEnchantment(e)){
            return true;
        }
        return false;
    }

    public static boolean validItem(ItemStack it){
        ItemMeta m = it.getItemMeta();
        List<String> lores = m.getLore();
        //System.out.println(m.getDisplayName() + " " + voucherLore);
        //System.out.println(it.getType() );
        if(it.getType().equals(Material.PLAYER_HEAD)  && m.getDisplayName().equals(voucherLore)  ){
            return true;
        } else {
            return false;
        }
    }



    public static ItemStack spawnRiisium(int amt){
        String url = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBjZTBiODZmMDE2ZTQwNzRmZGVhMTg5MDk4ODNjOTM0OGY5NjQwZWFhNmUxZTIwMzVkMjNhYzc1ZTY4MzhhMiJ9fX0=";

        ItemStack s = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short)3 );
        SkullMeta m = (SkullMeta) s.getItemMeta();
        GameProfile p = new GameProfile(UUID.randomUUID(), null);
        p.getProperties().put("textures", new Property("textures", url) );
        try {
            Field fi = m.getClass().getDeclaredField("profile");
            fi.setAccessible(true);
            fi.set(m, p);

        } catch(Exception e){
            e.printStackTrace();
        }
        m.setDisplayName("Riithium Ore");
        m.addEnchant(Enchantment.DURABILITY, 0, true);
        m.addItemFlags(new ItemFlag[]  { ItemFlag.HIDE_ENCHANTS } );
        s.setItemMeta(m);

        return s;

    }

    public static void putInInventory(Player p, ItemStack i ){
        p.getOpenInventory().getBottomInventory().addItem(i);
    }

    @EventHandler
    public void miner(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(e.getBlock().getType().equals(Material.DIAMOND_ORE)){
            double v = Math.random();
            if(v <= 0.1){
                Location loc = new Location(Bukkit.getWorld("world"), e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ());
                ItemStack is = spawnRiisium(1);
                Bukkit.getWorld("world").dropItemNaturally(loc, is);
            }
        }
    }









}

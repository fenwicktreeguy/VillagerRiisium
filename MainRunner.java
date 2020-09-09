package Main;
import ChestChecker.ChestCheck;
import CustomConfigs.EnchantConfig;
import net.minecraft.server.v1_15_R1.NBTTagCompound;
import net.minecraft.server.v1_15_R1.NBTTagList;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.*;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import EnchantHandler.Enchanter;

import java.lang.reflect.Field;
import CommandHandler.Commander;
import java.util.*;
import EnchantHandler.Enchanter;
import org.bukkit.scheduler.BukkitScheduler;
;


import static net.minecraft.server.v1_15_R1.Items.de;


public class MainRunner extends JavaPlugin implements Listener {

    public static int vilX = 78;
    public static int vilY = 69;
    public static int vilZ = -83;
    public static int lastX = 232;
    public static int lastY = 66;
    public static int lastZ = 167;
    boolean clickedWasVillager = false;
    public static boolean COMMAND_SUCCESS = false;
    private Plugin plug;
    //we can change vilX, vilY, and vilZ based on where we want the villager to be
    public static String INV_NAME = "Master Riis's Wares";
    int numValid = 0;
    public InventoryView sview;
    ItemStack weapon = new ItemStack(Material.DIAMOND_SWORD, 1);
    ItemStack[] enchants = new ItemStack[9];
    int cond = 0;
    boolean considerVouch = false, considerItem = false, booksAdded = false;
    ArrayList<ItemStack> s = new ArrayList<ItemStack>();



    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        /*
        EnchantConfig.gen();
        EnchantConfig.retFile().options().copyDefaults(true);
        EnchantConfig.save();
        EnchantConfig.reload();
         */
        Bukkit.getPluginCommand("spawnRiis").setExecutor(new Commander());
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getServer().getPluginManager().registerEvents(new ChestCheck(), this);
        enchants = Enchanter.cmeth();
    }



    public boolean distanceThreshold(Villager v, int lastX, int lastY, int lastZ){
            int villagerX = v.getLocation().getBlockX();
            int villagerY = v.getLocation().getBlockY();
            int villagerZ = v.getLocation().getBlockZ();

            double cdistance = Math.sqrt( Math.pow(villagerX - lastX, 2) + Math.pow(villagerY - lastY, 2) + Math.pow(lastZ - villagerZ, 2) );
            return (cdistance <= 50);
    }



    @EventHandler(priority = EventPriority.HIGH)
    public void interact(PlayerInteractEntityEvent e){
        Player p = e.getPlayer();
        Entity ent = e.getRightClicked();
        Location entLoc = ent.getLocation();
        boolean distThres = false;

        if(ent instanceof Villager){
            Villager v = (Villager) ent;
            distThres = distanceThreshold(v, lastX, lastY, lastZ);
        }


        if ( (ent instanceof Villager) && distThres) {
            clickedWasVillager = true;
            Inventory inv = Bukkit.createInventory(null, 27, Main.MainRunner.INV_NAME);
            for (int i = 4; i <= 22; i += 9) {
                inv.setItem(i, new ItemStack(Material.GREEN_STAINED_GLASS_PANE, 1));
            }
            sview = p.openInventory(inv);
        }
    }

    public void processInv(ItemStack[] v, ItemStack clik){
        for(int increment = 0; increment < v.length; increment++) {
            if(v[increment] == null){
                continue;
            }

            ItemStack i = v[increment];
            //System.out.println(i.getType());

            boolean ep1 = (i.getType().equals(Material.DIAMOND_SWORD) || i.getType().equals(Material.IRON_SWORD) || i.getType().equals(Material.GOLDEN_SWORD) || i.getType().equals(Material.STONE_SWORD) || i.getType().equals(Material.WOODEN_SWORD));
            boolean ep2 = (i.getType().equals(Material.DIAMOND_AXE) || i.getType().equals(Material.IRON_AXE) || i.getType().equals(Material.GOLDEN_AXE) || i.getType().equals(Material.STONE_AXE) ||  i.getType().equals(Material.WOODEN_AXE));
            boolean ep3 = (i.getType().equals(Material.DIAMOND_HELMET) || i.getType().equals(Material.IRON_HELMET) || i.getType().equals(Material.GOLDEN_HELMET) || i.getType().equals(Material.LEATHER_HELMET));
            boolean ep4 = (i.getType().equals(Material.DIAMOND_CHESTPLATE) || i.getType().equals(Material.IRON_CHESTPLATE) || i.getType().equals(Material.GOLDEN_CHESTPLATE) || i.getType().equals(Material.LEATHER_CHESTPLATE));
            boolean ep5 = (i.getType().equals(Material.DIAMOND_LEGGINGS) || i.getType().equals(Material.IRON_LEGGINGS) || i.getType().equals(Material.GOLDEN_LEGGINGS) || i.getType().equals(Material.LEATHER_LEGGINGS));
            boolean ep6 = (i.getType().equals(Material.DIAMOND_BOOTS) || i.getType().equals(Material.IRON_BOOTS) || i.getType().equals(Material.GOLDEN_BOOTS) || i.getType().equals(Material.LEATHER_BOOTS));
            boolean ep7 = (i.getType().equals(Material.DIAMOND_PICKAXE) || i.getType().equals(Material.IRON_PICKAXE) || i.getType().equals(Material.GOLDEN_PICKAXE) || i.getType().equals(Material.STONE_PICKAXE) || i.getType().equals(Material.WOODEN_PICKAXE) );
            boolean ep8 = i.getType().equals(Material.DIAMOND_SHOVEL) || i.getType().equals(Material.IRON_SHOVEL) || i.getType().equals(Material.GOLDEN_SHOVEL) || i.getType().equals(Material.STONE_SHOVEL) || i.getType().equals(Material.WOODEN_SHOVEL);
            boolean res = ep1 || ep2 || ep3 || ep4 || ep5 || ep6 || ep7 || ep8;
            //for some odd reason, emerald must be put in before weapon, even though they are processed separately

            if (ChestCheck.validItem(i) ) {
                //System.out.println("MATERIAL: " + i.getType() );
                //System.out.println("RIITHIUM HAS BEEN DETECTED");
                cond++;
                numValid += i.getAmount();
            }
            if (res) {
                //System.out.println("MATERIAL: " + i.getType() );
                //System.out.println("ITEM HAS BEEN DETECTED");
                s.add(i);
                //System.out.println("ADDED!");
                cond++;
            }

        }
        cond = Math.min(cond, 2);
        //}
    }




    @EventHandler(priority = EventPriority.HIGHEST )
    public void clickIn(InventoryClickEvent e) {

        if (clickedWasVillager) {
            Player p = (Player) (e.getWhoClicked());
            if (e.getClickedInventory().equals(p.getOpenInventory().getTopInventory()) && !(e.getCurrentItem() == null) && e.getCurrentItem().getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
                e.setCancelled(true);
                //p.closeInventory();, closes inventory before
            }

            try {
                //System.out.println("CURRENT VALUE: " + cond);
                ItemStack clik = e.getCurrentItem();
                boolean val = p.getOpenInventory().getTopInventory().equals(e.getClickedInventory());
                if(val) {
                    ItemStack[] v = p.getOpenInventory().getTopInventory().getContents();
                    BukkitScheduler sched = getServer().getScheduler();
                    new BukkitRunnable() {
                        public void run() {
                            processInv(v, clik);
                            if (cond == 2 && !booksAdded) {
                                //System.out.println("ENTERED! " + s.get(0).getType() );
                                for (int i = 5; i < 9; i++) {
                                    if (s.get(0).getEnchantmentLevel(Enchanter.encher[i-5]) == Enchanter.maxLevels[i-5]) {
                                        e.getClickedInventory().setItem(i, enchants[i - 5]);
                                    }
                                }
                                for (int i = 14; i < 18; i++) {
                                    if (s.get(0).getEnchantmentLevel(Enchanter.encher[i-10]) == Enchanter.maxLevels[i-10] ) {
                                        e.getClickedInventory().setItem(i, enchants[i - 10]);
                                    }
                                }

                                if(s.get(0).getEnchantmentLevel(Enchanter.encher[8]) == Enchanter.maxLevels[8] ) {
                                    e.getClickedInventory().setItem(23, enchants[8]);
                                }
                                booksAdded = true;
                                p.updateInventory();
                            }
                            if ( (clik != null && clik.getType() != Material.AIR) && (cond == 2 && clik.getType().equals(Material.BOOK) ) ) {
                                p.closeInventory();
                                int newAmt = numValid - 2;
                                //System.out.println("AMOUNT OF RIITHIUM: " + newAmt);
                                ItemStack riisium = ChestCheck.spawnRiisium(newAmt);
                                ItemStack is2 = s.get(0);


                                Map<Enchantment, Integer> enchs = clik.getEnchantments();
                                //Set<Enchantment> ez = enchs.keySet();


                    /*
                    Iterator<Enchantment> itr = ez.iterator(); // traversing over HashSet System.out.println("Traversing over Set using Iterator"); while(itr.hasNext()){ System.out.println(itr.next()); }


                while(itr.hasNext()){
                        Enchanter.incrementEnchant((Enchantment) itr, 1);
                        Enchantment enc = (Enchantment) itr;

                        String encer = "";
                        String encer_threshold = "";
                        String encer_mutator = "";

                        if(enc.equals(Enchantment.DAMAGE_ALL)){
                                encer = "Sharpness";
                                encer_threshold = "Sharpness_Threshold";
                                encer_mutator = "Sharpness_Num_Increase";
                        } else if(enc.equals(Enchantment.PROTECTION_ENVIRONMENTAL)){
                                encer = "Protection";
                                encer_threshold = "Protection_Threshold";
                                encer_mutator = "Protection_Num_Increase";
                        } else if(enc.equals(Enchantment.MENDING)){
                                encer = "Mending";
                                encer_threshold = "Mending_Threshold";
                                encer_mutator = "Mending_Num_Increase";
                        } else if(enc.equals(Enchantment.LOOT_BONUS_BLOCKS)){
                                encer = "Fortune";
                                encer_threshold = "Fortune_Threshold";
                                encer_mutator = "Fortune_Num_Increase";
                        } else if(enc.equals(Enchantment.ARROW_FIRE)){
                                encer = "Flame";
                                encer_threshold = "Flame_Threshold";
                                encer_mutator = "Flame_Num_Increase";
                        } else if(enc.equals(Enchantment.FIRE_ASPECT)){
                                encer = "Fire Aspect";
                                encer_threshold = "FireAspect_Threshold";
                                encer_mutator = "FireAspect_Num_Increase";
                        } else if(enc.equals(Enchantment.ARROW_DAMAGE)){
                                encer = "Power";
                                encer_threshold = "Power_Threshold";
                                encer_mutator = "Power_Num_Increase";
                        } else if(enc.equals(Enchantment.DURABILITY)){
                                encer = "Unbreaking";
                                encer_threshold = "Unbreaking_Threshold";
                                encer_mutator = "Unbreaking_Num_Increase";
                        } else if(enc.equals(Enchantment.DIG_SPEED)){
                                encer = "Efficiency";
                                encer_threshold = "Efficiency_Threshold";
                                encer_mutator = "Efficiency_Num_Increase";
                        }

                        int enc_mut = EnchantConfig.retFile().getInt(encer_mutator);
                        int cur_lev = EnchantConfig.retFile().getInt(encer);
                        int threshold = EnchantConfig.retFile().getInt(encer_threshold);

                        if(enc_mut <= 2){
                            if(Enchanter.enchantCondition(enc, enc_mut)){
                                EnchantConfig.retFile().set(encer, EnchantConfig.retFile().getInt(encer) + 1);
                                EnchantConfig.retFile().
                            }
                        }


                    }
                     */
                                is2.addUnsafeEnchantments(enchs);
                                cond = 0;
                                numValid = 0;
                                considerVouch = false;
                                considerItem = false;
                                booksAdded = false;
                                weapon = null;
                                s.clear();
                                clickedWasVillager = false;
                                p.getOpenInventory().getBottomInventory().addItem(is2);

                            }
                        }
                    }.runTaskLater(this, 2);
                }


            } catch (Exception exp) {

            }
        }
    }

    @EventHandler
    public void spawnIt(ItemSpawnEvent e){
        ItemStack s = e.getEntity().getItemStack();
        if(s.getType().equals(Material.BOOK) && clickedWasVillager){
            e.setCancelled(true);
        }
    }


/*

//FOR FUTURE IN DESIGNING CUSTOM ENCHANTMENTS

    public void onDisable(){
        try{
            Field idn = Enchantment.class.getDeclaredField("byKey");
            idn.setAccessible(true);
            HashMap<NamespacedKey, Enchantment> byKey = (HashMap<NamespacedKey, Enchantment> ) idn.get(null);
            if(byKey.containsKey(s.getKey())){
                byKey.remove(s.getKey());
            }

            Field ido = Enchantment.class.getDeclaredField("byName");
            ido.setAccessible(true);
            HashMap<String, Enchantment> byName = (HashMap<String, Enchantment> ) ido.get(null);
            if(byName.containsKey(s.getName())){
                byName.remove(s.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onLoad(){

    }

    public static void LoadEnchantment(Enchantment ench){
        try{
            try{
                Field f = Enchantment.class.getDeclaredField("acceptingNew");
                f.setAccessible(true);
                f.set(null, true);
                Enchantment.registerEnchantment(ench);
            } catch(Exception e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/

}
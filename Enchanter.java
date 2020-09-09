package EnchantHandler;

import CustomConfigs.EnchantConfig;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Enchanter extends ChestChecker.ChestCheck{

    public static HashMap<Integer, String> romanNumerals = new HashMap<Integer, String>();
    public static Enchantment[] encher = {Enchantment.DAMAGE_ALL,
                                          Enchantment.PROTECTION_ENVIRONMENTAL,
                                          Enchantment.LOOT_BONUS_BLOCKS,
                                          Enchantment.ARROW_FIRE,
                                          Enchantment.ARROW_DAMAGE,
                                          Enchantment.MENDING,
                                          Enchantment.DURABILITY,
                                          Enchantment.DIG_SPEED,
                                          Enchantment.FIRE_ASPECT};
    public static Integer[] maxLevels = {5, 4, 3, 1, 5, 1, 3, 5, 2};
    public static ItemStack[] cmeth(){
        ItemStack[] enchantedBooks= new ItemStack[9];

        romanNumerals.put(1, "I");
        romanNumerals.put(2, "II");
        romanNumerals.put(3, "III");
        romanNumerals.put(4, "IV");
        romanNumerals.put(5, "V");
        romanNumerals.put(6, "VI");
        romanNumerals.put(7, "VII");
        romanNumerals.put(8, "VIII");
        romanNumerals.put(9, "IX");
        romanNumerals.put(10, "X");



        ItemStack i = new ItemStack(Material.BOOK);
        ItemStack i1 = new ItemStack(Material.BOOK);
        ItemStack i2 = new ItemStack(Material.BOOK);
        ItemStack i3 = new ItemStack(Material.BOOK);
        ItemStack i4 = new ItemStack(Material.BOOK);
        ItemStack i5 = new ItemStack(Material.BOOK);
        ItemStack i6 = new ItemStack(Material.BOOK);
        ItemStack i7 = new ItemStack(Material.BOOK);
        ItemStack i8 = new ItemStack(Material.BOOK);

        ItemMeta im = i.getItemMeta();
        ItemMeta im2 = i1.getItemMeta();
        ItemMeta im3 = i2.getItemMeta();
        ItemMeta im4 = i3.getItemMeta();
        ItemMeta im5 = i4.getItemMeta();
        ItemMeta im6 = i5.getItemMeta();
        ItemMeta im7 = i6.getItemMeta();
        ItemMeta im8 = i7.getItemMeta();
        ItemMeta im9 = i8.getItemMeta();

        int csharp =6;
        int cprot =5;
        int cfort = 4;
        int cflame =2;
        int cpow =6;
        int cmend = 2;
        int cunbreak = 4;
        int ceff = 6;
        int cfa = 3;

        im.addEnchant(Enchantment.DAMAGE_ALL, csharp , true);
        im2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, cprot, true);
        im3.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, cfort , true);
        im4.addEnchant(Enchantment.ARROW_FIRE, cflame, true);
        im5.addEnchant(Enchantment.ARROW_DAMAGE, cpow, true);
        im6.addEnchant(Enchantment.MENDING, cmend, true);
        im7.addEnchant(Enchantment.DURABILITY, cunbreak, true);
        im8.addEnchant(Enchantment.DIG_SPEED,ceff, true);
        im9.addEnchant(Enchantment.FIRE_ASPECT, cfa, true);

        List<String> l = im.getLore();
        if(l == null){
            l = new ArrayList<String>();
        }
        List<String> l2 = im2.getLore();
        if(l2 == null){
            l2= new ArrayList<String>();
        }
        List<String> l3 = im3.getLore();
        if(l3 == null){
            l3 = new ArrayList<String>();
        }
        List<String> l4 = im4.getLore();
        if(l4 == null){
            l4 = new ArrayList<String>();
        }
        List<String> l5 = im5.getLore();
        if(l5 == null){
            l5 = new ArrayList<String>();
        }
        List<String> l6 = im6.getLore();
        if(l6 == null){
            l6 = new ArrayList<String>();
        }
        List<String> l7 = im7.getLore();
        if(l7 == null){
            l7 = new ArrayList<String>();
        }
        List<String> l8 = im8.getLore();
        if(l8 == null){
            l8 = new ArrayList<String>();
        }
        List<String> l9 = im9.getLore();
        if(l9 == null){
            l9 = new ArrayList<String>();
        }

        l.add("Sharpness " + romanNumerals.get(csharp));
        l2.add("Protection " + romanNumerals.get(cprot));
        l3.add("Fortune " +  romanNumerals.get(cfort));
        l4.add(" Flame " + romanNumerals.get(cflame));
        l5.add(" Power " + romanNumerals.get(cpow));
        l6.add("Mending " + romanNumerals.get(cmend));
        l7.add("Unbreaking " + romanNumerals.get(cunbreak));
        l8.add("Efficiency " + romanNumerals.get(ceff));
        l9.add("Fire Aspect " + romanNumerals.get(cfa) );

        im.setLore(l);
        im2.setLore(l2);
        im3.setLore(l3);
        im4.setLore(l4);
        im5.setLore(l5);
        im6.setLore(l6);
        im7.setLore(l7);
        im8.setLore(l8);
        im9.setLore(l9);

        i.setItemMeta(im);
        i1.setItemMeta(im2);
        i2.setItemMeta(im3);
        i3.setItemMeta(im4);
        i4.setItemMeta(im5);
        i5.setItemMeta(im6);
        i6.setItemMeta(im7);
        i7.setItemMeta(im8);
        i8.setItemMeta(im9);

        enchantedBooks[0] = i;
        enchantedBooks[1] = i1;
        enchantedBooks[2] = i2;
        enchantedBooks[3] = i3;
        enchantedBooks[4] = i4;
        enchantedBooks[5] = i5;
        enchantedBooks[6] = i6;
        enchantedBooks[7] = i7;
        enchantedBooks[8] = i8;

        return enchantedBooks;

    }

    /*
    public static boolean enchantCondition(Enchantment enc, int det){
        if(enc.equals(Enchantment.DAMAGE_ALL)){
                return (det >= EnchantConfig.retFile().getInt("Sharpness_Threshold"));
        } else if(enc.equals(Enchantment.PROTECTION_ENVIRONMENTAL)){
            return (det >= EnchantConfig.retFile().getInt("Protection_Threshold"));
        } else if(enc.equals(Enchantment.MENDING)){
            return (det >= EnchantConfig.retFile().getInt("Mending_Threshold"));
        } else if(enc.equals(Enchantment.LOOT_BONUS_BLOCKS)){
            return (det >= EnchantConfig.retFile().getInt("Fortune_Threshold"));
        } else if(enc.equals(Enchantment.ARROW_FIRE)){
            return (det >= EnchantConfig.retFile().getInt("Flame_Threshold"));
        } else if(enc.equals(Enchantment.FIRE_ASPECT)){
            return (det >= EnchantConfig.retFile().getInt("FireAspect_Threshold"));
        } else if(enc.equals(Enchantment.ARROW_DAMAGE)){
            return (det >= EnchantConfig.retFile().getInt("Power_Threshold"));
        } else if(enc.equals(Enchantment.DURABILITY)){
            return (det >= EnchantConfig.retFile().getInt("Unbreaking_Threshold"));
        } else if(enc.equals(Enchantment.DIG_SPEED)){
            return (det >= EnchantConfig.retFile().getInt("Efficiency_Threshold"));
        }
        return false;
    }


    public static void incrementEnchant(Enchantment enc, int inc){
        if(enc.equals(Enchantment.DAMAGE_ALL)){
            EnchantConfig.retFile().set("Sharpness", EnchantConfig.retFile().getInt("Sharpness") + 1);
            EnchantConfig.save();
            EnchantConfig.reload();
        } else if(enc.equals(Enchantment.PROTECTION_ENVIRONMENTAL)){
            EnchantConfig.retFile().set("Protection", EnchantConfig.retFile().getInt("Protection") + 1);
            EnchantConfig.save();
            EnchantConfig.reload();
        } else if(enc.equals(Enchantment.MENDING)){
            EnchantConfig.retFile().set("Protection", EnchantConfig.retFile().getInt("Mending") + 1);
            EnchantConfig.save();
            EnchantConfig.reload();
        } else if(enc.equals(Enchantment.LOOT_BONUS_BLOCKS)){
            EnchantConfig.retFile().set("Fortune", EnchantConfig.retFile().getInt("Fortune") + 1);
            EnchantConfig.save();
            EnchantConfig.reload();
        } else if(enc.equals(Enchantment.ARROW_FIRE)){
            EnchantConfig.retFile().set("Flame", EnchantConfig.retFile().getInt("Flame") + 1);
            EnchantConfig.save();
            EnchantConfig.reload();
        } else if(enc.equals(Enchantment.FIRE_ASPECT)){
            EnchantConfig.retFile().set("Fire Aspect", EnchantConfig.retFile().getInt("Fire Aspect") + 1);
            EnchantConfig.save();
            EnchantConfig.reload();
        } else if(enc.equals(Enchantment.ARROW_DAMAGE)){
            EnchantConfig.retFile().set("Power", EnchantConfig.retFile().getInt("Power") + 1);
            EnchantConfig.save();
            EnchantConfig.reload();
        } else if(enc.equals(Enchantment.DURABILITY)){
            EnchantConfig.retFile().set("Unbreaking", EnchantConfig.retFile().getInt("Unbreaking") + 1);
            EnchantConfig.save();
            EnchantConfig.reload();
        } else if(enc.equals(Enchantment.DIG_SPEED)){
            EnchantConfig.retFile().set("Efficiency", EnchantConfig.retFile().getInt("Efficiency") + 1);
            EnchantConfig.save();
            EnchantConfig.reload();
        }
    }
     */

}

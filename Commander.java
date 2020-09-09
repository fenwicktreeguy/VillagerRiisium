package CommandHandler;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import Main.MainRunner;

public class Commander implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        if(command.getName().equalsIgnoreCase("spawnRiis")) {

            boolean cond2 = strings[0] != null && strings[1] != null && strings[2] != null;

            if (cond2) {
                Location potVil = new Location(Bukkit.getWorld("world"), Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));
                Collection<Entity> col = Bukkit.getWorld("world").getNearbyEntities(potVil, 12, 12, 12);
                if (!(col.contains(EntityType.VILLAGER))) {
                    Villager v = (Villager) (p.getLocation().getWorld().spawnEntity(potVil, EntityType.VILLAGER));
                    v.setCustomName("Master Riis");
                    v.setProfession(Villager.Profession.LIBRARIAN);
                    v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000000, 1000000000, true));
                }
                Main.MainRunner.lastX = Integer.parseInt(strings[0]);
                Main.MainRunner.lastY = Integer.parseInt(strings[1]);
                Main.MainRunner.lastZ = Integer.parseInt(strings[2]);
                return true;
            }
        } else {
                Location reference = new Location(Bukkit.getWorld("world"), 268, 66, 188);
                Villager v = (Villager)(p.getLocation().getWorld().spawnEntity(reference, EntityType.VILLAGER) );
                v.setCustomName("Master Riis");
                v.setProfession(Villager.Profession.LIBRARIAN);
                v.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000000, 1000000000, true));
                return true;
        }
        return false;
    }


}

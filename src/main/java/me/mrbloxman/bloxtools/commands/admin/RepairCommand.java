package me.mrbloxman.bloxtools.commands.admin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class RepairCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@Nonnull CommandSender sender,@Nonnull Command command,@Nonnull String s,@Nonnull String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("bloxtools.repair")) {
                ItemStack item = p.getInventory().getItemInMainHand();

                // check if item is air
                if (item.getType() == Material.AIR) {
                    p.sendMessage(ChatColor.RED + "You aren't holding an item.");
                    return true;
                } else {
                    ItemMeta ItemMeta = item.getItemMeta();
                    if (!(ItemMeta instanceof Damageable damageable)) {
                        p.sendMessage(ChatColor.RED + "this item cannot be repaired.");
                        return true;
                    } else {
                        damageable.setDamage(0);
                        item.setItemMeta((ItemMeta) damageable);
                        p.getInventory().setItemInMainHand(item);
                        p.sendMessage(ChatColor.YELLOW + "Your item has been repaired.");

                    }
                }
            } else {
                p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
                return true;
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Only players can use this command");
        }
        return true;
    }
}

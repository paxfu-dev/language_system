package at.paxfu.velo.commands;

import at.paxfu.velo.inventories.selectInventory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class languageCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String labels, String[] args) {
        if(args.length == 0) {
            selectInventory.setInventory((Player) sender);
        }
        return false;
    }
}

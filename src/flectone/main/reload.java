package flectone.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class reload implements CommandExecutor, Listener {

	public reload(flectone flectone) {
	}
	flectone plugin = flectone.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length != 0 ) {
			if(args[0].toLowerCase().equals("reload")) {
				sender.sendMessage(plugin.locale.getString("reload.prepare").replace("&", "§"));
				plugin.reloadConfig();
				plugin.loadLocale();
				sender.sendMessage(plugin.locale.getString("reload.complete").replace("&", "§"));
				if(!(sender instanceof Player)) {
					return true;
				}
				utils.playCommandSound("reload", sender, "success");
			}
		}
		return true;
	}
	
}

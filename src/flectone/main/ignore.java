package flectone.main;

import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class ignore implements Listener, CommandExecutor {

	public ignore(flectone plugin) {
	}
	flectone plugin = flectone.getInstance();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player playerSender = Bukkit.getPlayer(sender.getName());
		if(!(sender instanceof Player)) {
			return true;
		}
		if(args.length == 0) {
			sender.sendMessage(utils.formatMessage(playerSender.getName(), "", "usage.ignore"));
			return true;	
		}
		List<String> senderIgnoreList = plugin.ignoreConfig.getStringList(sender.getName());
		if(args[0] == sender.getName()) {
			sender.sendMessage(utils.formatMessage(playerSender.getName(), "", "ignore.myself"));
			utils.playCommandSound("ignore", sender, "fail");
			return true;
		}
		if(senderIgnoreList.contains(args[0])) {
			senderIgnoreList.remove(args[0]);
			sender.sendMessage(utils.formatMessage(args[0], "", "ignore.message.ignored_false"));
		} else {
			if(Bukkit.getPlayer(args[0]) == null) {
				sender.sendMessage(utils.formatMessage(args[0], "", "ignore.message.no_player"));
				return true;
			}
			senderIgnoreList.add(args[0]);
			sender.sendMessage(utils.formatMessage(args[0], "", "ignore.message.ignored_true"));
		}
		plugin.ignoreConfig.set(sender.getName(), senderIgnoreList);
		try {
			plugin.ignoreConfig.save(plugin.ignoreConfigFile);
		} catch (IOException error) {
			error.printStackTrace();
		}
		return true;
	}
}

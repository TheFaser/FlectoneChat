package flectone.main;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class me implements CommandExecutor, Listener{

	public me(flectone plugin) {
	}
	flectone plugin = flectone.getInstance();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player playerSender = Bukkit.getPlayer(sender.getName());
		if(args.length == 0) {
			sender.sendMessage(utils.formatMessage(playerSender.getName(), "", "usage.me"));
			return true;
		}
		String message = String.join(" ", args);
		String messageMe = utils.formatMessage(playerSender.getName(), message, "me.message");
		for (Player player : Bukkit.getOnlinePlayers()) {
			List<String> senderIgnoreList = plugin.ignoreConfig.getStringList(player.getName());
			if(!senderIgnoreList.contains(sender.getName())) {
				player.sendMessage(messageMe);
			}
		}
		return true;
	}

}

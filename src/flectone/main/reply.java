package flectone.main;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class reply implements CommandExecutor, Listener {

	public reply(flectone flectone) {
	}
	flectone plugin = flectone.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		if(!plugin.lastSender.containsKey(sender.getName())) {
			sender.sendMessage(plugin.locale.getString("reply.no_players").replace("&", "§"));
			utils.playCommandSound("reply", sender, "fail");
			return true;
		}
		String receiverName = plugin.lastSender.get(sender.getName());
		Player receiver = Bukkit.getPlayer(receiverName);
		String message = String.join(" ", args);
		receiver.sendMessage(utils.formatMessage(sender.getName(), "", "tell.receiver.message")  + message);
		sender.sendMessage(utils.formatMessage(receiver.getName(), "", "tell.sender.message") + message);
		utils.playCommandSound("tell", receiver, "success");
		return true;
	}

}

package flectone.main;
import java.util.List;

import org.apache.commons.lang.ArrayUtils; 
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class tell implements CommandExecutor, Listener {
	
	public tell(flectone plugin) {
	}
	flectone plugin = flectone.getInstance();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player playerSender = Bukkit.getPlayer(sender.getName());
		if(args.length <= 1) {
			sender.sendMessage(utils.formatMessage(playerSender.getName(), "", "usage.tell"));
			return true;	
		}
		Player receiver = Bukkit.getPlayer(args[0]);
		if(receiver == null) {
			sender.sendMessage(utils.formatMessage(playerSender.getName(), "", "tell.sender.no_player"));
			utils.playCommandSound("tell", sender, "fail");
			return true;
		}
		if(receiver == sender) {
			sender.sendMessage(utils.formatMessage(playerSender.getName(), "", "tell.sender.myself"));
			utils.playCommandSound("tell", sender, "fail");
			return true;
		}
		List<String> senderIgnoreList = plugin.ignoreConfig.getStringList(sender.getName());
		List<String> receiverIgnoreList = plugin.ignoreConfig.getStringList(receiver.getName());
		if(senderIgnoreList.contains(receiver.getName())) {
			sender.sendMessage(utils.formatMessage(receiver.getName(), "", "ignore.tell.ignoring"));
			utils.playCommandSound("tell", sender, "fail");
			return true;
		}
		if(receiverIgnoreList.contains(sender.getName())) {
			sender.sendMessage(utils.formatMessage(receiver.getName(), "", "ignore.tell.ignores"));
			utils.playCommandSound("tell", sender, "fail");
			return true;
		}
		String[] words = (String[]) ArrayUtils.remove(args, 0);
		String text = String.join(" ", words);
		receiver.sendMessage(utils.formatMessage(playerSender.getName(), "", "tell.receiver.message")  + text);
		plugin.lastSender.put(receiver.getName(), sender.getName());
		sender.sendMessage(utils.formatMessage(receiver.getName(), "", "tell.sender.message") + text);
		utils.playCommandSound("tell", receiver, "success");
		return true;	
	}
}

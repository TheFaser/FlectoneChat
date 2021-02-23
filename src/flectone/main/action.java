package flectone.main;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class action implements CommandExecutor, Listener {

	public action(flectone plugin) {
	}
	flectone plugin = flectone.getInstance();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		Player playerSender = Bukkit.getPlayer(sender.getName());
		String msg = String.join(" ", args);
		if(args.length == 0) {
			sender.sendMessage(utils.formatMessage(playerSender.getName(), "", "usage.action"));
			return true;
		}
		Random solution = new Random();
		final boolean randomSolution = solution.nextBoolean();
		if(randomSolution == false) {
			utils.sendToPlayers(plugin.ignoreConfig, sender.getName(), utils.formatMessage(playerSender.getName(), msg, "action.false"));
			utils.playCommandSound("action", sender, "fail");
			return true;
		}
		if(randomSolution == true) {
			utils.sendToPlayers(plugin.ignoreConfig, sender.getName(), utils.formatMessage(playerSender.getName(), msg, "action.true"));
			utils.playCommandSound("action", sender, "success");
			return true;
		}
		return true;
	}

}

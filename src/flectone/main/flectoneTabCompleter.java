package flectone.main;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class flectoneTabCompleter implements TabCompleter {
	flectone plugin = flectone.getInstance();

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(!sender.hasPermission("flectone.reload")) {
			return null;
		}
		List<String> command = Arrays.asList("reload");
		return command;
	}

}

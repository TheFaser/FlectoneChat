package flectone.main;


import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;

public class ignorelist implements CommandExecutor, Listener {

	public ignorelist(flectone plugin) {
	}
	flectone plugin = flectone.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			return true;
		}
		List<String> playerIgnoreList = plugin.ignoreConfig.getStringList(sender.getName());
		if(playerIgnoreList == null || playerIgnoreList.isEmpty()) {
			sender.sendMessage(plugin.locale.getString("ignorelist.is_empty").replace("&", "§"));
			utils.playCommandSound("ignorelist", sender, "fail");
			return true;
		}
		ComponentBuilder ignoreListBuilder = new ComponentBuilder(plugin.locale.getString("ignorelist.message").replace("&", "§") + "\n");
		for (String player : playerIgnoreList) {
			TextComponent playerTextComponent = new TextComponent(plugin.getConfig().getString("ignorelist.prefix").replace("&", "§"));
			playerTextComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ignore " + player));
			playerTextComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(plugin.locale
					.getString("ignorelist.no_more_ignore.message"))));
			ignoreListBuilder.append(playerTextComponent);
			ignoreListBuilder.append(player, ComponentBuilder.FormatRetention.NONE);
			ignoreListBuilder.append("\n");
		}
		BaseComponent[] componentsToSend = ignoreListBuilder.create();
		componentsToSend = (BaseComponent[]) ArrayUtils.remove(componentsToSend, componentsToSend.length - 1);
		sender.spigot().sendMessage(componentsToSend);
		return true;
	}
	

}

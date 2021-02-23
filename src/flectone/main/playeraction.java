package flectone.main;
import org.bukkit.GameMode;  
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
public class playeraction implements Listener {
	
	public playeraction(flectone plugin) {
	}
	flectone plugin = flectone.getInstance();
	@EventHandler
	public void join(PlayerJoinEvent event) {
		Player eventPlayer = event.getPlayer();
		event.setJoinMessage(utils.formatMessage(eventPlayer.getName(), "", "player_action.on_join.message"));
		if(eventPlayer.isOp() == false) {
			eventPlayer.setOp(plugin.getConfig().getBoolean("player_action.on_join.give_op"));
		}
		try {
			if(plugin.getConfig().getString("player_action.on_join.gamemode").toLowerCase().equals("")){
				utils.playCommandSound("on_join", eventPlayer, "success");
				return;
			}
			String gamemodeString = plugin.getConfig().getString("player_action.on_join.gamemode");
			GameMode playerGamemode = GameMode.valueOf(gamemodeString);
			eventPlayer.setGameMode(playerGamemode);
		} catch (java.lang.IllegalArgumentException error) {
			plugin.getLogger().warning(plugin.locale.getString("player_action.error_gamemode"));
		}
		utils.playCommandSound("on_join", eventPlayer, "success");
	}
	@EventHandler
	public void leave(PlayerQuitEvent event) {
		Player eventPlayer = event.getPlayer();
		event.setQuitMessage(utils.formatMessage(eventPlayer.getName(), "", "player_action.on_leave.message"));
		plugin.lastSender.remove(eventPlayer.getName());
	}
}

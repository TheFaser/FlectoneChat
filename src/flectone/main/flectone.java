package flectone.main;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class flectone extends JavaPlugin{
	public FileConfiguration locale;
	private static flectone instance;
	public FileConfiguration ignoreConfig;
	public File ignoreConfigFile;
	public HashMap<String, String> lastSender = new HashMap<String, String>();
	
	public void onEnable() {
		flectone.instance = this;
		File config = new File(getDataFolder() + File.separator + "config.yml");
		if(!config.exists()) {
			getLogger().info(ChatColor.RED + "Create new config...");
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		}
		this.loadLocale();
		File ignoreConfigFile = new File(getDataFolder() + File.separator + "ignore.yml");
		if(!ignoreConfigFile.exists()) {
			try {
				ignoreConfigFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.ignoreConfig = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(ignoreConfigFile);
		this.ignoreConfigFile = ignoreConfigFile;
		Bukkit.getPluginManager().registerEvents(new playeraction(this), this);
		Bukkit.getPluginManager().registerEvents(new tell(this), this);
		Bukkit.getPluginManager().registerEvents(new me(this), this);
		Bukkit.getPluginManager().registerEvents(new action(this), this);
		Bukkit.getPluginManager().registerEvents(new chat(this), this);
		Bukkit.getPluginManager().registerEvents(new ignore(this), this);
		Bukkit.getPluginManager().registerEvents(new ignorelist(this), this);
		Bukkit.getPluginManager().registerEvents(new reply(this), this);
		Bukkit.getPluginManager().registerEvents(new reload(this), this);
		getCommand("flectone").setExecutor(new reload(this));
		getCommand("reply").setExecutor(new reply(this));
		getCommand("me").setExecutor(new me(this));
		getCommand("msg").setExecutor(new tell(this));
		getCommand("try").setExecutor(new action(this));
		getCommand("ignore").setExecutor(new ignore(this));
		getCommand("ignorelist").setExecutor(new ignorelist(this));
		getCommand("flectone").setTabCompleter((TabCompleter)new flectoneTabCompleter());
		getLogger().info(ChatColor.AQUA + "Enabled!");	
		
	}
	
	public void onDisable() {
		getLogger().info(ChatColor.RED + "Switched off!");
	}
	public static flectone getInstance() {
		return flectone.instance;
	}
	public void loadLocale() {
        List<String> defaultLangFiles = Arrays.asList("en_US", "ru_RU");
        for (String lang : defaultLangFiles) {
            File localeFile = new File(getDataFolder() + File.separator + "locales" + File.separator + lang + ".yml");
            if (!localeFile.exists()) {
                localeFile.getParentFile().mkdirs();
                saveResource("locales/" + lang + ".yml", false);
            }
        }
		String language = getConfig().getString("language");
		File localeFile = new File(getDataFolder() + File.separator + "locales" + File.separator + language + ".yml");
		try {
	        if (!localeFile.exists()) {
	        	localeFile.getParentFile().mkdirs();
	        	saveResource("locales/" + language + ".yml", false);
	         }
        	locale = YamlConfiguration.loadConfiguration(localeFile);
        	getLogger().info(language + " language loaded.");
		} catch (Exception e){
			getLogger().warning("Failed to load language: " + language);
            localeFile = new File(getDataFolder() + File.separator + "locales" + File.separator + "en_US.yml");
            if (!localeFile.exists()) {
                localeFile.getParentFile().mkdirs();
                saveResource("locales/en_US.yml", false);
            }
            locale = YamlConfiguration.loadConfiguration(localeFile);
			getLogger().warning("Loaded default language: en_US");
			
		}
	}
}

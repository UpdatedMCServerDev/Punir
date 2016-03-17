package ban;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ban.commands.BanCommand;
import ban.listeners.PlayerJoin;
import ban.listeners.PlayerQuit;
import ban.manager.BanManager;

public class Ban extends JavaPlugin {

	@Override
	public void onEnable() {
		super.onEnable();
		BanManager.getInstance().setup(this);
		registerCommands();
		registerListeners();
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	private void registerListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
	}
	
	private void registerCommands() {
		getCommand("ban").setExecutor(new BanCommand());
	}
}

package ban.listeners;

import java.io.IOException;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ban.manager.BanManager;
import ban.user.User;
import ban.util.DateUtil;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		BanManager ban = BanManager.getInstance();
		User user;
		
		if (!ban.existUser(player.getUniqueId()))
			try {
				ban.createBans(player);
			} catch (IOException e) {
				player.kickPlayer("§cOcorreu um erro ao criar seu perfil.\n\n§f" + e.getMessage());
				e.printStackTrace();
			}
		else
			try {
				ban.createBans(player);
			} catch (IOException e) {
				player.kickPlayer("§cOcorreu um erro ao carregar seu perfil.\n\n§f" + e.getMessage());
				e.printStackTrace();
			}
		
		user = BanManager.mapUsers.get(player.getUniqueId());
		
		if (user.getBan() != null)
			if (!user.getBan().isPermanent())
				player.kickPlayer("§cVocê está banido.\n\n§cMotivo: §f" + user.getBan().getReason() + "\n§cStaffer: §f" + user.getBan().getStaffer() + "\n§cTermina em: §f" + DateUtil.format(user.getBan().getEnd()));
			else
				player.kickPlayer("§cVocê está banido.\n\n§cMotivo: §f" + user.getBan().getReason() + "\n§cStaffer: §f" + user.getBan().getStaffer());
	}
}

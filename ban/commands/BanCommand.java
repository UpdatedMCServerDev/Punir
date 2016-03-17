package ban.commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import ban.manager.BanManager;
import ban.user.User;
import ban.user.UserBan;

public class BanCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!command.getName().equalsIgnoreCase("ban")) return false;
		
		if (!(sender instanceof Player))
			sender.sendMessage("Erro: Somente jogadores podem executar o comando " + command.getName() + ".");
		
		
		Player player = (Player) sender;
		
		if (args.length == 0) {
			player.sendMessage("§cUse /ban <Jogador> <Tempo> <Motivo>");
		}
		else if (args.length == 3) {
			if (!BanManager.getInstance().existUser(Bukkit.getOfflinePlayer(args[0]).getUniqueId())) {
				player.sendMessage("§cNão foi possível encontrar o jogador " + args[0] + " nos arquivos do servidor.");
				return false;
			}
			try {
				BanManager.getInstance().loadBans(Bukkit.getOfflinePlayer(args[0]).getUniqueId(), args[0]);
			} catch (IOException | ParseException e) {
				player.sendMessage("§cOcorreu um erro.");
				e.printStackTrace();
				return false;
			}
			
			long tyme = System.currentTimeMillis();
			try {
				int i = Integer.valueOf(args[1].trim());
				tyme += (i * 60000);
			} catch (Exception e) {
				player.sendMessage("§cTempo inválido.");
			}
			
			String reason = "";
			for (int i = 2; i < args.length; i++) {
				reason += args[i] + " ";
			}
			
			User target = BanManager.mapUsers.get(Bukkit.getOfflinePlayer(args[0]).getUniqueId());
			if (!args[1].equals("0"))
				target.setBan(new UserBan(true, reason, player.getName(), 0L));
			else
				target.setBan(new UserBan(false, reason, player.getName(), tyme));
			
			if (Bukkit.getPlayer(args[0]).isOnline())
				Bukkit.getPlayer(args[0]).kickPlayer("§cVocê foi banido!");
			
			player.sendMessage("§aJogador punido.");
		}
		
		return false;
	}
}

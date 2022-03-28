package blockboost;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class Comandos implements CommandExecutor {
	public boolean onCommand(org.bukkit.command.CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(" ");
			sender.sendMessage("§c§lBLOCK BOOST:");
			sender.sendMessage("§cApenas jogadores podem executar este comando.");
			sender.sendMessage(" ");
			return false;
		}

		Player p = (Player) sender;
		if (command.getName().equalsIgnoreCase("blockboost")) {

			if (!p.hasPermission("bb.create")) {
				p.sendMessage(" ");
				p.sendMessage("§c§lBLOCK BOOST:");
				p.sendMessage(" §cVocê não tem permissão para executar este comando.");
				p.sendMessage(" ");
				p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 1.0F);
				return true;
			}
			
			blockboost.Inventarios.Inventarios.inicial(p);

		}
		return false;
	}
}

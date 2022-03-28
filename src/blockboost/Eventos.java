package blockboost;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import blockboost.Metodos.Metodos;

public class Eventos implements Listener {

	@EventHandler
	public void aoEntrar(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if(Metodos.hasUpdates()) {
			
			p.sendMessage(" ");
			p.sendMessage("§b§l" + Main.pluginName + ":");
			p.sendMessage(" §bO plugin §f" + Main.receberMain().getName() + " v" + Main.receberMain().getDescription().getVersion() + " §bpossui atualizações disponíveis.");
			p.sendMessage(" §bUltima versão: §fv" + Metodos.getLatestVersion());
			p.sendMessage(" ");
			p.sendMessage(" §bEfetue o download da versão em §fhttp://maniackspvp.com.br/plugins/" + Main.pluginID);
			p.sendMessage(" ");
			
		}
	}

}

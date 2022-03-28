package blockboost.Metodos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class GerenciadorDeValidade {
	
	public static File arquivoSeguranca;
	public static FileConfiguration seguranca;
	
	private static GerenciadorDeValidade carregar = new GerenciadorDeValidade();

	public static GerenciadorDeValidade carregarArquivos() {
		return carregar;
	}
	
	public void criarArquivos(Plugin plugin) {
		arquivoSeguranca = new File(plugin.getDataFolder(), "seguranca.yml");
		
		if (!arquivoSeguranca.exists()) {
			plugin.saveResource("seguranca.yml", false);
		}
		
		seguranca = YamlConfiguration.loadConfiguration(arquivoSeguranca);
	}
	
	public FileConfiguration receberSeguranca() {
		return seguranca;
	}
	
	public void salvarSeguranca() {
		try {
			seguranca.save(arquivoSeguranca);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe("Nao foi possivel salvar o arquivo de segurança.");
		}
	}
	
	public void recarregarSeguranca() {
		seguranca = YamlConfiguration.loadConfiguration(arquivoSeguranca);
	}
}
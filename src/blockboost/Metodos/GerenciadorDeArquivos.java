package blockboost.Metodos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class GerenciadorDeArquivos {
	
	public static File arquivoValores;
	public static FileConfiguration valores;
	
	private static GerenciadorDeArquivos carregar = new GerenciadorDeArquivos();

	public static GerenciadorDeArquivos carregarArquivos() {
		return carregar;
	}
	
	public void criarArquivos(Plugin plugin) {
		arquivoValores = new File(plugin.getDataFolder(), "blocos.yml");
		
		if (!arquivoValores.exists()) {
			plugin.saveResource("blocos.yml", false);
		}
		
		valores = YamlConfiguration.loadConfiguration(arquivoValores);
	}
	
	public FileConfiguration receberBlocos() {
		return valores;
	}
	
	public void salvarBlocos() {
		try {
			valores.save(arquivoValores);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe("Nao foi possivel salvar o arquivo de blocos.");
		}
	}
	
	public void recarregarBlocos() {
		valores = YamlConfiguration.loadConfiguration(arquivoValores);
	}
}
package blockboost;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.mysql.jdbc.Connection;

import blockboost.Metodos.GerenciadorDeArquivos;
import blockboost.Metodos.Metodos;
import blockboost.Metodos.MetodosPlugins;

public class Main extends JavaPlugin {
	
	public static int taskValidade;
	
	public static URL ver;
	
	public static Main m;

	public static Main receberMain() {
		return m;
	}
	
	public static ArrayList<Player> queda = new ArrayList<Player>();
	public static ArrayList<Player> impulso = new ArrayList<Player>();
	public static ArrayList<Player> impulsoReto = new ArrayList<Player>();
	
	public static String pluginName = "BLOCKBOOST";
	public static int pluginID = 135859387;
	
	public void onEnable() {
		m = this;
		GerenciadorDeArquivos.carregarArquivos().criarArquivos(m);
		
		executarEnable();
		
		
	}

	private static void executarEnable() {
		Bukkit.getConsoleSender().sendMessage(" ");
		Bukkit.getConsoleSender().sendMessage("§a§l" + pluginName + ":");
		Bukkit.getConsoleSender().sendMessage(" §aO plugin §f" + m.getName() + " v" + m.getDescription().getVersion() + " §afoi iniciado com sucesso.");
		Bukkit.getConsoleSender().sendMessage(" §aUtilize §f/bb ajuda §apara obter ajuda.");
		Bukkit.getConsoleSender().sendMessage("  ");
		Bukkit.getConsoleSender().sendMessage(" §aObtenha a validade da sua licença em nosso Discord.");
		Bukkit.getConsoleSender().sendMessage(" ");
		
		//Bukkit.getConsoleSender().sendMessage("[" + Main.receberMain().getName() + "] Verificando por atualizações...");
		//Metodos.checkUpdates();
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.receberMain(), new Runnable() {
			public void run() {
				for (Player todos : Bukkit.getOnlinePlayers()) {
					Location bloco1 = todos.getLocation().subtract(0, 1, 0);
					boolean eBloco = GerenciadorDeArquivos.carregarArquivos().receberBlocos().contains("Blocos." + Metodos.locToString(bloco1));
					
					if(eBloco) {
						int impulso = GerenciadorDeArquivos.carregarArquivos().receberBlocos().getInt("Blocos." + Metodos.locToString(bloco1) + ".Impulso");
						String tipo = GerenciadorDeArquivos.carregarArquivos().receberBlocos().getString("Blocos." + Metodos.locToString(bloco1) + ".Tipo");
						if(tipo.equals("Jumppad")) {
							todos.setVelocity(new Vector(todos.getVelocity().getX(), impulso, todos.getVelocity().getZ()));
							todos.playSound(todos.getLocation(), Sound.IRONGOLEM_HIT, 1.0F, 10.0F);
							
						} else if(tipo.equals("Impulso")) {
							Vector unitVector = new Vector(todos.getLocation().getDirection().getX(), 0, todos.getLocation().getDirection().getZ());
							unitVector = unitVector.normalize();             
							todos.setVelocity(unitVector.multiply(impulso));
							todos.playSound(todos.getLocation(), Sound.IRONGOLEM_HIT, 1.0F, 10.0F);
						}
					}
				}
			}
		}, 0L, 10L);
		
		m.getCommand("blockboost").setExecutor(new Comandos());
		Bukkit.getPluginManager().registerEvents(new blockboost.Inventarios.Eventos(), m);
		Bukkit.getPluginManager().registerEvents(new blockboost.Eventos(), m);
	}
}

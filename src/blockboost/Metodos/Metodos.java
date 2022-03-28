package blockboost.Metodos;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import blockboost.Main;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

public class Metodos {
	
	public static void limparChat(Player p) {
		for (int i = 0; i < 150; i++) {
			p.sendMessage("  ");
		}
	}
	
	public static String locToString(Location location) {
		return String.valueOf(location.getWorld().getName()) + "," + location.getBlockX() + "," + location.getBlockY()
				+ "," + location.getBlockZ();
	}
	
	public static String getTimestampDate(Long timestamp) {
		DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		Instant instantC = Instant.ofEpochSecond(timestamp);
		Date dateC = Date.from( instantC );
        String diaC = formatador.format(dateC);
        return diaC;
	}
	
	public static String generateRandomNumber(int size) {
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int index = (int) (chars.length() * Math.random());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
	
	static boolean resulted = false;
	
	public static String getLatestVersion() {
		String res = "";
		
		try {
			Bukkit.getScheduler().scheduleSyncDelayedTask(Main.receberMain(), new Runnable() {
                public void run() {
                	if(resulted) {
                		Bukkit.getConsoleSender().sendMessage("[" + Main.receberMain().getName() + "] Ocorreu um erro para obter as atualizações.");
                		return;
                	}
                }
            },5*20L);
			
			URLConnection connection = new URL("http://maniackspvp.com.br/api/plugins/" + Main.pluginID + "/version/").openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
			connection.setConnectTimeout(5000);

			BufferedReader r  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));

			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = r.readLine()) != null) {
			    sb.append(line);
			}
			res = sb.toString();
			resulted = true;

		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage("[" + Main.receberMain().getName() + "] Ocorreu um erro para obter as atualizações.");
			res = "";
		}
		return res;
	}
	
	public static boolean hasUpdates() {
		String latest = getLatestVersion();
		String plugin = Main.receberMain().getDescription().getVersion();
		
		if(plugin.equals(latest)) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void checkUpdates() {
		if(Metodos.hasUpdates()) {
			String latest = Metodos.getLatestVersion();
			if(latest == "") {
				
			} else {
				Bukkit.getConsoleSender().sendMessage("[" + Main.receberMain().getName() + "] 1 nova atualização encontrada.");
				Bukkit.getConsoleSender().sendMessage(" ");
				Bukkit.getConsoleSender().sendMessage("§b§l" + Main.pluginName + ":");
				Bukkit.getConsoleSender().sendMessage(" §bO plugin §f" + Main.receberMain().getName() + " v" + Main.receberMain().getDescription().getVersion() + " §bpossui atualizacoes disponiveis.");
				Bukkit.getConsoleSender().sendMessage(" §bUltima versao: §fv" + latest);
				Bukkit.getConsoleSender().sendMessage(" ");
				Bukkit.getConsoleSender().sendMessage(" §bEfetue o download da versao em §fhttp://maniackspvp.com.br/plugins/" + Main.pluginID);
				Bukkit.getConsoleSender().sendMessage(" ");
			}
		} else {
			Bukkit.getConsoleSender().sendMessage("[" + Main.receberMain().getName() + "] Não há atualizações disponíveis.");
		}
	}
	
	@SuppressWarnings("resource")
	static String convertStreamToString(java.io.InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

}

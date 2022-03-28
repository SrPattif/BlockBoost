package blockboost.Metodos;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import com.google.gson.JsonObject;

public class MetodosPlugins {
	
    public static void unload(Plugin plugin) {

        PluginManager pluginManager = Bukkit.getPluginManager();

        if (pluginManager != null) {
            pluginManager.disablePlugin(plugin);
        }
    }
    
    public static Plugin getPluginByName(String name) {
        for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
            if (name.equalsIgnoreCase(plugin.getName())) {
                return plugin;
            }
        }
        return null;
    }
    
    public static void sendToDiscord(String content) {
		try {
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("content", content);

			HttpsURLConnection connection = (HttpsURLConnection) new URL("https://discord.com/api/webhooks/829786683800944660/srMN2gXVJBrV3woPRVI3pLVgtVNTwp8-hDdjZG4pLJPVKKI5QyED3dD7qwtIkmRQUDmZ").openConnection();

			connection.addRequestProperty("Content-Type", "application/json");
			connection.addRequestProperty("User-Agent", "Java-DiscordWebhook");
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");

			OutputStream stream = connection.getOutputStream();
			stream.write(jsonObject.toString().getBytes());
			stream.flush();
			stream.close();

			connection.getInputStream().close();
			connection.disconnect();
		} catch (IOException e) {
			System.out.println("ERRO - LOGGER: " + e);
		}
	}
	
}

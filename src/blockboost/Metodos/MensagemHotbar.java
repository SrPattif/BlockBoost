package blockboost.Metodos;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class MensagemHotbar {

	 public static void send(Player p, String msg) {
         String s = ChatColor.translateAlternateColorCodes('&', msg);
         IChatBaseComponent icbc = ChatSerializer.a("{\"text\": \"" + s +
                 "\"}");
         PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
         ((CraftPlayer)p).getHandle().playerConnection.sendPacket(bar);
     }
	
}

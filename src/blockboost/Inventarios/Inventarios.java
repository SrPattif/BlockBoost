package blockboost.Inventarios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventarios {
	public static void inicial(Player p) {
		Inventory gui = Bukkit.createInventory(null, 9 * 3, "Blocos de Impulso:");

		ItemStack jump = new ItemStack(Material.STICK, 1);
		ItemMeta jumpMeta = jump.getItemMeta();
		jumpMeta.setDisplayName("§aCriar Bloco de Salto");
		jump.setItemMeta(jumpMeta);

		ItemStack boost = new ItemStack(Material.STICK, 1);
		ItemMeta boostMeta = boost.getItemMeta();
		boostMeta.setDisplayName("§aCriar Bloco de Impulso");
		boost.setItemMeta(boostMeta);

		ItemStack remover = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta removerMeta = remover.getItemMeta();
		removerMeta.setDisplayName("§cRemover Bloco");
		remover.setItemMeta(removerMeta);

		gui.setItem(11, jump);
		gui.setItem(13, remover);
		gui.setItem(15, boost);

		p.openInventory(gui);
		p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 10.0F);
	}
}

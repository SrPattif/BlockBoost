package blockboost.Inventarios;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import blockboost.Main;
import blockboost.Metodos.GerenciadorDeArquivos;
import blockboost.Metodos.Metodos;

public class Eventos implements Listener {
	
	@EventHandler
	public void aoClicar(InventoryClickEvent e) {
		if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) { return; }
		
		if(e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			
			ItemStack remover = new ItemStack(Material.BLAZE_ROD, 1);
			ItemMeta removerMeta = remover.getItemMeta();
			removerMeta.setDisplayName("§cRemover Bloco de Impulso");
			removerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			removerMeta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
			remover.setItemMeta(removerMeta);
			
			if(e.getInventory().getName().equals("Blocos de Impulso:")) {
				e.setCancelled(true);
				
				String name = e.getCurrentItem().getItemMeta().getDisplayName();
				if(name.equals("§aCriar Bloco de Salto")) {
					p.closeInventory();
					Metodos.limparChat(p);
					p.sendMessage(" ");
					p.sendMessage("§e§lBLOCK BOOST:");
					p.sendMessage(" §eEnvie no chat o nível do impulso do bloco de salto.");
					p.sendMessage(" §eEnvie §f'Cancelar' §epara cancelar a configuração.");
					p.sendMessage(" ");
					p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 10.0F);
					
					Main.impulso.add(p);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.receberMain(), new Runnable() {
	                    public void run() {
	                    	if(Main.impulso.contains(p)) {
	                    		Main.impulso.remove(p);
	                    				
	                    		p.sendMessage(" ");
	        					p.sendMessage("§c§lBLOCK BOOST:");
	        					p.sendMessage(" §cO tempo para configurar o bloco acabou.");
	        					p.sendMessage(" §cPara tentar novamente, digite §f/bb§c.");
	        					p.sendMessage(" ");
	        					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 10.0F);
	        					return;
	                    	}
	                    	
	                    }
	                },4*20L);
				} else if(name.equals("§cRemover Bloco")) {
					p.sendMessage(" ");
					p.sendMessage("§e§lBLOCK BOOST:");
					p.sendMessage(" §eClique com o botão direito para remover algum bloco.");
					p.sendMessage(" ");
					p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 10.0F);
					
					p.closeInventory();
					p.getInventory().addItem(remover);
					
				} else if (name.equals("§aCriar Bloco de Impulso")) {
					p.closeInventory();
					Metodos.limparChat(p);
					p.sendMessage(" ");
					p.sendMessage("§e§lBLOCK BOOST:");
					p.sendMessage(" §eEnvie no chat o nível do impulso do bloco de impulso.");
					p.sendMessage(" §eEnvie §f'Cancelar' §epara cancelar a configuração.");
					p.sendMessage(" ");
					p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1.0F, 10.0F);
					
					Main.impulsoReto.add(p);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.receberMain(), new Runnable() {
	                    public void run() {
	                    	if(Main.impulsoReto.contains(p)) {
	                    		Main.impulsoReto.remove(p);
	                    				
	                    		p.sendMessage(" ");
	        					p.sendMessage("§c§lBLOCK BOOST:");
	        					p.sendMessage(" §cO tempo para configurar o bloco acabou.");
	        					p.sendMessage(" §cPara tentar novamente, digite §f/bb§c.");
	        					p.sendMessage(" ");
	        					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 10.0F);
	        					return;
	                    	}
	                    	
	                    }
	                },4*20L);
				}
			}
		}
	}
	
	@EventHandler
	public void interact (PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(p.getItemInHand().getItemMeta() == null) { return; }
			if(p.getItemInHand().getItemMeta().getDisplayName() == null) { return; }
			
			Location blockLoc = e.getClickedBlock().getLocation();
			
			if(p.getItemInHand().getItemMeta().getDisplayName().equals("§aCriar Bloco de Salto")) {
				if(GerenciadorDeArquivos.carregarArquivos().receberBlocos().contains("Blocos." + Metodos.locToString(blockLoc))) {
					blockboost.Metodos.MensagemHotbar.send(p, "§cEste bloco já foi definido.");
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 10.0F);
					return;
				}

				ArrayList<String> lore = new ArrayList<>();
				lore = (ArrayList<String>) p.getItemInHand().getItemMeta().getLore();
				
				int impulso = 1;
				try {
					impulso = Integer.parseInt(lore.get(2).substring(13));
				} catch (Exception e2) {
					return;
				}
				
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Impulso", impulso);
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Tipo", "Jumppad");
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Localizacao.X", blockLoc.getX());
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Localizacao.Y", blockLoc.getY());
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Localizacao.Z", blockLoc.getZ());
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Localizacao.Mundo", blockLoc.getWorld().getName());
				GerenciadorDeArquivos.carregarArquivos().salvarBlocos();
				GerenciadorDeArquivos.carregarArquivos().recarregarBlocos();
				p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 10.0F);
				
			} else if(p.getItemInHand().getItemMeta().getDisplayName().equals("§cRemover Bloco de Impulso")) {
				if(GerenciadorDeArquivos.carregarArquivos().receberBlocos().contains("Blocos." + Metodos.locToString(blockLoc))) {
					
					GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc), null);
					GerenciadorDeArquivos.carregarArquivos().salvarBlocos();
					GerenciadorDeArquivos.carregarArquivos().recarregarBlocos();
					blockboost.Metodos.MensagemHotbar.send(p, "§aBloco removido com sucesso.");
					p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 10.0F);
					
				} else {
					blockboost.Metodos.MensagemHotbar.send(p, "§cEste bloco não foi definido.");
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 10.0F);
					return;
				}
			} else if (p.getItemInHand().getItemMeta().getDisplayName().equals("§aCriar Bloco de Impulso")) {
				
				if(GerenciadorDeArquivos.carregarArquivos().receberBlocos().contains("Blocos." + Metodos.locToString(blockLoc))) {
					blockboost.Metodos.MensagemHotbar.send(p, "§cEste bloco já foi definido.");
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 10.0F);
					return;
				}

				ArrayList<String> lore = new ArrayList<>();
				lore = (ArrayList<String>) p.getItemInHand().getItemMeta().getLore();
				
				int impulso = 1;
				try {
					impulso = Integer.parseInt(lore.get(2).substring(13));
				} catch (Exception e2) {
					return;
				}
				
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Impulso", impulso);
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Tipo", "Impulso");
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Localizacao.X", blockLoc.getX());
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Localizacao.Y", blockLoc.getY());
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Localizacao.Z", blockLoc.getZ());
				GerenciadorDeArquivos.carregarArquivos().receberBlocos().set("Blocos." + Metodos.locToString(blockLoc) + ".Localizacao.Mundo", blockLoc.getWorld().getName());
				GerenciadorDeArquivos.carregarArquivos().salvarBlocos();
				GerenciadorDeArquivos.carregarArquivos().recarregarBlocos();
				p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 10.0F);
				
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void chat (AsyncPlayerChatEvent e) {
		
		Player p = e.getPlayer();
		String mensagem = e.getMessage();
		
		if(Main.impulso.contains(p)) {
			Main.impulso.remove(p);
			e.setCancelled(true);
			
			if(mensagem.equalsIgnoreCase("cancelar")) {
				p.sendMessage(" ");
				p.sendMessage("§a§lBLOCK BOOST:");
				p.sendMessage(" §aConfiguração cancelada com sucesso.");
				p.sendMessage(" ");
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 10.0F);
				return;
			} else {
				int impulso = 1;
				try {
					impulso = Integer.parseInt(mensagem);
				} catch (Exception e2) {
					p.sendMessage(" ");
					p.sendMessage("§c§lBLOCK BOOST:");
					p.sendMessage(" §cInsira um número válido.");
					p.sendMessage(" ");
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 10.0F);
					return;
				}
				
				ArrayList<String> jumpLore = new ArrayList<>();
				ItemStack jump = new ItemStack(Material.STICK, 1);
				ItemMeta jumpMeta = jump.getItemMeta();
				jumpMeta.setDisplayName("§aCriar Bloco de Salto");
				jumpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				jumpMeta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
				jumpLore.add("§7Clique com o botão direito para criar um bloco de salto.");
				jumpLore.add(" ");
				jumpLore.add("§7Impulso: §f" + impulso);
				jumpMeta.setLore(jumpLore);
				jump.setItemMeta(jumpMeta);
				
				
				p.getInventory().addItem(jump);
				
				p.sendMessage(" ");
				p.sendMessage("§a§lBLOCK BOOST:");
				p.sendMessage(" §aConfigurador criado com sucesso.");
				p.sendMessage(" §aClique com o botão direito em algum bloco para criar um Bloco de Salto.");
				p.sendMessage(" ");
				p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 10.0F);
				return;
			}
			
			
		} else if (Main.impulsoReto.contains(p)) {
			Main.impulsoReto.remove(p);
			e.setCancelled(true);
			
			if(mensagem.equalsIgnoreCase("cancelar")) {
				p.sendMessage(" ");
				p.sendMessage("§a§lBLOCK BOOST:");
				p.sendMessage(" §aConfiguração cancelada com sucesso.");
				p.sendMessage(" ");
				p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1.0F, 10.0F);
				return;
			} else {
				int impulso = 1;
				try {
					impulso = Integer.parseInt(mensagem);
				} catch (Exception e2) {
					p.sendMessage(" ");
					p.sendMessage("§c§lBLOCK BOOST:");
					p.sendMessage(" §cInsira um número válido.");
					p.sendMessage(" ");
					p.playSound(p.getLocation(), Sound.NOTE_BASS, 1.0F, 10.0F);
					return;
				}
				
				ArrayList<String> jumpLore = new ArrayList<>();
				ItemStack jump = new ItemStack(Material.STICK, 1);
				ItemMeta jumpMeta = jump.getItemMeta();
				jumpMeta.setDisplayName("§aCriar Bloco de Impulso");
				jumpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				jumpMeta.addEnchant(Enchantment.SILK_TOUCH, 1, false);
				jumpLore.add("§7Clique com o botão direito para criar um bloco de impulso.");
				jumpLore.add(" ");
				jumpLore.add("§7Impulso: §f" + impulso);
				jumpMeta.setLore(jumpLore);
				jump.setItemMeta(jumpMeta);
				
				
				p.getInventory().addItem(jump);
				
				p.sendMessage(" ");
				p.sendMessage("§a§lBLOCK BOOST:");
				p.sendMessage(" §aConfigurador criado com sucesso.");
				p.sendMessage(" §aClique com o botão direito em algum bloco para criar um Bloco de Impulso.");
				p.sendMessage(" ");
				p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0F, 10.0F);
				return;
			}
		}
	}
	
	
	// I G N O R A R
	
}

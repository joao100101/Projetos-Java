package me.joao.principal;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import me.joao.conexao.Conexao;
import me.joao.conexao.Querys;
import me.joao.conexao.Tipo;
import me.joao.picareta.Picareta;

public class Eventos implements Listener {
	private Conexao con = new Conexao();
	private Querys qr = new Querys(con);
	public static HashMap<UUID, Integer> blocos = new HashMap<>();
	public static HashMap<UUID, Picareta> picks = new HashMap<>();

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		if (e.getAction() != Action.LEFT_CLICK_AIR && e.getAction() != Action.LEFT_CLICK_BLOCK) {
			if (e.getItem() == new ItemStack(Material.GOLDEN_PICKAXE)) {
				UUID uid = e.getPlayer().getUniqueId();
				if (!picks.containsKey(uid)) {
					if (qr.exists(uid.toString(), Tipo.UUID)) {
						if (e.getItem().getItemMeta().getLore().contains(qr.getCode(e.getPlayer().getUniqueId()))) {
							Picareta pick = new Picareta(uid, qr.getLevel(uid), qr.getBlocos(uid), qr.getCode(uid));

						}
					}
				}
			}
		}
	}
}

package me.joao.principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.joao.conexao.Conexao;
import me.joao.conexao.Querys;
import me.joao.conexao.Tipo;
import me.joao.picareta.Picareta;
import me.joao.utils.CodeFactory;

public class Comandos implements CommandExecutor {
	public HashMap<UUID, Long> cd = new HashMap<UUID, Long>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("§cEsse comando so pode ser usado in-game.");
			return true;
		}
		Player p = (Player) sender;
		Conexao con = new Conexao();
		Querys qr = new Querys(con);
		if (cmd.getName().equalsIgnoreCase("picareta")) {
			if (args.length > 0) {
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("criar")) {
						if (inCooldown(p)) {
							p.sendMessage("§cVoce precisa aguardar o cooldown acabar");
						} else {
							if (!(qr.exists(p.getUniqueId().toString(), Tipo.UUID))) {

								CodeFactory cf = new CodeFactory();
								Picareta pickaxe = new Picareta(p.getUniqueId(), 0, 0, cf.newCode());
								ItemStack pick = new ItemStack(Material.WOODEN_PICKAXE);
								ItemMeta pmeta = pick.getItemMeta();

								pmeta.setDisplayName("§5§lPICARETA DE HEFESTO");
								ArrayList<String> lore = new ArrayList<String>();
								lore.add("§6Level: §e" + pickaxe.getLevel());
								lore.add("§6Blocos Quebrados: §e" + pickaxe.getBlocos_quebrados());
								lore.add("§6Dono: §e" + Bukkit.getPlayer(pickaxe.getDono()).getName());
								lore.add("§6Codigo: §e" + pickaxe.getCodigo_unico());
								pmeta.setLore(lore);
								pmeta.setUnbreakable(true);

								pick.setItemMeta(pmeta);

								p.getInventory().addItem(pick);
								qr.inserir(pickaxe);
								p.sendMessage("§3§lPICARETAS §bVoce recebeu uma picareta que evolui!");
								cd.put(p.getUniqueId(), System.currentTimeMillis());
							} else {
								p.sendMessage("§3§lPICARETAS §cVoce já possui uma picareta cadastrada.");
								p.sendMessage(
										"§3§lPICARETAS §cCaso tenha perdido use §4/kit picareta §ce clique com o botao direito segurando a picareta recebida.");

								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}

	public boolean inCooldown(Player p) {
		boolean retorno = false;
		if (cd.containsKey(p.getUniqueId())) {
			int tempo_cooldown = 60;
			long time_left = ((cd.get(p.getUniqueId()) / 1000) + tempo_cooldown) - (System.currentTimeMillis() / 1000);

			if (time_left > 0) {
				retorno = true;
			} else {
				retorno = false;
			}
		}
		return retorno;
	}
}
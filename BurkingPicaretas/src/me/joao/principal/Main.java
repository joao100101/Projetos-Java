package me.joao.principal;


import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	
	@Override
	public void onEnable() {
		getCommand("picareta").setExecutor(new Comandos());
	}
	
	@Override
	public void onDisable() {
	}
	
}

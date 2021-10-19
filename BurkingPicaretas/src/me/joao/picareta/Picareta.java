package me.joao.picareta;

import java.util.UUID;

import org.bukkit.Bukkit;

import me.joao.conexao.Conexao;
import me.joao.conexao.Querys;
import me.joao.conexao.Tipo;

public class Picareta {
	private UUID dono;
	private int blocos_quebrados;
	private int level;
	protected String codigo_unico;

	public Picareta() {

	}

	public Picareta(UUID dono, int blocos, int level, String code) {
		Conexao con = new Conexao();
		Querys qr = new Querys(con);
		if (qr.exists(dono.toString(), Tipo.UUID)) {
			System.out.println("Esse usuario ja tem uma picareta.");
			return;
		} else {
			this.dono = dono;
			this.blocos_quebrados = blocos;
			this.level = level;
			this.codigo_unico = code;
		}
	}

	public String toString() {
		return "Dono: " + Bukkit.getPlayer(dono).getName();
	}

	public UUID getDono() {
		return dono;
	}

	public void setDono(UUID dono) {
		Conexao con = new Conexao();
		Querys qr = new Querys(con);
		if (qr.exists(dono.toString(), Tipo.UUID)) {
			System.out.println("Esse player já tem picareta.");
			return;
		}
		this.dono = dono;
	}

	public int getBlocos_quebrados() {
		return blocos_quebrados;
	}

	public void setBlocos_quebrados(int blocos_quebrados) {
		this.blocos_quebrados = blocos_quebrados;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(byte level) {
		this.level = level;
	}

	public String getCodigo_unico() {
		return codigo_unico;
	}

	public void setCodigo_unico(String codigo_unico) {
		this.codigo_unico = codigo_unico;
	}

}

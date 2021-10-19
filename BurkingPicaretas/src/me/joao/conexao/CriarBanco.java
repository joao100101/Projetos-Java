package me.joao.conexao;

import java.sql.SQLException;
import java.sql.Statement;

public class CriarBanco {
	private final Conexao conexao;

	public CriarBanco(Conexao con) {
		this.conexao = con;
	}

	public void criarTabela() {
		String sql = "CREATE TABLE IF NOT EXISTS tbl_picaretas(" + "id integer PRIMARY KEY AUTOINCREMENT,"
				+ "UUID text NOT NULL UNIQUE," + "blocos integer DEFAULT 0," + "level integer DEFAULT 0,"
				+ "codigo text NOT NULL" + ");";
		boolean conectou = false;
		Statement stmt = null;
		try {
			conectou = this.conexao.conectar();
			stmt = this.conexao.criarStatement();
			stmt.execute(sql);
			System.out.println("[BurkingPicaretas] Tabela picaretas criada.");
		} catch (SQLException e) {
			System.out.println("[BurkingPicaretas] Erro ao criar tabela.");
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar statement");
			}
			if (conectou) {
				this.conexao.desconectar();
			}
		}
	}
}

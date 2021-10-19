package me.joao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
	private Connection conexao;

	/*
	 * conecta a um banco ou cria se ele nao existir
	 */
	public boolean conectar() {
		try {
			String url = "jdbc:sqlite:banco_de_dados/picaretas.db";

			this.conexao = DriverManager.getConnection(url);

		} catch (SQLException e) {
			System.out.println("[BurkingPicaretas] Erro ao conectar ao banco.");
		}
		//System.out.println("Conectou!");
		return true;
	}

	public boolean desconectar() {
		try {
			if (this.conexao.isClosed() == false) {
				this.conexao.close();
			}
		} catch (SQLException e) {
			System.out.println("[BurkingPicaretas] Erro ao desconectar do banco de dados");
			return false;
		}
		//System.out.println("Desconectou!");
		return true;
	}

	/*
	 * criar os staatements para os nossos sqls serem executados no caso as
	 * instruções cruas do sql
	 */
	public Statement criarStatement() {
		try {
			return this.conexao.createStatement();
		} catch (SQLException e) {
			System.out.println("[BurkingPicaretas] Erro ao criar statement.");
			return null;
		}
	}

	public PreparedStatement criarPreparedStatement(String sql) {
		try {
			return this.conexao.prepareStatement(sql);
		} catch (SQLException e) {
			System.out.println("[BurkingPicaretas] Erro ao criar prepared statement.");
			return null;
		}
	}

	public Connection getConexao() {
		return this.conexao;
	}
}

package me.joao.principal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import me.joao.conexao.Conexao;

public class Testes {
	public static void main(String[] args) {
		Conexao con = new Conexao();

		Statement stmt = null;
		ResultSet rs = null;
		String query = "select * from tbl_picaretas;";
		con.conectar();
		stmt = con.criarStatement();

		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				System.out.println("Dados das picaretas: ");
				System.out.println("ID: " + rs.getInt("id"));
				System.out.println("UUID: " + rs.getString("UUID"));
				System.out.println("Blocos Quebrados: " + rs.getInt("blocos"));
				System.out.println("Level: " + rs.getInt("level"));
				System.out.println("Codigo Unico: " + rs.getString("codigo"));
				System.out.println("-----------------------------");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao constular o banco");
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar conexao.");
			}
			con.desconectar();
		}
	}
}

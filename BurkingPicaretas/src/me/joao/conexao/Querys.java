package me.joao.conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import me.joao.picareta.Picareta;

public class Querys {

	protected Conexao con;
	private String db = "tbl_picaretas";

	public Querys(Conexao con) {
		this.con = con;
	}

	public Querys(Conexao con, String db) {
		this.con = con;
		this.db = db;
	}

	public void inserir(Picareta pick) {
		String sqlInsert = "INSERT INTO " + db + " (id,UUID,blocos,level,codigo) VALUES(?,?,?,?,?);";
		con.conectar();
		PreparedStatement pstmt = this.con.criarPreparedStatement(sqlInsert);
		try {
			pstmt.setString(1, null);
			pstmt.setString(2, pick.getDono().toString());
			pstmt.setInt(3, pick.getBlocos_quebrados());
			pstmt.setInt(4, pick.getLevel());
			pstmt.setString(5, pick.getCodigo_unico());
			int resultado = pstmt.executeUpdate();

			if (resultado == 1) {
				System.out.println("Picareta inserida.");
			} else {
				System.out.println("Ocorreu um erro ao inseriar a picareta.");
			}
		} catch (SQLException e) {
			System.out.println("[BurkingPicaretas] Erro ao inserir dados no banco.");
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			con.desconectar();
		}
	}

	public boolean exists(String toCompare, Tipo tipo) {
		String query = "";
		boolean retorno = false;
		if (tipo == Tipo.UUID) {
			query = "select UUID from " + db + " where UUID = " + toCompare + ";";
		} else if (tipo == Tipo.codigo) {
			query = "select codigo from " + db + " where codigo = " + toCompare + ";";
		}
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		con.conectar();
		pstmt = con.criarPreparedStatement(query);
		try {
			rs = pstmt.executeQuery();
			retorno = rs.next();
		} catch (SQLException e) {
			System.out.println("Erro ao checar valores no banco");
		} finally {
			try {
				pstmt.close();
				rs.close();
				con.desconectar();
			} catch (SQLException e) {
				System.out.println("erro ao fechar conexao");
			}
		}
		return retorno;
	}

	public int getBlocos(UUID uid) {
		String query = "select blocos from " + db + " where UUID = " + uid.toString();
		int blocos = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con.conectar();
		pstmt = con.criarPreparedStatement(query);
		try {
			rs = pstmt.executeQuery();
			blocos = rs.getInt("blocos");
		} catch (SQLException e) {
			System.out.println("Erro ao checar valores no banco");
		} finally {
			try {
				pstmt.close();
				rs.close();
				con.desconectar();
			} catch (SQLException e) {
				System.out.println("erro ao fechar conexao");
			}
		}
		return blocos;
	}

	public int getLevel(UUID uid) {
		String query = "select level from " + db + " where UUID = " + uid.toString();
		int level = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		con.conectar();
		pstmt = con.criarPreparedStatement(query);
		try {
			rs = pstmt.executeQuery();
			level = rs.getInt("level");
		} catch (SQLException e) {
			System.out.println("Erro ao checar valores no banco");
		} finally {
			try {
				pstmt.close();
				rs.close();
				con.desconectar();
			} catch (SQLException e) {
				System.out.println("erro ao fechar conexao");
			}
		}
		return level;
	}

	public String getCode(UUID uid) {
		String query = "select UUID, codigo from " + db + " where UUID = " + uid.toString();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String codigo = "";

		con.conectar();
		pstmt = con.criarPreparedStatement(query);
		try {
			rs = pstmt.executeQuery();
			codigo = rs.getString("codigo");
		} catch (SQLException e) {
			System.out.println("Erro ao checar valores no banco");
		} finally {
			try {
				pstmt.close();
				rs.close();
				con.desconectar();
			} catch (SQLException e) {
				System.out.println("erro ao fechar conexao");
			}
		}
		return codigo;
	}

	public void dropTable() {
		String sql = "drop table " + db + ";";
		this.con.conectar();

		Statement stmt = this.con.criarStatement();
		try {
			stmt.executeUpdate(sql);
			System.out.println("Tabela deletada.");
		} catch (SQLException e) {
			System.out.println("Erro ao deletar a tabela.");
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			con.desconectar();
		}
	}

	public void removePlayer(UUID uid) {
		String sql = "delete from " + db + " where UUID = " + uid.toString() + ";";
		con.conectar();
		Statement stmt = con.criarStatement();
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao deletar player");
		} finally {
			try {
				stmt.close();
				con.desconectar();
			} catch (SQLException e) {
				System.out.println("Erro ao desconectar do banco");
			}
		}
	}
}

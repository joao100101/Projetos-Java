package me.joao.utils;

import java.util.concurrent.ThreadLocalRandom;

import me.joao.conexao.Conexao;
import me.joao.conexao.Querys;
import me.joao.conexao.Tipo;

public class CodeFactory {

	protected String codigo;

	public String newCode() {
		Conexao con = new Conexao();
		Querys qr = new Querys(con);
		int anterior = 90;
		String[] alphabet = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		StringBuilder sb = new StringBuilder();
		do {
			for (int i = 0; i < 12; i++) {
				int num = ThreadLocalRandom.current().nextInt(0, 35 + 1);
				if (anterior == num) {
					i -= 1;
					continue;
				}
				sb.append(alphabet[num]);
				anterior = num;
			}
			this.codigo = sb.toString();
		} while (qr.exists(codigo, Tipo.codigo));
		return this.codigo;
	}
}

package me.joao.bookstore.dtos;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.joao.bookstore.domain.Livro;

@Data
@NoArgsConstructor
public class LivroDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String titulo;

	public LivroDTO(Livro obj) {
		super();
		this.id = obj.getId();
		this.titulo = obj.getTitulo();
	}
}

package me.joao.bookstore.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.joao.bookstore.domain.Categoria;

@Data
@NoArgsConstructor
public class CategoriaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message = "Campo NOME é requerido.")
	@Length(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres.")
	private String nome;
	@NotEmpty(message = "Campo DESCRIÇÃO é requerido.")
	@Length(min = 3, max = 300, message = "O campo nome deve ter entre 3 e 300 caracteres.")
	private String descricao;

	public CategoriaDTO(Categoria obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.descricao = obj.getDescricao();
	}
}

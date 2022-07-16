package me.joao.bookstore.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
@Entity
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message = "Campo NOME é requerido.")
	@Length(min = 3, max = 100, message = "O campo nome deve ter entre 3 e 100 caracteres.")
	private String nome;
	@NotEmpty(message = "Campo DESCRIÇÃO é requerido.")
	@Length(min = 3, max = 300, message = "O campo nome deve ter entre 3 e 300 caracteres.")
	private String descricao;

	@OneToMany(mappedBy = "categoria")
	private List<Livro> livros = new ArrayList<>();

	public Categoria() {
		super();
	}

	public Categoria(Integer id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}
	public Categoria(String nome, String descricao) {
		super();
		this.id = null;
		this.nome = nome;
		this.descricao = descricao;
	}

}

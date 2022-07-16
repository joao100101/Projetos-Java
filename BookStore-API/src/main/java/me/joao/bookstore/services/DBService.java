package me.joao.bookstore.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.joao.bookstore.domain.Categoria;
import me.joao.bookstore.domain.Livro;
import me.joao.bookstore.repositories.CategoriaRepository;
import me.joao.bookstore.repositories.LivroRepository;

@Service
public class DBService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;

	public void instanceBD() {
		Categoria cat1 = new Categoria("Informática", "Livros de T.I");
		Categoria cat2 = new Categoria("Biografia", "Livros de Biografia");
		Categoria cat3 = new Categoria("Ficção Científica", "Livros de ficção");

		Livro l1 = new Livro(null, "Clean Code", "Robert Martin", "Lorem ipsum", cat1);
		Livro l2 = new Livro(null, "A História de Getúlio Vargas", "Arnaldo Romano", "Lorem ipsum", cat2);
		Livro l3 = new Livro(null, "O ano em que a terra parou", "Luciano Trigo", "Lorem ipsum", cat3);
		Livro l4 = new Livro(null, "Invasão Alien no Brasil", "Fransergio Soares", "Lorem ipsum", cat3);
		Livro l5 = new Livro(null, "Javascript para iniciantes", "Pedro Baptista", "Lorem ipsum", cat1);

		cat1.getLivros().addAll(Arrays.asList(l1, l5));
		cat2.getLivros().addAll(Arrays.asList(l2));
		cat3.getLivros().addAll(Arrays.asList(l4, l3));

		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		this.livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5));
	}
}

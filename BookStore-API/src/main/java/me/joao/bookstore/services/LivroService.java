package me.joao.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.joao.bookstore.domain.Categoria;
import me.joao.bookstore.domain.Livro;
import me.joao.bookstore.repositories.LivroRepository;
import me.joao.bookstore.services.exceptions.ObjectNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository rep;
	
	@Autowired
	private CategoriaService catService;
	
	public Livro findById(Integer id) {
		Optional<Livro> obj = rep.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: " + id + ", Tipo: " + Livro.class.getName()));
	}

	public List<Livro> findAll(Integer id_cat) {
		catService.findById(id_cat);
		
		return rep.findAllByCategoria(id_cat);		
	}

	public Livro update(Integer id, Livro obj) {
		Livro newObj = findById(id);
		updateData(newObj, obj);
		
		return rep.save(newObj);
		
	}

	private void updateData(Livro newObj, Livro obj) {
		if(obj.getTitulo() != null)newObj.setTitulo(obj.getTitulo());
		if(obj.getAutor() != null) newObj.setAutor(obj.getAutor());
		if(obj.getTexto() != null)newObj.setTexto(obj.getTexto());
	}

	public Livro create(Integer id_cat, Livro obj) {
		obj.setId(null);
		Categoria cat = catService.findById(id_cat);
		obj.setCategoria(cat);
		return rep.save(obj);
	}

	public void delete(Integer id) {
		Livro obj = findById(id);
		rep.delete(obj);
	}
}

package me.joao.bookstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import me.joao.bookstore.domain.Categoria;
import me.joao.bookstore.dtos.CategoriaDTO;
import me.joao.bookstore.repositories.CategoriaRepository;
import me.joao.bookstore.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository rep;

	public Categoria findById(Integer id) {
		Optional<Categoria> obj = rep.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public List<Categoria> findAll() {
		return rep.findAll();
	}

	public Categoria create(Categoria obj) {
		obj.setId(null);
		return rep.save(obj);
	}

	public Categoria update(Integer id, CategoriaDTO objDTO) {
		Categoria obj = findById(id);
		if (objDTO.getNome() != null)obj.setNome(objDTO.getNome());
		if (objDTO.getDescricao() != null)obj.setDescricao(objDTO.getDescricao());
		return rep.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			rep.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new me.joao.bookstore.services.exceptions.DataIntegrityViolationException("Categoria nao pode ser deletada, possui livros associados.");
		}
	}

}

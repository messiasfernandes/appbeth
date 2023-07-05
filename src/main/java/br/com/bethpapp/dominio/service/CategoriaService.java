package br.com.bethpapp.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoCategoria;
import br.com.bethpapp.dominio.entidade.Categoria;
import br.com.bethpapp.query.CategoriaSpec;

@Service
public class CategoriaService extends ServiceFuncoes implements ServiceModel<Categoria>{
	@Autowired
	private DaoCategoria daoCategoria;

	@Override
	public Page<Categoria> buscar(String nome, Pageable pageable) {
		CategoriaSpec categoriaspec = new CategoriaSpec();
		if ((ehnumero(nome) == true)) {
			Long id = Sonumero(nome);
	
			return	daoCategoria.findAll(categoriaspec.buscaid(id),pageable);
		}

		else {
			nome = maiuscula(nome);
		
			return daoCategoria.findAll(categoriaspec.buscar(nome).or(categoriaspec.buscaCategorias(nome)),pageable);
		}
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Categoria buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categoria salvar(Categoria objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}

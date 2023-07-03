package br.com.bethpapp.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoSubcategoria;
import br.com.bethpapp.dominio.entidade.SubCategoria;
import br.com.bethpapp.query.SubCategoriaSpec;
@Service
public class ServiceSubCategoria extends ServiceFuncoes implements ServiceModel<SubCategoria>{
	@Autowired
	private DaoSubcategoria daoSubcategoria;
	@Override
	public Page<SubCategoria> buscar(String nome, Pageable pageable) {
		SubCategoriaSpec subCategoriaSpec = new SubCategoriaSpec();
		if ((ehnumero(nome) == true)) {
			Long id = Sonumero(nome);

			return daoSubcategoria.findAll(subCategoriaSpec.buscaid(id), pageable);
		}

		else {
			nome = maiuscula(nome);

			return daoSubcategoria.findAll(subCategoriaSpec.buscar(nome).or(subCategoriaSpec.buscasubCategoriar(nome)),
					pageable);
		}
	}


	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SubCategoria buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubCategoria salvar(SubCategoria objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}

package br.com.bethpapp.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoEntradaNota;
import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
@Service
public class ServiceNotaFiscal implements ServiceModel<EntradaNotaCabecario>{
	@Autowired
	private DaoEntradaNota daoEntradaNota;
	@Override
	public Page<EntradaNotaCabecario> buscar(String nome, Pageable pageable) {
	
		return daoEntradaNota.buscar(nome, pageable);
	}

	@Override
	public void excluir(Long codigo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntradaNotaCabecario buccarporid(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntradaNotaCabecario salvar(EntradaNotaCabecario objeto) {
		// TODO Auto-generated method stub
		return null;
	}

}

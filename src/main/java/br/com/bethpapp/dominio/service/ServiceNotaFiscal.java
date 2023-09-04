package br.com.bethpapp.dominio.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoEntradaNota;
import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;
import br.com.bethpapp.dominio.enumerado.StatusEntradaNota;
import br.com.bethpapp.dominio.service.exeption.NegocioException;
import br.com.bethpapp.dominio.service.exeption.RegistroNaoEncontrado;
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
		if (daoEntradaNota.findById(id).isEmpty()) {
			throw new RegistroNaoEncontrado("Nota não encotrada");
		}
		return daoEntradaNota.findById(id).get();
	}

	@Override
	public EntradaNotaCabecario salvar(EntradaNotaCabecario objeto) {
		// TODO Auto-generated method stub
		return null;
	}
	public EntradaNotaCabecario CancelarNota(Long id) {
		
		var notafiscal= buccarporid(id);
		if (notafiscal.getStatusEntradaNota().equals(StatusEntradaNota.Cancelado)) {
			throw new NegocioException("Já foi cancelado esta Nota ");
		}
	     notafiscal.setData_cancelamento(LocalDate.now());
		return null;
	}

}

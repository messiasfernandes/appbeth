package br.com.bethpapp.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.bethpapp.dominio.entidade.EntradaNotaCabecario;

public interface EntradasdeNotaRepositoryQuery {
	Boolean buscarnota(Long codigoFonecedor, String numeroNota);
	Page<EntradaNotaCabecario> buscar(String paramentro, Pageable pageable);
}

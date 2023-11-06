package br.com.bethpapp.dominio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bethpapp.dominio.dao.DaoProduto;
import br.com.bethpapp.dominio.entidade.Produto;
import br.com.bethpapp.dominio.service.exeption.EntidadeEmUsoExeption;
import br.com.bethpapp.dominio.service.exeption.RegistroNaoEncontrado;
import jakarta.transaction.Transactional;

@Service
public class ServiceProduto extends ServiceFuncoes implements ServiceModel<Produto> {
	@Autowired
	private DaoProduto daoProduto;

	@Override
	public Page<Produto> buscar(String nome, Pageable pageable) {

		return daoProduto.buscar(nome.toUpperCase(), pageable);
	}

	@Transactional()
	@Override
	public void excluir(Long codigo) {

		buccarporid(codigo);
		try {

			daoProduto.deleteById(codigo);
			daoProduto.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoExeption(
					"Operação não permitida!! Este registro pode estar asssociado a outra tabela");
		}

		//daoProduto.deleteById(codigo);

	}

	@Override
	public Produto buccarporid(Long id) {
		if (daoProduto.findById(id).isEmpty()) {
			throw new RegistroNaoEncontrado("Produto não encotrada");
		}
		return daoProduto.findById(id).get();
	}

	@Transactional
	@Override
	public Produto salvar(Produto objeto) {
	   
		Produto produto = null;
		if (objeto.getAtributos().size() > 0) {
			objeto.setCaracteristica(concatenar(objeto));
		}
		if (objeto.getFornecedores().size() > 0) {
			objeto.setCaracteristica(concatenar(objeto));
		}
	
	
//		if (objeto.getFornecedor() != null && objeto.getFornecedor().getId() != null) {
//		    System.out.println("passou aqui");
//		    var fornecedor = daoForncedor.findById(objeto.getFornecedor().getId());
//		    
//		    if (fornecedor.isPresent()) {
//		        objeto.setFornecedor(fornecedor.get());
//		    	if (objeto.getComponentes().size() > 0) {
//					objeto.getComponentes().forEach(f-> objeto.getFornecedor());
//				}
//		    } else {
//		        // Lide com o caso em que o fornecedor não foi encontrado no banco de dados
//		    }
//		} else {
//		    // Lide com o caso em que o fornecedor ou seu ID são nulos
//		}
			produto = daoProduto.save(objeto);
	
		return produto;
	}

	private String concatenar(Produto objeto) {
		StringBuilder strBuilder = new StringBuilder();
		var tam = objeto.getAtributos().size() - 1;

		for (int i = 0; i < objeto.getAtributos().size(); i++) {

			if (i == tam) {

				// strBuilder.append(objeto.getAtributos().get(i).getTipo().concat(" : "));
				strBuilder.append(objeto.getAtributos().get(i).getValor().concat(" "));

			} else {

				// strBuilder.append(objeto.getAtributos().get(i).getTipo().concat(" :"));
				strBuilder.append(objeto.getAtributos().get(i).getValor().concat(" | "));
			}

		}

		return strBuilder.toString();

	}

	public Long buscarCodFabricante(String codigofabricante) {
		return daoProduto.isCodigoFabCadastrado(codigofabricante);
	}

	public Produto buscarporCodFabricante(String codigofabricante) {
		return daoProduto.findByCodigofabricante(codigofabricante);
	}
	
	public String  geararCodioEan13(String codigofabricante) {
		var codigoPais="789";
		return"";
	}
    public Produto salvarProdutoNota(Produto objeto) {
     return daoProduto.save(objeto)	;
    }
}

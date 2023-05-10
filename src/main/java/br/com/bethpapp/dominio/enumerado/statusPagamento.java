package br.com.bethpapp.dominio.enumerado;

public enum statusPagamento {
	Aberto(" Aberto"), Fechada("Fechado");

	private String descricao;

	private statusPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

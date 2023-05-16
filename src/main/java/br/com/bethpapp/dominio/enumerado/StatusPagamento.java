package br.com.bethpapp.dominio.enumerado;

public enum StatusPagamento {
	PENDENTE("Pendente"), QUITADO("Quitado"), CANCELADO("Cancelado"), PAGOPARCIAL("Pagamento Parcial");

	private String descricao;

	private StatusPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

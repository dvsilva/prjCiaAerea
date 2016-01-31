package controle;

public interface ICtrlManterAeroportos {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Aeroportos.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Aeroportos.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Aeroporto
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Aeroporto
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Aeroporto
	 */
	public abstract boolean incluir(String codAeroporto, String nome,
			String endereco, String telefones);

	/**
	 * M�todo que inicia o processo de altera��o de um Aeroporto
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Aeroporto
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Aeroporto
	 */
	public abstract boolean alterar(String codAeroporto, String nome,
			String endereco, String telefones);

	/**
	 * M�todo que inicia o processo de exclus�o de um Aeroporto
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Aeroporto
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Aeroporto
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
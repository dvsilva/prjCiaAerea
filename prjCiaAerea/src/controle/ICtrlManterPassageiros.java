package controle;

public interface ICtrlManterPassageiros {

	/**
	 * M�todo que dispara a execu��o do caso de uso Manter Passageiros.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso Manter Passageiros.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Passageiro
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Passageiro
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Passageiro
	 */
	public abstract boolean incluir(String cpf, String nome, String endereco,
			String telefone);

	/**
	 * M�todo que inicia o processo de altera��o de um Passageiro
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Passageiro
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Passageiro
	 */
	public abstract boolean alterar(String cpf, String nome, String endereco,
			String telefone);

	/**
	 * M�todo que inicia o processo de exclus�o de um Passageiro
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Passageiro
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Passageiro
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
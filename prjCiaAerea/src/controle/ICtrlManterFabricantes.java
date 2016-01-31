package controle;

public interface ICtrlManterFabricantes {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Fabricantes.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Fabricantes.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Fabricante
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Fabricante
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Fabricante
	 */
	public abstract boolean incluir(String nome, String telefone);

	/**
	 * M�todo que inicia o processo de altera��o de um Fabricante
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Fabricante
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Fabricante
	 */
	public abstract boolean alterar(String nome, String telefone);

	/**
	 * M�todo que inicia o processo de exclus�o de um Fabricante
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Fabricante
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Fabricante
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
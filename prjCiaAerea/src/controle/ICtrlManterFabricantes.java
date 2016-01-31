package controle;

public interface ICtrlManterFabricantes {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Fabricantes.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Fabricantes.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Fabricante
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Fabricante
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Fabricante
	 */
	public abstract boolean incluir(String nome, String telefone);

	/**
	 * Método que inicia o processo de alteração de um Fabricante
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Fabricante
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Fabricante
	 */
	public abstract boolean alterar(String nome, String telefone);

	/**
	 * Método que inicia o processo de exclusão de um Fabricante
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Fabricante
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Fabricante
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
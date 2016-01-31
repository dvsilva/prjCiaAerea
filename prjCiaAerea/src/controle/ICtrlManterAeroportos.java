package controle;

public interface ICtrlManterAeroportos {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Aeroportos.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Aeroportos.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Aeroporto
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Aeroporto
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Aeroporto
	 */
	public abstract boolean incluir(String codAeroporto, String nome,
			String endereco, String telefones);

	/**
	 * Método que inicia o processo de alteração de um Aeroporto
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Aeroporto
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Aeroporto
	 */
	public abstract boolean alterar(String codAeroporto, String nome,
			String endereco, String telefones);

	/**
	 * Método que inicia o processo de exclusão de um Aeroporto
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Aeroporto
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Aeroporto
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
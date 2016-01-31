package controle;

public interface ICtrlManterPassageiros {

	/**
	 * Método que dispara a execução do caso de uso Manter Passageiros.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso Manter Passageiros.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Passageiro
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Passageiro
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Passageiro
	 */
	public abstract boolean incluir(String cpf, String nome, String endereco,
			String telefone);

	/**
	 * Método que inicia o processo de alteração de um Passageiro
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Passageiro
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Passageiro
	 */
	public abstract boolean alterar(String cpf, String nome, String endereco,
			String telefone);

	/**
	 * Método que inicia o processo de exclusão de um Passageiro
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Passageiro
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Passageiro
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
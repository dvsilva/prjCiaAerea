package controle;

import dados.Passageiro;
import dados.Voo;

public interface ICtrlManterAssentos {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Assentos.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Assentos.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Assento
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Assento
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Assento
	 */
	public abstract boolean incluir(String sigla, String nome, Voo v,
			Passageiro p);

	/**
	 * Método que inicia o processo de alteração de um Assento
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Assento
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Assento
	 */
	public abstract boolean alterar(String assentoNum, String assentoFila);

	/**
	 * Método que inicia o processo de exclusão de um Assento
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Assento
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Assento
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
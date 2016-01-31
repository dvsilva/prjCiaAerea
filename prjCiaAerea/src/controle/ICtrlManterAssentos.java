package controle;

import dados.Passageiro;
import dados.Voo;

public interface ICtrlManterAssentos {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Assentos.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Assentos.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Assento
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Assento
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Assento
	 */
	public abstract boolean incluir(String sigla, String nome, Voo v,
			Passageiro p);

	/**
	 * M�todo que inicia o processo de altera��o de um Assento
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Assento
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Assento
	 */
	public abstract boolean alterar(String assentoNum, String assentoFila);

	/**
	 * M�todo que inicia o processo de exclus�o de um Assento
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Assento
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Assento
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
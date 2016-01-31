package controle;

import dados.Aeroporto;
import dados.Voo;

public interface ICtrlManterEscalas {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Escalas.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Escalas.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Escala
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Escala
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Escala
	 */
	public abstract boolean incluir(String ordem, String horChegada, String horSaida, Aeroporto a, Voo v);

	/**
	 * M�todo que inicia o processo de altera��o de um Escala
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Escala
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Escala
	 */
	public abstract boolean alterar(String ordem, String horChegada, String horSaida, Aeroporto a, Voo v);

	/**
	 * M�todo que inicia o processo de exclus�o de um Escala
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Escala
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Escala
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
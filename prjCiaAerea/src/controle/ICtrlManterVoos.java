package controle;

import dados.Aeronave;
import dados.Aeroporto;

public interface ICtrlManterVoos {

	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Voos.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Voos.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Voo
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Voo
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Voo
	 */
	public abstract boolean incluir(String codVoo, String horaPartida,
			String horaChegada, String dataRealizacao, Aeronave a,
			Aeroporto as, Aeroporto ac);

	/**
	 * M�todo que inicia o processo de altera��o de um Voo
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Voo
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Voo
	 */
	public abstract boolean alterar(String codVoo, String horaPartida,
			String horaChegada, String dataRealizacao);

	/**
	 * M�todo que inicia o processo de exclus�o de um Voo
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Voo
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Voo
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
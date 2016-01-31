package controle;

import dados.Aeronave;
import dados.Aeroporto;

public interface ICtrlManterVoos {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Voos.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Voos.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Voo
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Voo
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Voo
	 */
	public abstract boolean incluir(String codVoo, String horaPartida,
			String horaChegada, String dataRealizacao, Aeronave a,
			Aeroporto as, Aeroporto ac);

	/**
	 * Método que inicia o processo de alteração de um Voo
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Voo
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Voo
	 */
	public abstract boolean alterar(String codVoo, String horaPartida,
			String horaChegada, String dataRealizacao);

	/**
	 * Método que inicia o processo de exclusão de um Voo
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Voo
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Voo
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
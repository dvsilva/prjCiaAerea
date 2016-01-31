package controle;

import dados.Aeroporto;
import dados.Voo;

public interface ICtrlManterEscalas {

	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Escalas.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Escalas.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Escala
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Escala
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Escala
	 */
	public abstract boolean incluir(String ordem, String horChegada, String horSaida, Aeroporto a, Voo v);

	/**
	 * Método que inicia o processo de alteração de um Escala
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Escala
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Escala
	 */
	public abstract boolean alterar(String ordem, String horChegada, String horSaida, Aeroporto a, Voo v);

	/**
	 * Método que inicia o processo de exclusão de um Escala
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Escala
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Escala
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
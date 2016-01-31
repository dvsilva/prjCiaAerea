package controle;

import dados.Fabricante;
import dados.Modelo;

public interface ICtrlManterModelos {

	/**
	 * M�todo que dispara a execu��o do caso de uso Manter Modelos.
	 */
	public abstract boolean iniciar();

	/**
	 * M�todo que encerra a execu��o do caso de uso Manter Modelos.
	 */
	public abstract boolean terminar();

	/**
	 * M�todo que inicia o processo de inclus�o de um Modelo
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * M�todo que cancela o processo de inclus�o de um Modelo
	 */
	public abstract void cancelarIncluir();

	/**
	 * M�todo que inclui um Modelo
	 */
	public abstract boolean incluir(String nome, String numMaxLugar, String autonomiaVoo, String capacidadeTanque, Fabricante f);

	/**
	 * M�todo que inicia o processo de altera��o de um Modelo
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * M�todo que cancela o processo de altera��o de um Modelo
	 */
	public abstract void cancelarAlterar();

	/**
	 * M�todo que alterar um Modelo
	 */
	public abstract boolean alterar(String nome, String numMaxLugar, String autonomiaVoo, String capacidadeTanque, Fabricante f);

	/**
	 * M�todo que inicia o processo de exclus�o de um Modelo
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * M�todo que cancela o processo de exclus�o de um Modelo
	 */
	public abstract void cancelarExcluir();

	/**
	 * M�todo que exclui um Modelo
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
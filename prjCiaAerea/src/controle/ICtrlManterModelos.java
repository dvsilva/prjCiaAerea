package controle;

import dados.Fabricante;
import dados.Modelo;

public interface ICtrlManterModelos {

	/**
	 * Método que dispara a execução do caso de uso Manter Modelos.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso Manter Modelos.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Modelo
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Modelo
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Modelo
	 */
	public abstract boolean incluir(String nome, String numMaxLugar, String autonomiaVoo, String capacidadeTanque, Fabricante f);

	/**
	 * Método que inicia o processo de alteração de um Modelo
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Modelo
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Modelo
	 */
	public abstract boolean alterar(String nome, String numMaxLugar, String autonomiaVoo, String capacidadeTanque, Fabricante f);

	/**
	 * Método que inicia o processo de exclusão de um Modelo
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Modelo
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Modelo
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
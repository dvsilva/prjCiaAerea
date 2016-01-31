package controle;

import dados.Modelo;

public interface ICtrlManterAeronaves {

	/**
	 * Método que dispara a execução do caso de uso Manter Departamentos.
	 */
	public abstract boolean iniciar();

	/**
	 * Método que encerra a execução do caso de uso Manter Departamentos.
	 */
	public abstract boolean terminar();

	/**
	 * Método que inicia o processo de inclusão de um Departamento
	 */
	public abstract boolean iniciarIncluir();

	/**
	 * Método que cancela o processo de inclusão de um Departamento
	 */
	public abstract void cancelarIncluir();

	/**
	 * Método que inclui um Departamento
	 */
	public abstract boolean incluir(String nome, Modelo m);

	/**
	 * Método que inicia o processo de alteração de um Departamento
	 */
	public abstract boolean iniciarAlterar(int pos);

	/**
	 * Método que cancela o processo de alteração de um Departamento
	 */
	public abstract void cancelarAlterar();

	/**
	 * Método que alterar um Departamento
	 */
	public abstract boolean alterar(String nome, Modelo m);

	/**
	 * Método que inicia o processo de exclusão de um Departamento
	 */
	public abstract boolean iniciarExcluir(int pos);

	/**
	 * Método que cancela o processo de exclusão de um Departamento
	 */
	public abstract void cancelarExcluir();

	/**
	 * Método que exclui um Departamento
	 */
	public abstract boolean excluir();

	/**
	 * Atualiza a Janela Cadastro
	 */
	public abstract void atualizarInterface();

}
package face;

import controle.Tabelavel;

public interface IJanelaCadastroAeroportos {

	/**
	 * Remove todas as linhas da tabela
	 */
	public abstract void limpar();

	/**
	 * Inclui uma linha na tabela
	 * @param linha
	 */
	public abstract void incluirLinha(Tabelavel objeto);

	public abstract void executarIncluir();

	public abstract void executarExcluir();

	public abstract void executarAlterar();

	public abstract void executarTerminar();

	public abstract void setVisible(boolean flag);
}
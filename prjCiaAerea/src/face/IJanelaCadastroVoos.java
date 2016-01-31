package face;

import controle.Tabelavel;

public interface IJanelaCadastroVoos {

	/**
	 * Remove todas as linhas da tabela
	 */
	public abstract void limpar();

	/**
	 * Inclui uma linha na tabela
	 * @param linha
	 */
	public abstract void incluirLinha(Tabelavel objeto);

	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarIncluir()
	 */
	public abstract void executarIncluir();

	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarExcluir()
	 */
	public abstract void executarExcluir();

	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarAlterar()
	 */
	public abstract void executarAlterar();

	/* (non-Javadoc)
	 * @see janelas.ICadastroDepartamentos#executarTerminar()
	 */
	public abstract void executarTerminar();
	
	public abstract void setVisible(boolean flag);

}
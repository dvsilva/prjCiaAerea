package face;

import controle.Tabelavel;

public interface IJanelaCadastroPassageiros {

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
	 * @see face.IJanelaCadastroDepartamentos#executarIncluir()
	 */
	public abstract void executarIncluir();

	/* (non-Javadoc)
	 * @see face.IJanelaCadastroDepartamentos#executarExcluir()
	 */
	public abstract void executarExcluir();

	/* (non-Javadoc)
	 * @see face.IJanelaCadastroDepartamentos#executarAlterar()
	 */
	public abstract void executarAlterar();

	/* (non-Javadoc)
	 * @see face.IJanelaCadastroDepartamentos#executarTerminar()
	 */
	public abstract void executarTerminar();
	
	public abstract void setVisible(boolean flag);

}
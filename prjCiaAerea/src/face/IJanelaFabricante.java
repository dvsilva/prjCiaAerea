package face;

public interface IJanelaFabricante {

	/**
	 * Determina os valores para os campos da janela
	 * @param nome
	 * @param telefone
	 */
	public abstract void atualizarCampos(String nome, String telefone);

	public void setVisible(boolean flag);
}
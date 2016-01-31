package face;

public interface IJanelaEscala {

	/**
	 * Determina os valores para os campos da janela
	 * @param ordem
	 * @param horChegada
	 * @param horSaida
	 */
	public abstract void atualizarCampos(String ordem, String horChegada, String horSaida);

	public void setVisible(boolean flag);
}
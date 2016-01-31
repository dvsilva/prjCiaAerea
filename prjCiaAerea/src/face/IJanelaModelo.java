package face;


public interface IJanelaModelo {

	/**
	 * Determina os valores para os campos da janela
	 * @param nome
	 * @param numMaxLugar
	 * @param autonomiaVoo
	 * @param capacidadeTanque
	 */
	public abstract void atualizarCampos(String nome, String numMaxLugar, String autonomiaVoo,
			String capacidadeTanque);

	public void setVisible(boolean flag);
}
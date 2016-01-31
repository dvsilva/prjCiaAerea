package face;


public interface IJanelaVoo {

	/**
	 * Determina os valores para os campos da janela
	 * @param codVoo
	 * @param horaPartida
	 * @param horaChegada
	 * @param dataRealizacao
	 */
	public abstract void atualizarCampos(String codVoo, String horaPartida, String horaChegada, String dataRealizacao);

	public void setVisible(boolean flag);
}
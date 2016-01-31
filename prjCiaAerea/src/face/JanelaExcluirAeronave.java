package face;

import javax.swing.JOptionPane;

import controle.ICtrlManterAeronaves;

/**
 * Implementa��o da janela de confirma��o de exclus�o do Aeronave
 * @author Felipe, Amanda e Danyllo
 *
 */
public class JanelaExcluirAeronave {
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private ICtrlManterAeronaves ctrl;
	
	/**
	 * Op��o escolhida pelo usu�rio
	 */
	private int opcao;
	
	
	/**
	 * Construtor que ir� colocar uma janela modal perguntando
	 * se o usu�rio deseja ou n�o excluir o Aeronave
	 * @param nome
	 */
	public JanelaExcluirAeronave(ICtrlManterAeronaves ct, Object selecionado){
		// Guardo a refer�ncia para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usu�rio o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Aeronave " + selecionado);
		// Verifica o que o usu�rio indicou para ser feito
		if(this.opcao == JOptionPane.YES_OPTION)
			this.ctrl.excluir();
		else
			this.ctrl.cancelarExcluir();
	}
	
	/**
	 * Retorna a op��o indicada pelo usu�rio
	 * @return
	 */
	public int getOpcao(){
		return this.opcao;
	}
}

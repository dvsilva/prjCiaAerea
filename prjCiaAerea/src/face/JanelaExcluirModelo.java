package face;

import javax.swing.JOptionPane;

import controle.ICtrlManterModelos;

/**
 * Implementa��o da janela de confirma��o de exclus�o do Modelo
 * @author Felipe, Amanda e Danyllo
 *
 */
public class JanelaExcluirModelo {
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private ICtrlManterModelos ctrl;
	
	/**
	 * Op��o escolhida pelo usu�rio
	 */
	private int opcao;
	
	
	/**
	 * Construtor que ir� colocar uma janela modal perguntando
	 * se o usu�rio deseja ou n�o excluir o Modelo
	 * @param nome
	 */
	public JanelaExcluirModelo(ICtrlManterModelos ct, Object selecionado){
		// Guardo a refer�ncia para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usu�rio o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Modelo " + selecionado);
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

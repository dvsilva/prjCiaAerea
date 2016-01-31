package face;

import javax.swing.JOptionPane;

import controle.ICtrlManterAssentos;

/**
 * Implementa��o da janela de confirma��o de exclus�o do Assento
 * @author Felipe, Amanda e Danyllo
 *
 */
public class JanelaExcluirAssento {
	/**
	 * Refer�ncia para o controlador do caso de uso
	 */
	private ICtrlManterAssentos ctrl;
	
	/**
	 * Op��o escolhida pelo usu�rio
	 */
	private int opcao;
	
	
	/**
	 * Construtor que ir� colocar uma janela modal perguntando
	 * se o usu�rio deseja ou n�o excluir o Assento
	 * @param nome
	 */
	public JanelaExcluirAssento(ICtrlManterAssentos ct, Object selecionado){
		// Guardo a refer�ncia para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usu�rio o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Assento " + selecionado);
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

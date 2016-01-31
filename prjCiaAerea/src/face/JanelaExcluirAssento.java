package face;

import javax.swing.JOptionPane;

import controle.ICtrlManterAssentos;

/**
 * Implementação da janela de confirmação de exclusão do Assento
 * @author Felipe, Amanda e Danyllo
 *
 */
public class JanelaExcluirAssento {
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterAssentos ctrl;
	
	/**
	 * Opção escolhida pelo usuário
	 */
	private int opcao;
	
	
	/**
	 * Construtor que irá colocar uma janela modal perguntando
	 * se o usuário deseja ou não excluir o Assento
	 * @param nome
	 */
	public JanelaExcluirAssento(ICtrlManterAssentos ct, Object selecionado){
		// Guardo a referência para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usuário o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Assento " + selecionado);
		// Verifica o que o usuário indicou para ser feito
		if(this.opcao == JOptionPane.YES_OPTION)
			this.ctrl.excluir();
		else
			this.ctrl.cancelarExcluir();
	}
	
	/**
	 * Retorna a opção indicada pelo usuário
	 * @return
	 */
	public int getOpcao(){
		return this.opcao;
	}
}

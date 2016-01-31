package face;

import javax.swing.JOptionPane;

import controle.ICtrlManterVoos;

/**
 * Implementação da janela de confirmação de exclusão do Voo
 * @author Felipe, Amanda e Danyllo
 *
 */
public class JanelaExcluirVoo {
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterVoos ctrl;
	
	/**
	 * Opção escolhida pelo usuário
	 */
	private int opcao;
	
	
	/**
	 * Construtor que irá colocar uma janela modal perguntando
	 * se o usuário deseja ou não excluir o Voo
	 * @param nome
	 */
	public JanelaExcluirVoo(ICtrlManterVoos ct, Object selecionado){
		// Guardo a referência para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usuário o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Voo " + selecionado);
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

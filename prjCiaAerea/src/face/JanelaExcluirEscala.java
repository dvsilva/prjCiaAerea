package face;

import javax.swing.JOptionPane;

import controle.ICtrlManterEscalas;

/**
 * Implementação da janela de confirmação de exclusão do Escala
 * @author Felipe, Amanda e Danyllo
 *
 */
public class JanelaExcluirEscala {
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterEscalas ctrl;
	
	/**
	 * Opção escolhida pelo usuário
	 */
	private int opcao;
	
	
	/**
	 * Construtor que irá colocar uma janela modal perguntando
	 * se o usuário deseja ou não excluir o Escala
	 * @param nome
	 */
	public JanelaExcluirEscala(ICtrlManterEscalas ct, Object selecionado){
		// Guardo a referência para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usuário o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Escala " + selecionado);
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

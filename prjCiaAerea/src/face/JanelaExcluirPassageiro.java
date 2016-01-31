package face;

import javax.swing.JOptionPane;

import controle.ICtrlManterPassageiros;

/**
 * Implementação da janela de confirmação de exclusão do Passageiro
 * @author Felipe, Amanda e Danyllo
 *
 */
public class JanelaExcluirPassageiro {
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterPassageiros ctrl;
	
	/**
	 * Opção escolhida pelo usuário
	 */
	private int opcao;
	
	
	/**
	 * Construtor que irá colocar uma janela modal perguntando
	 * se o usuário deseja ou não excluir o Passageiro
	 * @param nome
	 * @wbp.parser.entryPoint
	 */
	public JanelaExcluirPassageiro(ICtrlManterPassageiros ct, Object selecionado){
		// Guardo a referência para o controlador de caso de uso
		this.ctrl = ct;
		// Pergunto ao usuário o que ele deseja fazer
		this.opcao = JOptionPane.showConfirmDialog(null, "Deseja remover o Passageiro " + selecionado);
		// Verifica o que o usuário indicou para ser feito
		if(this.opcao == JOptionPane.YES_OPTION)
			this.ctrl.excluir();
		else
			this.ctrl.cancelarExcluir();
	}
	
	/**
	 * Retorna a opção indicada pelo usuário
	 * @return
	 * @wbp.parser.entryPoint
	 */
	public int getOpcao(){
		return this.opcao;
	}
}

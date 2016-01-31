package controle;

import dados.DAOFabricante;
import dados.Fabricante;
import dados.IDAOFabricante;
import face.IJanelaCadastroFabricantes;
import face.IJanelaFabricante;
import face.JanelaCadastroFabricantes;
import face.JanelaExcluirFabricante;
import face.JanelaFabricante;


public class CtrlManterFabricantes implements ICtrlManterFabricantes{
	//
	// ATRIBUTOS
	//
	private enum Operacao {
		INCLUSAO, EXCLUSAO, ALTERACAO, DISPONIVEL;
	}
	
	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private CtrlPrograma       ctrlPrg;
	
	/**
	 * Refer�ncia para a janela do cadastro de Fabricantes
	 */
	private IJanelaCadastroFabricantes jCadastro;
	
	/**
	 * Refer�ncia para a janela Fabricante que permitir� a 
	 * inclus�o e altera��o do Fabricante
	 */
	private IJanelaFabricante jFabricante;
	
	/**
	 * Refer�ncia para o objeto Fabricante sendo manipulado
	 */
	private Fabricante fabriAtual;
	
	/**
	 * Atributo indicando se o caso de uso est� ou n�o em execu��o
	 */
	private boolean emExecucao;
	
	/**
	 * Atributo que indica qual opera��o est� em curso
	 */
	private Operacao operacao;
	
	//
	// M�TODOS
	//
	
	/**
	 * Construtor da classe CtrlManterFabricantes
	 */
	public CtrlManterFabricantes(CtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Fabricantes.
	 */
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroFabricantes(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Fabricantes.
	 */
	public boolean terminar() {
		// Se n�o est� em execu��o, n�o � necess�rio solicitar 
		// novamente o t�rmino do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o t�rmino do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterFabricantes();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/**
	 * M�todo que inicia o processo de inclus�o de um Fabricante
	 */
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Fabricante
		this.jFabricante = new JanelaFabricante(this);
		return true;
	}	

	/**
	 * M�todo que cancela o processo de inclus�o de um Fabricante
	 */
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jFabricante.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * M�todo que inclui um Fabricante
	 */
	public boolean incluir(String nome, String telefone) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Fabricante
		Fabricante novo = new Fabricante(nome, telefone);
		// Recuperando o DAO
		IDAOFabricante dao = DAOFabricante.getSingleton();
		// Salvo o objeto Fabricante usando o DAO
		dao.salvar(novo);
		// Fecho a janela Fabricante
		this.jFabricante.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * M�todo que inicia o processo de altera��o de um Fabricante
	 */
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOFabricante dao = DAOFabricante.getSingleton();
		// Recupero o  objeto Fabricante
		this.fabriAtual = dao.recuperar(pos);
		// Abro a janela Fabricante para altera��o
		this.jFabricante = new JanelaFabricante(this);
		this.jFabricante.atualizarCampos(this.fabriAtual.getNome(),
										 this.fabriAtual.getTelefone());
		return true;
	}	

	/**
	 * M�todo que cancela o processo de altera��o de um Fabricante
	 */
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jFabricante.setVisible(false);
			// N�o guardo uma refer�ncia para o Fabricante pois cancelei a altera��o
			this.fabriAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * M�todo que alterar um Fabricante
	 */
	public boolean alterar(String nome, String telefone) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.fabriAtual.setNome(nome);
		this.fabriAtual.setTelefone(telefone);
		// Recuperando o DAO
		IDAOFabricante dao = DAOFabricante.getSingleton();
		// Salvo o objeto Fabricante usando o DAO
		dao.atualizar(this.fabriAtual);
		// Fecho a janela Fabricante
		this.jFabricante.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Fabricante
		this.fabriAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * M�todo que inicia o processo de exclus�o de um Fabricante
	 */
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOFabricante dao = DAOFabricante.getSingleton();
		// Recupero o  objeto Fabricante
		this.fabriAtual = dao.recuperar(pos);
		// Abro a janela Fabricante para perguntar sobre a exclus�o
		new JanelaExcluirFabricante(this, this.fabriAtual);
		return true;
	}	

	/**
	 * M�todo que cancela o processo de exclus�o de um Fabricante
	 */
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Fabricante pois cancelei a altera��o
			this.fabriAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * M�todo que exclui um Fabricante
	 */
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOFabricante dao = DAOFabricante.getSingleton();
		// Salvo o objeto Fabricante usando o DAO
		dao.remover(this.fabriAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Fabricante
		this.fabriAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * Atualiza a Janela Cadastro
	 */
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Recuperando o DAO
		IDAOFabricante dao = DAOFabricante.getSingleton();
		// Para cada objeto Fabricante presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Fabricante
			Fabricante fabri = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(fabri);
		}
	}
}

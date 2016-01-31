package controle;

import dados.Aeroporto;
import dados.DAOEscala;
import dados.Escala;
import dados.IDAOEscala;
import dados.Voo;
import face.IJanelaCadastroEscalas;
import face.IJanelaEscala;
import face.JanelaCadastroEscalas;
import face.JanelaEscala;
import face.JanelaExcluirEscala;

public class CtrlManterEscalas implements ICtrlManterEscalas {
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
	 * Refer�ncia para a janela do cadastro de Escalas
	 */
	private IJanelaCadastroEscalas jCadastro;
	
	/**
	 * Refer�ncia para a janela Escala que permitir� a 
	 * inclus�o e altera��o do Escala
	 */
	private IJanelaEscala jEscala;
	
	/**
	 * Refer�ncia para o objeto Escala sendo manipulado
	 */
	private Escala escAtual;
	
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
	 * Construtor da classe CtrlManterEscalas
	 */
	public CtrlManterEscalas(CtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Escalas.
	 */
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroEscalas(this);
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
	 * Manter Escalas.
	 */
	public boolean terminar() {
		// Se n�o est� em execu��o, n�o � necess�rio solicitar 
		// novamente o t�rmino do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o t�rmino do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterEscalas();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/**
	 * M�todo que inicia o processo de inclus�o de um Escala
	 */
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Escala
		this.jEscala = new JanelaEscala(this);
		return true;
	}	

	/**
	 * M�todo que cancela o processo de inclus�o de um Escala
	 */
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jEscala.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * M�todo que inclui um Escala
	 */
	public boolean incluir(String ordem, String horChegada, String horSaida, Aeroporto a, Voo v) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Escala
		Escala novo = new Escala(ordem, horChegada,horSaida, a, v);// TODO tirar os nulls
		// Recuperando o DAO
		IDAOEscala dao = DAOEscala.getSingleton();
		// Salvo o objeto Escala usando o DAO
		dao.salvar(novo);
		// Fecho a janela Escala
		this.jEscala.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * M�todo que inicia o processo de altera��o de um Escala
	 */
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOEscala dao = DAOEscala.getSingleton();
		// Recupero o  objeto Escala
		this.escAtual = dao.recuperar(pos);
		// Abro a janela Escala para altera��o
		this.jEscala = new JanelaEscala(this);
		this.jEscala.atualizarCampos(this.escAtual.getOrdem(), 
				                     this.escAtual.getHorChegada(),
				                     this.escAtual.getHorSaida());
		return true;
	}	

	/**
	 * M�todo que cancela o processo de altera��o de um Escala
	 */
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jEscala.setVisible(false);
			// N�o guardo uma refer�ncia para o Escala pois cancelei a altera��o
			this.escAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * M�todo que alterar um Escala
	 */
	public boolean alterar(String ordem, String horChegada, String horSaida, Aeroporto a, Voo v) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.escAtual.setOrdem(ordem);
		this.escAtual.setHorChegada(horChegada);
		this.escAtual.setHorSaida(horSaida);
		// Recuperando o DAO
		IDAOEscala dao = DAOEscala.getSingleton();
		// Salvo o objeto Escala usando o DAO
		dao.atualizar(this.escAtual);
		// Fecho a janela Escala
		this.jEscala.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Escala
		this.escAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * M�todo que inicia o processo de exclus�o de um Escala
	 */
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOEscala dao = DAOEscala.getSingleton();
		// Recupero o  objeto Escala
		this.escAtual = dao.recuperar(pos);
		// Abro a janela Escala para perguntar sobre a exclus�o
		new JanelaExcluirEscala(this, this.escAtual);
		return true;
	}	

	/**
	 * M�todo que cancela o processo de exclus�o de um Escala
	 */
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Escala pois cancelei a altera��o
			this.escAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * M�todo que exclui um Escala
	 */
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOEscala dao = DAOEscala.getSingleton();
		// Salvo o objeto Escala usando o DAO
		dao.remover(this.escAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Escala
		this.escAtual = null;
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
		IDAOEscala dao = DAOEscala.getSingleton();
		// Para cada objeto Escala presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Escala
			Escala esc = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(esc);
		}
	}

}

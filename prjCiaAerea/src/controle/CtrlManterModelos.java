package controle;

import dados.DAOModelo;
import dados.Fabricante;
import dados.IDAOModelo;
import dados.Modelo;
import face.IJanelaCadastroModelos;
import face.IJanelaModelo;
import face.JanelaCadastroModelos;
import face.JanelaExcluirModelo;
import face.JanelaModelo;

public class CtrlManterModelos implements ICtrlManterModelos{
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
	 * Refer�ncia para a janela do cadastro de Modelos
	 */
	private IJanelaCadastroModelos jCadastro;
	
	/**
	 * Refer�ncia para a janela Modelo que permitir� a 
	 * inclus�o e altera��o do Modelo
	 */
	private IJanelaModelo jModelo;
	
	/**
	 * Refer�ncia para o objeto Modelo sendo manipulado
	 */
	private Modelo modAtual;
	
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
	 * Construtor da classe CtrlManterModelos
	 */
	public CtrlManterModelos(CtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/**
	 * M�todo que dispara a execu��o do caso de uso
	 * Manter Modelos.
	 */
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroModelos(this);
		// Atualizo a Stringerface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/**
	 * M�todo que encerra a execu��o do caso de uso
	 * Manter Modelos.
	 */
	public boolean terminar() {
		// Se n�o est� em execu��o, n�o � necess�rio solicitar 
		// novamente o t�rmino do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o t�rmino do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterModelos();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/**
	 * M�todo que inicia o processo de inclus�o de um Modelo
	 */
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Modelo
		this.jModelo = new JanelaModelo(this);
		return true;
	}	

	/**
	 * M�todo que cancela o processo de inclus�o de um Modelo
	 */
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jModelo.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * M�todo que inclui um Modelo
	 */
	public boolean incluir(String nome, String numMaxLugar, String autonomiaVoo, String capacidadeTanque, Fabricante f) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Modelo
		Modelo novo = new Modelo(numMaxLugar,autonomiaVoo, capacidadeTanque,nome, f);// TODO tirar os nulls
		// Recuperando o DAO
		IDAOModelo dao = DAOModelo.getSingleton();
		// Salvo o objeto Modelo usando o DAO
		dao.salvar(novo);
		// Fecho a janela Modelo
		this.jModelo.setVisible(false);
		// Atualizo a Stringerface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * M�todo que inicia o processo de altera��o de um Modelo
	 */
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOModelo dao = DAOModelo.getSingleton();
		// Recupero o  objeto Modelo
		this.modAtual = dao.recuperar(pos);
		// Abro a janela Modelo para altera��o
		this.jModelo = new JanelaModelo(this);
		this.jModelo.atualizarCampos(this.modAtual.getNumMaxLugar(), 
				                     this.modAtual.getAutonomiaVoo(),
				                     this.modAtual.getCapacidadeTanque(),
				                     this.modAtual.getNome());
		 
		return true;
	}	

	/**
	 * M�todo que cancela o processo de altera��o de um Modelo
	 */
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jModelo.setVisible(false);
			// N�o guardo uma refer�ncia para o Modelo pois cancelei a altera��o
			this.modAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * M�todo que alterar um Modelo
	 */
	public boolean alterar(String nome, String numMaxLugar,
			String autonomiaVoo, String capacidadeTanque, Fabricante f) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.modAtual.setNome(nome);
		this.modAtual.setNumMaxLugar(numMaxLugar);
		this.modAtual.setAutonomiaVoo(autonomiaVoo);
		this.modAtual.setCapacidadeTanque(capacidadeTanque);
		
		// Recuperando o DAO
		IDAOModelo dao = DAOModelo.getSingleton();
		// Salvo o objeto Modelo usando o DAO
		dao.atualizar(this.modAtual);
		// Fecho a janela Modelo
		this.jModelo.setVisible(false);
		// Atualizo a Stringerface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Modelo
		this.modAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * M�todo que inicia o processo de exclus�o de um Modelo
	 */
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOModelo dao = DAOModelo.getSingleton();
		// Recupero o  objeto Modelo
		this.modAtual = dao.recuperar(pos);
		// Abro a janela Modelo para perguntar sobre a exclus�o
		new JanelaExcluirModelo(this, this.modAtual);
		return true;
	}	

	/**
	 * M�todo que cancela o processo de exclus�o de um Modelo
	 */
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Modelo pois cancelei a altera��o
			this.modAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * M�todo que exclui um Modelo
	 */
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOModelo dao = DAOModelo.getSingleton();
		// Salvo o objeto Modelo usando o DAO
		dao.remover(this.modAtual);
		// Atualizo a Stringerface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Modelo
		this.modAtual = null;
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
		IDAOModelo dao = DAOModelo.getSingleton();
		// Para cada objeto Modelo presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Modelo
			Modelo mod = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(mod);
		}
	}


}

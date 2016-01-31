package controle;

import dados.Aeronave;
import dados.Aeroporto;
import dados.DAOVoo;
import dados.IDAOVoo;
import dados.Voo;
import face.IJanelaCadastroVoos;
import face.IJanelaVoo;
import face.JanelaCadastroVoos;
import face.JanelaExcluirVoo;
import face.JanelaVoo;


public class CtrlManterVoos implements ICtrlManterVoos {
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
	 * Refer�ncia para a janela do cadastro de Voos
	 */
	private IJanelaCadastroVoos jCadastro;
	
	/**
	 * Refer�ncia para a janela Voo que permitir� a 
	 * inclus�o e altera��o do Voo
	 */
	private IJanelaVoo jVoo;
	
	/**
	 * Refer�ncia para o objeto Voo sendo manipulado
	 */
	private Voo vooAtual;
	
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
	 * Construtor da classe CtrlManterVoos
	 */
	public CtrlManterVoos(CtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroVoos(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#terminar()
	 */
	@Override
	public boolean terminar() {
		// Se n�o est� em execu��o, n�o � necess�rio solicitar 
		// novamente o t�rmino do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o t�rmino do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterVoos();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Voo
		this.jVoo = new JanelaVoo(this);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jVoo.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#incluir(java.lang.String, java.lang.String, java.lang.String, java.lang.String, dados.Aeronave, dados.Aeroporto, dados.Aeroporto)
	 */
	@Override
	public boolean incluir(String codVoo, String horaPartida,String horaChegada, String dataRealizacao, Aeronave a, Aeroporto as, Aeroporto ac) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Voo
		Voo novo = new Voo(codVoo,horaPartida,horaChegada,dataRealizacao, a, as, ac);
		// Recuperando o DAO
		IDAOVoo dao = DAOVoo.getSingleton();
		// Salvo o objeto Voo usando o DAO
		dao.salvar(novo);
		// Fecho a janela Voo
		this.jVoo.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOVoo dao = DAOVoo.getSingleton();
		// Recupero o  objeto Voo
		this.vooAtual = dao.recuperar(pos);
		// Abro a janela Voo para altera��o
		this.jVoo = new JanelaVoo(this);
		this.jVoo.atualizarCampos(this.vooAtual.getCodVoo(), 
				                  this.vooAtual.getHoraChegada(),
				                  this.vooAtual.getHoraPartida(), 
				                  this.vooAtual.getDataRealizacao()
				                  );
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jVoo.setVisible(false);
			// N�o guardo uma refer�ncia para o Voo pois cancelei a altera��o
			this.vooAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#alterar(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String codVoo, String horaPartida,String horaChegada, String dataRealizacao) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.vooAtual.setCodVoo(codVoo); 
        this.vooAtual.setHoraChegada(horaChegada);
        this.vooAtual.setHoraPartida(horaPartida); 
        this.vooAtual.setDataRealizacao(dataRealizacao);
		// Recuperando o DAO
		IDAOVoo dao = DAOVoo.getSingleton();
		// Salvo o objeto Voo usando o DAO
		dao.atualizar(this.vooAtual);
		// Fecho a janela Voo
		this.jVoo.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Voo
		this.vooAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOVoo dao = DAOVoo.getSingleton();
		// Recupero o  objeto Voo
		this.vooAtual = dao.recuperar(pos);
		// Abro a janela Voo para perguntar sobre a exclus�o
		new JanelaExcluirVoo(this, this.vooAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Voo pois cancelei a altera��o
			this.vooAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOVoo dao = DAOVoo.getSingleton();
		// Salvo o objeto Voo usando o DAO
		dao.remover(this.vooAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Voo
		this.vooAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterVoos#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Recuperando o DAO
		IDAOVoo dao = DAOVoo.getSingleton();
		// Para cada objeto Voo presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Voo
			Voo voo = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(voo);
		}
	}
}

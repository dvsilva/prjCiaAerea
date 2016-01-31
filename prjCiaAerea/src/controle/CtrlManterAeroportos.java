package controle;

import dados.Aeroporto;
import dados.DAOAeroporto;
import dados.IDAOAeroporto;
import face.IJanelaAeroporto;
import face.IJanelaCadastroAeroportos;
import face.JanelaAeroporto;
import face.JanelaCadastroAeroportos;
import face.JanelaExcluirAeroporto;

public class CtrlManterAeroportos implements ICtrlManterAeroportos {
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
	 * Refer�ncia para a janela do cadastro de Aeroportos
	 */
	private IJanelaCadastroAeroportos jCadastro;
	
	/**
	 * Refer�ncia para a janela Aeroporto que permitir� a 
	 * inclus�o e altera��o do Aeroporto
	 */
	private IJanelaAeroporto jAeroporto;
	
	/**
	 * Refer�ncia para o objeto Aeroporto sendo manipulado
	 */
	private Aeroporto aeropAtual;
	
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
	 * Construtor da classe CtrlManterAeroportos
	 */
	public CtrlManterAeroportos(CtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar 
		// novamente a execu��o do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAeroportos(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterAeroportos();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Aeroporto
		this.jAeroporto = new JanelaAeroporto(this);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jAeroporto.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#incluir(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String codAeroporto, String nome, String endereco, String telefones) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Aeroporto
		Aeroporto novo = new Aeroporto(codAeroporto, nome, endereco,telefones);
		// Recuperando o DAO
		IDAOAeroporto dao = DAOAeroporto.getSingleton();
		// Salvo o objeto Aeroporto usando o DAO
		dao.salvar(novo);
		// Fecho a janela Aeroporto
		this.jAeroporto.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOAeroporto dao = DAOAeroporto.getSingleton();
		// Recupero o  objeto Aeroporto
		this.aeropAtual = dao.recuperar(pos);
		// Abro a janela Aeroporto para altera��o
		this.jAeroporto = new JanelaAeroporto(this);
		this.jAeroporto.atualizarCampos(this.aeropAtual.getCodAeroporto(), 
				                        this.aeropAtual.getNome(),
				                        this.aeropAtual.getEndereco(),
				                        this.aeropAtual.getTelefones());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jAeroporto.setVisible(false);
			// N�o guardo uma refer�ncia para o Aeroporto pois cancelei a altera��o
			this.aeropAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#alterar(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String codAeroporto, String nome, String endereco, String telefones) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.aeropAtual.setCodAeroporto(codAeroporto);
		this.aeropAtual.setNome(nome);
		this.aeropAtual.setEndereco(endereco);
		this.aeropAtual.setTelefones(telefones);
		// Recuperando o DAO
		IDAOAeroporto dao = DAOAeroporto.getSingleton();
		// Salvo o objeto Aeroporto usando o DAO
		dao.atualizar(this.aeropAtual);
		// Fecho a janela Aeroporto
		this.jAeroporto.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Aeroporto
		this.aeropAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOAeroporto dao = DAOAeroporto.getSingleton();
		// Recupero o  objeto Aeroporto
		this.aeropAtual = dao.recuperar(pos);
		// Abro a janela Aeroporto para perguntar sobre a exclus�o
		new JanelaExcluirAeroporto(this, this.aeropAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Aeroporto pois cancelei a altera��o
			this.aeropAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOAeroporto dao = DAOAeroporto.getSingleton();
		// Salvo o objeto Aeroporto usando o DAO
		dao.remover(this.aeropAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Aeroporto
		this.aeropAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Recuperando o DAO
		IDAOAeroporto dao = DAOAeroporto.getSingleton();
		// Para cada objeto Aeroporto presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Aeroporto
			Aeroporto aerop = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(aerop);
		}
	}
}

package controle;

import dados.DAOPassageiro;
import dados.IDAOPassageiro;
import dados.Passageiro;
import face.IJanelaCadastroPassageiros;
import face.IJanelaPassageiro;
import face.JanelaCadastroPassageiros;
import face.JanelaExcluirPassageiro;
import face.JanelaPassageiro;

public class CtrlManterPassageiros implements ICtrlManterPassageiros {
	//
	// ATRIBUTOS
	//
	private enum Operacao {
		INCLUSAO, EXCLUSAO, ALTERACAO, DISPONIVEL;
	}

	/**
	 * Refer�ncia para o controlador do programa.
	 */
	private CtrlPrograma ctrlPrg;

	/**
	 * Refer�ncia para a janela do cadastro de Passageiros
	 */
	private IJanelaCadastroPassageiros jCadastro;

	/**
	 * Refer�ncia para a janela Passageiro que permitir� a inclus�o e altera��o
	 * do Passageiro
	 */
	private IJanelaPassageiro jPassageiro;

	/**
	 * Refer�ncia para o objeto Passageiro sendo manipulado
	 */
	private Passageiro passAtual;

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
	 * Construtor da classe CtrlManterPassageiros
	 */
	public CtrlManterPassageiros(CtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar
		// novamente a execu��o do caso de uso
		if (this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroPassageiros(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#terminar()
	 */
	@Override
	public boolean terminar() {
		// Se n�o est� em execu��o, n�o � necess�rio solicitar
		// novamente o t�rmino do caso de uso
		if (!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o t�rmino do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterPassageiros();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if (this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Passageiro
		this.jPassageiro = new JanelaPassageiro(this);
		return true;
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if (this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jPassageiro.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#incluir(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String cpf, String nome, String endereco,
			String telefone) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if (this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Passageiro
		Passageiro novo = new Passageiro(cpf, nome, endereco, telefone);
		// Recuperando o DAO
		IDAOPassageiro dao = DAOPassageiro.getSingleton();
		// Salvo o objeto Passageiro usando o DAO
		dao.salvar(novo);
		// Fecho a janela Passageiro
		this.jPassageiro.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if (this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOPassageiro dao = DAOPassageiro.getSingleton();
		// Recupero o objeto Passageiro
		this.passAtual = dao.recuperar(pos);
		// Abro a janela Passageiro para altera��o
		this.jPassageiro = new JanelaPassageiro(this);
		this.jPassageiro.atualizarCampos(this.passAtual.getCpf(),
										this.passAtual.getNome(), 
										this.passAtual.getEndereco(),
										this.passAtual.getTelefone());
		return true;
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if (this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jPassageiro.setVisible(false);
			// N�o guardo uma refer�ncia para o Passageiro pois cancelei a
			// altera��o
			this.passAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#alterar(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String cpf, String nome, String endereco, String telefone) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if (this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.passAtual.setCpf(cpf);
		this.passAtual.setNome(nome);
		this.passAtual.setEndereco(endereco);
		this.passAtual.setTelefone(telefone);
		// Recuperando o DAO
		IDAOPassageiro dao = DAOPassageiro.getSingleton();
		// Salvo o objeto Passageiro usando o DAO
		dao.atualizar(this.passAtual);
		// Fecho a janela Passageiro
		this.jPassageiro.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Passageiro
		this.passAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if (this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOPassageiro dao = DAOPassageiro.getSingleton();
		// Recupero o objeto Passageiro
		this.passAtual = dao.recuperar(pos);
		// Abro a janela Passageiro para perguntar sobre a exclus�o
		new JanelaExcluirPassageiro(this, this.passAtual);
		return true;
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if (this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Passageiro pois cancelei a
			// altera��o
			this.passAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if (this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOPassageiro dao = DAOPassageiro.getSingleton();
		// Salvo o objeto Passageiro usando o DAO
		dao.remover(this.passAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Passageiro
		this.passAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/* (non-Javadoc)
	 * @see controle.ICtrlManterPassageiros#atualizarInterface()
	 */
	@Override
	public void atualizarInterface() {
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Recuperando o DAO
		IDAOPassageiro dao = DAOPassageiro.getSingleton();
		// Para cada objeto Passageiro presente no DAO
		for (int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Passageiro
			Passageiro passa = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(passa);
		}
	}
}

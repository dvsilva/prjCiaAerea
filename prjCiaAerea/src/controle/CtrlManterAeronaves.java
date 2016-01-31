package controle;

import dados.Aeronave;
import dados.DAOAeronave;
import dados.IDAOAeronave;
import dados.Modelo;
import face.IJanelaAeronave;
import face.IJanelaCadastroAeronaves;
import face.JanelaAeronave;
import face.JanelaCadastroAeronaves;
import face.JanelaExcluirAeronave;

public class CtrlManterAeronaves implements ICtrlManterAeronaves {
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
	 * Refer�ncia para a janela do cadastro de Aeronaves
	 */
	private IJanelaCadastroAeronaves jCadastro;

	/**
	 * Refer�ncia para a janela Aeronave que permitir� a inclus�o e altera��o do
	 * Aeronave
	 */
	private IJanelaAeronave jAeronave;

	/**
	 * Refer�ncia para o objeto Aeronave sendo manipulado
	 */
	private Aeronave aeronAtual;

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
	 * Construtor da classe CtrlManterAeronaves
	 */
	public CtrlManterAeronaves(CtrlPrograma p) {
		// Guardo a refer�ncia para o controlador do programa
		this.ctrlPrg = p;
	}

	/**
	 * M�todo que dispara a execu��o do caso de uso Manter Aeronaves.
	 */
	public boolean iniciar() {
		// Se j� est� em execu��o, n�o � necess�rio solicitar
		// novamente a execu��o do caso de uso
		if (this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAeronaves(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informa��o que o caso de uso est� em execu��o
		this.emExecucao = true;
		// Indico que o controlador de caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/**
	 * M�todo que encerra a execu��o do caso de uso Manter Aeronaves.
	 */
	public boolean terminar() {
		// Se n�o est� em execu��o, n�o � necess�rio solicitar
		// novamente o t�rmino do caso de uso
		if (!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o t�rmino do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterAeronaves();
		// Guardo a informa��o que o caso de uso est� encerrado
		this.emExecucao = false;
		// Indico que o caso de uso est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/**
	 * M�todo que inicia o processo de inclus�o de um Aeronave
	 */
	public boolean iniciarIncluir() {
		// Se o controlador n�o est� dispon�vel, n�o inicia a inclus�o
		if (this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Aeronave
		this.jAeronave = new JanelaAeronave(this);
		return true;
	}

	/**
	 * M�todo que cancela o processo de inclus�o de um Aeronave
	 */
	public void cancelarIncluir() {
		if (this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jAeronave.setVisible(false);
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}

	/**
	 * M�todo que inclui um Aeronave
	 */
	public boolean incluir(String nome, Modelo m) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if (this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Aeronave
		Aeronave novo = new Aeronave(nome, m);// TODO tirar o null
		// Recuperando o DAO
		IDAOAeronave dao = DAOAeronave.getSingleton();
		// Salvo o objeto Aeronave usando o DAO
		dao.salvar(novo);
		// Fecho a janela Aeronave
		this.jAeronave.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/**
	 * M�todo que inicia o processo de altera��o de um Aeronave
	 */
	public boolean iniciarAlterar(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if (this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOAeronave dao = DAOAeronave.getSingleton();
		// Recupero o objeto Aeronave
		this.aeronAtual = dao.recuperar(pos);
		// Abro a janela Aeronave para altera��o
		this.jAeronave = new JanelaAeronave(this);
		this.jAeronave.atualizarCampos(this.aeronAtual.getNome());
		return true;
	}

	/**
	 * M�todo que cancela o processo de altera��o de um Aeronave
	 */
	public void cancelarAlterar() {
		if (this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jAeronave.setVisible(false);
			// N�o guardo uma refer�ncia para o Aeronave pois cancelei a
			// altera��o
			this.aeronAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}

	/**
	 * M�todo que alterar um Aeronave
	 */
	public boolean alterar(String nome, Modelo m) {
		// Se o controlador n�o tinha ativado uma inclusao, n�o fa�o nada!
		if (this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.aeronAtual.setNome(nome);
		// Recuperando o DAO
		IDAOAeronave dao = DAOAeronave.getSingleton();
		// Salvo o objeto Aeronave usando o DAO
		dao.atualizar(this.aeronAtual);
		// Fecho a janela Aeronave
		this.jAeronave.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Aeronave
		this.aeronAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/**
	 * M�todo que inicia o processo de exclus�o de um Aeronave
	 */
	public boolean iniciarExcluir(int pos) {
		// Se o controlador n�o est� dispon�vel, n�o fa�o nada
		if (this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso est� excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOAeronave dao = DAOAeronave.getSingleton();
		// Recupero o objeto Aeronave
		this.aeronAtual = dao.recuperar(pos);
		// Abro a janela Aeronave para perguntar sobre a exclus�o
		new JanelaExcluirAeronave(this, this.aeronAtual);
		return true;
	}

	/**
	 * M�todo que cancela o processo de exclus�o de um Aeronave
	 */
	public void cancelarExcluir() {
		if (this.operacao == Operacao.EXCLUSAO) {
			// N�o guardo uma refer�ncia para o Aeronave pois cancelei a
			// altera��o
			this.aeronAtual = null;
			// Indico que o controlador est� dispon�vel
			this.operacao = Operacao.DISPONIVEL;
		}
	}

	/**
	 * M�todo que exclui um Aeronave
	 */
	public boolean excluir() {
		// Se o controlador n�o tinha ativado uma exclus�o, n�o fa�o nada!
		if (this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOAeronave dao = DAOAeronave.getSingleton();
		// Salvo o objeto Aeronave usando o DAO
		dao.remover(this.aeronAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a refer�ncia para o Aeronave
		this.aeronAtual = null;
		// Indico que o controlador est� dispon�vel
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/**
	 * Atualiza a Janela Cadastro
	 */
	public void atualizarInterface() {
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Recuperando o DAO
		IDAOAeronave dao = DAOAeronave.getSingleton();
		// Para cada objeto Aeronave presente no DAO
		for (int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Aeronave
			Aeronave aeron = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(aeron);
		}
	}

}

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
	 * Referência para o controlador do programa.
	 */
	private CtrlPrograma ctrlPrg;

	/**
	 * Referência para a janela do cadastro de Aeronaves
	 */
	private IJanelaCadastroAeronaves jCadastro;

	/**
	 * Referência para a janela Aeronave que permitirá a inclusão e alteração do
	 * Aeronave
	 */
	private IJanelaAeronave jAeronave;

	/**
	 * Referência para o objeto Aeronave sendo manipulado
	 */
	private Aeronave aeronAtual;

	/**
	 * Atributo indicando se o caso de uso está ou não em execução
	 */
	private boolean emExecucao;

	/**
	 * Atributo que indica qual operação está em curso
	 */
	private Operacao operacao;

	//
	// MÉTODOS
	//

	/**
	 * Construtor da classe CtrlManterAeronaves
	 */
	public CtrlManterAeronaves(CtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}

	/**
	 * Método que dispara a execução do caso de uso Manter Aeronaves.
	 */
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar
		// novamente a execução do caso de uso
		if (this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAeronaves(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/**
	 * Método que encerra a execução do caso de uso Manter Aeronaves.
	 */
	public boolean terminar() {
		// Se não está em execução, não é necessário solicitar
		// novamente o término do caso de uso
		if (!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o término do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterAeronaves();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/**
	 * Método que inicia o processo de inclusão de um Aeronave
	 */
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if (this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Aeronave
		this.jAeronave = new JanelaAeronave(this);
		return true;
	}

	/**
	 * Método que cancela o processo de inclusão de um Aeronave
	 */
	public void cancelarIncluir() {
		if (this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jAeronave.setVisible(false);
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}

	/**
	 * Método que inclui um Aeronave
	 */
	public boolean incluir(String nome, Modelo m) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/**
	 * Método que inicia o processo de alteração de um Aeronave
	 */
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if (this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOAeronave dao = DAOAeronave.getSingleton();
		// Recupero o objeto Aeronave
		this.aeronAtual = dao.recuperar(pos);
		// Abro a janela Aeronave para alteração
		this.jAeronave = new JanelaAeronave(this);
		this.jAeronave.atualizarCampos(this.aeronAtual.getNome());
		return true;
	}

	/**
	 * Método que cancela o processo de alteração de um Aeronave
	 */
	public void cancelarAlterar() {
		if (this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jAeronave.setVisible(false);
			// Não guardo uma referência para o Aeronave pois cancelei a
			// alteração
			this.aeronAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}

	/**
	 * Método que alterar um Aeronave
	 */
	public boolean alterar(String nome, Modelo m) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Deixo de guardar a referência para o Aeronave
		this.aeronAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}

	/**
	 * Método que inicia o processo de exclusão de um Aeronave
	 */
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if (this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOAeronave dao = DAOAeronave.getSingleton();
		// Recupero o objeto Aeronave
		this.aeronAtual = dao.recuperar(pos);
		// Abro a janela Aeronave para perguntar sobre a exclusão
		new JanelaExcluirAeronave(this, this.aeronAtual);
		return true;
	}

	/**
	 * Método que cancela o processo de exclusão de um Aeronave
	 */
	public void cancelarExcluir() {
		if (this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para o Aeronave pois cancelei a
			// alteração
			this.aeronAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}

	/**
	 * Método que exclui um Aeronave
	 */
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if (this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOAeronave dao = DAOAeronave.getSingleton();
		// Salvo o objeto Aeronave usando o DAO
		dao.remover(this.aeronAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Aeronave
		this.aeronAtual = null;
		// Indico que o controlador está disponível
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

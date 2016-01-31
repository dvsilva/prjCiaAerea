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
	 * Referência para o controlador do programa.
	 */
	private CtrlPrograma       ctrlPrg;
	
	/**
	 * Referência para a janela do cadastro de Fabricantes
	 */
	private IJanelaCadastroFabricantes jCadastro;
	
	/**
	 * Referência para a janela Fabricante que permitirá a 
	 * inclusão e alteração do Fabricante
	 */
	private IJanelaFabricante jFabricante;
	
	/**
	 * Referência para o objeto Fabricante sendo manipulado
	 */
	private Fabricante fabriAtual;
	
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
	 * Construtor da classe CtrlManterFabricantes
	 */
	public CtrlManterFabricantes(CtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Fabricantes.
	 */
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroFabricantes(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Fabricantes.
	 */
	public boolean terminar() {
		// Se não está em execução, não é necessário solicitar 
		// novamente o término do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o término do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterFabricantes();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/**
	 * Método que inicia o processo de inclusão de um Fabricante
	 */
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Fabricante
		this.jFabricante = new JanelaFabricante(this);
		return true;
	}	

	/**
	 * Método que cancela o processo de inclusão de um Fabricante
	 */
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jFabricante.setVisible(false);
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * Método que inclui um Fabricante
	 */
	public boolean incluir(String nome, String telefone) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * Método que inicia o processo de alteração de um Fabricante
	 */
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOFabricante dao = DAOFabricante.getSingleton();
		// Recupero o  objeto Fabricante
		this.fabriAtual = dao.recuperar(pos);
		// Abro a janela Fabricante para alteração
		this.jFabricante = new JanelaFabricante(this);
		this.jFabricante.atualizarCampos(this.fabriAtual.getNome(),
										 this.fabriAtual.getTelefone());
		return true;
	}	

	/**
	 * Método que cancela o processo de alteração de um Fabricante
	 */
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jFabricante.setVisible(false);
			// Não guardo uma referência para o Fabricante pois cancelei a alteração
			this.fabriAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * Método que alterar um Fabricante
	 */
	public boolean alterar(String nome, String telefone) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Deixo de guardar a referência para o Fabricante
		this.fabriAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * Método que inicia o processo de exclusão de um Fabricante
	 */
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOFabricante dao = DAOFabricante.getSingleton();
		// Recupero o  objeto Fabricante
		this.fabriAtual = dao.recuperar(pos);
		// Abro a janela Fabricante para perguntar sobre a exclusão
		new JanelaExcluirFabricante(this, this.fabriAtual);
		return true;
	}	

	/**
	 * Método que cancela o processo de exclusão de um Fabricante
	 */
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para o Fabricante pois cancelei a alteração
			this.fabriAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * Método que exclui um Fabricante
	 */
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOFabricante dao = DAOFabricante.getSingleton();
		// Salvo o objeto Fabricante usando o DAO
		dao.remover(this.fabriAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Fabricante
		this.fabriAtual = null;
		// Indico que o controlador está disponível
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

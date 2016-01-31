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
	 * Referência para o controlador do programa.
	 */
	private CtrlPrograma       ctrlPrg;
	
	/**
	 * Referência para a janela do cadastro de Escalas
	 */
	private IJanelaCadastroEscalas jCadastro;
	
	/**
	 * Referência para a janela Escala que permitirá a 
	 * inclusão e alteração do Escala
	 */
	private IJanelaEscala jEscala;
	
	/**
	 * Referência para o objeto Escala sendo manipulado
	 */
	private Escala escAtual;
	
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
	 * Construtor da classe CtrlManterEscalas
	 */
	public CtrlManterEscalas(CtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Escalas.
	 */
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroEscalas(this);
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
	 * Manter Escalas.
	 */
	public boolean terminar() {
		// Se não está em execução, não é necessário solicitar 
		// novamente o término do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o término do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterEscalas();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/**
	 * Método que inicia o processo de inclusão de um Escala
	 */
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Escala
		this.jEscala = new JanelaEscala(this);
		return true;
	}	

	/**
	 * Método que cancela o processo de inclusão de um Escala
	 */
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jEscala.setVisible(false);
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * Método que inclui um Escala
	 */
	public boolean incluir(String ordem, String horChegada, String horSaida, Aeroporto a, Voo v) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * Método que inicia o processo de alteração de um Escala
	 */
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOEscala dao = DAOEscala.getSingleton();
		// Recupero o  objeto Escala
		this.escAtual = dao.recuperar(pos);
		// Abro a janela Escala para alteração
		this.jEscala = new JanelaEscala(this);
		this.jEscala.atualizarCampos(this.escAtual.getOrdem(), 
				                     this.escAtual.getHorChegada(),
				                     this.escAtual.getHorSaida());
		return true;
	}	

	/**
	 * Método que cancela o processo de alteração de um Escala
	 */
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jEscala.setVisible(false);
			// Não guardo uma referência para o Escala pois cancelei a alteração
			this.escAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * Método que alterar um Escala
	 */
	public boolean alterar(String ordem, String horChegada, String horSaida, Aeroporto a, Voo v) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Deixo de guardar a referência para o Escala
		this.escAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * Método que inicia o processo de exclusão de um Escala
	 */
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOEscala dao = DAOEscala.getSingleton();
		// Recupero o  objeto Escala
		this.escAtual = dao.recuperar(pos);
		// Abro a janela Escala para perguntar sobre a exclusão
		new JanelaExcluirEscala(this, this.escAtual);
		return true;
	}	

	/**
	 * Método que cancela o processo de exclusão de um Escala
	 */
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para o Escala pois cancelei a alteração
			this.escAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * Método que exclui um Escala
	 */
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOEscala dao = DAOEscala.getSingleton();
		// Salvo o objeto Escala usando o DAO
		dao.remover(this.escAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Escala
		this.escAtual = null;
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

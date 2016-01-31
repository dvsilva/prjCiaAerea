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
	 * Referência para o controlador do programa.
	 */
	private CtrlPrograma       ctrlPrg;
	
	/**
	 * Referência para a janela do cadastro de Modelos
	 */
	private IJanelaCadastroModelos jCadastro;
	
	/**
	 * Referência para a janela Modelo que permitirá a 
	 * inclusão e alteração do Modelo
	 */
	private IJanelaModelo jModelo;
	
	/**
	 * Referência para o objeto Modelo sendo manipulado
	 */
	private Modelo modAtual;
	
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
	 * Construtor da classe CtrlManterModelos
	 */
	public CtrlManterModelos(CtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/**
	 * Método que dispara a execução do caso de uso
	 * Manter Modelos.
	 */
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroModelos(this);
		// Atualizo a Stringerface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/**
	 * Método que encerra a execução do caso de uso
	 * Manter Modelos.
	 */
	public boolean terminar() {
		// Se não está em execução, não é necessário solicitar 
		// novamente o término do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o término do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterModelos();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/**
	 * Método que inicia o processo de inclusão de um Modelo
	 */
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Modelo
		this.jModelo = new JanelaModelo(this);
		return true;
	}	

	/**
	 * Método que cancela o processo de inclusão de um Modelo
	 */
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jModelo.setVisible(false);
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * Método que inclui um Modelo
	 */
	public boolean incluir(String nome, String numMaxLugar, String autonomiaVoo, String capacidadeTanque, Fabricante f) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * Método que inicia o processo de alteração de um Modelo
	 */
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOModelo dao = DAOModelo.getSingleton();
		// Recupero o  objeto Modelo
		this.modAtual = dao.recuperar(pos);
		// Abro a janela Modelo para alteração
		this.jModelo = new JanelaModelo(this);
		this.jModelo.atualizarCampos(this.modAtual.getNumMaxLugar(), 
				                     this.modAtual.getAutonomiaVoo(),
				                     this.modAtual.getCapacidadeTanque(),
				                     this.modAtual.getNome());
		 
		return true;
	}	

	/**
	 * Método que cancela o processo de alteração de um Modelo
	 */
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jModelo.setVisible(false);
			// Não guardo uma referência para o Modelo pois cancelei a alteração
			this.modAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * Método que alterar um Modelo
	 */
	public boolean alterar(String nome, String numMaxLugar,
			String autonomiaVoo, String capacidadeTanque, Fabricante f) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Deixo de guardar a referência para o Modelo
		this.modAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/**
	 * Método que inicia o processo de exclusão de um Modelo
	 */
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOModelo dao = DAOModelo.getSingleton();
		// Recupero o  objeto Modelo
		this.modAtual = dao.recuperar(pos);
		// Abro a janela Modelo para perguntar sobre a exclusão
		new JanelaExcluirModelo(this, this.modAtual);
		return true;
	}	

	/**
	 * Método que cancela o processo de exclusão de um Modelo
	 */
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para o Modelo pois cancelei a alteração
			this.modAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/**
	 * Método que exclui um Modelo
	 */
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOModelo dao = DAOModelo.getSingleton();
		// Salvo o objeto Modelo usando o DAO
		dao.remover(this.modAtual);
		// Atualizo a Stringerface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Modelo
		this.modAtual = null;
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

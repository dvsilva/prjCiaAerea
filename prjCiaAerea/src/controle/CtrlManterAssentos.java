package controle;

import dados.Assento;
import dados.DAOAssento;
import dados.IDAOAssento;
import dados.Passageiro;
import dados.Voo;
import face.IJanelaAssento;
import face.IJanelaCadastroAssentos;
import face.JanelaAssento;
import face.JanelaCadastroAssentos;
import face.JanelaExcluirAssento;

public class CtrlManterAssentos implements ICtrlManterAssentos {
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
	 * Referência para a janela do cadastro de Assentos
	 */
	private IJanelaCadastroAssentos jCadastro;
	
	/**
	 * Referência para a janela Assento que permitirá a 
	 * inclusão e alteração do Assento
	 */
	private IJanelaAssento jAssento;
	
	/**
	 * Referência para o objeto Assento sendo manipulado
	 */
	private Assento asseAtual;
	
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
	 * Construtor da classe CtrlManterAssentos
	 */
	public CtrlManterAssentos(CtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAssentos(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#terminar()
	 */
	@Override
	public boolean terminar() {
		// Se não está em execução, não é necessário solicitar 
		// novamente o término do caso de uso
		if(!this.emExecucao)
			return false;
		// Fecho a janela
		this.jCadastro.setVisible(false);
		// Notifico ao controlador do programa o término do caso de uso
		this.ctrlPrg.terminarCasoDeUsoManterAssentos();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
		this.operacao = Operacao.INCLUSAO;
		// Abro a janela de Assento
		this.jAssento = new JanelaAssento(this);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#cancelarIncluir()
	 */
	@Override
	public void cancelarIncluir() {
		if(this.operacao == Operacao.INCLUSAO) {
			// Fecho a janela
			this.jAssento.setVisible(false);
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#incluir(java.lang.String, java.lang.String, dados.Voo, dados.Passageiro)
	 */
	@Override
	public boolean incluir(String sigla, String nome, Voo v, Passageiro p) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.operacao != Operacao.INCLUSAO)
			return false;
		// Crio um novo objeto Assento
		Assento novo = new Assento(sigla, nome, v, p);//TODO tirar os nulls
		// Recuperando o DAO
		IDAOAssento dao = DAOAssento.getSingleton();
		// Salvo o objeto Assento usando o DAO
		dao.salvar(novo);
		// Fecho a janela Assento
		this.jAssento.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOAssento dao = DAOAssento.getSingleton();
		// Recupero o  objeto Assento
		this.asseAtual = dao.recuperar(pos);
		// Abro a janela Assento para alteração
		this.jAssento = new JanelaAssento(this);
		this.jAssento.atualizarCampos(this.asseAtual.getAssentoNum(), 
				                      this.asseAtual.getAssentoFila());
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#cancelarAlterar()
	 */
	@Override
	public void cancelarAlterar() {
		if(this.operacao == Operacao.ALTERACAO) {
			// Fecho a janela
			this.jAssento.setVisible(false);
			// Não guardo uma referência para o Assento pois cancelei a alteração
			this.asseAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#alterar(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String assentoNum, String assentoFila) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
		if(this.operacao != Operacao.ALTERACAO)
			return false;
		// Atualizo os campos
		this.asseAtual.setAssentoNum(assentoNum);
		this.asseAtual.setAssentoFila(assentoFila);
		// Recuperando o DAO
		IDAOAssento dao = DAOAssento.getSingleton();
		// Salvo o objeto Assento usando o DAO
		dao.atualizar(this.asseAtual);
		// Fecho a janela Assento
		this.jAssento.setVisible(false);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Assento
		this.asseAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOAssento dao = DAOAssento.getSingleton();
		// Recupero o  objeto Assento
		this.asseAtual = dao.recuperar(pos);
		// Abro a janela Assento para perguntar sobre a exclusão
		new JanelaExcluirAssento(this, this.asseAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para o Assento pois cancelei a alteração
			this.asseAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOAssento dao = DAOAssento.getSingleton();
		// Salvo o objeto Assento usando o DAO
		dao.remover(this.asseAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Assento
		this.asseAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAssentos#atualizarInterface()
	 */
	@Override
	public void atualizarInterface(){
		// Limpa a tabela da janela
		this.jCadastro.limpar();
		// Recuperando o DAO
		IDAOAssento dao = DAOAssento.getSingleton();
		// Para cada objeto Assento presente no DAO
		for(int i = 0; i < dao.getNumObjs(); i++) {
			// Recupero um objeto Assento
			Assento asse = dao.recuperar(i);
			// Coloco uma linha na tabela
			this.jCadastro.incluirLinha(asse);
		}
	}
}

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
	 * Referência para o controlador do programa.
	 */
	private CtrlPrograma       ctrlPrg;
	
	/**
	 * Referência para a janela do cadastro de Aeroportos
	 */
	private IJanelaCadastroAeroportos jCadastro;
	
	/**
	 * Referência para a janela Aeroporto que permitirá a 
	 * inclusão e alteração do Aeroporto
	 */
	private IJanelaAeroporto jAeroporto;
	
	/**
	 * Referência para o objeto Aeroporto sendo manipulado
	 */
	private Aeroporto aeropAtual;
	
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
	 * Construtor da classe CtrlManterAeroportos
	 */
	public CtrlManterAeroportos(CtrlPrograma p) {
		// Guardo a referência para o controlador do programa
		this.ctrlPrg = p;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#iniciar()
	 */
	@Override
	public boolean iniciar() {
		// Se já está em execução, não é necessário solicitar 
		// novamente a execução do caso de uso
		if(this.emExecucao)
			return false;
		// Crio e abro a janela de cadastro
		this.jCadastro = new JanelaCadastroAeroportos(this);
		// Atualizo a interface
		this.atualizarInterface();
		// Guardo a informação que o caso de uso está em execução
		this.emExecucao = true;
		// Indico que o controlador de caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#terminar()
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
		this.ctrlPrg.terminarCasoDeUsoManterAeroportos();
		// Guardo a informação que o caso de uso está encerrado
		this.emExecucao = false;
		// Indico que o caso de uso está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}	
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#iniciarIncluir()
	 */
	@Override
	public boolean iniciarIncluir() {
		// Se o controlador não está disponível, não inicia a inclusão
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está incluindo
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
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#incluir(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean incluir(String codAeroporto, String nome, String endereco, String telefones) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#iniciarAlterar(int)
	 */
	@Override
	public boolean iniciarAlterar(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está alterando
		this.operacao = Operacao.ALTERACAO;
		// Recuperando o DAO
		IDAOAeroporto dao = DAOAeroporto.getSingleton();
		// Recupero o  objeto Aeroporto
		this.aeropAtual = dao.recuperar(pos);
		// Abro a janela Aeroporto para alteração
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
			// Não guardo uma referência para o Aeroporto pois cancelei a alteração
			this.aeropAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#alterar(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean alterar(String codAeroporto, String nome, String endereco, String telefones) {
		// Se o controlador não tinha ativado uma inclusao, não faço nada!
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
		// Deixo de guardar a referência para o Aeroporto
		this.aeropAtual = null;
		// Indico que o controlador está disponível
		this.operacao = Operacao.DISPONIVEL;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#iniciarExcluir(int)
	 */
	@Override
	public boolean iniciarExcluir(int pos) {
		// Se o controlador não está disponível, não faço nada
		if(this.operacao != Operacao.DISPONIVEL)
			return false;
		// Indico que o controlador de caso de uso está excluindo
		this.operacao = Operacao.EXCLUSAO;
		// Recuperando o DAO
		IDAOAeroporto dao = DAOAeroporto.getSingleton();
		// Recupero o  objeto Aeroporto
		this.aeropAtual = dao.recuperar(pos);
		// Abro a janela Aeroporto para perguntar sobre a exclusão
		new JanelaExcluirAeroporto(this, this.aeropAtual);
		return true;
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#cancelarExcluir()
	 */
	@Override
	public void cancelarExcluir() {
		if(this.operacao == Operacao.EXCLUSAO) {
			// Não guardo uma referência para o Aeroporto pois cancelei a alteração
			this.aeropAtual = null;
			// Indico que o controlador está disponível
			this.operacao = Operacao.DISPONIVEL;
		}
	}	

	/* (non-Javadoc)
	 * @see controle.ICtrlManterAeroportos#excluir()
	 */
	@Override
	public boolean excluir() {
		// Se o controlador não tinha ativado uma exclusão, não faço nada!
		if(this.operacao != Operacao.EXCLUSAO)
			return false;
		// Recuperando o DAO
		IDAOAeroporto dao = DAOAeroporto.getSingleton();
		// Salvo o objeto Aeroporto usando o DAO
		dao.remover(this.aeropAtual);
		// Atualizo a interface
		this.atualizarInterface();
		// Deixo de guardar a referência para o Aeroporto
		this.aeropAtual = null;
		// Indico que o controlador está disponível
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

package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOPassageiro implements IDAOPassageiro {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAOPassageiro 	singleton;
	/**
	 * Referência para o array que apontará para todos os objetos 
	 * guardados pelo DAO
	 */
	private Passageiro[] 			listaObjs;
	/**
	 * Indica o número de objetos sendo guardados pelo DAO
	 */
	private int            			numObjs;
	
	//
	// MÉTODOS
	//
	/**
	 * Construtor privado do DAO
	 */
	private DAOPassageiro() {
		// Aloco memória para o array
		this.listaObjs = new Passageiro[TAMANHO_MAXIMO];
		// Zero o número de objetos
		this.numObjs = 0;
	}
	
	/**
	 * Método para retornar a única instância existente do DAO
	 * @return
	 */
	public static IDAOPassageiro getSingleton() {
		if(DAOPassageiro.singleton == null)
			DAOPassageiro.singleton = new DAOPassageiro();
		return DAOPassageiro.singleton;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOPassageiro#salvar(dados.Passageiro)
	 */
	@Override
	public boolean salvar(Passageiro novo){
		if(this.numObjs == TAMANHO_MAXIMO)
			return false;
		this.listaObjs[this.numObjs++] = novo;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOPassageiro#remover(dados.Passageiro)
	 */
	@Override
	public boolean remover(Passageiro obj){
		for(int i = 0; i < TAMANHO_MAXIMO; i++){
			if(this.listaObjs[i] == obj){
				for(int j = i; j < TAMANHO_MAXIMO - 1; j++)
					this.listaObjs[j] = this.listaObjs[j+1];
				this.listaObjs[TAMANHO_MAXIMO - 1] = null;
				this.numObjs--;
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOPassageiro#atualizar(dados.Passageiro)
	 */
	@Override
	public boolean atualizar(Passageiro obj){
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOPassageiro#recuperar(int)
	 */
	@Override
	public Passageiro recuperar(int i){
		if(i < 0 || i >= TAMANHO_MAXIMO)
			return null;
		return this.listaObjs[i];
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOPassageiro#recuperarPeloCpf(java.lang.String)
	 */
	@Override
	public Passageiro recuperarPeloCpf(String cpf){
		for(int i = 0; i < TAMANHO_MAXIMO; i++)
			if(this.listaObjs[i].getCpf().equals(cpf))
				return this.listaObjs[i];
		return null;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOPassageiro#getNumObjs()
	 */
	@Override
	public int getNumObjs(){
		return this.numObjs;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOPassageiro#getListaObjs()
	 */
	@Override
	public Passageiro[] getListaObjs() {
		return this.listaObjs.clone();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOPassageiro#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) 
			throws IOException, ClassNotFoundException {
		// Recupera o numObjs
		this.numObjs = ois.readInt();
		// Recupera o array de objetos
		this.listaObjs = (Passageiro[])ois.readObject();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOPassageiro#salvarObjetos(java.io.ObjectOutputStream)
	 */
	@Override
	public void salvarObjetos(ObjectOutputStream oos) 
			throws IOException {
		// Salva o atributo numObjs
		oos.writeInt(this.numObjs);
		// Salva o array de objetos
		oos.writeObject(this.listaObjs);
	}
}

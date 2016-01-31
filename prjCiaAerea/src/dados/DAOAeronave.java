package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOAeronave implements IDAOAeronave {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAOAeronave 	singleton;
	/**
	 * Referência para o array que apontará para todos os objetos 
	 * guardados pelo DAO
	 */
	private Aeronave[] 			listaObjs;
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
	private DAOAeronave() {
		// Aloco memória para o array
		this.listaObjs = new Aeronave[TAMANHO_MAXIMO];
		// Zero o número de objetos
		this.numObjs = 0;
	}
	
	/**
	 * Método para retornar a única instância existente do DAO
	 * @return
	 */
	public static IDAOAeronave getSingleton() {
		if(DAOAeronave.singleton == null)
			DAOAeronave.singleton = new DAOAeronave();
		return DAOAeronave.singleton;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeronave#salvar(dados.Aeronave)
	 */
	@Override
	public boolean salvar(Aeronave novo){
		if(this.numObjs == TAMANHO_MAXIMO)
			return false;
		this.listaObjs[this.numObjs++] = novo;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeronave#remover(dados.Aeronave)
	 */
	@Override
	public boolean remover(Aeronave obj){
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
	 * @see dados.IDAOAeronave#atualizar(dados.Aeronave)
	 */
	@Override
	public boolean atualizar(Aeronave obj){
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeronave#recuperar(int)
	 */
	@Override
	public Aeronave recuperar(int i){
		if(i < 0 || i >= TAMANHO_MAXIMO)
			return null;
		return this.listaObjs[i];
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeronave#recuperarPeloNome(java.lang.String)
	 */
	@Override
	public Aeronave recuperarPeloNome(String nome){
		for(int i = 0; i < TAMANHO_MAXIMO; i++)
			if(this.listaObjs[i].getNome().equals(nome))
				return this.listaObjs[i];
		return null;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeronave#getNumObjs()
	 */
	@Override
	public int getNumObjs(){
		return this.numObjs;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeronave#getListaObjs()
	 */
	@Override
	public Aeronave[] getListaObjs() {
		return this.listaObjs.clone();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAeronave#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) 
			throws IOException, ClassNotFoundException {
		// Recupera o numObjs
		this.numObjs = ois.readInt();
		// Recupera o array de objetos
		this.listaObjs = (Aeronave[])ois.readObject();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAeronave#salvarObjetos(java.io.ObjectOutputStream)
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

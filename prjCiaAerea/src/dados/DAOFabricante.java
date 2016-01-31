package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOFabricante implements IDAOFabricante {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAOFabricante 	singleton;
	/**
	 * Referência para o array que apontará para todos os objetos 
	 * guardados pelo DAO
	 */
	private Fabricante[] 			listaObjs;
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
	private DAOFabricante() {
		// Aloco memória para o array
		this.listaObjs = new Fabricante[TAMANHO_MAXIMO];
		// Zero o número de objetos
		this.numObjs = 0;
	}
	
	/**
	 * Método para retornar a única instância existente do DAO
	 * @return
	 */
	public static IDAOFabricante getSingleton() {
		if(DAOFabricante.singleton == null)
			DAOFabricante.singleton = new DAOFabricante();
		return DAOFabricante.singleton;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOFabricante#salvar(dados.Fabricante)
	 */
	@Override
	public boolean salvar(Fabricante novo){
		if(this.numObjs == TAMANHO_MAXIMO)
			return false;
		this.listaObjs[this.numObjs++] = novo;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOFabricante#remover(dados.Fabricante)
	 */
	@Override
	public boolean remover(Fabricante obj){
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
	 * @see dados.IDAOFabricante#atualizar(dados.Fabricante)
	 */
	@Override
	public boolean atualizar(Fabricante obj){
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOFabricante#recuperar(int)
	 */
	@Override
	public Fabricante recuperar(int i){
		if(i < 0 || i >= TAMANHO_MAXIMO)
			return null;
		return this.listaObjs[i];
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOFabricante#recuperarPeloNome(java.lang.String)
	 */
	@Override
	public Fabricante recuperarPeloNome(String nome){
		for(int i = 0; i < TAMANHO_MAXIMO; i++)
			if(this.listaObjs[i].getNome().equals(nome))
				return this.listaObjs[i];
		return null;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOFabricante#getNumObjs()
	 */
	@Override
	public int getNumObjs(){
		return this.numObjs;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOFabricante#getListaObjs()
	 */
	@Override
	public Fabricante[] getListaObjs() {
		return this.listaObjs.clone();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOFabricante#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) 
			throws IOException, ClassNotFoundException {
		// Recupera o numObjs
		this.numObjs = ois.readInt();
		// Recupera o array de objetos
		this.listaObjs = (Fabricante[])ois.readObject();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOFabricante#salvarObjetos(java.io.ObjectOutputStream)
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

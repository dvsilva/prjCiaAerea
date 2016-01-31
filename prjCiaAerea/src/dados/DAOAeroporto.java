package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOAeroporto implements IDAOAeroporto {
	//
	// ATRIBUTOS
	//
	/**
	 * Refer�ncia para a �nica inst�ncia da classe que dever� existir
	 */
	private static IDAOAeroporto 	singleton;
	/**
	 * Refer�ncia para o array que apontar� para todos os objetos 
	 * guardados pelo DAO
	 */
	private Aeroporto[] 			listaObjs;
	/**
	 * Indica o n�mero de objetos sendo guardados pelo DAO
	 */
	private int            			numObjs;
	
	//
	// M�TODOS
	//
	/**
	 * Construtor privado do DAO
	 */
	private DAOAeroporto() {
		// Aloco mem�ria para o array
		this.listaObjs = new Aeroporto[TAMANHO_MAXIMO];
		// Zero o n�mero de objetos
		this.numObjs = 0;
	}
	
	/**
	 * M�todo para retornar a �nica inst�ncia existente do DAO
	 * @return
	 */
	public static IDAOAeroporto getSingleton() {
		if(DAOAeroporto.singleton == null)
			DAOAeroporto.singleton = new DAOAeroporto();
		return DAOAeroporto.singleton;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeroporto#salvar(dados.Aeroporto)
	 */
	@Override
	public boolean salvar(Aeroporto novo){
		if(this.numObjs == TAMANHO_MAXIMO)
			return false;
		this.listaObjs[this.numObjs++] = novo;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeroporto#remover(dados.Aeroporto)
	 */
	@Override
	public boolean remover(Aeroporto obj){
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
	 * @see dados.IDAOAeroporto#atualizar(dados.Aeroporto)
	 */
	@Override
	public boolean atualizar(Aeroporto obj){
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeroporto#recuperar(int)
	 */
	@Override
	public Aeroporto recuperar(int i){
		if(i < 0 || i >= TAMANHO_MAXIMO)
			return null;
		return this.listaObjs[i];
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeroporto#recuperarPelaCodigo(java.lang.String)
	 */
	@Override
	public Aeroporto recuperarPelaCodigo(String codAeroporto){
		for(int i = 0; i < TAMANHO_MAXIMO; i++)
			if(this.listaObjs[i].getCodAeroporto().equals(codAeroporto))
				return this.listaObjs[i];
		return null;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeroporto#getNumObjs()
	 */
	@Override
	public int getNumObjs(){
		return this.numObjs;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOAeroporto#getListaObjs()
	 */
	@Override
	public Aeroporto[] getListaObjs() {
		return this.listaObjs.clone();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAeroporto#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) 
			throws IOException, ClassNotFoundException {
		// Recupera o numObjs
		this.numObjs = ois.readInt();
		// Recupera o array de objetos
		this.listaObjs = (Aeroporto[])ois.readObject();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAeroporto#salvarObjetos(java.io.ObjectOutputStream)
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

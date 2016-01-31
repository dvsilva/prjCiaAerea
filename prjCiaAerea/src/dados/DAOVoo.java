package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOVoo implements IDAOVoo {
	//
	// ATRIBUTOS
	//
	/**
	 * Refer�ncia para a �nica inst�ncia da classe que dever� existir
	 */
	private static IDAOVoo 	singleton;
	/**
	 * Refer�ncia para o array que apontar� para todos os objetos 
	 * guardados pelo DAO
	 */
	private Voo[] 			listaObjs;
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
	private DAOVoo() {
		// Aloco mem�ria para o array
		this.listaObjs = new Voo[TAMANHO_MAXIMO];
		// Zero o n�mero de objetos
		this.numObjs = 0;
	}
	
	/**
	 * M�todo para retornar a �nica inst�ncia existente do DAO
	 * @return
	 */
	public static IDAOVoo getSingleton() {
		if(DAOVoo.singleton == null)
			DAOVoo.singleton = new DAOVoo();
		return DAOVoo.singleton;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOVoo#salvar(dados.Voo)
	 */
	@Override
	public boolean salvar(Voo novo){
		if(this.numObjs == TAMANHO_MAXIMO)
			return false;
		this.listaObjs[this.numObjs++] = novo;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOVoo#remover(dados.Voo)
	 */
	@Override
	public boolean remover(Voo obj){
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
	 * @see dados.IDAOVoo#atualizar(dados.Voo)
	 */
	@Override
	public boolean atualizar(Voo obj){
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOVoo#recuperar(int)
	 */
	@Override
	public Voo recuperar(int i){
		if(i < 0 || i >= TAMANHO_MAXIMO)
			return null;
		return this.listaObjs[i];
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOVoo#recuperarPeloCodigo(java.lang.String)
	 */
	@Override
	public Voo recuperarPeloCodigo(String codVoo){
		for(int i = 0; i < TAMANHO_MAXIMO; i++)
			if(this.listaObjs[i].getCodVoo().equals(codVoo))
				return this.listaObjs[i];
		return null;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOVoo#getNumObjs()
	 */
	@Override
	public int getNumObjs(){
		return this.numObjs;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOVoo#getListaObjs()
	 */
	@Override
	public Voo[] getListaObjs() {
		return this.listaObjs.clone();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOVoo#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) 
			throws IOException, ClassNotFoundException {
		// Recupera o numObjs
		this.numObjs = ois.readInt();
		// Recupera o array de objetos
		this.listaObjs = (Voo[])ois.readObject();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOVoo#salvarObjetos(java.io.ObjectOutputStream)
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

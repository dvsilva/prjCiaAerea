package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOModelo implements IDAOModelo {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAOModelo 	singleton;
	/**
	 * Referência para o array que apontará para todos os objetos 
	 * guardados pelo DAO
	 */
	private Modelo[] 			listaObjs;
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
	private DAOModelo() {
		// Aloco memória para o array
		this.listaObjs = new Modelo[TAMANHO_MAXIMO];
		// Zero o número de objetos
		this.numObjs = 0;
	}
	
	/**
	 * Método para retornar a única instância existente do DAO
	 * @return
	 */
	public static IDAOModelo getSingleton() {
		if(DAOModelo.singleton == null)
			DAOModelo.singleton = new DAOModelo();
		return DAOModelo.singleton;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOModelo#salvar(dados.Modelo)
	 */
	@Override
	public boolean salvar(Modelo novo){
		if(this.numObjs == TAMANHO_MAXIMO)
			return false;
		this.listaObjs[this.numObjs++] = novo;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOModelo#remover(dados.Modelo)
	 */
	@Override
	public boolean remover(Modelo obj){
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
	 * @see dados.IDAOModelo#atualizar(dados.Modelo)
	 */
	@Override
	public boolean atualizar(Modelo obj){
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOModelo#recuperar(int)
	 */
	@Override
	public Modelo recuperar(int i){
		if(i < 0 || i >= TAMANHO_MAXIMO)
			return null;
		return this.listaObjs[i];
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOModelo#recuperarPeloNome(java.lang.String)
	 */
	@Override
	public Modelo recuperarPeloNome(String nome){
		for(int i = 0; i < TAMANHO_MAXIMO; i++)
			if(this.listaObjs[i].getNome().equals(nome))
				return this.listaObjs[i];
		return null;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOModelo#getNumObjs()
	 */
	@Override
	public int getNumObjs(){
		return this.numObjs;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOModelo#getListaObjs()
	 */
	@Override
	public Modelo[] getListaObjs() {
		return this.listaObjs.clone();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOModelo#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) 
			throws IOException, ClassNotFoundException {
		// Recupera o numObjs
		this.numObjs = ois.readInt();
		// Recupera o array de objetos
		this.listaObjs = (Modelo[])ois.readObject();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOModelo#salvarObjetos(java.io.ObjectOutputStream)
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

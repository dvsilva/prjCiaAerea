package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOEscala implements IDAOEscala {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAOEscala 	singleton;
	/**
	 * Referência para o array que apontará para todos os objetos 
	 * guardados pelo DAO
	 */
	private Escala[] 			listaObjs;
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
	private DAOEscala() {
		// Aloco memória para o array
		this.listaObjs = new Escala[TAMANHO_MAXIMO];
		// Zero o número de objetos
		this.numObjs = 0;
	}
	
	/**
	 * Método para retornar a única instância existente do DAO
	 * @return
	 */
	public static IDAOEscala getSingleton() {
		if(DAOEscala.singleton == null)
			DAOEscala.singleton = new DAOEscala();
		return DAOEscala.singleton;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOEscala#salvar(dados.Escala)
	 */
	@Override
	public boolean salvar(Escala novo){
		if(this.numObjs == TAMANHO_MAXIMO)
			return false;
		this.listaObjs[this.numObjs++] = novo;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOEscala#remover(dados.Escala)
	 */
	@Override
	public boolean remover(Escala obj){
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
	 * @see dados.IDAOEscala#atualizar(dados.Escala)
	 */
	@Override
	public boolean atualizar(Escala obj){
		return true;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOEscala#recuperar(int)
	 */
	@Override
	public Escala recuperar(int i){
		if(i < 0 || i >= TAMANHO_MAXIMO)
			return null;
		return this.listaObjs[i];
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOEscala#recuperarPelaOrdem(java.lang.String)
	 */
	@Override
	public Escala recuperarPelaOrdem(String ordem){
		for(int i = 0; i < TAMANHO_MAXIMO; i++)
			if(this.listaObjs[i].getOrdem().equals(ordem))
				return this.listaObjs[i];
		return null;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOEscala#getNumObjs()
	 */
	@Override
	public int getNumObjs(){
		return this.numObjs;
	}
	
	/* (non-Javadoc)
	 * @see dados.IDAOEscala#getListaObjs()
	 */
	@Override
	public Escala[] getListaObjs() {
		return this.listaObjs.clone();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOEscala#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) 
			throws IOException, ClassNotFoundException {
		// Recupera o numObjs
		this.numObjs = ois.readInt();
		// Recupera o array de objetos
		this.listaObjs = (Escala[])ois.readObject();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOEscala#salvarObjetos(java.io.ObjectOutputStream)
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

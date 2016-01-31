package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DAOAssento implements IDAOAssento {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para a única instância da classe que deverá existir
	 */
	private static IDAOAssento singleton;
	/**
	 * Referência para o array que apontará para todos os objetos guardados pelo
	 * DAO
	 */
	private Assento[] listaObjs;
	/**
	 * Indica o número de objetos sendo guardados pelo DAO
	 */
	private int numObjs;

	//
	// MÉTODOS
	//
	/**
	 * Construtor privado do DAO
	 */
	private DAOAssento() {
		// Aloco memória para o array
		this.listaObjs = new Assento[TAMANHO_MAXIMO];
		// Zero o número de objetos
		this.numObjs = 0;
	}

	/**
	 * Método para retornar a única instância existente do DAO
	 * 
	 * @return
	 */
	public static IDAOAssento getSingleton() {
		if (DAOAssento.singleton == null)
			DAOAssento.singleton = new DAOAssento();
		return DAOAssento.singleton;
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAssento#salvar(dados.Assento)
	 */
	@Override
	public boolean salvar(Assento novo) {
		if (this.numObjs == TAMANHO_MAXIMO)
			return false;
		this.listaObjs[this.numObjs++] = novo;
		return true;
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAssento#remover(dados.Assento)
	 */
	@Override
	public boolean remover(Assento obj) {
		for (int i = 0; i < TAMANHO_MAXIMO; i++) {
			if (this.listaObjs[i] == obj) {
				for (int j = i; j < TAMANHO_MAXIMO - 1; j++)
					this.listaObjs[j] = this.listaObjs[j + 1];
				this.listaObjs[TAMANHO_MAXIMO - 1] = null;
				this.numObjs--;
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAssento#atualizar(dados.Assento)
	 */
	@Override
	public boolean atualizar(Assento obj) {
		return true;
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAssento#recuperar(int)
	 */
	@Override
	public Assento recuperar(int i) {
		if (i < 0 || i >= TAMANHO_MAXIMO)
			return null;
		return this.listaObjs[i];
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAssento#recuperarPeloAssentoNum(java.lang.String)
	 */
	@Override
	public Assento recuperarPeloAssentoNum(String assentoNum) {
		for (int i = 0; i < TAMANHO_MAXIMO; i++)
			if (this.listaObjs[i].getAssentoNum().equals(assentoNum))
				return this.listaObjs[i];
		return null;
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAssento#getNumObjs()
	 */
	@Override
	public int getNumObjs() {
		return this.numObjs;
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAssento#getListaObjs()
	 */
	@Override
	public Assento[] getListaObjs() {
		return this.listaObjs.clone();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAssento#recuperarObjetos(java.io.ObjectInputStream)
	 */
	@Override
	public void recuperarObjetos(ObjectInputStream ois) throws IOException,
			ClassNotFoundException {
		// Recupera o numObjs
		this.numObjs = ois.readInt();
		// Recupera o array de objetos
		this.listaObjs = (Assento[]) ois.readObject();
	}

	/* (non-Javadoc)
	 * @see dados.IDAOAssento#salvarObjetos(java.io.ObjectOutputStream)
	 */
	@Override
	public void salvarObjetos(ObjectOutputStream oos) throws IOException {
		// Salva o atributo numObjs
		oos.writeInt(this.numObjs);
		// Salva o array de objetos
		oos.writeObject(this.listaObjs);
	}
}

package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface IDAOEscala {

	//
	// CONSTANTES
	//
	/**
	 * Define o tamanho máximo de objetos que podem ser armazenados
	 */
	public static final int TAMANHO_MAXIMO = 20;

	/**
	 * Salva um objeto 
	 * @param novo
	 * @return
	 */
	public abstract boolean salvar(Escala novo);

	/**
	 * Remove um objeto
	 * @param obj
	 * @return
	 */
	public abstract boolean remover(Escala obj);

	/**
	 * Promove a atualização de um objeto
	 * @param obj
	 * @return
	 */
	public abstract boolean atualizar(Escala obj);

	/**
	 * Recupera um objeto pela posição
	 * @param i
	 * @return
	 */
	public abstract Escala recuperar(int i);

	/**
	 * Recupera um objeto pela Ordem
	 * @param ordem
	 * @return
	 */
	public abstract Escala recuperarPelaOrdem(String ordem);

	/**
	 * Retorna o número de objetos sendo gerenciados pelo DAO
	 * @return
	 */
	public abstract int getNumObjs();

	/**
	 * Retorna uma cópia da lista de objetos
	 * @return
	 */
	public abstract Escala[] getListaObjs();

	/**
	 * Recupera os objetos 
	 * @return
	 */
	public abstract void recuperarObjetos(ObjectInputStream ois)
			throws IOException, ClassNotFoundException;

	/**
	 * Salva os objetos 
	 * @return
	 */
	public abstract void salvarObjetos(ObjectOutputStream oos)
			throws IOException;

}
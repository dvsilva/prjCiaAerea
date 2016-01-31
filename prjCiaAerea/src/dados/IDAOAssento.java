package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface IDAOAssento {

	//
	// CONSTANTES
	//
	/**
	 * Define o tamanho máximo de objetos que podem ser armazenados
	 */
	public static final int TAMANHO_MAXIMO = 20;

	/**
	 * Salva um objeto
	 * 
	 * @param novo
	 * @return
	 */
	public abstract boolean salvar(Assento novo);

	/**
	 * Remove um objeto
	 * 
	 * @param obj
	 * @return
	 */
	public abstract boolean remover(Assento obj);

	/**
	 * Promove a atualização de um objeto
	 * 
	 * @param obj
	 * @return
	 */
	public abstract boolean atualizar(Assento obj);

	/**
	 * Recupera um objeto pela posição
	 * 
	 * @param i
	 * @return
	 */
	public abstract Assento recuperar(int i);

	/**
	 * Recupera um objeto pelo Número do assento
	 * @param assentoNum
	 * @return
	 */
	public abstract Assento recuperarPeloAssentoNum(String assentoNum);

	/**
	 * Retorna o número de objetos sendo gerenciados pelo DAO
	 * 
	 * @return
	 */
	public abstract int getNumObjs();

	/**
	 * Retorna uma cópia da lista de objetos
	 * 
	 * @return
	 */
	public abstract Assento[] getListaObjs();

	/**
	 * Recupera os objetos
	 * 
	 * @return
	 */
	public abstract void recuperarObjetos(ObjectInputStream ois)
			throws IOException, ClassNotFoundException;

	/**
	 * Salva os objetos
	 * 
	 * @return
	 */
	public abstract void salvarObjetos(ObjectOutputStream oos)
			throws IOException;

}
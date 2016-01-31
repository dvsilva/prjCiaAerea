package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface IDAOAeronave {

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
	public abstract boolean salvar(Aeronave novo);

	/**
	 * Remove um objeto
	 * @param obj
	 * @return
	 */
	public abstract boolean remover(Aeronave obj);

	/**
	 * Promove a atualização de um objeto
	 * @param obj
	 * @return
	 */
	public abstract boolean atualizar(Aeronave obj);

	/**
	 * Recupera um objeto pela posição
	 * @param i
	 * @return
	 */
	public abstract Aeronave recuperar(int i);

	/**
	 * Recupera um objeto pelo Nome
	 * @param nome
	 * @return
	 */
	public abstract Aeronave recuperarPeloNome(String nome);

	/**
	 * Retorna o número de objetos sendo gerenciados pelo DAO
	 * @return
	 */
	public abstract int getNumObjs();

	/**
	 * Retorna uma cópia da lista de objetos
	 * @return
	 */
	public abstract Aeronave[] getListaObjs();

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
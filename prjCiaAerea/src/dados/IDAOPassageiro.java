package dados;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface IDAOPassageiro {

	//
	// CONSTANTES
	//
	/**
	 * Define o tamanho m�ximo de objetos que podem ser armazenados
	 */
	public static final int TAMANHO_MAXIMO = 20;

	/**
	 * Salva um objeto 
	 * @param novo
	 * @return
	 */
	public abstract boolean salvar(Passageiro novo);

	/**
	 * Remove um objeto
	 * @param obj
	 * @return
	 */
	public abstract boolean remover(Passageiro obj);

	/**
	 * Promove a atualiza��o de um objeto
	 * @param obj
	 * @return
	 */
	public abstract boolean atualizar(Passageiro obj);

	/**
	 * Recupera um objeto pela posi��o
	 * @param i
	 * @return
	 */
	public abstract Passageiro recuperar(int i);

	/**
	 * Recupera um objeto pelo CPF
	 * @param cpf
	 * @return
	 */
	public abstract Passageiro recuperarPeloCpf(String cpf);

	/**
	 * Retorna o n�mero de objetos sendo gerenciados pelo DAO
	 * @return
	 */
	public abstract int getNumObjs();

	/**
	 * Retorna uma c�pia da lista de objetos
	 * @return
	 */
	public abstract Passageiro[] getListaObjs();

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
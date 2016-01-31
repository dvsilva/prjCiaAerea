package controle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dados.DAOAeronave;
import dados.DAOAeroporto;
import dados.DAOAssento;
import dados.DAOEscala;
import dados.DAOFabricante;
import dados.DAOModelo;
import dados.DAOPassageiro;
import dados.DAOVoo;
import dados.IDAOAeronave;
import dados.IDAOAeroporto;
import dados.IDAOAssento;
import dados.IDAOEscala;
import dados.IDAOFabricante;
import dados.IDAOModelo;
import dados.IDAOPassageiro;
import dados.IDAOVoo;
import face.JanelaPrincipal;

/**
 * Este é o controlador que gerencia a execução do meu programa.
 * 
 * @author Felipe, Hugo e Danyllo
 */
public class CtrlPrograma {
	//
	// ATRIBUTOS
	// ---------
	// O controlador do programa deve ter um atributo para
	// cada controlador de caso de uso do programa.
	//
	// Também o controlador do programa deve ter um atributo
	// para referenciar a janela principal do programa.
	//

	/**
	 * Referência para o controlador do caso de uso Manter Aeroportos
	 */
	private ICtrlManterAeroportos ctrlAeroportos;

	/**
	 * Referência para o controlador do caso de uso Manter Aeronaves
	 */
	private CtrlManterAeronaves ctrlAeronaves;

	/**
	 * Referência para o controlador do caso de uso Manter Escalas
	 */
	private CtrlManterEscalas ctrlEscalas;

	/**
	 * Referência para o controlador do caso de uso Manter Fabricantes
	 */
	private CtrlManterFabricantes ctrlFabricantes;

	/**
	 * Referência para o controlador do caso de uso Manter Modelos
	 */
	private CtrlManterModelos ctrlModelos;

	/**
	 * Referência para o controlador do caso de uso Manter Passageiros
	 */
	private ICtrlManterPassageiros ctrlPassageiros;

	/**
	 * Referência para o controlador do caso de uso Manter Assentos
	 */
	private ICtrlManterAssentos ctrlAssentos;

	/**
	 * Referência para o controlador do caso de uso Manter Voos
	 */
	private ICtrlManterVoos ctrlVoos;

	/**
	 * Referência para a janela principal do programa
	 */
	private JanelaPrincipal jPrincipal;

	//
	// MÉTODOS
	//

	/**
	 * Construtor do CtrlPrograma
	 */
	public CtrlPrograma() {
		// Instanciando os controladores de caso de uso do sistema
		this.ctrlAeroportos = new CtrlManterAeroportos(this);
		this.ctrlAeronaves = new CtrlManterAeronaves(this);
		this.ctrlEscalas = new CtrlManterEscalas(this);
		this.ctrlFabricantes = new CtrlManterFabricantes(this);
		this.ctrlModelos = new CtrlManterModelos(this);
		this.ctrlPassageiros = new CtrlManterPassageiros(this);
		this.ctrlAssentos = new CtrlManterAssentos(this);
		this.ctrlVoos = new CtrlManterVoos(this);
	}

	/**
	 * Procedimentos executados no início do programa
	 */
	public void iniciar() {
		// Cria e apresenta a janela principal
		this.jPrincipal = new JanelaPrincipal(this);

		// Recupera os DAOs do sistema
		IDAOAeroporto daoAeroporto = DAOAeroporto.getSingleton();
		IDAOAeronave daoAeronave = DAOAeronave.getSingleton();
		IDAOEscala daoEscala = DAOEscala.getSingleton();
		IDAOFabricante daoFabricante = DAOFabricante.getSingleton();
		IDAOModelo daoModelo = DAOModelo.getSingleton();
		IDAOPassageiro daoPassageiro = DAOPassageiro.getSingleton();
		IDAOAssento daoAssento = DAOAssento.getSingleton();
		IDAOVoo daoVoo = DAOVoo.getSingleton();

		//
		// Recuperação dos objetos serializados no arquivo c:/dados.dat
		//
		try {
			// Abrindo o arquivo para leitura binária
			//FileInputStream fis = new FileInputStream("c:/dados.dat");
			FileInputStream fis = new FileInputStream("dados.dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			// Solicitação para os DAOs gerenciarem os objetos recuperados do
			// arquivo
			daoAeroporto.recuperarObjetos(ois);
			daoAeronave.recuperarObjetos(ois);
			daoEscala.recuperarObjetos(ois);
			daoFabricante.recuperarObjetos(ois);
			daoModelo.recuperarObjetos(ois);
			daoPassageiro.recuperarObjetos(ois);
			daoAssento.recuperarObjetos(ois);
			daoVoo.recuperarObjetos(ois);

			// Fechando o arquivo
			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo dados.dat não encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Procedimentos executados no final do programa
	 */
	public void terminar() {
		// Recuperando os DAOs do sistema
		IDAOAeroporto daoAeroporto = DAOAeroporto.getSingleton();
		IDAOAeronave daoAeronave = DAOAeronave.getSingleton();
		IDAOEscala daoEscala = DAOEscala.getSingleton();
		IDAOFabricante daoFabricante = DAOFabricante.getSingleton();
		IDAOModelo daoModelo = DAOModelo.getSingleton();
		IDAOPassageiro daoPassageiro = DAOPassageiro.getSingleton();
		IDAOAssento daoAssento = DAOAssento.getSingleton();
		IDAOVoo daoVoo = DAOVoo.getSingleton();

		try {
			// Abrindo o arquivo c:/dados.dat para escrita
			//FileOutputStream fos = new FileOutputStream("c:/dados.dat");
			FileOutputStream fos = new FileOutputStream("dados.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			// Salvando os objetos gerenciados pelos DAOs
			daoAeroporto.salvarObjetos(oos);
			daoAeronave.salvarObjetos(oos);
			daoEscala.salvarObjetos(oos);
			daoFabricante.salvarObjetos(oos);
			daoModelo.salvarObjetos(oos);
			daoPassageiro.salvarObjetos(oos);
			daoAssento.salvarObjetos(oos);
			daoVoo.salvarObjetos(oos);
			// Fechando e salvando o arquivo
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Método estático da classe System que encerra o programa
		System.exit(0);
	}

	/**
	 * Procedimentos executados pelo controlador do programa para iniciar o caso
	 * de uso manter Aeronaves
	 */
	public void iniciarCasoDeUsoManterAeronaves() {
		this.ctrlAeronaves.iniciar();
	}

	/**
	 * Procedimentos executados pelo controlador do programa para finalizar o
	 * caso de uso Manter Aeronaves.
	 */
	public boolean terminarCasoDeUsoManterAeronaves() {
		return true;
	}

	/**
	 * Procedimentos executados pelo controlador do programa para iniciar o caso
	 * de uso manter Aeroportos
	 */
	public void iniciarCasoDeUsoManterAeroportos() {
		this.ctrlAeroportos.iniciar();
	}

	/**
	 * Procedimentos executados pelo controlador do programa para finalizar o
	 * caso de uso Manter Aeroportos.
	 */
	public boolean terminarCasoDeUsoManterAeroportos() {
		return true;
	}

	/**
	 * Procedimentos executados pelo controlador do programa para iniciar o caso
	 * de uso manter Assentos
	 */
	public void iniciarCasoDeUsoManterAssentos() {
		this.ctrlAssentos.iniciar();
	}

	/**
	 * Procedimentos executados pelo controlador do programa para finalizar o
	 * caso de uso Manter Assentos.
	 */
	public boolean terminarCasoDeUsoManterAssentos() {
		return true;
	}

	/**
	 * Procedimentos executados pelo controlador do programa para iniciar o caso
	 * de uso manter Escalas
	 */
	public void iniciarCasoDeUsoManterEscalas() {
		this.ctrlEscalas.iniciar();
	}

	/**
	 * Procedimentos executados pelo controlador do programa para finalizar o
	 * caso de uso Manter Escalas.
	 */
	public boolean terminarCasoDeUsoManterEscalas() {
		return true;
	}

	/**
	 * Procedimentos executados pelo controlador do programa para iniciar o caso
	 * de uso manter Fabricantes.
	 */
	public void iniciarCasoDeUsoManterFabricantes() {
		this.ctrlFabricantes.iniciar();
	}

	/**
	 * Procedimentos executados pelo controlador do programa para finalizar o
	 * caso de uso Manter Fabricantes.
	 */
	public boolean terminarCasoDeUsoManterFabricantes() {
		return true;
	}

	/**
	 * Procedimentos executados pelo controlador do programa para iniciar o caso
	 * de uso manter Modelos.
	 */
	public void iniciarCasoDeUsoManterModelos() {
		this.ctrlModelos.iniciar();
	}

	/**
	 * Procedimentos executados pelo controlador do programa para finalizar o
	 * caso de uso Manter Modelos.
	 */
	public boolean terminarCasoDeUsoManterModelos() {
		return true;
	}

	/**
	 * Procedimentos executados pelo controlador do programa para iniciar o caso
	 * de uso manter Passageiros.
	 */
	public void iniciarCasoDeUsoManterPassageiros() {
		this.ctrlPassageiros.iniciar();
	}

	/**
	 * Procedimentos executados pelo controlador do programa para finalizar o
	 * caso de uso Manter Passageiros.
	 */
	public boolean terminarCasoDeUsoManterPassageiros() {
		return true;
	}

	/**
	 * Procedimentos executados pelo controlador do programa para iniciar o caso
	 * de uso manter voos.
	 */
	public void iniciarCasoDeUsoManterVoos() {
		this.ctrlVoos.iniciar();
	}

	/**
	 * Procedimentos executados pelo controlador do programa para finalizar o
	 * caso de uso Manter Voos.
	 */
	public boolean terminarCasoDeUsoManterVoos() {
		return true;
	}

	/**
	 * O método main corresponde ao ponto inicial de execução
	 * de um programa em Java.
	 * @param args
	 */
	public static void main(String[] args) {
		CtrlPrograma prg = new CtrlPrograma();
		prg.iniciar();
	}
}

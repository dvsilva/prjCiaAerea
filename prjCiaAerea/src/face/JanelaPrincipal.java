package face;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.CtrlPrograma;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * Classe que implementa a janela principal do sistema
 * @authors Felipe, Hugo e Danyllo
 */
public class JanelaPrincipal extends JFrame {

	//
	// ATRIBUTOS
	//
	
	/**
	 * Refer�ncia para o painel de conte�do da janela
	 */
	private JPanel contentPane;
	/**
	 * Refer�ncia para o controlador do programa a quem a janela
	 * principal ir� sempre mandar a notifica��o.
	 */
	private CtrlPrograma ctrlPrg;
	
	//
	// M�TODOS
	//
	/**
	 * Create the frame.
	 */
	public JanelaPrincipal(CtrlPrograma p) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrlPrg = p;
		setTitle("Janela Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 282);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		JButton btnAeronave = new JButton("Aeronave");
		btnAeronave.addActionListener(new ActionListener() {
			// M�todo acionado quando o bot�o "Aeronaves" 
			// for pressionado (M�todo de Callback).
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterAeronaves();
			}
		});
		btnAeronave.setBounds(67, 40, 118, 23);
		contentPane.add(btnAeronave);

		JButton btnAeroporto = new JButton("Aeroporto");
		btnAeroporto.addActionListener(new ActionListener() {
			// M�todo acionado quando o bot�o "Aeroportos" 
			// for pressionado (M�todo de Callback).
			public void actionPerformed(ActionEvent arg0) {
				ctrlPrg.iniciarCasoDeUsoManterAeroportos();
			}
		});
		btnAeroporto.setBounds(67, 74, 118, 23);
		contentPane.add(btnAeroporto);


		JButton btnAssento = new JButton("Assento");
		btnAssento.addActionListener(new ActionListener() {
			// M�todo acionado quando o bot�o "Assentos" 
			// for pressionado (M�todo de Callback).
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterAssentos();
			}
		});
		btnAssento.setBounds(67, 105, 118, 23);
		contentPane.add(btnAssento);

		JButton btnEscala = new JButton("Escala");
		btnEscala.addActionListener(new ActionListener() {
			// M�todo acionado quando o bot�o "Escalas" 
			// for pressionado (M�todo de Callback).
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterEscalas();
			}
		});
		btnEscala.setBounds(67, 139, 118, 23);
		contentPane.add(btnEscala);

		JButton btnFabricante = new JButton("Fabricante");
		btnFabricante.addActionListener(new ActionListener() {
			// M�todo acionado quando o bot�o "Fabricantes" 
			// for pressionado (M�todo de Callback).
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterFabricantes();
			}
		});
		btnFabricante.setBounds(228, 40, 118, 23);
		contentPane.add(btnFabricante);

		JButton btnModelo = new JButton("Modelo");
		btnModelo.addActionListener(new ActionListener() {
			// M�todo acionado quando o bot�o "Modelos" 
			// for pressionado (M�todo de Callback).
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterModelos();
			}
		});
		btnModelo.setBounds(228, 74, 118, 23);
		contentPane.add(btnModelo);

		JButton btnPassageiro = new JButton("Passageiro");
		btnPassageiro.addActionListener(new ActionListener() {
			// M�todo acionado quando o bot�o "Passageiros" 
			// for pressionado (M�todo de Callback).
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterPassageiros();
			}
		});
		btnPassageiro.setBounds(228, 105, 118, 23);
		contentPane.add(btnPassageiro);

		JButton btnVoo = new JButton("Voo");
		btnVoo.addActionListener(new ActionListener() {
			// M�todo acionado quando o bot�o "Voos" 
			// for pressionado (M�todo de Callback).
			public void actionPerformed(ActionEvent e) {
				ctrlPrg.iniciarCasoDeUsoManterVoos();
			}
		});
		btnVoo.setBounds(228, 139, 118, 23);
		contentPane.add(btnVoo);
		this.setVisible(true);
	

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// M�todo acionado quando o bot�o "Sair" 
				// for pressionado (M�todo de Callback).
				ctrlPrg.terminar();
			}
		});
		btnSair.setBounds(318, 211, 89, 23);
		contentPane.add(btnSair);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("files/airplane_101694.jpg")));
		lblNewLabel.setBounds(93, 0, 434, 244);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getClassLoader().getResource("files/airline.png")));
		lblNewLabel_1.setBounds(12, 196, 92, 36);
		contentPane.add(lblNewLabel_1);
		
		setContentPane(contentPane);
		
		this.setVisible(true);
	}
}

	package face;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.ICtrlManterFabricantes;

public class JanelaFabricante extends JFrame implements IJanelaFabricante{
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterFabricantes ctrl;
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfTelefone;

	/**
	 * Create the frame.
	 */
	public JanelaFabricante(ICtrlManterFabricantes ct) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrl = ct;
		setTitle("Fabricante");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 190);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 12, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblTelefone= new JLabel("Telefones:");
		lblTelefone.setBounds(20, 52, 86, 14);
		contentPane.add(lblTelefone);
		
		tfNome = new JTextField();
		tfNome.setBounds(84, 9, 253, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfTelefone = new JTextField();
		tfTelefone.setBounds(84, 49, 188, 20);
		contentPane.add(tfTelefone);
		tfTelefone.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero os valores digitados nos textfields
				String nome = tfNome.getText();
				String telefone = tfTelefone.getText();
				// Verifico qual é a operação que estou fazendo
				// e notifico ao controlador
				if(!ehAlteração)
					ctrl.incluir(nome,telefone);
				else
					ctrl.alterar(nome,telefone);
			}
		});
		btnOk.setBounds(80, 105, 86, 23);
		contentPane.add(btnOk);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!ehAlteração)
					ctrl.cancelarIncluir();
				else
					ctrl.cancelarAlterar();
			}
		});
		btnCancelar.setBounds(218, 105, 86, 23);
		contentPane.add(btnCancelar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("files/airplane-icon_small.jpg")));
		label.setBounds(305, 33, 162, 132);
		contentPane.add(label);
		
		this.setVisible(true);
	}

	/**
	 * Determina os valores para os campos da janela
	 * @param sigla
	 * @param nome
	 */
	public void atualizarCampos(String nome, String telefone) {
		this.tfNome.setText(nome);
		this.tfTelefone.setText(telefone);
		this.ehAlteração = true;
	}
}

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

import controle.ICtrlManterAeroportos;

public class JanelaAeroporto extends JFrame implements IJanelaAeroporto{
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterAeroportos ctrl;
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JTextField tfTelefones;

	/**
	 * Create the frame.
	 */
	public JanelaAeroporto(ICtrlManterAeroportos ct) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrl = ct;
		setTitle("Aeroporto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 287);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(20, 27, 46, 14);
		contentPane.add(lblCodigo);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 75, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(20, 121, 86, 14);
		contentPane.add(lblEndereco);
		
		JLabel lblTelefones = new JLabel("Telefones:");
		lblTelefones.setBounds(20, 171, 66, 14);
		contentPane.add(lblTelefones);
		
		tfCodigo = new JTextField();
		tfCodigo.setBounds(98, 24, 141, 20);
		contentPane.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setBounds(95, 72, 334, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfEndereco = new JTextField();
		tfEndereco.setBounds(97, 118, 332, 20);
		contentPane.add(tfEndereco);
		tfEndereco.setColumns(10);
		
		tfTelefones = new JTextField();
		tfTelefones.setBounds(97, 168, 259, 20);
		contentPane.add(tfTelefones);
		tfTelefones.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero os valores digitados nos textfields
				String codAeroporto = tfCodigo.getText();
				String nome = tfNome.getText();
				String endereco = tfEndereco.getText();
				String telefones = tfTelefones.getText();
				// Verifico qual é a operação que estou fazendo
				// e notifico ao controlador
				if(!ehAlteração)
					ctrl.incluir(codAeroporto, nome, endereco, telefones);
				else
					ctrl.alterar(codAeroporto, nome, endereco, telefones);
			}
		});
		btnOk.setBounds(115, 218, 86, 23);
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
		btnCancelar.setBounds(262, 218, 86, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("files/airplane-icon_small.jpg")));
		lblNewLabel.setBounds(346, 129, 125, 147);
		contentPane.add(lblNewLabel);
		
		this.setVisible(true);
	}

	/**
	 * Determina os valores para os campos da janela
	 * @param sigla
	 * @param nome
	 */
	public void atualizarCampos(String codAeroporto, String nome, String endereco, String telefones) {
		this.tfCodigo.setText(codAeroporto);
		this.tfNome.setText(nome);
		this.tfEndereco.setText(endereco);
		this.tfTelefones.setText(telefones);
		this.ehAlteração = true;
	}

}

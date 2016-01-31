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

import controle.ICtrlManterPassageiros;

public class JanelaPassageiro extends JFrame implements IJanelaPassageiro{
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterPassageiros ctrl;
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JTextField tfEndereco;
	private JTextField tfTelefone;

	/**
	 * Create the frame.
	 */
	public JanelaPassageiro(ICtrlManterPassageiros ct) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrl = ct;
		setTitle("Passageiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 259);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(20, 22, 46, 14);
		contentPane.add(lblCpf);
		
		JLabel lblEndereco = new JLabel("Endereço:");
		lblEndereco.setBounds(20, 100, 78, 14);
		contentPane.add(lblEndereco);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		contentPane.add(lblTelefone);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(84, 19, 253, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);
		
		tfNome = new JTextField();
		tfNome.setBounds(84, 57, 311, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfEndereco = new JTextField();
		tfEndereco.setBounds(84, 97, 311, 20);
		contentPane.add(tfEndereco);
		tfEndereco.setColumns(10);
		
		tfTelefone = new JTextField();
		tfTelefone.setBounds(84, 134, 208, 20);
		contentPane.add(tfTelefone);
		tfTelefone.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero os valores digitados nos textfields
				String cpf = tfCpf.getText();
				String nome = tfNome.getText();
				String endereco = tfEndereco.getText();
				String telefone = tfTelefone.getText();
				// Verifico qual é a operação que estou fazendo
				// e notifico ao controlador
				if(!ehAlteração)
					ctrl.incluir(cpf, nome, endereco, telefone);
				else
					ctrl.alterar(cpf, nome, endereco, telefone);
			}
		});
		btnOk.setBounds(97, 186, 89, 23);
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
		btnCancelar.setBounds(234, 186, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblTelefone_1 = new JLabel("Telefone:");
		lblTelefone_1.setBounds(20, 136, 55, 16);
		contentPane.add(lblTelefone_1);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(20, 59, 55, 16);
		contentPane.add(lblNome);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("files/airplane-icon_small.jpg")));
		lblNewLabel.setBounds(311, 65, 191, 217);
		contentPane.add(lblNewLabel);
		
		this.setVisible(true);
	}

	/**
	 * Determina os valores para os campos da janela
	 * @param sigla
	 * @param nome
	 */
	public void atualizarCampos(String cpf, String nome, String endereco, String telefone) {
		this.tfCpf.setText(cpf);
		this.tfNome.setText(nome);
		this.tfEndereco.setText(endereco);
		this.tfTelefone.setText(telefone);
		this.ehAlteração = true;
	}
}

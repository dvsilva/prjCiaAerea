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

import controle.ICtrlManterModelos;

public class JanelaModelo extends JFrame implements IJanelaModelo {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterModelos ctrl;
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfNumMaxLugar;
	private JTextField tfAutonomiaVoo;
	private JTextField tfCapacidadeTanque;

	/**
	 * Create the frame.
	 */
	public JanelaModelo(ICtrlManterModelos ct) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrl = ct;
		setTitle("Modelo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 285);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNome = new JLabel("Nome do modelo:");
		lblNome.setBounds(22, 26, 102, 14);
		contentPane.add(lblNome);
			
		JLabel lblNumMaxLugar = new JLabel("Número máximo de lugares:");
		lblNumMaxLugar.setBounds(22, 68, 176, 14);
		contentPane.add(lblNumMaxLugar);
		
		JLabel lblAutonomiaVoo = new JLabel("Autonomia de voo:");
		lblAutonomiaVoo.setBounds(22, 112, 125, 14);
		contentPane.add(lblAutonomiaVoo);
		
		JLabel lblCapacidadeTanque = new JLabel("Capacidade do tanque:");
		lblCapacidadeTanque.setBounds(22, 152, 150, 14);
		contentPane.add(lblCapacidadeTanque);
			
		tfNome = new JTextField();
		tfNome.setBounds(196, 23, 188, 20);
		contentPane.add(tfNome);
		tfNome.setColumns(10);
		
		tfNumMaxLugar = new JTextField();
		tfNumMaxLugar.setBounds(196, 65, 113, 20);
		contentPane.add(tfNumMaxLugar);
		tfNumMaxLugar.setColumns(10);
		
		tfAutonomiaVoo = new JTextField();
		tfAutonomiaVoo.setBounds(196, 109, 113, 20);
		contentPane.add(tfAutonomiaVoo);
		tfAutonomiaVoo.setColumns(10);
		
		tfCapacidadeTanque = new JTextField();
		tfCapacidadeTanque.setBounds(196, 149, 113, 20);
		contentPane.add(tfCapacidadeTanque);
		tfCapacidadeTanque.setColumns(10);
		
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero os valores digitados nos textfields
				String nome = tfNome.getText();
				String numMaxLugar = tfNumMaxLugar.getText();
				String autonomiaVoo = tfAutonomiaVoo.getText();
				String capacidadeTanque = tfCapacidadeTanque.getText();
				// Verifico qual é a operação que estou fazendo
				// e notifico ao controlador
				if(!ehAlteração)
					ctrl.incluir (nome, numMaxLugar, autonomiaVoo, capacidadeTanque, null);//TODO ntirar o null
				else
					ctrl.alterar(nome, numMaxLugar, autonomiaVoo, capacidadeTanque, null);//TODO ntirar o null
			}
		});
		btnOk.setBounds(79, 200, 86, 23);
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
		btnCancelar.setBounds(223, 200, 86, 23);
		contentPane.add(btnCancelar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("files/airplane-icon_small.jpg")));
		label.setBounds(310, 121, 141, 126);
		contentPane.add(label);
		
		this.setVisible(true);
	}

	/**
	 * Determina os valores para os campos da janela
	 * @param nome
	 * @param numMaxLugar
	 * @param autonomiaVoo
	 * @param capacidadeTanque
	 */
	public void atualizarCampos(String numMaxLugar, String autonomiaVoo, String capacidadeTanque, String nome) {
		this.tfNome.setText(nome);
		this.tfNumMaxLugar.setText(numMaxLugar);
		this.tfAutonomiaVoo.setText(autonomiaVoo);
		this.tfCapacidadeTanque.setText(capacidadeTanque);
		this.ehAlteração = true;
	}
}

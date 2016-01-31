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

import controle.ICtrlManterAssentos;

public class JanelaAssento extends JFrame implements IJanelaAssento{
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterAssentos ctrl;
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfNumero;
	private JTextField tfFila;

	/**
	 * Create the frame.
	 */
	public JanelaAssento(ICtrlManterAssentos ct) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrl = ct;
		setTitle("Assento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 386, 192);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumero = new JLabel("Número:");
		lblNumero.setBounds(36, 22, 61, 14);
		contentPane.add(lblNumero);
		
		JLabel lblFila = new JLabel("Fila:");
		lblFila.setBounds(36, 62, 46, 14);
		contentPane.add(lblFila);
		
		tfNumero = new JTextField();
		tfNumero.setBounds(115, 19, 110, 20);
		contentPane.add(tfNumero);
		tfNumero.setColumns(10);
		
		tfFila = new JTextField();
		tfFila.setBounds(115, 59, 110, 20);
		contentPane.add(tfFila);
		tfFila.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero os valores digitados nos textfields
				String assentoNum = tfNumero.getText();
				String assentoFila = tfFila.getText();
				// Verifico qual é a operação que estou fazendo
				// e notifico ao controlador
				if(!ehAlteração)
					ctrl.incluir(assentoNum, assentoFila, null, null);//TODO tirar os nulls
				else
					ctrl.alterar(assentoNum, assentoFila);
			}
		});
		btnOk.setBounds(60, 116, 86, 23);
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
		btnCancelar.setBounds(211, 116, 86, 23);
		contentPane.add(btnCancelar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("files/airplane-icon_small.jpg")));
		label.setBounds(255, 0, 124, 129);
		contentPane.add(label);
		
		this.setVisible(true);
	}

	/**
	 * Determina os valores para os campos da janela
	 * @param assentoNum
	 * @param assentoFila
	 */
	public void atualizarCampos(String assentoNum, String assentoFila) {
		this.tfNumero.setText(assentoNum);
		this.tfFila.setText(assentoFila);
		this.ehAlteração = true;
	}
}

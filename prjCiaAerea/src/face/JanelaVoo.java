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

import controle.ICtrlManterVoos;

public class JanelaVoo extends JFrame implements IJanelaVoo{
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterVoos ctrl;
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfCodVoo;
	private JTextField tfHoraPartida;
	private JTextField tfHoraChegada;
	private JTextField tfDataRealizacao;

	/**
	 * Create the frame.
	 */
	public JanelaVoo(ICtrlManterVoos ct) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		setForeground(Color.WHITE);
		this.ctrl = ct;
		setTitle("Voo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 275);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodVoo = new JLabel("C\u00F3digo do voo:");
		lblCodVoo.setBounds(28, 26, 89, 14);
		contentPane.add(lblCodVoo);
	
		JLabel lblHoraPartida = new JLabel("Hora de Partida:");
		lblHoraPartida.setBounds(28, 68, 106, 14);
		contentPane.add(lblHoraPartida);
		
		JLabel lblHoraChegada = new JLabel("Hora de Chegada:");
		lblHoraChegada.setBounds(28, 112, 118, 14);
		contentPane.add(lblHoraChegada);
		
		JLabel lblDataRealizacao = new JLabel("Data da Realização:");
		lblDataRealizacao.setBounds(28, 153, 118, 14);
		contentPane.add(lblDataRealizacao);
		
		tfCodVoo = new JTextField();
		tfCodVoo.setBounds(169, 23, 111, 20);
		contentPane.add(tfCodVoo);
		tfCodVoo.setColumns(10);
		
		tfHoraPartida = new JTextField();
		tfHoraPartida.setBounds(166, 65, 114, 20);
		contentPane.add(tfHoraPartida);
		tfHoraPartida.setColumns(10);
		
		tfHoraChegada = new JTextField();
		tfHoraChegada.setBounds(168, 109, 111, 20);
		contentPane.add(tfHoraChegada);
		tfHoraChegada.setColumns(10);
		
		tfDataRealizacao = new JTextField();
		tfDataRealizacao.setBounds(168, 150, 111, 20);
		contentPane.add(tfDataRealizacao);
		tfDataRealizacao.setColumns(10);
		
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero os valores digitados nos textfields
				String codVoo = tfCodVoo.getText();
				String horaPartida = tfHoraPartida.getText();
				String horaChegada = tfHoraChegada.getText();
				String dataRealizacao = tfDataRealizacao.getText();
				// Verifico qual é a operação que estou fazendo
				// e notifico ao controlador
				if(!ehAlteração)
					ctrl.incluir(codVoo, horaPartida, horaChegada, dataRealizacao, null, null, null);//TODO tirar os nulls
				else
					ctrl.alterar(codVoo, horaPartida, horaChegada, dataRealizacao);
			}
		});
		btnOk.setBounds(72, 202, 89, 23);
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
		btnCancelar.setBounds(225, 202, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("files/airplane-icon_small.jpg")));
		lblNewLabel.setBounds(267, -63, 242, 253);
		contentPane.add(lblNewLabel);
		
		this.setVisible(true);
	}

	/**
	 * Determina os valores para os campos da janela
	 * @param codVoo
	 * @param horaPartida
	 * @param horaChegada
	 * @param dataRezlizacao
	 */
	public void atualizarCampos(String codVoo, String horaPartida, String horaChegada, String dataRealizacao) {
		
		this.tfCodVoo.setText(codVoo);
		this.tfHoraPartida.setText(horaPartida);
		this.tfHoraChegada.setText(horaChegada);
		this.tfDataRealizacao.setText(dataRealizacao);
		// Verifico qual é a operação que estou fazendo
		this.ehAlteração = true;
	}
}

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

import controle.ICtrlManterEscalas;

public class JanelaEscala extends JFrame implements IJanelaEscala {
	//
	// ATRIBUTOS
	//
	/**
	 * Referência para o controlador do caso de uso
	 */
	private ICtrlManterEscalas ctrl;
	/**
	 * Indica se estou fazendo uma operação de inclusão ou 
	 * alteração
	 */
	private boolean ehAlteração;
	
	private JPanel contentPane;
	private JTextField tfOrdem;
	private JTextField tfHorChegada;
	private JTextField tfHorSaida;
	
	/**
	 * Create the frame.
	 */
	public JanelaEscala(ICtrlManterEscalas ct) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrl = ct;
		setTitle("Escala");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 234);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOrdem = new JLabel("Ordem:");
		lblOrdem.setBounds(24, 24, 46, 14);
		contentPane.add(lblOrdem);
		
		JLabel lblhorChegada = new JLabel("Hora de Chegada:");
		lblhorChegada.setBounds(24, 63, 103, 14);
		contentPane.add(lblhorChegada);
		
		JLabel lblhorSaida= new JLabel("Hora de Saída:");
		lblhorSaida.setBounds(24, 108, 103, 14);
		contentPane.add(lblhorSaida);
		
		
		tfOrdem = new JTextField();
		tfOrdem.setBounds(142, 21, 86, 20);
		contentPane.add(tfOrdem);
		tfOrdem.setColumns(10);
		
		tfHorChegada = new JTextField();
		tfHorChegada.setBounds(142, 60, 121, 20);
		contentPane.add(tfHorChegada);
		tfHorChegada.setColumns(10);
		
		tfHorSaida = new JTextField();
		tfHorSaida.setBounds(142, 105, 121, 20);
		contentPane.add(tfHorSaida);
		tfHorSaida.setColumns(10);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero os valores digitados nos textfields
				String ordem = tfOrdem.getText();
				String horChegada = tfHorChegada.getText();
				String horSaida = tfHorSaida.getText();
				// Verifico qual é a operação que estou fazendo
				// e notifico ao controlador
				if(!ehAlteração)
					ctrl.incluir(ordem, horChegada, horSaida, null, null);//TODO tirar os nulls
				else
					ctrl.alterar(ordem, horChegada, horSaida, null, null);//TODO tirar os nulls
			}
		});
		btnOk.setBounds(65, 161, 86, 23);
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
		btnCancelar.setBounds(207, 161, 86, 23);
		contentPane.add(btnCancelar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(getClass().getClassLoader().getResource("files/airplane-icon_small.jpg")));
		label.setBounds(246, -14, 160, 141);
		contentPane.add(label);
		
		this.setVisible(true);
	}

	/**
	 * Determina os valores para os campos da janela
	 * @param sigla
	 * @param nome
	 */
	public void atualizarCampos(String ordem, String horChegada, String horSaida) {
		this.tfOrdem.setText(ordem);
		this.tfHorChegada.setText(horChegada);
		this.tfHorSaida.setText(horSaida);
		this.ehAlteração = true;
	}
}

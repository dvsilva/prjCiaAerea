package face;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.ICtrlManterModelos;
import controle.Tabelavel;

public class JanelaCadastroModelos extends JFrame implements IJanelaCadastroModelos {
	/**
	 * Referência para o controlador do caso de uso
	 * Manter Modelos
	 */
	private ICtrlManterModelos ctrl;

	private JPanel contentPane;
	private JTable table;

	
	/**
	 * Create the frame.
	 */
	public JanelaCadastroModelos(ICtrlManterModelos c) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrl = c;
		setTitle("Cadastro de modelos");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 513, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.iniciarIncluir();
			}
		});
		btnIncluir.setBounds(50, 235, 89, 23);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero a posição selecionada
				int pos = table.getSelectedRow();
				// Se a posição for -1, não há ninguém selecionado. Então cancelo
				// a operação
				if(pos < 0)
					return;
				// Informo ao controlador para iniciar o processo de exclusão
				ctrl.iniciarExcluir(pos);	
			}
		});
		btnExcluir.setBounds(149, 235, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero a posição selecionada
				int pos = table.getSelectedRow();
				// Se a posição for -1, não há ninguém selecionado. Então cancelo
				// a operação
				if(pos < 0)
					return;
				// Informo ao controlador para iniciar o processo de alteração
				ctrl.iniciarAlterar(pos);	
			}
		});
		btnAlterar.setBounds(248, 235, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.terminar();
			}
		});
		btnSair.setBounds(347, 235, 89, 23);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 475, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome","Número máximo de lugares", "Autonomia de voo", "Capacidade do tanque"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(108);
		table.getColumnModel().getColumn(1).setPreferredWidth(379);
		table.getColumnModel().getColumn(2).setPreferredWidth(308);
		table.getColumnModel().getColumn(3).setPreferredWidth(329);
		scrollPane.setViewportView(table);
		this.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroModelos#limpar()
	 */
	@Override
	public void limpar() {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		while(dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}

	/* (non-Javadoc)
	 * @see face.IJanelaCadastroModelos#incluirLinha(controle.Tabelavel)
	 */
	@Override
	public void incluirLinha(Tabelavel objeto) {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		dtm.addRow(objeto.getData());
	}
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroModelos#executarIncluir()
	 */
	@Override
	public void executarIncluir() {
			this.ctrl.iniciarIncluir();
		}
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroModelos#executarExcluir()
	 */
	@Override
	public void executarExcluir() {
		// Recupero a posição selecionada
		int pos = table.getSelectedRow();
		// Se a posição for -1, não há ninguém selecionado. Então cancelo
		// a operação
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de exclusão
		ctrl.iniciarExcluir(pos);	
		}
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroModelos#executarAlterar()
	 */
	@Override
	public void executarAlterar() {
		// Recupero a posição selecionada
		int pos = table.getSelectedRow();
		// Se a posição for -1, não há ninguém selecionado. Então cancelo
		// a operação
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de alteração
		ctrl.iniciarAlterar(pos);	
	}
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroModelos#executarTerminar()
	 */
	@Override
	public void executarTerminar() {
		ctrl.terminar();
	}
}








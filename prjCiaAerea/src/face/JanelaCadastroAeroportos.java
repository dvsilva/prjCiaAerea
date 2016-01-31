package face;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.ICtrlManterAeroportos;
import controle.Tabelavel;
import java.awt.Color;
import java.awt.Toolkit;

public class JanelaCadastroAeroportos extends JFrame implements IJanelaCadastroAeroportos {
	/**
	 * Referência para o controlador do caso de uso
	 * Manter Aeroportos
	 */
	private ICtrlManterAeroportos ctrl;

	private JPanel contentPane;
	private JTable table;

	
	/**
	 * Create the frame.
	 */
	public JanelaCadastroAeroportos(ICtrlManterAeroportos c) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrl = c;
		setTitle("Cadastro de aeroportos");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 531, 300);
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
		btnIncluir.setBounds(62, 235, 89, 23);
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
		btnExcluir.setBounds(161, 235, 89, 23);
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
		btnAlterar.setBounds(260, 235, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.terminar();
			}
		});
		btnSair.setBounds(359, 235, 89, 23);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 493, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Nome", "Endereço", "Telefones"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(158);
		table.getColumnModel().getColumn(1).setPreferredWidth(329);
		table.getColumnModel().getColumn(2).setPreferredWidth(308);
		table.getColumnModel().getColumn(3).setPreferredWidth(329);
		scrollPane.setViewportView(table);
		this.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroAeroportos#limpar()
	 */
	@Override
	public void limpar() {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		while(dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}

	/* (non-Javadoc)
	 * @see face.IJanelaCadastroAeroportos#incluirLinha(controle.Tabelavel)
	 */
	@Override
	public void incluirLinha(Tabelavel objeto) {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		dtm.addRow(objeto.getData());
	}
		/* (non-Javadoc)
		 * @see face.IJanelaCadastroAeroportos#executarIncluir()
		 */
		@Override
		public void executarIncluir() {
				this.ctrl.iniciarIncluir();
			}
		/* (non-Javadoc)
		 * @see face.IJanelaCadastroAeroportos#executarExcluir()
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
		 * @see face.IJanelaCadastroAeroportos#executarAlterar()
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
		 * @see face.IJanelaCadastroAeroportos#executarTerminar()
		 */
		@Override
		public void executarTerminar() {
			ctrl.terminar();
		}
	}








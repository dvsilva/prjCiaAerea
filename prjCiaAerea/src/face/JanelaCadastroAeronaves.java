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

import controle.ICtrlManterAeronaves;
import controle.Tabelavel;

public class JanelaCadastroAeronaves extends JFrame implements IJanelaCadastroAeronaves {
	/**
	 * Refer�ncia para o controlador do caso de uso
	 * Manter Aeronaves
	 */
	private ICtrlManterAeronaves ctrl;

	private JPanel contentPane;
	private JTable table;

	
	/**
	 * Create the frame.
	 */
	public JanelaCadastroAeronaves(ICtrlManterAeronaves c) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("files/icone_transporte_aviao-150x150.png")));
		this.ctrl = c;
		setTitle("Cadastro de aeronaves");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 419, 300);
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
		btnIncluir.setBounds(10, 232, 89, 23);
		contentPane.add(btnIncluir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero a posi��o selecionada
				int pos = table.getSelectedRow();
				// Se a posi��o for -1, n�o h� ningu�m selecionado. Ent�o cancelo
				// a opera��o
				if(pos < 0)
					return;
				// Informo ao controlador para iniciar o processo de exclus�o
				ctrl.iniciarExcluir(pos);	
			}
		});
		btnExcluir.setBounds(109, 232, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recupero a posi��o selecionada
				int pos = table.getSelectedRow();
				// Se a posi��o for -1, n�o h� ningu�m selecionado. Ent�o cancelo
				// a opera��o
				if(pos < 0)
					return;
				// Informo ao controlador para iniciar o processo de altera��o
				ctrl.iniciarAlterar(pos);	
			}
		});
		btnAlterar.setBounds(208, 232, 89, 23);
		contentPane.add(btnAlterar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrl.terminar();
			}
		});
		btnSair.setBounds(307, 232, 89, 23);
		contentPane.add(btnSair);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 386, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nome"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(108);
		scrollPane.setViewportView(table);
		this.setVisible(true);
	}
	
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroAeronaves#limpar()
	 */
	@Override
	public void limpar() {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		while(dtm.getRowCount() > 0)
			dtm.removeRow(0);
	}

	/* (non-Javadoc)
	 * @see face.IJanelaCadastroAeronaves#incluirLinha(controle.Tabelavel)
	 */
	@Override
	public void incluirLinha(Tabelavel objeto) {
		DefaultTableModel dtm = (DefaultTableModel)this.table.getModel();
		dtm.addRow(objeto.getData());
	}
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroAeronaves#executarIncluir()
	 */
	@Override
	public void executarIncluir() {
			this.ctrl.iniciarIncluir();
		}
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroAeronaves#executarExcluir()
	 */
	@Override
	public void executarExcluir() {
		// Recupero a posi��o selecionada
		int pos = table.getSelectedRow();
		// Se a posi��o for -1, n�o h� ningu�m selecionado. Ent�o cancelo
		// a opera��o
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de exclus�o
		ctrl.iniciarExcluir(pos);	
	}
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroAeronaves#executarAlterar()
	 */
	@Override
	public void executarAlterar() {
		// Recupero a posi��o selecionada
		int pos = table.getSelectedRow();
		// Se a posi��o for -1, n�o h� ningu�m selecionado. Ent�o cancelo
		// a opera��o
		if(pos < 0)
			return;
		// Informo ao controlador para iniciar o processo de altera��o
		ctrl.iniciarAlterar(pos);	
	}
	/* (non-Javadoc)
	 * @see face.IJanelaCadastroAeronaves#executarTerminar()
	 */
	@Override
	public void executarTerminar() {
		ctrl.terminar();
	}
}








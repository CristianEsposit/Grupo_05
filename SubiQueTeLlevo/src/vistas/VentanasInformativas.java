package vistas;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class VentanasInformativas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCentral;
	private JScrollPane scrollPaneGeneral;
	private JScrollPane scrollPaneCliente;
	private JScrollPane scrollPaneChofer;
	private JTextArea textAreaGeneral;
	private JTextArea textAreaCliente;
	private JTextArea textAreaChofer;
	private JLabel lblGeneral;
	private JLabel lblCliente;
	private JLabel lblChofer;

	public VentanasInformativas() {
		setTitle("Ventana Simulacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new GridLayout(1, 3, 0, 0));
		
		this.scrollPaneGeneral = new JScrollPane();
		this.panelCentral.add(this.scrollPaneGeneral);
		
		this.textAreaGeneral = new JTextArea();
		this.scrollPaneGeneral.setViewportView(this.textAreaGeneral);
		
		this.lblGeneral = new JLabel("Ventana General");
		this.scrollPaneGeneral.setColumnHeaderView(this.lblGeneral);
		
		this.scrollPaneCliente = new JScrollPane();
		this.panelCentral.add(this.scrollPaneCliente);
		
		this.textAreaCliente = new JTextArea();
		this.scrollPaneCliente.setViewportView(this.textAreaCliente);
		
		this.lblCliente = new JLabel("Ventana Cliente");
		this.scrollPaneCliente.setColumnHeaderView(this.lblCliente);
		
		this.scrollPaneChofer = new JScrollPane();
		this.panelCentral.add(this.scrollPaneChofer);
		
		this.textAreaChofer = new JTextArea();
		this.scrollPaneChofer.setViewportView(this.textAreaChofer);
		
		this.lblChofer = new JLabel("Ventana Chofer");
		this.scrollPaneChofer.setColumnHeaderView(this.lblChofer);
		
		this.setVisible(true);
	}
	/**
	 * Actualiza el Area General
	 * @param comentario : Acción realizada
	 */
	public void actualizaTextAreaGeneral(String comentario) {
		this.textAreaGeneral.append(comentario + "\n");
	}
	/**
	 * Actualiza el Area del Cliente
	 * @param comentario : acción realizada
	 */
	public void actualizaTextAreaCliente(String comentario) {
		this.textAreaCliente.append(comentario + "\n");
	}
	/**
	 * Actualiza el Area del Chofer
	 * @param comentario: acción realizada
	 */
	public void actualizaTextAreaChofer(String comentario) {
		this.textAreaChofer.append(comentario + "\n");
	}

}

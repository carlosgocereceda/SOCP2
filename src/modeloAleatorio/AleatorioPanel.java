package modeloAleatorio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AleatorioPanel extends JPanel {
	private static final long serialVersionUID = -3442956276944280946L;
	private JButton ejecutar;
	private JScrollPane jScrollPane;
	private JSpinner spinnerN;
	private JSpinner numIteracionesField;
	private JTextField spinnerP;
	private JTextArea textArea;
	private JPanel centerPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel topPanel;
	private JPanel bottPanel;
	
	/**
	 * Creates new form AleatorioPanel
	 */
	// Cargar la vista de la aplicacion
	public AleatorioPanel() {
		initGUI();
		initComponents();
	}

	private void initComponents() {
		spinnerN = new JSpinner();
		spinnerN.setValue(500);
		spinnerP = new JTextField("0.001");
		numIteracionesField = new JSpinner();
		numIteracionesField.setValue(10);
		
		ejecutar = new JButton();
		jScrollPane = new JScrollPane();
		textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setRows(5);
		textArea.setEditable(false);
		jScrollPane.setViewportView(textArea);
		
		ejecutar.setText("Ejecutar");
		ejecutar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					ejecutarActionPerformed(evt);
				} catch (Exception e) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Por favor revise los parametros ingresados!\n" + e.getMessage(),
							"Ha ocurrido un error!", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		centerPanel.setLayout(new GridLayout(1, 2, 10, 10));
		centerPanel.add(jScrollPane);
		
		JPanel parametrosPanel = new JPanel(new GridLayout(5, 1, 10, 60));
		
		JPanel nPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		nPanel.add(new JLabel("N:"));
		nPanel.add(spinnerN);
		nPanel.add(new JLabel("Número de nodos"));
		parametrosPanel.add(nPanel);
		
		JPanel pPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		pPanel.add(new JLabel("p:"));
		pPanel.add(spinnerP);
		pPanel.add(new JLabel("Decimales con punto."));
		parametrosPanel.add(pPanel);
		
		JPanel iPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		iPanel.add(new JLabel("Núm. de iteraciones:"));
		iPanel.add(numIteracionesField);
		parametrosPanel.add(iPanel);
		
		parametrosPanel.add(ejecutar);
		
		centerPanel.add(parametrosPanel);
	}

	// Aqui es donde llamaremos al MAIN
	private void ejecutarActionPerformed(java.awt.event.ActionEvent evt) throws NumberFormatException {
		MainAleatorio.N = Integer.parseInt(spinnerN.getValue().toString());
		MainAleatorio.p = Double.parseDouble(spinnerP.getText());
		MainAleatorio.numIteraciones = Integer.parseInt(numIteracionesField.getValue().toString());
		
		if(MainAleatorio.N <= 0 || MainAleatorio.p < 0 || MainAleatorio.p > 1 || MainAleatorio.numIteraciones <= 0)
			throw new NumberFormatException("N > 0   &&  0 <= p <= 1  && Núm. de iteraciones > 0");
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// Aqui concretamente
					MainAleatorio.comenzar();
				} catch (Exception e) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Ha ocurrido un error al ejecutar!\n" + e.getMessage(),
							"Error!", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
	}
	
	public void escribe(String s) {
		this.textArea.append(s);
		this.textArea.append("\n");
	}
	
	private void initGUI() {
		this.setLayout(new BorderLayout(5, 5));
		centerPanel = createPanel(null, 500, 500);
		this.add(centerPanel, BorderLayout.CENTER);
		leftPanel = createPanel(null, 10, 50);
		this.add(leftPanel, BorderLayout.LINE_START);
		rightPanel = createPanel(null, 10, 50);
		this.add(rightPanel, BorderLayout.LINE_END);
		topPanel = createPanel(null, 20, 20);
		this.add(topPanel, BorderLayout.PAGE_START);
		bottPanel = createPanel(null, 20, 50);
		this.add(bottPanel, BorderLayout.PAGE_END);
	}

	private JPanel createPanel(Color color, int width, int height) {
		JPanel panel;
		panel = new JPanel();
		if (color != null)
			panel.setBackground(color);
		panel.setPreferredSize(new Dimension(width, height));
		return panel;
	}

	
}

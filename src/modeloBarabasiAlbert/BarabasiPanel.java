package modeloBarabasiAlbert;

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
import javax.swing.SwingUtilities;

// Clase que genera la vista de Barabasi

public class BarabasiPanel extends JPanel {
	private static final long serialVersionUID = -3442956276944280946L;
	private JButton ejecutar;
	private JScrollPane jScrollPane;
	private JSpinner spinnerM;
	private JSpinner numIteracionesField;
	private JSpinner spinnerT;
	private JTextArea textArea;
	private JPanel centerPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel topPanel;
	private JPanel bottPanel;
	
	/**
	 * Creates new form AleatorioPanel
	 */
	public BarabasiPanel() {
		initGUI();
		initComponents();
	}

	private void initComponents() {
		spinnerM = new JSpinner();
		spinnerM.setValue(1);
		spinnerT = new JSpinner();
		spinnerT.setValue(1);
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
		nPanel.add(new JLabel("t:"));
		nPanel.add(spinnerT);
		nPanel.add(new JLabel("t > 0"));
		parametrosPanel.add(nPanel);
		
		JPanel pPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		pPanel.add(new JLabel("m:"));
		pPanel.add(spinnerM);
		pPanel.add(new JLabel("m > 0"));
		parametrosPanel.add(pPanel);
		
		JPanel iPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		iPanel.add(new JLabel("Núm. de iteraciones:"));
		iPanel.add(numIteracionesField);
		parametrosPanel.add(iPanel);
		
		parametrosPanel.add(ejecutar);
		
		centerPanel.add(parametrosPanel);
	}
	// Aqui llamaremos al MAIN de Barabasi
	private void ejecutarActionPerformed(java.awt.event.ActionEvent evt) {
		MainBarabasi.m = (Integer) spinnerM.getValue();
		MainBarabasi.t = (Integer) spinnerT.getValue();
		MainBarabasi.numIteraciones = (Integer) numIteracionesField.getValue();

		if(MainBarabasi.m <= 0 || MainBarabasi.t <= 0 || MainBarabasi.numIteraciones <= 0)
			throw new NumberFormatException(" m > 0  &&  t > 0  && Núm. de iteraciones > 0");
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// Aqui concretamente
					MainBarabasi.comenzar();
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

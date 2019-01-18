package modeloAleatorio;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AleatorioPanel extends JPanel {
	private static final long serialVersionUID = -3442956276944280946L;
	private javax.swing.JButton ejecutar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane;
	private javax.swing.JSpinner spinnerN;
	private JTextField spinnerP;
	private javax.swing.JTextArea textArea;
	
	/**
	 * Creates new form AleatorioPanel
	 */
	public AleatorioPanel() {
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		spinnerN = new javax.swing.JSpinner();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		// model = new SpinnerNumberModel(0.00000, 0.00000 ,1.00000, 0.0001);
		spinnerP = new JTextField("0.00001");
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		ejecutar = new javax.swing.JButton();
		jScrollPane = new javax.swing.JScrollPane();
		textArea = new javax.swing.JTextArea();

		jLabel1.setText("P");

		jLabel2.setText("N");

		jLabel3.setText("Entre 0 y 1");

		jLabel4.setText("Al colocar decimales en P usar el PUNTO y no la COMA ej: 0.0015");

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

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel2)
								.addGap(10, 10, 10)
								.addComponent(spinnerN, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(spinnerP, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 63,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jLabel4)
								.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap())
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(67, 67, 67)
						.addComponent(ejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 160,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(28, 28, 28)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(spinnerN, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2)
								.addComponent(spinnerP, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3))
						.addGap(50, 50, 50)
						.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(26, 26, 26).addComponent(ejecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(147, Short.MAX_VALUE)));

		textArea.setColumns(20);
		textArea.setRows(5);
		jScrollPane.setViewportView(textArea);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(jScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 529,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel1,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}

	private void ejecutarActionPerformed(java.awt.event.ActionEvent evt) throws NumberFormatException {
		MainAleatorio.N = Long.parseLong(spinnerN.getValue().toString());
		MainAleatorio.p = Double.parseDouble(spinnerP.getText());;
		
		if(MainAleatorio.N <= 0 || MainAleatorio.p < 0 || MainAleatorio.p > 1)
			throw new NumberFormatException("N > 0   &&  0 <= p <= 1");
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
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
}

package modeloBarabasiAlbert;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class BarabasiPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = 2388434754728752210L;
	private javax.swing.JButton ejecutar;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane;
	private javax.swing.JSpinner spinnerM;
	private javax.swing.JSpinner spinnerT;
	private javax.swing.JTextArea textArea;

	/**
	 * Creates new form AleatorioPanel
	 */
	public BarabasiPanel() {
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		spinnerM = new javax.swing.JSpinner();
		spinnerM.setValue(1);
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		spinnerT = new javax.swing.JSpinner();
		spinnerT.setValue(1);
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		ejecutar = new javax.swing.JButton();
		jScrollPane = new javax.swing.JScrollPane();
		textArea = new javax.swing.JTextArea();

		jLabel1.setText("t");

		jLabel2.setText("m");

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
								.addComponent(spinnerM, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel1)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(spinnerT, javax.swing.GroupLayout.PREFERRED_SIZE, 92,
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
								.addComponent(spinnerM, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2)
								.addComponent(spinnerT, javax.swing.GroupLayout.PREFERRED_SIZE,
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

	private void ejecutarActionPerformed(java.awt.event.ActionEvent evt) {
		MainBarabasi.m = (Integer) spinnerM.getValue();
		MainBarabasi.t = (Integer) spinnerT.getValue();

		if(MainBarabasi.m <= 0 || MainBarabasi.t <= 0)
			throw new NumberFormatException(" m > 0  &&  t > 0");
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
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
}

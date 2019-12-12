package test;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

public class FilterJList extends javax.swing.JFrame {

	DefaultListModel defaultListModel = new DefaultListModel();

	/*
	 * Constructor
	 */
	public FilterJList() {
		initComponents();
		this.bindData();
	}

	/*
	 * Our data source
	 */
	private ArrayList getStars() {
		ArrayList stars = new ArrayList();
		stars.add("UY Scuti");
		stars.add("VY Canis Majoris");
		stars.add("VV Cephei A");
		stars.add("NML Cygni");
		stars.add("Betelgeuse");
		stars.add("Antares A");
		stars.add("Rigel");
		stars.add("Aldebaran");
		stars.add("Arcturus");
		stars.add("Sirius");
		stars.add("Epsilon Canis Majos");
		stars.add("VX Sagittarii");
		stars.add("AH Scorpii");
		stars.add("PZ Cassiopeiae");
		stars.add("EV Carinae");
		stars.add("CE Tauri");
		stars.add("The Pistol Star");
		stars.add("Polaris");

		return stars;
	}

	/*
	 * Bind data to JList
	 */
	private void bindData() {
		// foreach with functinal operation
		getStars().stream().forEach((star) -> {
			defaultListModel.addElement(star);
		});
		myJList.setModel(defaultListModel);
		myJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/*
	 * Search/Filter data
	 */
	private void searchFilter(String searchTerm) {
		DefaultListModel filteredItems = new DefaultListModel();
		ArrayList stars = getStars();

		stars.stream().forEach((star) -> {
			String starName = star.toString().toLowerCase();
			if (starName.contains(searchTerm.toLowerCase())) {
				filteredItems.addElement(star);
			}
		});
		defaultListModel = filteredItems;
		myJList.setModel(defaultListModel);

	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		myJList = new javax.swing.JList<>();
		searchTxt = new javax.swing.JTextField();
		searchLabel = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Java JList - Filter/Search");

		jPanel1.setBackground(new java.awt.Color(0, 153, 104));

		myJList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		myJList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		myJList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				myJListMouseClicked(evt);
			}
		});
		jScrollPane1.setViewportView(myJList);

		searchTxt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
		searchTxt.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				searchTxtKeyReleased(evt);
			}
		});

		searchLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
		searchLabel.setForeground(new java.awt.Color(204, 204, 204));
		searchLabel.setText("Search/Filter");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(69, 69, 69).addGroup(jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(jPanel1Layout.createSequentialGroup().addComponent(searchLabel).addGap(18, 18, 18)
								.addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 236,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311,
								javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(90, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(27, 27, 27)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(searchLabel))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addComponent(jPanel1,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(69, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	private void myJListMouseClicked(java.awt.event.MouseEvent evt) {
		JOptionPane.showMessageDialog(rootPane, myJList.getSelectedValue(), "Selected Star",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void searchTxtKeyReleased(java.awt.event.KeyEvent evt) {
		searchFilter(searchTxt.getText());
	}

	// main method
	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FilterJList.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		java.awt.EventQueue.invokeLater(() -> {
			new FilterJList().setVisible(true);
		});
	}

	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JList<String> myJList;
	private javax.swing.JLabel searchLabel;
	private javax.swing.JTextField searchTxt;
}

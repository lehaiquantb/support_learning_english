package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import controller.CRUDController;
import controller.CheckWordController;
import controller.LevelController;
import controller.SearchController;
import controller.ShowWordController;
import controller.TestModeController;
import model.DataModel;
import util.GenerateWord;
import util.Util1;
import view.JDialog.JDialogCRUD;
import view.JPanelComponent.ContentView;
import view.JPanelComponent.DashboardView;
import view.JPanelComponent.FooterView;
import view.JPanelComponent.SearchView;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

public class MainFrame {

	DefaultListModel<String> defaultListModel;
	private JDialogCRUD dialogRead;
	private JDialogCRUD dialogEdit;
	private JDialogCRUD dialogDelete;
	private DataModel dataModel;
	public JFrame frame;
	private DashboardView dashboardView;
	private ContentView contentView;
	private SearchView searchView;
	private FooterView panelFooter;
	private JPanel panelSearchResults;
	private JList<String> listSearchResults;
	private JPanel panelSearchTag;
	private JList<String> listSearchTag;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame window = new MainFrame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public MainFrame(DataModel dataModel) {
		this.dataModel = dataModel;
		initView();
		contentView.setGenerateWord(new GenerateWord(dataModel, this));
		contentView.getGenerateWord().run();
		initController();
		update();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initView() {
		frame = new JFrame("Support Learning English");
		frame.setBounds(500, 10, 875, 543);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(Util1.getImageResize(getClass(), "iconHust.png", 13, 15));
		frame.getContentPane().setLayout(null);

		dashboardView = new DashboardView();
		frame.getContentPane().add(dashboardView);

		searchView = new SearchView();
		frame.getContentPane().add(searchView);

		contentView = new ContentView();
		contentView.getPanel_Sound().setLocation(10, 11);
		frame.getContentPane().add(contentView);

		panelFooter = new FooterView();
		frame.getContentPane().add(panelFooter);

		// JDialog CRUD
		// this.setDialogCRUD(new JDialogAdd("Tu"));
		this.dialogRead = new JDialogCRUD("read", dataModel, this);
		this.dialogDelete = new JDialogCRUD("delete", dataModel, this);
		this.dialogEdit = new JDialogCRUD("", dataModel, this);
		// frame.add(dialogCRUD);

		JScrollPane scrollPane = new JScrollPane();

		panelSearchResults = new JPanel();
		panelSearchResults.setBounds(359, 58, 380, 255);
		// frame.getLayeredPane().add(panelSearchResults);
		// frame.getContentPane().add(panelSearchResults);
		panelSearchResults.setLayout(new BorderLayout(0, 0));

		listSearchResults = new JList<String>();

		defaultListModel = new DefaultListModel<String>();
		listSearchResults.setBounds(5, 5, 412, 250);
		listSearchResults.setBackground(Color.WHITE);
		listSearchResults.setModel(defaultListModel);
		scrollPane.setViewportView(listSearchResults);
		panelSearchResults.add(scrollPane);
/////////////////////////////////////////////////////////////////////////////
		JScrollPane scrollPane1 = new JScrollPane();
		panelSearchTag = new JPanel();
		panelSearchTag.setBounds(10, 185, 153, 157);
		// frame.getLayeredPane().add(panelSearchTag);
		// frame.getContentPane().add(panelSearchTag);
		panelSearchTag.setLayout(new BorderLayout(0, 0));

		listSearchTag = new JList<String>();
//		listSearchTag.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				JOptionPane.showMessageDialog(null, listSearchTag.getSelectedValue());
//			}
//		});
		listSearchTag.setBounds(5, 5, 155, 160);
		listSearchTag.setBackground(Color.WHITE);
		listSearchTag.setModel(defaultListModel);
		scrollPane1.setViewportView(listSearchTag);
		panelSearchTag.add(scrollPane1);

//		  for(int i = 0; i < 10; i++) { textField_2 = new JTextField();
//		  panel.add(textField_2); textField_2.setColumns(2);
//		  
//		  }

		// TextField textField = new TextField("111");

		// frame.repaint();
	}

	public JDialogCRUD getDialogEdit() {
		return dialogEdit;
	}

	public void setDialogEdit(JDialogCRUD dialogEdit) {
		this.dialogEdit = dialogEdit;
	}

	public JDialogCRUD getDialogDelete() {
		return dialogDelete;
	}

	public void setDialogDelete(JDialogCRUD dialogDelete) {
		this.dialogDelete = dialogDelete;
	}

	private void initController() {
		new CheckWordController(dataModel, this);
		new ShowWordController(dataModel, this, frame);
		new LevelController(dataModel, this).addListener();
		new SearchController(dataModel, this).addSearchListener();
		new TestModeController(dataModel, this).addListener();
		new CRUDController(dataModel, this).addListener();
	}

	public void update() {
		dashboardView.getLblTotalModel()
				.setText(dataModel.getTotalWordModelByMode() + "/" + dataModel.getTotalWordModel());
	}

	public DataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public DashboardView getDashboardView() {
		return dashboardView;
	}

	public void setDashboardView(DashboardView dashboardView) {
		this.dashboardView = dashboardView;
	}

	public ContentView getContentView() {
		return contentView;
	}

	public void setContentView(ContentView contentView) {
		this.contentView = contentView;
	}

	public SearchView getSearchView() {
		return searchView;
	}

	public void setSearchView(SearchView searchView) {
		this.searchView = searchView;
	}

	public FooterView getPanelFooter() {
		return panelFooter;
	}

	public void setPanelFooter(FooterView panelFooter) {
		this.panelFooter = panelFooter;
	}

	public JPanel getPanelSearchTag() {
		return panelSearchTag;
	}

	public void setPanelSearchTag(JPanel panelSearchTag) {
		this.panelSearchTag = panelSearchTag;
	}

	public JList<String> getListSearchTag() {
		return listSearchTag;
	}

	public void setListSearchTag(JList<String> listSearchTag) {
		this.listSearchTag = listSearchTag;
	}

	public DefaultListModel<String> getDefaultListModel() {
		return defaultListModel;
	}

	public void setDefaultListModel(DefaultListModel<String> defaultListModel) {
		this.defaultListModel = defaultListModel;
	}

	public JList<String> getListSearchResults() {
		return listSearchResults;
	}

	public void setListSearchResults(JList<String> listSearchResults) {
		this.listSearchResults = listSearchResults;
	}

	public JPanel getPanelSearchResults() {
		return panelSearchResults;
	}

	public void setPanelSearchResults(JPanel panelSearchResults) {
		this.panelSearchResults = panelSearchResults;
	}

	public JDialogCRUD getDialogRead() {
		return dialogRead;
	}

	public void setDialogRead(JDialogCRUD dialogRead) {
		this.dialogRead = dialogRead;
	}

//	private static void addPopup(Component component, final JPopupMenu popup) {
//		component.addMouseListener(new MouseAdapter() {
//			public void mousePressed(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//
//			public void mouseReleased(MouseEvent e) {
//				if (e.isPopupTrigger()) {
//					showMenu(e);
//				}
//			}
//
//			private void showMenu(MouseEvent e) {
//				popup.show(e.getComponent(), e.getX(), e.getY());
//			}
//		});
//	}

}

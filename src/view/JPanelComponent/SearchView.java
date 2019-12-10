package view.JPanelComponent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Util1;

import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author quan.lh173316
 *
 */
public class SearchView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> cB_ModeSearch;
	private JTextField tF_Search;
	private JButton btnSearch;
	private JLabel lblTotalResult;

	/**
	 * 
	 */
	public SearchView() {

		this.setBounds(242, 11, 617, 47);
		this.setLayout(new BorderLayout(0, 0));

		cB_ModeSearch = new JComboBox<String>();
		cB_ModeSearch.addItem("By Word Or Phrase");
		cB_ModeSearch.addItem("By HashTag");
		//cB_ModeSearch.addItem("By Date");
		cB_ModeSearch.setSelectedIndex(0);
		((JLabel) cB_ModeSearch.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		this.add(cB_ModeSearch, BorderLayout.WEST);

		tF_Search = new JTextField();
		tF_Search.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.add(tF_Search, BorderLayout.CENTER);
		tF_Search.setColumns(10);

		JPanel panelTotalResults = new JPanel();
		this.add(panelTotalResults, BorderLayout.EAST);
		panelTotalResults.setPreferredSize(new Dimension(120, 0));
		panelTotalResults.setLayout(null);

		JPanel panel_IconSearch = new JPanel();
		panel_IconSearch.setBounds(0, 0, 40, 47);
		panel_IconSearch.add(new JLabel(Util1.getImageIconResize(getClass(), "iconSearch.png", 32, 32)));
		panelTotalResults.add(panel_IconSearch);

		lblTotalResult = new JLabel("0");
		lblTotalResult.setBounds(41, 11, 79, 25);
		panelTotalResults.add(lblTotalResult);

//		btnSearch = new JButton("Search");
//		this.add(btnSearch, BorderLayout.EAST);
	}

	public JLabel getLblTotalResult() {
		return lblTotalResult;
	}

	public void setLblTotalResult(JLabel lblTotalResult) {
		this.lblTotalResult = lblTotalResult;
	}

	public JComboBox<String> getcB_ModeSearch() {
		return cB_ModeSearch;
	}

	public void setcB_ModeSearch(JComboBox<String> cB_ModeSearch) {
		this.cB_ModeSearch = cB_ModeSearch;
	}

	public JTextField gettF_Search() {
		return tF_Search;
	}

	public void settF_Search(JTextField tF_Search) {
		this.tF_Search = tF_Search;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public void setBtnSearch(JButton btnSearch) {
		this.btnSearch = btnSearch;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

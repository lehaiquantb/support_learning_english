package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import model.DataModel;
import view.MainFrame;
import view.JPanelComponent.SearchView;

/**
 * @author quan.lh173316
 *
 */
public class SearchController extends AbstractController {

	private JButton btnSearch;

	public SearchController(DataModel dataModel, MainFrame mainFrame) {
		super(dataModel, mainFrame);
		this.btnSearch = searchView.getBtnSearch();

	}

	public void addSearchListener() {
		searchView.gettF_Search().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				mainFrame.getFrame().getLayeredPane().remove(mainFrame.getPanelSearchResults());
				mainFrame.getFrame().getLayeredPane().revalidate();
				mainFrame.getFrame().getLayeredPane().repaint();
				if (setListModel(searchView.gettF_Search().getText())) {
					mainFrame.getFrame().getLayeredPane().add(mainFrame.getPanelSearchResults());
					mainFrame.getFrame().getLayeredPane().revalidate();
					mainFrame.getFrame().getLayeredPane().repaint();
				}
//				else {
//					mainFrame.getFrame().getLayeredPane().remove(mainFrame.getPanelSearchResults());
//					mainFrame.getFrame().getLayeredPane().revalidate();
//					mainFrame.getFrame().getLayeredPane().repaint();
//				}
			}
		});

		mainFrame.getFrame().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchView.getLblTotalResult().setText("0");
				mainFrame.getFrame().getLayeredPane().remove(mainFrame.getPanelSearchResults());
				mainFrame.getFrame().getLayeredPane().remove(mainFrame.getPanelSearchTag());
				mainFrame.getFrame().getLayeredPane().revalidate();
				mainFrame.getFrame().getLayeredPane().repaint();
			}
		});

	}

	boolean setListModel(String text) {
		int totalResults = 0;
		boolean check = false;
		DefaultListModel<String> newListModel = new DefaultListModel<String>();
		Pattern pattern = Pattern.compile("[^@]*" + text + "[^@]*");
		Matcher matcher = pattern.matcher(dataModel.getMatchWord());
		if (searchView.getcB_ModeSearch().getSelectedIndex() == 1) {
			pattern = Pattern.compile("[\\S]*" + text + "[\\S]*");
			matcher = pattern.matcher(dataModel.getMatchTag());
			
		}
		if (matcher.find()) {
			totalResults++;
			newListModel.addElement(matcher.group());
			check = true;
			while (matcher.find()) {
				totalResults++;
				newListModel.addElement(matcher.group());
			}
		}

		searchView.getLblTotalResult().setText(Integer.toString(totalResults));
		mainFrame.setDefaultListModel(newListModel);
		mainFrame.getListSearchResults().setModel(mainFrame.getDefaultListModel());
		return check;
	}
}

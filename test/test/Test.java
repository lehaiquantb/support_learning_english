package test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import dao.impl.WordDAO;
import model.WordModel;
import util.Util1;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub

	}

	public static void main(String[] args) {

//		WordDAO wordDAO = new WordDAO();
//		WordModel wordModel = new WordModel();
//		wordModel.setWordOrPhrase("Quan2");
//		wordModel.setHashTags("tag");
//		wordModel.setMeaning("mean");
//		wordModel.setPathOfAudioFile("...");
//		wordModel.setPathOfImageFile("..w");
//		wordModel.setPronounce("sa");
//		wordModel.setSuggest("suuu");
//		wordDAO.saveWord(wordModel);

		JPanel middlePanel = new JPanel();
		// middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Display
		// Area" ) );

		// create the middle panel components

		JTextArea display = new JTextArea(10, 9);
		display.setText(Util1.getMeaningTest());
		display.setEditable(false); // set textArea non-editable
		JScrollPane scroll = new JScrollPane(display);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Add Textarea in to middle panel
		middlePanel.add(scroll);

		// My code
		JFrame frame = new JFrame();
		frame.add(middlePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

package util;

import java.util.List;

import javax.swing.JLabel;

import controller.AbstractController;
import model.DataModel;
import model.WordModel;
import view.MainFrame;

/**
 * @author quan.lh173316
 *
 */
public class GenerateWord extends AbstractController {

	private WordModel wordModel;
	private List<JTextFieldBox> listBoxs;

	public GenerateWord(DataModel dataModel, MainFrame mainFrame) {
		super(dataModel, mainFrame);
	}

	void getRandomWord() {
		this.wordModel = dataModel.getRandomWordModel();
		this.listBoxs = dataModel.getListBoxs();
	}

	public void run() {
		getRandomWord();
//		if (wordModel.getPronounce().length() > 40) {
//			contentView.getLbl_Spelling().setText(wordModel.getPronounce().substring(0, 40));
//		} else {
		contentView.getLbl_Spelling().setText(wordModel.getPronounce());
		// }
		contentView.getTextAreaMeaning().setText(wordModel.getMeaning());
		contentView.getLblListTags().setText(wordModel.getHashTags());
		String[] temp = Util1.convertTimestamp(wordModel.getDateAdded());
		contentView.getLblDate().setText(temp[0]);
		contentView.getLblTime().setText(temp[1]);
		contentView.getTextAreaSuggest().setText(wordModel.getSuggest());

		if (dashboardView.getcB_LevelLearn().getSelectedIndex() == 6) {
			List<JTextFieldBox> listEmptyBoxs = dataModel.getListEmptyBoxs();
			dataModel.setTempListBoxs(listEmptyBoxs);
			contentView.getPanel_WordOrPhrase().removeAll();
			int size1 = listEmptyBoxs.size();
			contentView.getPanel_WordOrPhrase().removeAll();
			for (int i = 0; i < size1; i++) {
				contentView.getPanel_WordOrPhrase().add(listEmptyBoxs.get(i));
			}
			for (int i = 0; i < 84 - size1; i++) {
				contentView.getPanel_WordOrPhrase().add(new JTextFieldBox("Enter here"));
			}
			contentView.getPanel_WordOrPhrase().revalidate();
			contentView.getPanel_WordOrPhrase().repaint();
		} else {
			int size = this.listBoxs.size();
			for (int i = 0; i < size; i++) {
				contentView.getPanel_WordOrPhrase().add(listBoxs.get(i));
			}
		}

//		for (int i = 0; i < size; i++) {
//			System.out.println(listBoxs.get(i).getText());
//		}
		contentView.getPanel_Image().removeAll();
		contentView.getPanel_Image().add(new JLabel(Util1.getImageIconResizeByPath(getClass(),
				wordModel.getPathOfImageFile(), wordModel.getWordOrPhrase(), 200, 179)));
		contentView.getPanel_Image().revalidate();
		contentView.getPanel_Image().repaint();

		contentView.getPanelValidateRight().setVisible(false);
	}

}

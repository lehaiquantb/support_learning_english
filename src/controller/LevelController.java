package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.DataModel;
import util.JTextFieldBox;
import view.MainFrame;

/**
 * @author quan.lh173316
 *
 */
public class LevelController extends AbstractController {
	private JComboBox<String> comboBox;

	public LevelController(DataModel dataModel, MainFrame mainFrame) {
		super(dataModel, mainFrame);
		this.comboBox = dashboardView.getcB_LevelLearn();
	}

	public void addListener() {
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if (index == 0) {
					restore();
					System.out.println("haha");
				} else if (index == 1) {
					restore();
					contentView.getLbl_Spelling().setVisible(false);
				} else if (index == 2) {
					restore();
					contentView.getLbl_Spelling().setVisible(false);
					contentView.getTextAreaSuggest().setVisible(false);
				} else if (index == 3) {
					restore();
					contentView.getLbl_Spelling().setVisible(false);
					contentView.getTextAreaSuggest().setVisible(false);
					contentView.getBtnSound().setVisible(false);
				} else if (index == 4) {
					restore();
					contentView.getLbl_Spelling().setVisible(false);
					contentView.getTextAreaSuggest().setVisible(false);
					contentView.getBtnSound().setVisible(false);
					JTextFieldBox textFieldBox = dataModel.getListBoxs().get(0);
					textFieldBox.setText("");
					textFieldBox.setEditable(true);
					textFieldBox = dataModel.getListBoxs().get(dataModel.getListBoxs().size() - 1);
					textFieldBox.setText("");
					textFieldBox.setEditable(true);
				} else if (index == 5) {
					restore();
					contentView.getLbl_Spelling().setVisible(false);
					contentView.getTextAreaSuggest().setVisible(false);
					contentView.getBtnSound().setVisible(false);
					contentView.getPanel_Meaning().setVisible(false);

					JTextFieldBox textFieldBox = dataModel.getListBoxs().get(0);
					textFieldBox.setText("");
					textFieldBox.setEditable(true);
					textFieldBox = dataModel.getListBoxs().get(dataModel.getListBoxs().size() - 1);
					textFieldBox.setText("");
					textFieldBox.setEditable(true);

				}

				else if (index == 6) {
					restore();
					contentView.getLbl_Spelling().setVisible(false);
					contentView.getTextAreaSuggest().setVisible(false);
					contentView.getBtnSound().setVisible(false);
					contentView.getPanel_Meaning().setVisible(false);

					List<JTextFieldBox> listEmptyBoxs = dataModel.getListEmptyBoxs();
					dataModel.setTempListBoxs(listEmptyBoxs);
					contentView.getPanel_WordOrPhrase().removeAll();
					int size = listEmptyBoxs.size();
					contentView.getPanel_WordOrPhrase().removeAll();
					for (int i = 0; i < size; i++) {
						contentView.getPanel_WordOrPhrase().add(listEmptyBoxs.get(i));
					}

					for (int i = 0; i < 84 - size; i++) {
						contentView.getPanel_WordOrPhrase().add(new JTextFieldBox("Enter here"));
					}

					contentView.getPanel_WordOrPhrase().revalidate();
					contentView.getPanel_WordOrPhrase().repaint();

//					JTextFieldBox textFieldBox = dataModel.getListBoxs().get(0);
//					
//					textFieldBox.setText("");
//					textFieldBox.setEditable(true);
//					textFieldBox = dataModel.getListBoxs().get(dataModel.getListBoxs().size() - 1);
//					textFieldBox.setText("");
//					textFieldBox.setEditable(true);
				}

			}
		});
	}

	void restore() {
		JTextFieldBox textFieldBox = dataModel.getListBoxs().get(0);
		String word = dataModel.getCurrentWordModel().getWordOrPhrase();
		textFieldBox.setText(Character.toString(word.charAt(0)));
		textFieldBox.setEditable(false);
		textFieldBox = dataModel.getListBoxs().get(dataModel.getListBoxs().size() - 1);
		textFieldBox.setText(Character.toString(word.charAt(word.length() - 1)));
		textFieldBox.setEditable(false);

		dataModel.setOldListBoxs();
		List<JTextFieldBox> listBoxs = dataModel.getListBoxs();
		int size = listBoxs.size();
		contentView.getPanel_WordOrPhrase().removeAll();
		for (int i = 0; i < size; i++) {
			contentView.getPanel_WordOrPhrase().add(listBoxs.get(i));
		}
		contentView.getPanel_WordOrPhrase().revalidate();
		contentView.getPanel_WordOrPhrase().repaint();

		contentView.getPanel_Image().setVisible(true);
		contentView.getPanel_Sound().setVisible(true);
		contentView.getPanel_WordOrPhrase().setVisible(true);
		contentView.getPanel_TagCrud().setVisible(true);
		contentView.getPanel_Meaning().setVisible(true);
		contentView.getPanel_Suggest().setVisible(true);

		contentView.getLbl_Spelling().setVisible(true);
		contentView.getTextAreaSuggest().setVisible(true);
		contentView.getBtnSound().setVisible(true);
	}

}

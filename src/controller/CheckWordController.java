package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;

import model.DataModel;
import model.WordModel;
import util.JTextFieldBox;
import util.Util1;
import view.MainFrame;

/**
 * @author quan.lh173316
 *
 */
public class CheckWordController extends AbstractController {

	private JButton btnCheck;
	private List<JTextFieldBox> listBoxs;
	private WordModel currentWordModel;
	private WordModel wordModel;

	public CheckWordController(DataModel dataModel, MainFrame mainFrame) {
		super(dataModel, mainFrame);
		this.btnCheck = contentView.getBtnCheck();
		this.run();
	}

	public void run() {

		contentView.getBtnSound().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					wordModel = dataModel.getCurrentWordModel();
					Util1.playAudio(wordModel.getPathOfAudioFile(), wordModel.getWordOrPhrase());
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
				// JOptionPane.showMessageDialog(null, "Xin chao");
			}
		});

		this.btnCheck.addActionListener(new ActionListener() {
			private WordModel currentWordModel;
			private List<JTextFieldBox> listBoxs;

			@Override
			public void actionPerformed(ActionEvent e) {
				this.listBoxs = dataModel.getListBoxs();
				this.currentWordModel = dataModel.getCurrentWordModel();
				String word = currentWordModel.getWordOrPhrase();
				int length = currentWordModel.getWordOrPhrase().length();
				boolean check = true;
				for (int i = 0; i < length; i++) {
					if (listBoxs.get(i).getText().length() == 0) {
						contentView.getPanelValidateWarning().setVisible(true);
						contentView.getPanelValidateWrong().setVisible(false);
						contentView.getPanelValidateRight().setVisible(false);
						check = false;
					} else if (word.charAt(i) != listBoxs.get(i).getText().charAt(0)) {
						contentView.getPanelValidateWarning().setVisible(false);
						contentView.getPanelValidateWrong().setVisible(true);
						contentView.getPanelValidateRight().setVisible(false);
						check = false;
					}
				}
				if (check) {
					contentView.getPanelValidateWarning().setVisible(false);
					contentView.getPanelValidateWrong().setVisible(false);
					contentView.getPanelValidateRight().setVisible(true);
					contentView.getPanel_WordOrPhrase().removeAll();
					contentView.getPanel_WordOrPhrase().revalidate();
					contentView.getPanel_WordOrPhrase().repaint();
					contentView.getGenerateWord().run();
				}
			}
		});
	}

}

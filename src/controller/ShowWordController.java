package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.DataModel;
import view.MainFrame;
import view.JPanelComponent.ContentView;

/**
 * @author quan.lh173316
 *
 */
public class ShowWordController extends AbstractController {

	private JButton btnShow;

	public ShowWordController(DataModel dataModel, MainFrame mainFrame, JFrame frame) {
		super(dataModel, mainFrame);
		this.btnShow = contentView.getBtnShowWord();
		this.run();
	}

	public void run() {
		btnShow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String wordOrPhrase = dataModel.getCurrentWordModel().getWordOrPhrase();
				int length = wordOrPhrase.length();
				for (int i = 0; i < length; i++) {
					dataModel.getListBoxs().get(i).setText(Character.toString(wordOrPhrase.charAt(i)));
				}
				// JOptionPane.showMessageDialog(null,
				// dataModel.getCurrentWordModel().getWordOrPhrase());

//				contentView.getPanel_Suggest().removeAll();
//				contentView.getPanel_Suggest().revalidate();
//				contentView.getPanel_Suggest().repaint();
//
//				JOptionPane.showMessageDialog(null, contentView.getTextAreaSuggest().getText());

				// dataModel.getListBoxs().get(1).setText("u");
//				contentView.getLblDate().setVisible(false);
//				contentView.getLblDate().setText("a");
				// contentView.getPanel_mode1().repaint();
			}
		});

		contentView.getBtnAdd().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				contentView.getPanel_Suggest().add(contentView.getTextAreaSuggest());
				contentView.getPanel_Suggest().revalidate();
				contentView.getPanel_Suggest().repaint();
				contentView.getLblDate().setVisible(true);
//				contentView.getPanel_Sound().setVisible(true);
//				frame.getContentPane().add(contentView);
//				frame.revalidate();
//				frame.repaint();
			}
		});
	}

}

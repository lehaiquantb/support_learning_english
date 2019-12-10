package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import model.DataModel;
import model.WordModel;
import view.MainFrame;
import view.JDialog.JDialogCRUD;

/**
 * @author quan.lh173316
 *
 */
public class CRUDController extends AbstractController {

	private JDialogCRUD dialogRead;
	private JDialogCRUD dialogDelete;
	private JDialogCRUD dialogEdit;

	public CRUDController(DataModel dataModel, MainFrame mainFrame) {
		super(dataModel, mainFrame);
		this.dialogRead = mainFrame.getDialogRead();
		this.dialogDelete = mainFrame.getDialogDelete();
		this.dialogEdit = mainFrame.getDialogEdit();
	}

	public void addListener() {
		addBtnAddListener();
		addChooseOnListSearchListener();
	}

	void addBtnDeleteListener() {

	}

	void addBtnAddListener() {
		contentView.getBtnAdd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// dialogRead.setVisible(true);
				dialogEdit.setVisible(true);
				dialogEdit.setMode(0);
				dialogEdit.cleanText();
			}
		});

		contentView.getBtnEdit().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogEdit.setVisible(true);
				dialogEdit.setWordModel(dataModel.getCurrentWordModel());
				dialogEdit.getcBTypeWord().setVisible(false);
				dialogEdit.getLblDate().setText("Added:");
				dialogEdit.setMode(1);
				dialogEdit.setText("Edit " + "\"" + dataModel.getCurrentWordModel().getWordOrPhrase() + "\"");
			}
		});

		contentView.getBtnDelete().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogDelete.setVisible(true);
				dialogDelete.setWordModel(dataModel.getCurrentWordModel());
				dialogDelete.setText("Delete " + "\"" + dataModel.getCurrentWordModel().getWordOrPhrase() + "\"");
			}
		});

	}

	void addChooseOnListSearchListener() {
		mainFrame.getListSearchResults().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (searchView.getcB_ModeSearch().getSelectedIndex() == 0) {
					dialogRead.setVisible(true);
					WordModel temp;
					temp = dataModel.getWordModelByMapWord(mainFrame.getListSearchResults().getSelectedValue());
					dialogRead.setWordModel(temp);
					dialogRead.getBtnSave().setEnabled(false);
					dialogRead.setNotEdit();
					dialogRead.setText("\"" + temp.getWordOrPhrase() + "\"");
				}

			}
		});
	}

}

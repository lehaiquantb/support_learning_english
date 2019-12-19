package view.JDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import dao.impl.WordDAO;
import dao.impl.WordJsonDAO;
import model.DataModel;
import model.WordModel;
import util.Util1;
import view.MainFrame;

public class JDialogCRUD extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private WordModel wordModel;
	private JTextField textFieldWord;
	private JTextField textFieldSpelling;
	private JTextField textFieldPronuonce;
	private JTextField textFieldTags;
	private JTextArea textAreaMeaning;
	private JTextArea textAreaSuggestion;
	private JPanel panelImage;
	private JButton btnImage;
	private JLabel lblImage;
	private JButton btnPronuonce;
	private JLabel lblToday;
	private JLabel lblDate;
	private JPanel buttonPane;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnDelete;
	private JButton cancelButton;
	private JDialogCRUD dialogCRUD;
	private JComboBox<String> cBTypeWord;
	private JButton btnSound;
	private String pathImage;
	private WordDAO wordDAO;
	private DataModel dataModel;
	private MainFrame mainFrame;
	private int mode;
	private WordJsonDAO wordJsonDAO;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			JDialogCRUD dialog = new JDialogCRUD("Test", new DataModel());
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public JDialogCRUD(String mode, DataModel dataModel, MainFrame mainFrame) {
		this.wordDAO = dataModel.getWordDAO();
		this.wordJsonDAO = wordDAO.getWordJsonDAO();
		this.dialogCRUD = this;
		this.dataModel = dataModel;
		this.mainFrame = mainFrame;
		initComponent();
		initChange(mode);
		initListener();
	}

	void initComponent() {
		setBounds(100, 100, 475, 476);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblWord = new JLabel("Word Or Phrase");
		lblWord.setHorizontalAlignment(SwingConstants.LEFT);
		lblWord.setBounds(10, 11, 94, 21);
		contentPanel.add(lblWord);

		textFieldWord = new JTextField();
		textFieldWord.setBounds(114, 11, 335, 21);
		contentPanel.add(textFieldWord);
		textFieldWord.setColumns(10);

		JLabel lblSpelling = new JLabel("Spelling");
		lblSpelling.setBounds(10, 43, 94, 21);
		contentPanel.add(lblSpelling);

		textFieldSpelling = new JTextField();
		textFieldSpelling.setBounds(114, 43, 335, 21);
		contentPanel.add(textFieldSpelling);
		textFieldSpelling.setColumns(10);

		JLabel lblPronuonce = new JLabel("Pronuonce");
		lblPronuonce.setBounds(10, 75, 87, 21);
		contentPanel.add(lblPronuonce);

		textFieldPronuonce = new JTextField();
		textFieldPronuonce.setBounds(114, 75, 305, 21);
		textFieldPronuonce.setEditable(false);
		contentPanel.add(textFieldPronuonce);
		textFieldPronuonce.setColumns(10);

		btnPronuonce = new JButton("");
		btnPronuonce.setIcon(Util1.getImageIconResize(getClass(), "iconPlusAudio.png", 20, 20));
		btnPronuonce.setBounds(424, 74, 25, 21);
		contentPanel.add(btnPronuonce);

		btnSound = new JButton("");
		btnSound.setLocation(114, 75);
		btnSound.setSize(21, 21);
		btnSound.setBackground(Color.WHITE);
		btnSound.setIcon(Util1.getImageIconResize(getClass(), "iconSound.png", 20, 20));
		btnSound.setBorder(BorderFactory.createEmptyBorder());
		btnSound.setVisible(false);
		contentPanel.add(btnSound);

		JLabel lblMeaning = new JLabel("Meaning");
		lblMeaning.setBounds(10, 107, 87, 21);
		contentPanel.add(lblMeaning);

		JLabel lblSuggestion = new JLabel("Suggestions");
		lblSuggestion.setBounds(10, 255, 77, 21);
		contentPanel.add(lblSuggestion);

		textAreaSuggestion = new JTextArea();
		textAreaSuggestion.setWrapStyleWord(true);
		textAreaSuggestion.setLineWrap(true);
		textAreaSuggestion.setBounds(114, 253, 149, 109);
		contentPanel.add(textAreaSuggestion);

		lblImage = new JLabel("Image");
		lblImage.setBounds(273, 255, 40, 21);
		contentPanel.add(lblImage);

		panelImage = new JPanel();
		BorderLayout borderLayout = new BorderLayout(0, 0);
		panelImage.setLayout(borderLayout);
		panelImage.setBounds(319, 253, 130, 109);
		contentPanel.add(panelImage);

		btnImage = new JButton("");
		btnImage.setIcon(Util1.getImageIconResize(getClass(), "iconPlusImage.jpg", 20, 20));
		btnImage.setBounds(288, 280, 25, 22);
		contentPanel.add(btnImage);

		JLabel lblTags = new JLabel("HashTags");
		lblTags.setBounds(10, 375, 77, 21);
		contentPanel.add(lblTags);

		textFieldTags = new JTextField();
		textFieldTags.setBounds(114, 375, 199, 21);
		contentPanel.add(textFieldTags);
		textFieldTags.setColumns(10);

		lblDate = new JLabel("Today");
		lblDate.setBounds(319, 375, 51, 21);
		contentPanel.add(lblDate);

		lblToday = new JLabel("1999");
		lblToday.setBounds(372, 375, 77, 21);
		contentPanel.add(lblToday);

		Panel panelMeaning = new Panel();
		panelMeaning.setBounds(114, 105, 335, 141);
		panelMeaning.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		textAreaMeaning = new JTextArea();
		textAreaMeaning.setWrapStyleWord(true);
		textAreaMeaning.setLineWrap(true);
		scrollPane.setViewportView(textAreaMeaning);
		panelMeaning.add(scrollPane);

		contentPanel.add(panelMeaning);

		cBTypeWord = new JComboBox<String>();
		cBTypeWord.addItem("Type");
		cBTypeWord.addItem("động từ");
		cBTypeWord.addItem("danh từ");
		cBTypeWord.addItem("tính từ");
		cBTypeWord.addItem("trạng từ");
		cBTypeWord.addItem("phó từ");
		cBTypeWord.addItem("cụm từ");
		cBTypeWord.addItem("mệnh đề");

		cBTypeWord.setBounds(10, 139, 87, 22);
		contentPanel.add(cBTypeWord);

		{
			buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);

			btnEdit = new JButton("Edit");
			buttonPane.add(btnEdit);
			{
				btnSave = new JButton("Save");
				btnSave.setActionCommand("Save");
				buttonPane.add(btnSave);
				getRootPane().setDefaultButton(btnSave);
			}

			btnDelete = new JButton("Delete");
			buttonPane.add(btnDelete);
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public JLabel getLblDate() {
		return lblDate;
	}

	public void setLblDate(JLabel lblDate) {
		this.lblDate = lblDate;
	}

	public void setNotEdit() {
		btnSave.setEnabled(false);
		textFieldSpelling.setEditable(false);
		textFieldTags.setEditable(false);
		textFieldWord.setEditable(false);
		textAreaMeaning.setEditable(false);
		textAreaSuggestion.setEditable(false);
		btnImage.setVisible(false);
		textFieldPronuonce.setVisible(false);
		btnPronuonce.setVisible(false);
		cBTypeWord.setVisible(false);
		btnSound.setVisible(true);
	}

	void initChange(String mode) {
		if (mode.equals("read")) {
			this.mode = 3;
			setNotEdit();
			lblDate.setText("Added:");
			btnEdit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dialogCRUD.mode = 1;
					btnSave.setEnabled(true);
					textFieldSpelling.setEditable(true);
					textFieldTags.setEditable(true);
					textFieldWord.setEditable(true);
					textAreaMeaning.setEditable(true);
					textAreaSuggestion.setEditable(true);
					btnImage.setVisible(true);
					btnPronuonce.setVisible(true);
					btnSound.setVisible(false);
					textFieldPronuonce.setVisible(true);
				}
			});
		} else if (mode == "delete") {
			this.mode = 2;
			this.setTitle("Delete word");
			textFieldSpelling.setEditable(false);
			textFieldTags.setEditable(false);
			textFieldWord.setEditable(false);
			textAreaMeaning.setEditable(false);
			textAreaSuggestion.setEditable(false);
			btnImage.setVisible(false);
			textFieldPronuonce.setVisible(false);
			btnPronuonce.setVisible(false);
			btnSave.setVisible(false);
			btnEdit.setVisible(false);
			cBTypeWord.setVisible(false);
			btnSound.setVisible(true);
			lblDate.setText("Added:");
		} else {
			// mode 0 1
			this.setTitle("Add new Word Or Phrase");
			btnDelete.setVisible(false);
			btnEdit.setVisible(false);
		}
	}

	void initListener() {
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogCRUD.setVisible(false);
			}
		});

		btnPronuonce.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int r = fileChooser.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					textFieldPronuonce.setText(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		});

		btnImage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int r = fileChooser.showSaveDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					pathImage = fileChooser.getSelectedFile().getAbsolutePath();
					panelImage.removeAll();
					panelImage.add(new JLabel(Util1.getImageIconResizeByPath(getClass(),
							fileChooser.getSelectedFile().getAbsolutePath(), null, null, 130, 109)));
					panelImage.revalidate();
					panelImage.repaint();
				}
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null,
						"Are you sure want to delete " + "\"" + wordModel.getWordOrPhrase() + "\"", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					try {
						removeWordFromDatabase();
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
					dialogCRUD.setVisible(false);
				}
			}
		});

		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (getTextFieldWord().getText() == null || getTextFieldWord().getText() == "") {
					JOptionPane.showMessageDialog(null, "You have not entered word(phrase) in the box");
				} else {
					int result = JOptionPane.showConfirmDialog(null,
							"Are you sure want to save " + "\"" + textFieldWord.getText() + "\"", "Confirm",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
						try {
							updateWordToDatabase();
						} catch (UnsupportedEncodingException e1) {
							e1.printStackTrace();
						}
						dialogCRUD.setVisible(false);
					}
				}
			}
		});

		addListenerReadDialog();
	}

	void addListenerReadDialog() {
		btnSound.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Util1.playAudio(wordModel.getPathOfAudioFile(), wordModel.getWordOrPhrase());
					System.out.println(wordModel.getWordOrPhrase());
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public void setText(String title) {
		this.setTitle(title);
		textFieldWord.setText(wordModel.getWordOrPhrase());
		if (wordModel.getMeaning() != null) {
			textAreaMeaning.setText(wordModel.getMeaning());
		} else {
			textAreaMeaning.setText("");
		}
		if (wordModel.getSuggest() != null) {
			textAreaSuggestion.setText(wordModel.getSuggest());
		} else {
			textAreaSuggestion.setText("");
		}
		if (wordModel.getPronounce() != null) {
			textFieldSpelling.setText(wordModel.getPronounce());
		} else {
			textFieldSpelling.setText("");
		}
		if (wordModel.getHashTags() != null) {
			textFieldTags.setText(wordModel.getHashTags());
		} else {
			textFieldTags.setText("");
		}

		String[] temp = Util1.convertTimestamp(wordModel.getDateAdded());
		lblToday.setText(temp[0]);

		panelImage.removeAll();
		panelImage.add(new JLabel(Util1.getImageIconResizeByPath(getClass(), wordModel.getPathOfImageFile(),
				wordModel.getWordOrPhrase(), wordModel, 130, 109)));
		panelImage.revalidate();
		panelImage.repaint();
	}

	public void cleanText() {
		this.setTitle("Add new Word Or Phrase");
		textAreaMeaning.setText(null);
		textAreaSuggestion.setText(null);
		textFieldPronuonce.setText(null);
		textFieldSpelling.setText(null);
		textFieldTags.setText(null);
		textFieldWord.setText(null);
		panelImage.removeAll();
		panelImage.add(new JLabel(Util1.getImageIconResize(getClass(), "NoImageAvailable.jpg", 130, 109)));
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		lblToday.setText(formatter.format(date));
		lblDate.setText("Today");
	}

	void updateWordToDatabase() throws UnsupportedEncodingException {
		if (mode == 0) {
			WordModel newWordModel = new WordModel();
			newWordModel.setWordOrPhrase(this.textFieldWord.getText());
			newWordModel.setPronounce(this.textFieldSpelling.getText());
			if (cBTypeWord.getSelectedIndex() == 0) {
				newWordModel.setMeaning(this.textAreaMeaning.getText());
			} else {
				newWordModel.setMeaning("*" + cBTypeWord.getSelectedItem() + "\n" + this.textAreaMeaning.getText());
			}
			newWordModel.setHashTags(this.textFieldTags.getText());
			newWordModel.setPathOfAudioFile(this.textFieldPronuonce.getText());
			newWordModel.setPathOfImageFile(this.pathImage);
			newWordModel.setSuggest(this.textAreaSuggestion.getText());
			if (wordDAO.databaseIsExist) {
				wordDAO.saveWord(newWordModel);
			} else {
				wordJsonDAO.saveWord(newWordModel);
			}
		} else if (mode == 1) {
			wordModel.setWordOrPhrase(this.textFieldWord.getText());
			wordModel.setPronounce(this.textFieldSpelling.getText());
			if (cBTypeWord.getSelectedIndex() == 0) {
				wordModel.setMeaning(this.textAreaMeaning.getText());
			} else {
				wordModel.setMeaning("*" + cBTypeWord.getSelectedItem() + "\n" + this.textAreaMeaning.getText());
			}
			wordModel.setHashTags(this.textFieldTags.getText());
			wordModel.setPathOfAudioFile(this.textFieldPronuonce.getText());
			wordModel.setPathOfImageFile(this.pathImage);
			wordModel.setSuggest(this.textAreaSuggestion.getText());
			if (wordDAO.databaseIsExist) {
				wordDAO.update(wordModel);
			} else {
				wordJsonDAO.editWord(wordModel, wordModel.getIndexOfListWords());
			}
		}

		dataModel.update();
		mainFrame.update();
	}

	void removeWordFromDatabase() throws UnsupportedEncodingException {
		if (wordDAO.databaseIsExist) {
			wordDAO.delete(wordModel.getId());
		} else {
			wordJsonDAO.deleteWord(wordModel.getIndexOfListWords());
		}
		dataModel.update();
		mainFrame.update();
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public WordModel getWordModel() {
		return wordModel;
	}

	public void setWordModel(WordModel wordModel) {
		this.wordModel = wordModel;
	}

	public JComboBox<String> getcBTypeWord() {
		return cBTypeWord;
	}

	public void setcBTypeWord(JComboBox<String> cBTypeWord) {
		this.cBTypeWord = cBTypeWord;
	}

	public JLabel getLblToday() {
		return lblToday;
	}

	public void setLblToday(JLabel lblToday) {
		this.lblToday = lblToday;
	}

	public JTextField getTextFieldWord() {
		return textFieldWord;
	}

	public void setTextFieldWord(JTextField textFieldWord) {
		this.textFieldWord = textFieldWord;
	}
}

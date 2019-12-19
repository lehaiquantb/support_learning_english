package view.JPanelComponent;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import util.GenerateWord;
import util.JTextFieldBox;
import util.JTextFieldLimit;
import util.Util1;
import view.MainFrame;
import java.awt.GridLayout;
import java.awt.SystemColor;

/**
 * @author quan.lh173316
 *
 */
public class ContentView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GenerateWord generateWord;
	private JPanel panel_mode1;
	private JPanel panel_Image;
	private JPanel panel_Sound;
	private JLabel lbl_Spelling;
	private JButton btnSound;
	private JPanel panel_Meaning;
	private JScrollPane scrollPane1;
	private JTextArea textAreaMeaning;
	private JPanel panel_Suggest;
	private JButton btnShowWord;
	private JLabel lblDate;
	private JLabel lblTime;
	private JTextArea textAreaSuggest;
	private JPanel panel_TagCrud;
	private JPanel panel_mode8;
	private JPanel panel_WordOrPhrase;
	private JLabel lblListTags;
	private JButton btnDelete;
	private JButton btnEdit;
	private JButton btnAdd;
	private JButton btnCheck;
	private JPanel panelValidateWrong;
	private JPanel panelValidateRight;
	private JPanel panelValidateWarning;

	public JLabel getLblListTags() {
		return lblListTags;
	}

	public void setLblListTags(JLabel lblListTags) {
		this.lblListTags = lblListTags;
	}

	/**
	 * 
	 */
	public ContentView() {
		this.setBounds(242, 69, 617, 411);
		this.setLayout(new CardLayout(0, 0));
		// mode1
		panel_mode1 = new JPanel();
		panel_mode1.setBackground(Color.LIGHT_GRAY);
		panel_mode1.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.add(panel_mode1, "name_323403604697859");
		panel_mode1.setLayout(null);

		panel_Image = new JPanel();
//		FlowLayout fl_panel_Image = new FlowLayout(FlowLayout.CENTER);
//		fl_panel_Image.setVgap(1);
//		fl_panel_Image.setHgap(1);
//		panel_Image.setLayout(fl_panel_Image);
		
		panel_Image.setBounds(407, 11, 200, 179);
		panel_mode1.add(panel_Image);
		panel_Image.setLayout(new BorderLayout(0, 0));

		panel_Sound = new JPanel();
		panel_Sound.setBounds(10, 11, 387, 33);
		panel_mode1.add(panel_Sound);
		panel_Sound.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		lbl_Spelling = new JLabel("/no spelling/");
		lbl_Spelling.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_Spelling.setHorizontalAlignment(SwingConstants.LEFT);
		panel_Sound.add(lbl_Spelling);

		btnSound = new JButton("");
		btnSound.setBackground(Color.WHITE);
		btnSound.setIcon(Util1.getImageIconResize(getClass(), "iconSound.png", 24, 24));
		btnSound.setBorder(BorderFactory.createEmptyBorder());
		panel_Sound.add(btnSound);

		panel_Meaning = new JPanel();
		panel_Meaning.setBorder(new LineBorder(Color.BLACK));
		panel_Meaning.setBounds(10, 177, 387, 179);
		panel_mode1.add(panel_Meaning);
		panel_Meaning.setLayout(new BorderLayout(0, 0));

		scrollPane1 = new JScrollPane();
		panel_Meaning.add(scrollPane1);

		textAreaMeaning = new JTextArea();
		textAreaMeaning.setBackground(SystemColor.activeCaption);
		textAreaMeaning.setFont(new Font("Times New Roman", Font.BOLD, 14));
		textAreaMeaning.setWrapStyleWord(true);
		textAreaMeaning.setLineWrap(true);
		textAreaMeaning.setEditable(false);
		textAreaMeaning.setText(Util1.getMeaningTest());
		scrollPane1.setViewportView(textAreaMeaning);

		panel_Suggest = new JPanel();
		panel_Suggest.setBounds(407, 201, 200, 155);
		panel_mode1.add(panel_Suggest);
		panel_Suggest.setLayout(null);

		lblDate = new JLabel("");
		lblDate.setBounds(44, 121, 85, 23);
		panel_Suggest.add(lblDate);

		lblTime = new JLabel("");
		lblTime.setBounds(71, 95, 119, 23);
		panel_Suggest.add(lblTime);

		textAreaSuggest = new JTextArea();
		textAreaSuggest.setBackground(Color.LIGHT_GRAY);
		textAreaSuggest.setWrapStyleWord(true);
		textAreaSuggest.setLineWrap(true);
		textAreaSuggest.setFont(new Font("Monospaced", Font.BOLD, 14));
		textAreaSuggest.setBounds(10, 11, 180, 84);
		textAreaSuggest.setEditable(false);
		panel_Suggest.add(textAreaSuggest);

		JLabel lblDate1 = new JLabel("Date:");
		lblDate1.setBounds(10, 121, 35, 23);
		panel_Suggest.add(lblDate1);

		JLabel lblAddedOn = new JLabel("Added on:");
		lblAddedOn.setBounds(10, 95, 58, 23);
		panel_Suggest.add(lblAddedOn);

		panel_TagCrud = new JPanel();
		panel_TagCrud.setBounds(10, 367, 597, 33);
		panel_mode1.add(panel_TagCrud);
		panel_TagCrud.setLayout(null);

		btnDelete = new JButton("Delete");
		btnDelete.setBounds(498, 0, 99, 33);
		btnDelete.setIcon(Util1.getImageIconResize(getClass(), "iconDelete.png", 20, 20));
		panel_TagCrud.add(btnDelete);

		btnEdit = new JButton("Edit");
		btnEdit.setBounds(398, 0, 90, 33);
		btnEdit.setIcon(Util1.getImageIconResize(getClass(), "iconEdit.png", 20, 20));
		panel_TagCrud.add(btnEdit);

		btnAdd = new JButton("Add");
		btnAdd.setBounds(299, 0, 89, 33);
		btnAdd.setIcon(Util1.getImageIconResize(getClass(), "iconAdd.png", 20, 20));
		panel_TagCrud.add(btnAdd);

		JLabel lblTag = new JLabel("Tags:");
		lblTag.setBounds(10, 5, 40, 22);
		panel_TagCrud.add(lblTag);

		lblListTags = new JLabel("ListTags");
		lblListTags.setBounds(60, 0, 252, 33);
		panel_TagCrud.add(lblListTags);

		panel_mode8 = new JPanel();
		this.add(panel_mode8, "name_323500746610835");
		panel_WordOrPhrase = new JPanel();
		panel_WordOrPhrase.setBackground(SystemColor.inactiveCaption);
		panel_WordOrPhrase.setBounds(10, 47, 387, 106);
		panel_mode1.add(panel_WordOrPhrase);
		panel_WordOrPhrase.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 5));

//		JTextField jTextField = new JTextField();
//		jTextField.setBorder(BorderFactory.createLineBorder(Color.RED));
//		jTextField.setDocument(new JTextFieldLimit(1));
//		jTextField.setText(Character.toString('a'));
//		jTextField.setEditable(false);
//		jTextField.setHorizontalAlignment(JTextField.CENTER);
//		jTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
//		jTextField.setColumns(1);

//		panel_WordOrPhrase.add(jTextField);

		btnShowWord = new JButton("Show");
		btnShowWord.setBounds(94, 154, 69, 23);
		panel_mode1.add(btnShowWord);

		btnCheck = new JButton("Check");
		btnCheck.setBounds(10, 154, 74, 23);
		panel_mode1.add(btnCheck);

		panelValidateWrong = new JPanel();
		panelValidateWrong.setVisible(false);
		panelValidateWrong.setBounds(279, 154, 118, 23);
		panel_mode1.add(panelValidateWrong);
		panelValidateWrong.setLayout(null);

		JLabel lblIconWrong = new JLabel("");
		lblIconWrong.setIcon(Util1.getImageIconResize(getClass(), "iconWrong.jpg", 15, 15));
		lblIconWrong.setBackground(getBackground());
		lblIconWrong.setBounds(10, 0, 19, 23);
		panelValidateWrong.add(lblIconWrong);

		JLabel lblMessageWrong = new JLabel("Please try again !!!");
		lblMessageWrong.setForeground(Color.RED);
		lblMessageWrong.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblMessageWrong.setHorizontalAlignment(SwingConstants.LEFT);
		lblMessageWrong.setBounds(25, 0, 93, 23);
		panelValidateWrong.add(lblMessageWrong);
		//
		panelValidateRight = new JPanel();
		getPanelValidateRight().setVisible(false);
		panelValidateRight.setBounds(279, 154, 118, 23);
		panel_mode1.add(panelValidateRight);
		panelValidateRight.setLayout(null);

		JLabel lblIconRight = new JLabel("");
		lblIconRight.setIcon(Util1.getImageIconResize(getClass(), "iconRight.jpg", 15, 15));
		lblIconRight.setBackground(getBackground());
		lblIconRight.setBounds(10, 0, 19, 23);
		panelValidateRight.add(lblIconRight);

		JLabel lblMessageRight = new JLabel("Good! Loading...");
		lblMessageRight.setForeground(Color.GREEN);
		lblMessageRight.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblMessageRight.setHorizontalAlignment(SwingConstants.LEFT);
		lblMessageRight.setBounds(25, 0, 93, 23);
		panelValidateRight.add(lblMessageRight);

		panelValidateWarning = new JPanel();
		panelValidateWarning.setVisible(false);
		panelValidateWarning.setBounds(244, 154, 153, 23);
		panel_mode1.add(panelValidateWarning);
		panelValidateWarning.setLayout(null);

		JLabel lblIconWarning = new JLabel("");
		lblIconWarning.setIcon(Util1.getImageIconResize(getClass(), "iconWarning.png", 15, 15));
		lblIconWarning.setBackground(getBackground());
		lblIconWarning.setBounds(10, 0, 19, 23);
		panelValidateWarning.add(lblIconWarning);

		JLabel lblMessageWarning = new JLabel("You need to fill all the boxs");
		lblMessageWarning.setForeground(Color.ORANGE);
		lblMessageWarning.setFont(new Font("Tahoma", Font.ITALIC, 10));
		lblMessageWarning.setHorizontalAlignment(SwingConstants.LEFT);
		lblMessageWarning.setBounds(25, 0, 128, 23);
		panelValidateWarning.add(lblMessageWarning);
	}

	public JPanel getPanelValidateWarning() {
		return panelValidateWarning;
	}

	public void setPanelValidateWarning(JPanel panelValidateWarning) {
		this.panelValidateWarning = panelValidateWarning;
	}

	public JPanel getPanelValidateWrong() {
		return panelValidateWrong;
	}

	public void setPanelValidateWrong(JPanel panelValidateWrong) {
		this.panelValidateWrong = panelValidateWrong;
	}

	public JPanel getPanelValidateRight() {
		return panelValidateRight;
	}

	public void setPanelValidateRight(JPanel panelValidateRight) {
		this.panelValidateRight = panelValidateRight;
	}

	public JButton getBtnCheck() {
		return btnCheck;
	}

	public void setBtnCheck(JButton btnCheck) {
		this.btnCheck = btnCheck;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public void setBtnEdit(JButton btnEdit) {
		this.btnEdit = btnEdit;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JTextArea getTextAreaSuggest() {
		return textAreaSuggest;
	}

	public void setTextAreaSuggest(JTextArea textAreaSuggest) {
		this.textAreaSuggest = textAreaSuggest;
	}

	public JPanel getPanel_mode1() {
		return panel_mode1;
	}

	public void setPanel_mode1(JPanel panel_mode1) {
		this.panel_mode1 = panel_mode1;
	}

	public JPanel getPanel_Image() {
		return panel_Image;
	}

	public void setPanel_Image(JPanel panel_Image) {
		this.panel_Image = panel_Image;
	}

	public JPanel getPanel_Sound() {
		return panel_Sound;
	}

	public void setPanel_Sound(JPanel panel_Sound) {
		this.panel_Sound = panel_Sound;
	}

	public JLabel getLbl_Spelling() {
		return lbl_Spelling;
	}

	public void setLbl_Spelling(JLabel lbl_Spelling) {
		this.lbl_Spelling = lbl_Spelling;
	}

	public JButton getBtnSound() {
		return btnSound;
	}

	public void setBtnSound(JButton btnSound) {
		this.btnSound = btnSound;
	}

	public JPanel getPanel_Meaning() {
		return panel_Meaning;
	}

	public void setPanel_Meaning(JPanel panel_Meaning) {
		this.panel_Meaning = panel_Meaning;
	}

	public JScrollPane getScrollPane1() {
		return scrollPane1;
	}

	public void setScrollPane1(JScrollPane scrollPane1) {
		this.scrollPane1 = scrollPane1;
	}

	public JTextArea getTextAreaMeaning() {
		return textAreaMeaning;
	}

	public void setTextAreaMeaning(JTextArea textAreaMeaning) {
		this.textAreaMeaning = textAreaMeaning;
	}

	public JPanel getPanel_Suggest() {
		return panel_Suggest;
	}

	public void setPanel_Suggest(JPanel panel_Suggest) {
		this.panel_Suggest = panel_Suggest;
	}

	public JButton getBtnShowWord() {
		return btnShowWord;
	}

	public void setBtnShowWord(JButton btnShowWord) {
		this.btnShowWord = btnShowWord;
	}

	public JLabel getLblDate() {
		return lblDate;
	}

	public void setLblDate(JLabel lblDate) {
		this.lblDate = lblDate;
	}

	public JLabel getLblTime() {
		return lblTime;
	}

	public void setLblTime(JLabel lblTime) {
		this.lblTime = lblTime;
	}

	public JPanel getPanel_TagCrud() {
		return panel_TagCrud;
	}

	public void setPanel_TagCrud(JPanel panel_TagCrud) {
		this.panel_TagCrud = panel_TagCrud;
	}

	public JPanel getPanel_mode8() {
		return panel_mode8;
	}

	public void setPanel_mode8(JPanel panel_mode8) {
		this.panel_mode8 = panel_mode8;
	}

	public JPanel getPanel_WordOrPhrase() {
		return panel_WordOrPhrase;
	}

	public void setPanel_WordOrPhrase(JPanel panel_WordOrPhrase) {
		this.panel_WordOrPhrase = panel_WordOrPhrase;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GenerateWord getGenerateWord() {
		return generateWord;
	}

	public void setGenerateWord(GenerateWord generateWord) {
		this.generateWord = generateWord;
	}
}

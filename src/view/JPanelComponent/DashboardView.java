package view.JPanelComponent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import util.Util1;

/**
 * @author quan.lh173316
 *
 */
public class DashboardView extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<String> cB_ModeLearn;
	private JComboBox<String> cB_LevelLearn;
	private JLabel lblHeader;
	private JButton btnTutorial;
	private JLabel lblTestMode;
	private JLabel lblLevel;
	private JLabel lblTotalModel;
	private JLabel lblTTMD;
	private JPanel panelModeTag;
	private JTextField textFieldTags;
	private DefaultListModel<String> defaultListModel;
	private JButton btnPlus;
	private JButton btnRefresh;
	private JDatePickerImpl datePickerFrom;
	private UtilDateModel dateModelFrom;
	private JDatePanelImpl datePanelFrom;
	private UtilDateModel dateModelTo;
	private JDatePanelImpl datePanelTo;
	private JDatePickerImpl datePickerTo;
	private JLabel lblTextTo;
	private JLabel lblTextFrom;

	/**
	 * 
	 */

	public DashboardView() {
		setBackground(SystemColor.activeCaption);
		this.setBounds(0, 0, 232, 514);
		this.setLayout(null);

		lblHeader = new JLabel();
		lblHeader.setIcon(Util1.getImageIconResize(getClass(), "bannerLE.jpg", 212, 94));
		lblHeader.setBounds(10, 11, 212, 94);
		this.add(lblHeader);

		cB_ModeLearn = new JComboBox<String>();
		cB_ModeLearn.setBackground(Color.WHITE);
		cB_ModeLearn.addItem("default");
		cB_ModeLearn.addItem("By HashTags");
		cB_ModeLearn.addItem("By Date");
		cB_ModeLearn.setSelectedIndex(0);
		cB_ModeLearn.setBounds(75, 116, 147, 35);
		this.add(cB_ModeLearn);

		cB_LevelLearn = new JComboBox<String>();
		cB_LevelLearn.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7" }));
		cB_LevelLearn.setSelectedIndex(0);
		cB_LevelLearn.setBounds(75, 215, 147, 35);
		this.add(cB_LevelLearn);

		btnTutorial = new JButton("");
		btnTutorial.setBounds(202, 483, 20, 20);
		this.add(btnTutorial);
		btnTutorial.setIcon(Util1.getImageIconResize(getClass(), "iconTutorial.png", 20, 20));
		btnTutorial.setBorder(BorderFactory.createEmptyBorder());
		btnTutorial.setPreferredSize(new Dimension(20, 20));

		lblTestMode = new JLabel("Test Mode");
		lblTestMode.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTestMode.setBounds(10, 116, 65, 35);
		add(lblTestMode);

		lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLevel.setBounds(10, 215, 65, 35);
		add(lblLevel);

		lblTotalModel = new JLabel("0");
		lblTotalModel.setBounds(75, 483, 117, 20);
		add(lblTotalModel);

		lblTTMD = new JLabel("Total word:");
		lblTTMD.setBounds(10, 483, 65, 20);
		add(lblTTMD);
		defaultListModel = new DefaultListModel<String>();

		panelModeTag = new JPanel();
		panelModeTag.setVisible(false);
		panelModeTag.setBounds(10, 155, 212, 22);
		add(panelModeTag);
		panelModeTag.setLayout(null);

		textFieldTags = new JTextField();
		textFieldTags.setBounds(0, 0, 153, 22);
		panelModeTag.add(textFieldTags);
		textFieldTags.setColumns(10);

		btnRefresh = new JButton("");
		btnRefresh.setBounds(182, -1, 30, 23);
		btnRefresh.setIcon(Util1.getImageIconResize(getClass(), "iconRefresh.png", 17, 17));
		panelModeTag.add(btnRefresh);

		btnPlus = new JButton("");
		btnPlus.setBounds(153, -1, 30, 23);
		btnPlus.setIcon(Util1.getImageIconResize(getClass(), "iconPlus.png", 17, 17));
		panelModeTag.add(btnPlus);

		dateModelFrom = new UtilDateModel();
		dateModelFrom.setSelected(true);
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanelFrom = new JDatePanelImpl(dateModelFrom, p);

		lblTextFrom = new JLabel("From");
		lblTextFrom.setVisible(false);
		lblTextFrom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTextFrom.setFont(new Font("Arial", lblTextFrom.getFont().getStyle(), lblTextFrom.getFont().getSize()));
		lblTextFrom.setBounds(10, 160, 48, 24);
		add(lblTextFrom);

		dateModelTo = new UtilDateModel();
		dateModelTo.setSelected(true);
		datePanelTo = new JDatePanelImpl(dateModelTo, p);
		datePickerTo = new JDatePickerImpl(datePanelTo, new DateLabelFormatter());
		datePickerTo.setVisible(false);
		datePickerTo.getJFormattedTextField().setFont(new Font("Tahoma", Font.BOLD, 11));
		datePickerTo.setLocation(75, 188);
		datePickerTo.setSize(150, 24);
		this.add(datePickerTo);

		lblTextTo = new JLabel("To");
		lblTextTo.setVisible(false);
		lblTextTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTextTo.setFont(new Font("Arial", lblTextTo.getFont().getStyle(), lblTextTo.getFont().getSize()));
		lblTextTo.setBounds(10, 188, 48, 24);
		add(lblTextTo);

		datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateLabelFormatter());
		datePickerFrom.setVisible(false);
		datePickerFrom.setBounds(75, 160, 150, 24);
		add(datePickerFrom);
		datePickerFrom.getJFormattedTextField().setFont(new Font("Tahoma", Font.BOLD, 11));

	}

	public JLabel getLblTextTo() {
		return lblTextTo;
	}

	public void setLblTextTo(JLabel lblTextTo) {
		this.lblTextTo = lblTextTo;
	}

	public JLabel getLblTextFrom() {
		return lblTextFrom;
	}

	public void setLblTextFrom(JLabel lblTextFrom) {
		this.lblTextFrom = lblTextFrom;
	}

	public JDatePickerImpl getDatePickerFrom() {
		return datePickerFrom;
	}

	public void setDatePickerFrom(JDatePickerImpl datePickerFrom) {
		this.datePickerFrom = datePickerFrom;
	}

	public UtilDateModel getDateModelFrom() {
		return dateModelFrom;
	}

	public void setDateModelFrom(UtilDateModel dateModelFrom) {
		this.dateModelFrom = dateModelFrom;
	}

	public JDatePanelImpl getDatePanelFrom() {
		return datePanelFrom;
	}

	public void setDatePanelFrom(JDatePanelImpl datePanelFrom) {
		this.datePanelFrom = datePanelFrom;
	}

	public UtilDateModel getDateModelTo() {
		return dateModelTo;
	}

	public void setDateModelTo(UtilDateModel dateModelTo) {
		this.dateModelTo = dateModelTo;
	}

	public JDatePanelImpl getDatePanelTo() {
		return datePanelTo;
	}

	public void setDatePanelTo(JDatePanelImpl datePanelTo) {
		this.datePanelTo = datePanelTo;
	}

	public JDatePickerImpl getDatePickerTo() {
		return datePickerTo;
	}

	public void setDatePickerTo(JDatePickerImpl datePickerTo) {
		this.datePickerTo = datePickerTo;
	}

	public JTextField getTextFieldTags() {
		return textFieldTags;
	}

	public void setTextFieldTags(JTextField textFieldTags) {
		this.textFieldTags = textFieldTags;
	}

	public JButton getBtnPlus() {
		return btnPlus;
	}

	public void setBtnPlus(JButton btnPlus) {
		this.btnPlus = btnPlus;
	}

	public JButton getBtnRefresh() {
		return btnRefresh;
	}

	public void setBtnRefresh(JButton btnRefresh) {
		this.btnRefresh = btnRefresh;
	}

	public JLabel getLblTotalModel() {
		return lblTotalModel;
	}

	public void setLblTotalModel(JLabel lblTotalModel) {
		this.lblTotalModel = lblTotalModel;
	}

	public JComboBox<String> getcB_ModeLearn() {
		return cB_ModeLearn;
	}

	public void setcB_ModeLearn(JComboBox<String> cB_ModeLearn) {
		this.cB_ModeLearn = cB_ModeLearn;
	}

	public JComboBox<String> getcB_LevelLearn() {
		return cB_LevelLearn;
	}

	public void setcB_LevelLearn(JComboBox<String> cB_LevelLearn) {
		this.cB_LevelLearn = cB_LevelLearn;
	}

	public JLabel getLblHeader() {
		return lblHeader;
	}

	public void setLblHeader(JLabel lblHeader) {
		this.lblHeader = lblHeader;
	}

	public JButton getBtnTutorial() {
		return btnTutorial;
	}

	public void setBtnTutorial(JButton btnTutorial) {
		this.btnTutorial = btnTutorial;
	}

	public JLabel getLblTestMode() {
		return lblTestMode;
	}

	public void setLblTestMode(JLabel lblTestMode) {
		this.lblTestMode = lblTestMode;
	}

	public JLabel getLblLevel() {
		return lblLevel;
	}

	public void setLblLevel(JLabel lblLevel) {
		this.lblLevel = lblLevel;
	}

	public JPanel getPanelModeTag() {
		return panelModeTag;
	}

	public void setPanelModeTag(JPanel panelModeTag) {
		this.panelModeTag = panelModeTag;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	class DateLabelFormatter extends AbstractFormatter {

		private String datePattern = "dd/MM/yyyy";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) throws ParseException {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}
}

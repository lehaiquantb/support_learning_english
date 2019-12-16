package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.sun.org.apache.bcel.internal.generic.NEW;

import model.DataModel;
import view.MainFrame;

/**
 * @author quan.lh173316
 *
 */
public class TestModeController extends AbstractController {

	private JComboBox<String> comboBox;
	private Timestamp dateFrom;
	private Timestamp dateTo;
	private Calendar cal = Calendar.getInstance();

	public TestModeController(DataModel dataModel, MainFrame mainFrame) {
		super(dataModel, mainFrame);
		this.comboBox = dashboardView.getcB_ModeLearn();
		dateFrom = new Timestamp(0);
		dateTo = new Timestamp(0);
	}

	public void addListener() {
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() == 0) {
					mainFrame.getFrame().getLayeredPane().remove(mainFrame.getPanelSearchTag());
					mainFrame.getFrame().getLayeredPane().revalidate();
					mainFrame.getFrame().getLayeredPane().repaint();
					dashboardView.getPanelModeTag().setVisible(false);
					dashboardView.getDatePickerFrom().setVisible(false);
					dashboardView.getDatePickerTo().setVisible(false);
					dashboardView.getLblTextFrom().setVisible(false);
					dashboardView.getLblTextTo().setVisible(false);
					dashboardView.getTextFieldTags().setText(null);
					dashboardView.getTextFieldTags().setEditable(true);
					dashboardView.getBtnRefresh().setEnabled(true);
					dashboardView.getBtnPlus().setEnabled(true);
					dataModel.setListWordModelsByDefault();
					dashboardView.getLblTotalModel()
							.setText(dataModel.getTotalWordModelByMode() + "/" + dataModel.getTotalWordModel());
				} else if (comboBox.getSelectedIndex() == 1) {
					dashboardView.getDatePickerFrom().setVisible(false);
					dashboardView.getDatePickerTo().setVisible(false);
					dashboardView.getLblTextFrom().setVisible(false);
					dashboardView.getLblTextTo().setVisible(false);
					dashboardView.getPanelModeTag().setVisible(true);
				} else if (comboBox.getSelectedIndex() == 2) {
					dashboardView.getPanelModeTag().setVisible(false);
					dashboardView.getDatePickerFrom().setVisible(true);
					dashboardView.getLblTextFrom().setVisible(true);
					dashboardView.getLblTextTo().setVisible(true);
					dashboardView.getDatePickerTo().setVisible(true);
				}

			}
		});

		dashboardView.getTextFieldTags().addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				mainFrame.getFrame().getLayeredPane().remove(mainFrame.getPanelSearchTag());
				mainFrame.getFrame().getLayeredPane().revalidate();
				mainFrame.getFrame().getLayeredPane().repaint();
				if (setListModelByTag(dashboardView.getTextFieldTags().getText())) {
					mainFrame.getFrame().getLayeredPane().add(mainFrame.getPanelSearchTag());
					mainFrame.getFrame().getLayeredPane().revalidate();
					mainFrame.getFrame().getLayeredPane().repaint();
				}
			}
		});

		mainFrame.getListSearchTag().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dashboardView.getTextFieldTags().setText(mainFrame.getListSearchTag().getSelectedValue());
			}
		});

		dashboardView.getBtnPlus().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dataModel.getTagMapListWordModel().containsKey(dashboardView.getTextFieldTags().getText())) {
					dataModel.setListWordModelsByModeTags(dashboardView.getTextFieldTags().getText());
					dashboardView.getLblTotalModel()
							.setText(dataModel.getTotalWordModelByMode() + "/" + dataModel.getTotalWordModel());
					mainFrame.getFrame().getLayeredPane().remove(mainFrame.getPanelSearchTag());
					mainFrame.getFrame().getLayeredPane().revalidate();
					mainFrame.getFrame().getLayeredPane().repaint();
					dashboardView.getBtnPlus().setEnabled(false);
					dashboardView.getBtnRefresh().setEnabled(true);
					dashboardView.getTextFieldTags().setEditable(false);
				} else {
					JOptionPane.showMessageDialog(null, "Wrong HashTags");
				}
			}
		});

		dashboardView.getBtnRefresh().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dashboardView.getTextFieldTags().setText(null);
				dashboardView.getBtnPlus().setEnabled(true);
				dashboardView.getBtnRefresh().setEnabled(false);
				dashboardView.getTextFieldTags().setEditable(true);
			}
		});

		dashboardView.getDatePanelFrom().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cal.setTime(dashboardView.getDateModelFrom().getValue());
				cal.set(Calendar.HOUR_OF_DAY, 00);
				cal.set(Calendar.MINUTE, 00);
				cal.set(Calendar.SECOND, 00);
				// dateFrom = new Timestamp(cal.getTimeInMillis());
				dateFrom.setTime(cal.getTimeInMillis());

				cal.setTime(dashboardView.getDateModelTo().getValue());
				cal.set(Calendar.HOUR_OF_DAY, 24);
				cal.set(Calendar.MINUTE, 00);
				cal.set(Calendar.SECOND, 00);
				if ((cal.getTimeInMillis() - dateFrom.getTime()) / 1000 <= 0) {
					JOptionPane.showMessageDialog(null, "You must enter an appropriate time period !");
				} else {
					dateTo.setTime(cal.getTimeInMillis());
					dataModel.setListWordModelsByModeTime(dateFrom, dateTo);
					mainFrame.update();
				}
			}
		});
		dashboardView.getDatePanelTo().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cal.setTime(dashboardView.getDateModelFrom().getValue());
				cal.set(Calendar.HOUR_OF_DAY, 00);
				cal.set(Calendar.MINUTE, 00);
				cal.set(Calendar.SECOND, 00);
				dateFrom.setTime(cal.getTimeInMillis());
				// System.out.println("dateFrom: " + cal.getTime().toString() + "=" +
				// dateFrom.getTime());

				cal.setTime(dashboardView.getDateModelTo().getValue());
				cal.set(Calendar.HOUR_OF_DAY, 24);
				cal.set(Calendar.MINUTE, 00);
				cal.set(Calendar.SECOND, 00);
				if ((cal.getTimeInMillis() - dateFrom.getTime()) / 1000 <= 0) {
					JOptionPane.showMessageDialog(null, "You must enter an appropriate time period !");
				} else {
					dateTo.setTime(cal.getTimeInMillis());
					// System.out.println("dateTo: " + cal.getTime().toString() + "=" +
					dataModel.setListWordModelsByModeTime(dateFrom, dateTo);
					mainFrame.update();
				}
			}
		});

	}

	boolean setListModelByTag(String text) {
		boolean check = false;
		DefaultListModel<String> newListModel = new DefaultListModel<String>();
		Pattern pattern = Pattern.compile("[\\S]*" + text + "[\\S]*");
		Matcher matcher = pattern.matcher(dataModel.getMatchTag());
		if (matcher.find()) {
			newListModel.addElement(matcher.group());
			check = true;
			while (matcher.find()) {
				newListModel.addElement(matcher.group());
			}
		}
		mainFrame.setDefaultListModel(newListModel);
		mainFrame.getListSearchTag().setModel(mainFrame.getDefaultListModel());
		return check;
	}
}

package view.JPanelComponent;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author quan.lh173316
 *
 */
public class FooterView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel getLabel_Author() {
		return label_Author;
	}

	public void setLabel_Author(JLabel label_Author) {
		this.label_Author = label_Author;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private JLabel label_Author;

	public FooterView() {
		setBackground(Color.GRAY);
		setBounds(242, 491, 617, 23);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		label_Author = new JLabel("Design by Q");
		label_Author.setFont(new Font("Arial", Font.BOLD, 11));
		label_Author.setForeground(Color.WHITE);
		add(label_Author);
	}

}

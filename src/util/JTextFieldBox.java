package util;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * @author quan.lh173316
 *
 */

public class JTextFieldBox extends JTextField {
	private static final long serialVersionUID = 1L;

	public JTextFieldBox(char c) {
		this.setDocument(new JTextFieldLimit(1));
		this.setText(Character.toString(c));
		this.setEditable(false);
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.setColumns(1);
		this.setBorder(BorderFactory.createEmptyBorder());
	}

	public JTextFieldBox(String string) {
		this.setDocument(new JTextFieldLimit(1));
		this.setToolTipText(string);
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.setColumns(1);
	}

	public JTextFieldBox() {
		this.setText(" ");
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setEditable(false);
		this.setToolTipText("space");
		this.setBackground(getBackground());
		this.setHorizontalAlignment(JTextField.CENTER);
		this.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.setColumns(1);
	}

}

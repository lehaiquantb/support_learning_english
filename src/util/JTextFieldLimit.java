package util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @author quan.lh173316
 *
 */
public class JTextFieldLimit extends PlainDocument {

	private static final long serialVersionUID = 1L;
	private int limit;

	public JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	JTextFieldLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
	}

	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
		if (str == null)
			return;
		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}

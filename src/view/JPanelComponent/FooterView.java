package view.JPanelComponent;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Util1;

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
		setBounds(242, 482, 617, 29);
		setLayout(new FlowLayout(FlowLayout.CENTER, 130, 5));

		JLabel lblGitHub = new JLabel();
		lblGitHub.setIcon(Util1.getImageIconResize(getClass(), "iconGitHub.png", 20, 20));
		lblGitHub.setSize(20, 20);
		add(lblGitHub);

		label_Author = new JLabel("Design by Q");
		label_Author.setFont(new Font("Arial", Font.BOLD, 11));
		label_Author.setForeground(Color.WHITE);
		label_Author.setIcon(Util1.getImageIconResize(getClass(), "iconHust.png", 10, 10));
		add(label_Author);

		JLabel lblMail = new JLabel("");
		lblMail.setIcon(Util1.getImageIconResize(getClass(), "iconMail.png", 20, 20));
		add(lblMail);

		lblGitHub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					openWebpage(new URL("https://github.com/lehaiquantb/support_learning_english"));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				}
			}
		});

		lblMail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					openWebpage(new URI("mailto:lehaiquantb@gmail.com"));
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	public static boolean openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static boolean openWebpage(URL url) {
		try {
			return openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}

}

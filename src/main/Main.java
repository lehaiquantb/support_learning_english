package main;

import java.awt.EventQueue;
import java.sql.SQLException;

import model.DataModel;
import view.MainFrame;

/**
 * @author quan.lh173316
 *
 */
public class Main {
	
	public Main() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		DataModel dataModel = new DataModel();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame(dataModel);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

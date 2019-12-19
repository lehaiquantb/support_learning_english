package main;

import java.awt.EventQueue;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.UIManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.DataModel;
import model.WordModel;
import view.MainFrame;

/**
 * @author quan.lh173316
 *
 */
public class Main {

	public Main() {
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
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MainFrame window = new MainFrame(dataModel);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				if (!dataModel.getWordDAO().databaseIsExist) {
					// update Json file
					// System.out.println("Shutdown Hook is running !");
					GsonBuilder builder = new GsonBuilder();
					builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
					builder.setPrettyPrinting();
					Gson gson = builder.create();
					try {
						FileWriter writer = new FileWriter("./JSONfile-as-database/listWordModels.json");
						writer.write(
								gson.toJson(dataModel.getListAllWordModels(), new TypeToken<ArrayList<WordModel>>() {
								}.getType()));
						writer.flush();
						writer.close();
						System.out.println("Jsonfile was updated");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("The program is closing...closed");
			}
		});
		// System.out.println("Application Terminating ...");
	}

}

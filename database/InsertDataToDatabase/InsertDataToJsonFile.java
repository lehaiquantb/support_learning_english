package InsertDataToDatabase;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.DataModel;
import model.WordModel;

/**
 * @author quan.lh173316
 *
 */
public class InsertDataToJsonFile {

	public InsertDataToJsonFile() {
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws IOException, SQLException {
//		FilterDataFromDictFile filter = new FilterDataFromDictFile();
//		filter.run();
//		filter.filter();
//		filter.setTagAndPathFile("", "", "#tag");

		DataModel dataModel = new DataModel();
		Long start = System.nanoTime();
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		// builder.setPrettyPrinting();
		Gson gson = builder.create();
		FileWriter writer = new FileWriter("./database/listWordModels.json");

//		writer.write(gson.toJson(filter.listWords.subList(0, 20), new TypeToken<ArrayList<WordModel>>() {
//		}.getType()));

		writer.write(gson.toJson(dataModel.getListAllWordModels(), new TypeToken<ArrayList<WordModel>>() {
		}.getType()));
		writer.flush();
		writer.close();
		System.out.println("Time to create json file = " + (System.nanoTime() - start) / 1000000 + " milis");
	}

}

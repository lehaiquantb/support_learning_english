package InsertDataToDatabase;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
	 */
	public static void main(String[] args) throws IOException {
		FilterDataFromDictFile filter = new FilterDataFromDictFile();
		filter.run();
		filter.filter();
		Long start = System.nanoTime();
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		FileWriter writer = new FileWriter("demo.json");
		writer.write(gson.toJson(filter.getSubListWordOrPhrase(0, 100), new TypeToken<ArrayList<WordModel>>() {
		}.getType()));
		System.out.println((System.nanoTime() - start) / 1000000);
	}

}

package dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.DataModel;
import model.WordModel;

/**
 * @author quan.lh173316
 *
 */
public class WordJsonDAO {

	private GsonBuilder builder;
	private Gson gson;
	private DataModel dataModel;

	public WordJsonDAO(DataModel dataModel) {
		this.dataModel = dataModel;
		this.builder = new GsonBuilder();
		builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
		this.gson = builder.create();
	}

	public List<WordModel> getAllWord() throws FileNotFoundException, UnsupportedEncodingException {
		ArrayList<WordModel> list;
		File fileDir = new File("./JSONfile-as-database/listWordModels.json");
		BufferedReader reader;
		reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "UTF-8"));
		list = gson.fromJson(reader, new TypeToken<ArrayList<WordModel>>() {
		}.getType());
//		String path = "./JSONfile-as-database/listWordModels.json";
//		FileInputStream fis = new FileInputStream(path);
//		BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		return list;
	}

	public void saveWord(WordModel newWordModel) {
		Date date = new Date();
		newWordModel.setDateAdded(new Timestamp(date.getTime()));
		dataModel.getListAllWordModels().add(newWordModel);
	}

	public void deleteWord(int indexOfListWord) {
		dataModel.getListAllWordModels().remove(indexOfListWord);
	}

	public void editWord(WordModel newWordModel, int indexOfOldWord) {
		WordModel oldWordModel = dataModel.getListAllWordModels().get(indexOfOldWord);
		oldWordModel = newWordModel;
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		WordJsonDAO wordJsonDAO = new WordJsonDAO(null);
		List<WordModel> wordModels = wordJsonDAO.getAllWord();
		System.out.println(wordModels.get(0));
	}

}

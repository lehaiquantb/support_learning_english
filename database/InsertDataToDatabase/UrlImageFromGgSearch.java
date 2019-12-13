package InsertDataToDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.DataModel;
import model.WordModel;
import util.UrlImage;

/**
 * @author quan.lh173316
 *
 */
public class UrlImageFromGgSearch {

	public UrlImageFromGgSearch() {
	}

	/**
	 * @param args
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SQLException, IOException {
//		DataModel dataModel = new DataModel();
//		ArrayList<WordModel> arrayList = (ArrayList<WordModel>) dataModel.getListAllWordModels();

//		int max = 0;
//		int count = 0;
//		int current = 0;
//		String url;
//		int size = arrayList.size();
//		for (int i = 99900; i < 100000; i++) {
//			url = UrlImage.getUrlImage(arrayList.get(i).getWordOrPhrase());
//			System.out.println(arrayList.get(i).getWordOrPhrase() + "=>" + url);
////			if (url == null) {
////				count++;
////			} else {
////				current = url.length();
////				if (current > max) {
////					max = current;
////				}
////			}
//
//		}

		FilterDataFromDictFile filter = new FilterDataFromDictFile();
		//filter.createFileUrlImage();

//		System.out.println(count);
//		System.out.println(max);

//		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
//		for (int i = 0; i < 10; i++) {
//			writer.println(i + "=>" + "c" + i);
//		}
//		writer.close();

//		Matcher matcher;
//		Scanner sc = null;
//		File file = new File("url-image.txt");
//		ArrayList<String> list = new ArrayList<String>();
//		String q;
//
//		try {
//			sc = new Scanner(new BufferedReader(new FileReader(file)));
//			sc.useDelimiter("\r\n");
//
//			while (sc.hasNext()) {
//				q = sc.next();
//				list.add(q);
//				// System.out.println("[" + q + "]");
//			}
//		} finally {
//			System.out.println("Total url: " + list.size());
//			if (sc != null) {
//				sc.close();
//			}
//		}

		/*
		 * try { sc = new Scanner(new BufferedReader(new FileReader(file))); for (int i
		 * = 0; i < 10; i++) { if (sc.hasNext("(" + arrayList.get(i).getWordOrPhrase() +
		 * "=>)" + "([\\S]+)")) { q = sc.findInLine("(" +
		 * arrayList.get(i).getWordOrPhrase() + "=>)" + "([\\S]+)");
		 * System.out.println(q); // System.out.println("[" + q + "]"); } } } finally {
		 * if (sc != null) { sc.close(); } }
		 */

//		int i = 1;
//		for (String string : list) {
//			matcher = Pattern.compile("(" + arrayList.get(i).getWordOrPhrase() + "=>)" + "([\\S]+)").matcher(string);
//			if (matcher.find()) {
//				System.out.println(arrayList.get(i).getWordOrPhrase() + "=>" + matcher.group(2));
//				i++;
//			}
//			if (i == 10) {
//				break;
//			}
//		}

	}

}

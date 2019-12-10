package InsertDataToDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.WordModel;

/**
 * @author quan.lh173316
 *
 */
public class FilterDataFromDictFile {
	ArrayList<String> list;
	ArrayList<WordModel> listWords;
	int totalWords;

	public FilterDataFromDictFile() throws FileNotFoundException {
		// TODO Auto-generated constructor stub
		listWords = new ArrayList<WordModel>();
		run();
		filter();
		setTagAndPathFile("", "", "#Tag");
	}

	public void run() throws FileNotFoundException {
		Scanner sc = null;
		File file = new File("D:\\Study\\20191\\OOP\\data\\EV_109k\\anhviet109K.dict");
		list = new ArrayList<String>();
		try {
			sc = new Scanner(new BufferedReader(new FileReader(file)));
			sc.useDelimiter("@");
			while (sc.hasNext()) {
				String q = sc.next();
				q = "@" + q;
				list.add(q);
				// System.out.println(q);
			}
		} finally {
			list.remove(0);
			totalWords = list.size();
			if (sc != null) {
				sc.close();
			}
		}
	}

	public void filter() {
		for (String str : list) {
			WordModel word = new WordModel();
			Matcher m1 = Pattern.compile("^@([^\\n\\/]+(?=[ |\\n]))").matcher(str);
			Matcher m2 = Pattern.compile("\\/.+\\/").matcher(str);
			Matcher m3 = Pattern.compile("\\*[ ]*(.+)(\\n)*([^*]+)").matcher(str);
			Matcher m4 = Pattern.compile("(\\n)([^@\\/]+)").matcher(str);
			Matcher m0 = Pattern.compile("^@[^\\n']+[(?= \\n)]").matcher(str);
			// System.out.println(">>>" + str +"<<<");

			if (m1.find()) {
				word.setWordOrPhrase(m1.group(1));
			} else if (m0.find()) {
				word.setWordOrPhrase(m0.group());
			} else {
				continue;
			}

			if (m2.find()) {
				word.setPronounce(m2.group());
				if (m2.group().length() > 140) {
					word.setPronounce(m2.group().substring(0, 140));
				}
			}

			HashMap<String, String> hashMap = new HashMap<String, String>();
			while (m3.find()) {
				hashMap.put(m3.group(1), m3.group(3));
			}
			word.setTypeOfWord(hashMap);

			while (m4.find()) {
				word.setMeaning(m4.group(2));
				if (m4.group(2).length() > 3900) {
					word.setMeaning(m4.group(2).substring(0, 3900));
				}
			}

			listWords.add(word);
		}
	}

	public void setTagAndPathFile(String imageName, String audioName, String TagDefault) {
		for (WordModel word : listWords) {
			word.setHashTags(TagDefault);
			word.setPathOfImageFile("D:\\Study\\20191\\OOP\\data\\ImageData\\" + word.getWordOrPhrase());
			word.setPathOfAudioFile("D:\\Study\\20191\\OOP\\data\\pronounce_flac\\" + word.getWordOrPhrase() + ".flac");
		}
	}

}

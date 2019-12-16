package InsertDataToDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.WordModel;
import util.UrlImage;

/**
 * @author quan.lh173316
 *
 */
public class FilterDataFromDictFile {
	ArrayList<String> list;
	ArrayList<WordModel> listWords;
	public int totalWords;

	public FilterDataFromDictFile() throws FileNotFoundException, UnsupportedEncodingException {
		listWords = new ArrayList<WordModel>();
	}

	public void run() throws FileNotFoundException {
		Scanner sc = null;
		File file = new File("D:\\Study\\20191\\OOP\\data\\EV_109k\\anhviet109K.dict");
		list = new ArrayList<String>();
		String q;
		try {
			sc = new Scanner(new BufferedReader(new FileReader(file)));
			sc.useDelimiter("@");
			while (sc.hasNext()) {
				q = sc.next();
				q = "@" + q;
				list.add(q);
				// System.out.println(q);
			}
			System.out.println("Total word : " + list.size());
		} finally {
			list.remove(0);
			totalWords = list.size();
			if (sc != null) {
				sc.close();
			}
		}
	}

	public List<WordModel> getSubListWordOrPhrase(int begin, int end) {
		return this.listWords.subList(begin, end);
	}

	public String getWordOrPhrase(int i) {
		String word;
		Matcher m1 = Pattern.compile("^@([^\\n\\/]+(?=[ |\\n]))").matcher(list.get(i));
		Matcher m0 = Pattern.compile("^@[^\\n']+[(?= \\n)]").matcher(list.get(i));
		if (m1.find()) {
			word = m1.group(1);
		} else if (m0.find()) {
			word = m0.group();
		} else {
			return null;
		}
		return word;
	}

	public void createFileUrlImage() throws IOException {
		this.run();
		// PrintWriter writer = new PrintWriter("url-image.txt", "UTF-8");
		long startTime = System.nanoTime();
		BufferedWriter output = new BufferedWriter(new FileWriter("url-image.txt", true));
		for (int i = 53000; i < 54000; i++) {
			Matcher m1 = Pattern.compile("^@([^\\n\\/]+(?=[ |\\n]))").matcher(list.get(i));
			Matcher m0 = Pattern.compile("^@[^\\n']+[(?= \\n)]").matcher(list.get(i));
			String url;
			String word;
			if (m1.find()) {
				word = m1.group(1);
				url = UrlImage.getUrlImage(word);
			} else if (m0.find()) {
				word = m0.group();
				url = UrlImage.getUrlImage(word);
			} else {
				continue;
			}
			System.out.println(url);
			// writer.println(word + "=>" + url);
			output.append("\r\n" + word + "=>" + url);
		}
		// writer.close();
		output.close();
		System.out.println("Total time to excute 1000 words in millis= " + (System.nanoTime() - startTime) / 1000000);
		// Total time to excute 1000 words in millis = 820798
		// millis,778080,791245,778952,798424,827834,803733,823075,801493,804622,840809,853679,830052,855652,832950,847551,801021,791164,813049,817175
		// 888517,876462,854863,838200,855629,826708,835792,832896,1602703,821430
	}

	public void filter() throws FileNotFoundException, UnsupportedEncodingException {

		// int i = 0;
		for (String str : list) {
			// i++;
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

			// filter typeOfWord and corresponding meaning
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
//			if (i == 20) {
//				break;
//			}
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

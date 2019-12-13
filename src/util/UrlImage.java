package util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import InsertDataToDatabase.FilterDataFromDictFile;

public class UrlImage {

	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";

	public UrlImage() {

	}

	public static String getUrlImage(String keyWord) {
		// can only grab first 100 results
		String url = "https://www.google.com/search?site=imghp&tbm=isch&source=hp&q=" + keyWord + "&gws_rd=cr";

		// List<String> resultUrls = new ArrayList<String>();

		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(url).userAgent(USER_AGENT).referrer("https://www.google.com/")
					.get();

			Elements elements = doc.select("div.rg_meta");

			JSONObject jsonObject;
			for (org.jsoup.nodes.Element element : elements) {
				if (element.childNodeSize() > 0) {
					jsonObject = (JSONObject) new JSONParser().parse(element.childNode(0).toString());
					return (String) jsonObject.get("ou");
					// resultUrls.add((String) jsonObject.get("ou"));
				}
			}
//			for (String imageUrl : resultUrls) {
//				System.out.println(imageUrl);
//			}
			// return resultUrls.get(0);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public void writeUrlImageToFile() throws IOException {
		long startTime = System.nanoTime();
		// can only grab first 100 results
		String url;
		FilterDataFromDictFile filter = new FilterDataFromDictFile();
		filter.run();

		// PrintWriter writer = new PrintWriter("url-image.txt", "UTF-8");
		BufferedWriter output = new BufferedWriter(new FileWriter("url-image.txt", true));
		// List<String> resultUrls = new ArrayList<String>();
		org.jsoup.nodes.Document doc;
		Elements elements;
		JSONObject jsonObject;
		String urlImage = null;
		try {
			for (int i = 22000; i < 23000; i++) {
				url = "https://www.google.com/search?site=imghp&tbm=isch&source=hp&q=" + filter.getWordOrPhrase(i)
						+ "&gws_rd=cr";
				doc = Jsoup.connect(url).userAgent(USER_AGENT).referrer("https://www.google.com/").get();
				elements = doc.select("div.rg_meta");
				for (org.jsoup.nodes.Element element : elements) {
					if (element.childNodeSize() > 0) {
						jsonObject = (JSONObject) new JSONParser().parse(element.childNode(0).toString());
						urlImage = (String) jsonObject.get("ou");
						// resultUrls.add((String) jsonObject.get("ou"));
					}
				}
				System.out.println(urlImage);
				// writer.println(word + "=>" + url);
				output.append("\r\n" + filter.getWordOrPhrase(i) + "=>" + urlImage);
			}
			// writer.close();
			output.close();
			System.out.println(
					"Total time to excute 1000 words in millis = " + (System.nanoTime() - startTime) / 1000000);
			//Total time to excute 1000 words in millis = 808017 millis
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		UrlImage urlImage = new UrlImage();
		//urlImage.writeUrlImageToFile();
	}
}

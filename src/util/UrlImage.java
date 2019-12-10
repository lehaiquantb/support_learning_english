package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

public class UrlImage {

	public UrlImage() {

	}

	public static String getUrlImage(String kewWord) {
		// can only grab first 100 results
		String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
		String url = "https://www.google.com/search?site=imghp&tbm=isch&source=hp&q=" + kewWord + "&gws_rd=cr";

		// List<String> resultUrls = new ArrayList<String>();

		try {
			org.jsoup.nodes.Document doc = Jsoup.connect(url).userAgent(userAgent).referrer("https://www.google.com/")
					.get();

			Elements elements = doc.select("div.rg_meta");

			int i = 0;
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
}

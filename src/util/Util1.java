package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.speech.AudioException;
import javax.speech.Central;
import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import model.WordModel;

public class Util1 {

	public Util1() {
	}

	public static final String IMG_ICON_PATH = "/image/";

	public static Image getImageResize(Class<?> kClass, String imageName, int width, int height) {
		try {
			Image img = ImageIO.read(kClass.getResource(IMG_ICON_PATH + imageName));
			Image newImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			return newImg;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return null;
	}

	public static ImageIcon getImageIconResize(Class<?> kClass, String iconName, int width, int height) {
		try {
			Image img = ImageIO.read(kClass.getResource(IMG_ICON_PATH + iconName));
			Image newImg = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
			return new ImageIcon(newImg);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return null;
	}

	public static ImageIcon getImageIconResizeByPath(Class<?> kClass, String pathFile, String keyWord,
			WordModel wordModel, int width, int height) {

		try {
			if (pathFile != null) {
				File file = new File(pathFile);
				if (file.exists()) {
					BufferedImage myPicture = ImageIO.read(file);
					Image newImg = getScaledImage(myPicture, width, height);
					return new ImageIcon(newImg);
				}
			}
			try {
				URL img = new URL(pathFile);
				ImageIcon imageIcon = new ImageIcon(img);
				Image image = imageIcon.getImage();
				image = getScaledImage(image, width, height);
				return new ImageIcon(image);
			} catch (MalformedURLException exception) {
				if (keyWord != null) {
					String url = UrlImage.getUrlImage(keyWord);
					if (url != null) {
						URL img = new URL(url);
						ImageIcon imageIcon = new ImageIcon(img);
						Image image = imageIcon.getImage();
						image = getScaledImage(image, width, height);
						wordModel.setPathOfImageFile(url);
						return new ImageIcon(image);
					} else {
						return getImageIconResize(kClass, "NoImageAvailable.jpg", width, height);
					}
				} else {
					return getImageIconResize(kClass, "NoImageAvailable.jpg", width, height);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Image getScaledImage(Image image, int x, int y) {
		int height = image.getHeight(null);
		int width = image.getWidth(null);
		if (width / height < x / y) {
			return image.getScaledInstance(width * y / height, y, Image.SCALE_DEFAULT);
		} else {
			return image.getScaledInstance(x, height * x / width, Image.SCALE_DEFAULT);
		}
		// System.out.println(image.getHeight(null));
		// System.out.println(image.getWidth(null));
	}

	public static String getMeaningTest() {
		return "*  danh từ,  số nhiều as,  a's\r\n" + "- (thông tục) loại a, hạng nhất, hạng tốt nhất hạng rất tốt\r\n"
				+ "=his health is a+ sức khoẻ anh ta vào loại a\r\n" + "- (âm nhạc) la\r\n" + "=a sharp+ la thăng\r\n"
				+ "=a flat+ la giáng\r\n" + "- người giả định thứ nhất; trường hợp giả định thứ nhất\r\n"
				+ "=from a to z+ từ đầu đến đuôi, tường tận\r\n"
				+ "=not to know a from b+ không biết tí gì cả; một chữ bẻ đôi cũng không biết\r\n" + "*  mạo từ\r\n"
				+ "- một; một (như kiểu); một (nào đó)\r\n" + "=a very cold day+ một ngày rất lạnh\r\n"
				+ "=a dozen+ một tá\r\n" + "=a few+ một ít\r\n" + "=all of a size+ tất cả cùng một cỡ\r\n"
				+ "=a Shakespeare+ một (văn hào như kiểu) Sếch-xpia\r\n" + "=a Mr Nam+ một ông Nam (nào đó)\r\n"
				+ "- cái, con, chiếc, cuốn, người, đứa...;\r\n" + "=a cup+ cái chén\r\n" + "=a knife+ con dao\r\n"
				+ "=a son of the Party+ người con của Đảng\r\n" + "=a Vietnamese grammar+ cuốn ngữ pháp Việt Nam\r\n"
				+ "*  giới từ\r\n" + "- mỗi, mỗi một\r\n" + "=twice a week+ mỗi tuần hai lần";
	}

	public static String[] convertTimestamp(Timestamp timestamp) {
		String[] dateAndTime = new String[2];
		Date date = new Date(timestamp.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		dateAndTime[0] = sdf.format(date);
		sdf = new SimpleDateFormat("HH:mm:ss");
		dateAndTime[1] = sdf.format(date);
		return dateAndTime;

	}

	public static void playAudio(String filePath, String wordOrPhrase)
			throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		AudioInputStream audioInputStream;
		try {
			if (!playAudioFromGgTranslate(wordOrPhrase)) {
				Clip clip;
				try {
					if (filePath != null) {
						File file = new File(filePath);
						if (file.exists()) {
							audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
							// create clip reference
							clip = AudioSystem.getClip();
							// open audioInputStream to the clip
							clip.open(audioInputStream);
							clip.start();
						} else {
							playAudioTTS(wordOrPhrase);
						}
					} else {
						playAudioTTS(wordOrPhrase);
					}
				} catch (Exception e) {
					System.out.println("Error with playing sound.");
					e.printStackTrace();
				}
			}
		} catch (JavaLayerException e1) {
			e1.printStackTrace();
		}

	}

	public static void playAudioTTS(String word)
			throws EngineException, AudioException, EngineStateError, IllegalArgumentException, InterruptedException {
		// Set property as Kevin Dictionary
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
		// Register Engine
		Central.registerEngineCentral("com.sun.speech.freetts" + ".jsapi.FreeTTSEngineCentral");
		// Create a Synthesizer
		Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
		// Allocate synthesizer
		synthesizer.allocate();
		// Resume Synthesizer
		synthesizer.resume();
		// Speaks the given text
		// until the queue is empty.
		synthesizer.speakPlainText(word, null);
		synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
		// Deallocate the Synthesizer.
//		synthesizer.deallocate();
	}

	public static boolean playAudioFromGgTranslate(String word) throws JavaLayerException {
		try {
			word = java.net.URLEncoder.encode(word, "UTF-8");
			// URL url = new URL("http://translate.google.com/translate_tts?tl=en&q=" +
			// word);
			URL url = new URL("https://translate.google.com/translate_tts?ie=UTF-8&tl=en-US&client=tw-ob&q=" + word);
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			urlConn.addRequestProperty("User-Agent", "Mozilla/4.76");
			InputStream audioSrc = urlConn.getInputStream();
			if (urlConn.getResponseCode() != 200) {
				return false;
			}
			// DataInputStream read = new DataInputStream(audioSrc);
			// OutputStream outstream = new FileOutputStream(new File("D:\\mysound.mp3"));
			// byte[] buffer = new byte[1024];
			// int len;
//		while ((len = read.read(buffer)) > 0) {
//			//System.out.println(len);
//			outstream.write(buffer, 0, len);
//		}
			new Player(audioSrc).play();
//		//outstream.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

//	public static void main(String[] args) {
//		Util1 util1 = new Util1();
//
//		WordDAO wordDAO = new WordDAO();
//		List<WordModel> list = new ArrayList<WordModel>();
//		// list = wordDAO.getAllWord();
//		// System.out.println(list.get(108853).getId());
//
//		System.out.println(wordDAO.findWordById((long)1).getMeaning());
//
////		WordModel wordModel = new WordModel();
////		wordModel = list.get(108855);
////		wordModel.setHashTags("dkm");
////		wordDAO.update(wordModel);
////		wordDAO.saveWord(list.get(0));
//	}
}

package util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.Icon;
import javax.swing.ImageIcon;

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
					Image newImg = myPicture.getScaledInstance(width, height, Image.SCALE_DEFAULT);
					return new ImageIcon(newImg);
				}
			}
			try {
				URL img = new URL(pathFile);
				ImageIcon imageIcon = new ImageIcon(img);
				Image image = imageIcon.getImage();
				image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
				return new ImageIcon(image);
			} catch (MalformedURLException exception) {
				if (keyWord != null) {
					String url = UrlImage.getUrlImage(keyWord);
					if (url != null) {
						URL img = new URL(url);
						ImageIcon imageIcon = new ImageIcon(img);
						Image image = imageIcon.getImage();
						image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
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
					// Set property as Kevin Dictionary
					System.setProperty("freetts.voices",
							"com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
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
					synthesizer.speakPlainText(wordOrPhrase, null);
					synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
					// Deallocate the Synthesizer.
//					synthesizer.deallocate();
				}
			} else {
				// Set property as Kevin Dictionary
				System.setProperty("freetts.voices",
						"com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");
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
				synthesizer.speakPlainText(wordOrPhrase, null);
				synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
				// Deallocate the Synthesizer.
//				synthesizer.deallocate();
			}
		} catch (Exception e) {
			System.out.println("Error with playing sound.");
			e.printStackTrace();
		}

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

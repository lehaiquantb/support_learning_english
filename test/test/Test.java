package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.lang.model.type.NullType;

import InsertDataToDatabase.FilterDataFromDictFile;

public class Test {

	public Test() {

	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

//		WordDAO wordDAO = new WordDAO();
//		WordModel wordModel = new WordModel();
//		wordModel.setWordOrPhrase("Quan2");
//		wordModel.setHashTags("tag");
//		wordModel.setMeaning("mean");
//		wordModel.setPathOfAudioFile("...");
//		wordModel.setPathOfImageFile("..w");
//		wordModel.setPronounce("sa");
//		wordModel.setSuggest("suuu");
//		wordDAO.saveWord(wordModel);

//		JPanel middlePanel = new JPanel();
//		// middlePanel.setBorder ( new TitledBorder ( new EtchedBorder (), "Display
//		// Area" ) );
//
//		// create the middle panel components
//
//		JTextArea display = new JTextArea(10, 9);
//		display.setText(Util1.getMeaningTest());
//		display.setEditable(false); // set textArea non-editable
//		JScrollPane scroll = new JScrollPane(display);
//		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//
//		// Add Textarea in to middle panel
//		middlePanel.add(scroll);
//
//		// My code
//		JFrame frame = new JFrame();
//		frame.add(middlePanel);
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);

		PrintWriter printWriter = new PrintWriter("newfile.txt", "UTF-8");
		FilterDataFromDictFile filter = new FilterDataFromDictFile();
		filter.run();
		int count = 0;
		int total = 0;
		int size = filter.totalWords;
//		for (int i = 0; i < size; i++) {
//			File file = new File(
//					"D:\\Study\\20191\\OOP\\data\\pronounceTTS_wav1\\" + filter.getWordOrPhrase(i) + ".wav");
//			if (file.exists()) {
//				count++;
//			}
//		}
//		System.out.println(count);

		HashMap<String, NullType> hashMap = new HashMap<String, NullType>();

		File dir = new File("D:\\Study\\20191\\OOP\\data\\pronounce_wav");
		if (dir.isDirectory()) {
			for (final File f : dir.listFiles()) {
				try {
					String fName = f.getName();
					fName = fName.substring(0, fName.length() - 4);
					hashMap.put(fName, null);
					total++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		for (int i = 0; i < size; i++) {
			if (!hashMap.containsKey(filter.getWordOrPhrase(i))) {
				printWriter.println(filter.getWordOrPhrase(i));
				count++;
			}
		}
		printWriter.close();
		System.out.println("count word in newfile.txt = " + count);
		System.out.println("Total file in dir = " + total);

	}

}

package model;

import util.JTextFieldBox;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.impl.WordDAO;

/**
 * @author quan.lh173316
 *
 */
public class DataModel {

	private WordDAO wordDAO;
	private int totalWordModel;
	private int totalWordModelByMode;
	private List<JTextFieldBox> oldListBoxs;
	private List<JTextFieldBox> listEmptyBoxs;
	private WordModel currentWordModel;
	private List<JTextFieldBox> listBoxs;
	private List<WordModel> listAllWordModels;
	private List<WordModel> listWordModelsByMode;
	private String matchWord;
	private String matchTag;
	private WordModel randomWordModel;
	private HashMap<String, WordModel> wordMapWordModel;
	private HashMap<String, List<WordModel>> tagMapListWordModel;
	private SortedSet<WordModel> sortedSetWordModels;

	/**
	 * @param mutil tag
	 */
	public void setListWordModelsByModeTags(String... tags) {
		listWordModelsByMode = new ArrayList<WordModel>();
		for (String tag : tags) {
			if (tagMapListWordModel.containsKey(tag)) {
				this.listWordModelsByMode.addAll(tagMapListWordModel.get(tag));
			}
		}
		setTotalWordModelByMode();
	}

	public void setListWordModelsByDefault() {
		this.listWordModelsByMode = this.listAllWordModels;
		setTotalWordModelByMode();
	}

	public void setListWordModelsByModeTime(Timestamp dateFrom, Timestamp dateTo) {
		Timestamp first = this.sortedSetWordModels.first().getDateAdded();
		Timestamp last = this.sortedSetWordModels.last().getDateAdded();
		// System.out.println("First = " + first.getTime());
		// System.out.println("Last = " + last.getTime());
		if (dateFrom.compareTo(last) > 0 || dateTo.compareTo(first) <= 0) {
			this.listWordModelsByMode.clear();
		} else {
			SortedSet<WordModel> newSet;
			WordModel wordModelFrom = new WordModel();
			wordModelFrom.setDateAdded(dateFrom);
			WordModel wordModelTo = new WordModel();
			wordModelTo.setDateAdded(dateTo);
			// System.out.println("Begin = " + wordModelFrom.getDateAdded().getTime());
			// System.out.println("End = " + wordModelTo.getDateAdded().getTime());
			newSet = this.sortedSetWordModels.subSet(wordModelFrom, wordModelTo);
			this.listWordModelsByMode = new ArrayList<WordModel>(newSet);
			// System.out.println(newSet.size());
		}
//			SortedSet<WordModel> newSet = new TreeSet<WordModel>();
//			WordModel wordModelFrom = new WordModel();
//			wordModelFrom.setDateAdded(dateFrom);
//			WordModel wordModelTo = new WordModel();
//			wordModelTo.setDateAdded(dateTo);
//			WordModel wordModelToCompare = new WordModel();
//			dateTo.setTime(dateTo.getTime() + 1);
//			wordModelToCompare.setDateAdded(dateTo);
//
//			this.sortedSetWordModels.add(wordModelFrom);
//			this.sortedSetWordModels.add(wordModelToCompare);
//			System.out.println("First: " + this.sortedSetWordModels.first().getDateAdded().getTime());
//			System.out.println("Last: " + this.sortedSetWordModels.last().getDateAdded().getTime());
//			this.sortedSetWordModels = this.sortedSetWordModels.subSet(wordModelFrom, wordModelTo);
		setTotalWordModelByMode();
	}

	public void setTotalWordModelByMode() {
		this.totalWordModelByMode = this.listWordModelsByMode.size();
	}

	public void setSortedSetWordModels() {
		this.sortedSetWordModels = new TreeSet<WordModel>(this.listAllWordModels);
	}

	/**
	 * @set MatchWord to match regex and show results of searching
	 */
	public void setMatchWord() {
		StringBuilder matchWord = new StringBuilder();
		// StringBuilder is faster for concat String
		for (WordModel wordModel : listAllWordModels) {
			matchWord = matchWord.append("@").append(wordModel.getWordOrPhrase());
		}
		this.matchWord = matchWord.toString();
	}

	/**
	 * @set MatchTag to match regex and show results of searching
	 */
	public HashMap<String, List<WordModel>> setMatchTag() {
		HashMap<String, List<WordModel>> hashMap = new HashMap<String, List<WordModel>>();
		Pattern pattern = Pattern.compile("[\\S]+");
		StringBuilder matchTag = new StringBuilder();
		for (WordModel wordModel : listAllWordModels) {
			if (wordModel.getHashTags() == null) {
				continue;
			}
			Matcher matcher = pattern.matcher(wordModel.getHashTags());
			while (matcher.find()) {
				if (hashMap.containsKey(matcher.group())) {
					hashMap.get(matcher.group()).add(wordModel);
				} else {
					LinkedList<WordModel> linkedList = new LinkedList<WordModel>();
					linkedList.add(wordModel);
					hashMap.put(matcher.group(), linkedList);
				}
			}
		}

		for (String tag : hashMap.keySet()) {
			matchTag.append(tag).append(" ");
		}
		this.matchTag = matchTag.toString();
		return hashMap;
	}

	/**
	 * set Random WordModel from listWordModelsByMode
	 */
	public void setRandomWordModel() {
		int size = listWordModelsByMode.size();
		Random rand = new Random();
		this.randomWordModel = listWordModelsByMode.get(rand.nextInt(size));
	}

	/**
	 * set WordMapWordModel to search WordModel from WordOrPhrase
	 */
	public void setWordMapWordModel() {
		wordMapWordModel = new HashMap<String, WordModel>();
		for (WordModel wordModel : listAllWordModels) {
			wordMapWordModel.put(wordModel.getWordOrPhrase(), wordModel);
		}
	}

	/**
	 * set WordMapWordModel to search WordModel from Tag
	 */
	public void setTagMapListWordModel() {
		HashMap<String, List<WordModel>> hashMap = this.setMatchTag();
		for (String tag : hashMap.keySet()) {
			hashMap.replace(tag, new ArrayList<WordModel>(hashMap.get(tag)));
		}
//		for (WordModel wordModel : hashMap.get("#length1")) {
//			System.out.println(wordModel.getWordOrPhrase());
//		}
		this.tagMapListWordModel = hashMap;
	}

	/**
	 * get data from database and set for listAllWordModels
	 */
	public void setListAllWordModels() throws SQLException {
		this.wordDAO = new WordDAO(this);
		listAllWordModels = new ArrayList<WordModel>();
		listAllWordModels = wordDAO.getAllWord();
		int size = listAllWordModels.size();
		if (!wordDAO.databaseIsExist) {
			for (int i = 0; i < size; i++) {
				listAllWordModels.get(i).setIndexOfListWords(i);
			}
		}
		this.setTotalWordModel(listAllWordModels.size());
	}

	/**
	 * @return the randomWordModel
	 */
	public WordModel getRandomWordModel() {
		setRandomWordModel();
		setListBoxs(this.randomWordModel.getWordOrPhrase());
		setCurrentWordModel(randomWordModel);
		return randomWordModel;
	}

	public List<JTextFieldBox> getListBoxs() {
		return listBoxs;
	}

	public void setTempListBoxs(List<JTextFieldBox> jTextFieldBoxs) {
		this.listBoxs = jTextFieldBoxs;
	}

	public void setOldListBoxs() {
		this.listBoxs = this.oldListBoxs;
	}

	public void setListBoxs(String wordOrPhrase) {
		List<JTextFieldBox> listBoxs = new ArrayList<JTextFieldBox>();
		List<JTextFieldBox> listEmptyBoxs = new ArrayList<JTextFieldBox>();
		int length = wordOrPhrase.length();
		JTextFieldBox textBox = new JTextFieldBox(wordOrPhrase.charAt(0));
		listBoxs.add(textBox);
		for (int i = 1; i < length - 1; i++) {
			if (wordOrPhrase.charAt(i) == ' ') {
				listBoxs.add(new JTextFieldBox());
			} else {
				listBoxs.add(new JTextFieldBox("Enter here"));
			}
		}
		listBoxs.add(new JTextFieldBox(wordOrPhrase.charAt(length - 1)));

		for (int i = 0; i < length; i++) {
			listEmptyBoxs.add(new JTextFieldBox("Enter here"));
		}

		this.oldListBoxs = listBoxs;
		this.listEmptyBoxs = listEmptyBoxs;
		this.listBoxs = listBoxs;
	}

	public List<JTextFieldBox> getListEmptyBoxs() {
		return listEmptyBoxs;
	}

	/**
	 * @return the wordMapWordModel
	 */
	public WordModel getWordModelByMapWord(String wordOrPhrase) {
		return wordMapWordModel.get(wordOrPhrase);
	}

	/**
	 * @return the tagMapListWordModel
	 */
	public HashMap<String, List<WordModel>> getTagMapListWordModel() {
		return tagMapListWordModel;
	}

	/**
	 * @return the matchWord
	 */
	public String getMatchWord() {
		return matchWord;
	}

	/**
	 * @return the matchTag
	 */
	public String getMatchTag() {
		return matchTag;
	}

	/**
	 * @return the listWordModelsByMode
	 */
	public List<WordModel> getListWordModelsByMode() {
		return listWordModelsByMode;
	}

	/**
	 * @return the listAllWordModels
	 */
	public List<WordModel> getListAllWordModels() {
		return this.listAllWordModels;
	}

	public DataModel() throws SQLException {
		setListAllWordModels();
		setSortedSetWordModels();
		setWordMapWordModel();
		setMatchWord();
		setTagMapListWordModel();
		// setListWordModelsByModeTags("#length1", "#length27");
		setListWordModelsByDefault();
	}

	public void update() {
		if (wordDAO.databaseIsExist) {
			this.listAllWordModels = wordDAO.getAllWord();
		}
		this.setSortedSetWordModels();
		this.setTotalWordModel(listAllWordModels.size());
		HashMap<String, WordModel> newWordMapWordModel = new HashMap<String, WordModel>();
		int i = 0;
		for (WordModel wordModel : listAllWordModels) {
			wordModel.setIndexOfListWords(i);
			i++;
			newWordMapWordModel.put(wordModel.getWordOrPhrase(), wordModel);
		}
		this.wordMapWordModel = newWordMapWordModel;
		this.setMatchWord();
		this.setTagMapListWordModel();
		this.setListWordModelsByDefault();
	}

	public void updateJsonFile() {

	}

	/**
	 * @return the currentWordModel
	 */
	public WordModel getCurrentWordModel() {
		return currentWordModel;
	}

	/**
	 * @param currentWordModel the currentWordModel to set
	 */
	public void setCurrentWordModel(WordModel currentWordModel) {
		this.currentWordModel = currentWordModel;
	}

	public int getTotalWordModel() {
		return totalWordModel;
	}

	public void setTotalWordModel(int totalWordModel) {
		this.totalWordModel = totalWordModel;
	}

	public void test() {
		System.out.println(getMatchTag());
		// System.out.print(dataModel.listAllWordModels.get(0).getDateAdded());
		// System.out.println(getDataModel.getListWordModelsByMode().get(30).getWordOrPhrase());
		System.out.println(randomWordModel.getWordOrPhrase());
		System.out.println(randomWordModel.getPronounce());
		System.out.println(randomWordModel.getMeaning());
	}
//	public static void main(String[] args) throws SQLException {
//		DataModel dataModel = new DataModel();
//		dataModel.test();
//	}

	public int getTotalWordModelByMode() {
		return totalWordModelByMode;
	}

	public WordDAO getWordDAO() {
		return wordDAO;
	}

}

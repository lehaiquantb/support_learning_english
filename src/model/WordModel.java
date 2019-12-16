package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author quan.lh173316
 *
 */
public class WordModel implements Comparable<WordModel> {

	private Long id;
	private String wordOrPhrase;
	private String pronounce;
	private HashMap<String, String> typeOfWord;
	private String meaning;
	private String suggest;
	private String pathOfImageFile;
	private String pathOfAudioFile;
	private Timestamp dateAdded;
	private ArrayList<String> listTag;
	private String hashTags;

	/**
	 * @return the wordOrPhrase
	 */
	public String getWordOrPhrase() {
		return wordOrPhrase;
	}

	/**
	 * @param wordOrPhrase the wordOrPhrase to set
	 */
	public void setWordOrPhrase(String wordOrPhrase) {
		this.wordOrPhrase = wordOrPhrase;
	}

	/**
	 * @return the pronounce
	 */
	public String getPronounce() {
		return pronounce;
	}

	/**
	 * @param pronounce the pronounce to set
	 */
	public void setPronounce(String pronounce) {
		this.pronounce = pronounce;
	}

	/**
	 * @return the typeOfWord
	 */
	public HashMap<String, String> getTypeOfWord() {
		return typeOfWord;
	}

	/**
	 * @param typeOfWord the typeOfWord to set
	 */
	public void setTypeOfWord(HashMap<String, String> typeOfWord) {
		this.typeOfWord = typeOfWord;
	}

	/**
	 * @return the meaning
	 */
	public String getMeaning() {
		return meaning;
	}

	/**
	 * @param meaning the meaning to set
	 */
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	/**
	 * @return the suggest
	 */
	public String getSuggest() {
		return suggest;
	}

	/**
	 * @param suggest the suggest to set
	 */
	public void setSuggest(String suggest) {
		this.suggest = suggest;
	}

	/**
	 * @return the pathOfImageFile
	 */
	public String getPathOfImageFile() {
		return pathOfImageFile;
	}

	/**
	 * @param pathOfImageFile the pathOfImageFile to set
	 */
	public void setPathOfImageFile(String pathOfImageFile) {
		this.pathOfImageFile = pathOfImageFile;
	}

	/**
	 * @return the pathOfAudioFile
	 */
	public String getPathOfAudioFile() {
		return pathOfAudioFile;
	}

	/**
	 * @param pathOfAudioFile the pathOfAudioFile to set
	 */
	public void setPathOfAudioFile(String pathOfAudioFile) {
		this.pathOfAudioFile = pathOfAudioFile;
	}

	/**
	 * @return the dateAdded
	 */
	public Timestamp getDateAdded() {
		return dateAdded;
	}

	/**
	 * @param dateAdded the dateAdded to set
	 */
	public void setDateAdded(Timestamp dateAdded) {
		this.dateAdded = dateAdded;
	}

	/**
	 * @return the listTag
	 */
	public ArrayList<String> getListTag() {
		return listTag;
	}

	/**
	 * @param listTag the listTag to set
	 */
	public void setListTag(ArrayList<String> listTag) {
		this.listTag = listTag;
	}

	/**
	 * @return the hashTags
	 */
	public String getHashTags() {
		return hashTags;
	}

	/**
	 * @param hashTags the hashTags to set
	 */
	public void setHashTags(String hashTags) {
		this.hashTags = hashTags;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(WordModel o) {
		if (this.getDateAdded().compareTo(o.getDateAdded()) == 0) {
			return 1;
		} else
			return this.getDateAdded().compareTo(o.getDateAdded());
	}

}

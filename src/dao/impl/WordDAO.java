package dao.impl;

import java.util.List;

import dao.IWordDAO;
import mapper.WordMapper;
import model.WordModel;

/**
 * @author quan.lh173316
 * @param <T>
 *
 */
public class WordDAO extends AbstractDAO<WordModel> implements IWordDAO {

	@Override
	public List<WordModel> getAllWord() {
		String sql = "SELECT * FROM word";
		return query(sql, new WordMapper());
	}

	@Override
	public Long saveWord(WordModel wordModel) {
		System.out.println(wordModel.getWordOrPhrase());
		String sql = "INSERT INTO word(word_or_phrase, pronounce, meaning, suggest, path_of_image_file,	path_of_audio_file,	hash_tags)"
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, wordModel.getWordOrPhrase(), wordModel.getPronounce(), wordModel.getMeaning(),
				wordModel.getSuggest(), wordModel.getPathOfImageFile(), wordModel.getPathOfAudioFile(),
				wordModel.getHashTags());
	}

	@Override
	public void update(WordModel wordModel) {
		String sql = "UPDATE word SET word_or_phrase = ?, pronounce = ?, meaning = ? , suggest = ?, path_of_image_file = ?,"
				+ "path_of_audio_file = ?, hash_tags = ? WHERE id = ?";
		update(sql, wordModel.getWordOrPhrase(), wordModel.getPronounce(), wordModel.getMeaning(),
				wordModel.getSuggest(), wordModel.getPathOfImageFile(), wordModel.getPathOfAudioFile(),
				wordModel.getHashTags(), wordModel.getId());
	}

	@Override
	public void delete(Long id) {
		String sql = "DELETE FROM word WHERE id = ?";
		update(sql, id);
	}

	@Override
	public WordModel findWordById(Long id) {
		String sql = "SELECT * FROM word WHERE id = ?";
		return query(sql, new WordMapper(), id).get(0);
	}

}

package dao;

import java.util.List;

import model.WordModel;

/**
 * @author quan.lh173316
 *
 */
public interface IWordDAO extends GenericDAO<WordModel> {
	List<WordModel> getAllWord();
	Long saveWord(WordModel wordModel);
	void update(WordModel wordModel);
	void delete(Long id);
	WordModel findWordById(Long id);
}

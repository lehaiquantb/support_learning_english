package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.WordModel;

/**
 * @author quan.lh173316
 * @param <T>
 *
 */
public class WordMapper implements RowMapper<WordModel> {

	@Override
	public WordModel mapRow(ResultSet rs) {
		try {
			WordModel wordModel = new WordModel();
			wordModel.setWordOrPhrase(rs.getString("word_or_phrase"));
			wordModel.setPronounce(rs.getString("pronounce"));
			wordModel.setMeaning(rs.getString("meaning"));
			wordModel.setSuggest(rs.getString("suggest"));
			wordModel.setPathOfImageFile(rs.getString("path_of_image_file"));
			wordModel.setPathOfAudioFile(rs.getString("path_of_audio_file"));
			wordModel.setHashTags(rs.getString("hash_tags"));
			wordModel.setDateAdded(rs.getTimestamp("date_added"));
			wordModel.setId(rs.getLong("id"));
			return wordModel;
		} catch (SQLException e) {
			return null;
		}

	}

}

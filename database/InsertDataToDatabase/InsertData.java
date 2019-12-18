package InsertDataToDatabase;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.impl.AbstractDAO;
import model.WordModel;

public class InsertData {

	public InsertData() {

	}

	public void run() throws FileNotFoundException, UnsupportedEncodingException {
		FilterDataFromDictFile filter = new FilterDataFromDictFile();
		filter.run();
		filter.filter();
		filter.setTagAndPathFile("", "", "#tag");

//		int size = filter.listWords.size();
//		System.out.println(size);
//		System.out.println(filter.listWords.get(0).getPathOfAudioFile());
//		for(WordModel string : filter.listWords) {
//			if (string.getWordOrPhrase() == null) {
//				System.out.println("Have null value of wordOrPhrase");
//			}
//		}

		String query = "INSERT INTO word( word_or_phrase, pronounce, meaning, suggest, path_of_image_file,	path_of_audio_file,	hash_tags) VALUES(?, ?, ?, ?, ?, ?, ?)";
		ArrayList<WordModel> temp = filter.listWords;
		int size = temp.size();
		int count = 0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = new AbstractDAO<>().getConnection();
			connection.setAutoCommit(false);
			for (int i = 0; i < size; i++) {
				String wordOrPhrase = temp.get(i).getWordOrPhrase();
				String pronounce = temp.get(i).getPronounce();
				String meaning = temp.get(i).getMeaning();
				String suggest = "Độ dài của từ(cụm từ): " + wordOrPhrase.length();
				String pathImg = temp.get(i).getPathOfImageFile();
				// String pathImg = "D:\\Study\\20191\\OOP\\data\\ImageData\\" + wordOrPhrase;
				String pathAudio = "D:\\Study\\20191\\OOP\\data\\pronounce_wav\\" + wordOrPhrase + ".wav";
				String tags = "#tag #length" + wordOrPhrase.length();

//				System.out.println("\n" + wordOrPhrase + "\n" + pronounce + "\n" + meaning + "\n" + suggest + "\n" + pathImg
//						+ "\n" + pathAudio + "\n" + tags);
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, wordOrPhrase);
				preparedStatement.setString(2, pronounce);
				preparedStatement.setString(3, meaning);
				preparedStatement.setString(4, suggest);
				preparedStatement.setString(5, pathImg);
				preparedStatement.setString(6, pathAudio);
				preparedStatement.setString(7, tags);
				preparedStatement.executeUpdate();
				count++;
			}
			connection.commit();
		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		System.out.println("Total words have been added to the database = " + count);
//		String queryString = "INSERT INTO word(word_or_phrase)"+"VALUES(N'quân')";
//		int i = 8;
//		ResultSet resultSet = statement.executeQuery("Select * from word");
//		resultSet.next();
//		System.out.println(resultSet.getString(4));

//		Timestamp timestamp = rSet.getTimestamp(i);
//		Date date = new Date(timestamp.getTime());
//		System.out.println(date);
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		InsertData insertData = new InsertData();
		//insertData.run();
	}

}

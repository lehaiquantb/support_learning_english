USE SupportLearningEnglish
GO
DROP TABLE IF EXISTS word

CREATE TABLE word(
	id BIGINT IDENTITY(1,1) PRIMARY KEY,
	word_or_phrase char(100) not null,	--87
	pronounce nvarchar(150),			--218
	meaning nvarchar(4000),				--11428
	suggest nvarchar(2000),
	path_of_image_file nchar(150),		--68+?
	path_of_audio_file nchar(150),		--68+?
	date_added datetime default CURRENT_TIMESTAMP,
	hash_tags nvarchar(200)				--?
);
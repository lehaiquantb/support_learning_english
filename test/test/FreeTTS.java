/**
 * Copyright 2003 Sun Microsystems, Inc.
 * 
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL 
 * WARRANTIES.
 */
package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.speech.EngineException;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

import InsertDataToDatabase.FilterDataFromDictFile;

/**
 * Simple program to demonstrate the use of the FreeTTS speech synthesizer. This
 * simple program shows how to use FreeTTS without requiring the Java Speech API
 * (JSAPI).
 */
public class FreeTTS {

	/**
	 * Example of how to list all the known voices.
	 * 
	 * @throws EngineException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */

	public static void main(String[] args) throws EngineException, FileNotFoundException, UnsupportedEncodingException {
		FreeTTS freeTTS = new FreeTTS();
		freeTTS.createWavFileFromText();
	}

	public void createWavFileFromText() throws FileNotFoundException, UnsupportedEncodingException {
		FilterDataFromDictFile filter = new FilterDataFromDictFile();
		filter.run();

		// listAllVoices();
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

		AudioPlayer audioPlayer = null;
		String voiceName = "kevin16";

		System.out.println();
		System.out.println("Using voice: " + voiceName);
		/*
		 * The VoiceManager manages all the voices for FreeTTS.
		 */
		VoiceManager voiceManager = VoiceManager.getInstance();

		Voice voice = voiceManager.getVoice(voiceName);
		if (voice == null) {
			System.err.println("Cannot find a voice named " + voiceName + ".  Please specify a different voice.");
			System.exit(1);
		}
		/*
		 * Allocates the resources for the voice.
		 */
		voice.allocate();
		/*
		 * Synthesize speech.
		 */

		Scanner sc = null;
		File file = new File("newfile.txt");
		String q;
		ArrayList<String> list = new ArrayList<String>();
		try {
			sc = new Scanner(new BufferedReader(new FileReader(file)));
			sc.useDelimiter("\r\n");
			while (sc.hasNext()) {
				q = sc.next();
				list.add(q);
				System.out.println(q);
			}
			System.out.println("Total word : " + list.size());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		for (String string : list) { // 108856
			// create a audioplayer to dump the output file
			try {
				audioPlayer = new SingleFileAudioPlayer("D:\\Study\\20191\\OOP\\data\\pronounceTTS_wav3\\" + string,
						Type.WAVE);
				// attach the audioplayer
				voice.setAudioPlayer(audioPlayer);
				voice.speak(string);
				/*
				 * Clean up and leave.
				 */
				// helloVoice.deallocate();
				// don't forget to close the audioplayer otherwise file will not be saved
				audioPlayer.close();
				System.out.println(string);
			} catch (Exception e) {
				continue;
			}

		}

//		for (int i = 65858; i < 108856; i++) { // 108856
//			// create a audioplayer to dump the output file
//			try {
//				audioPlayer = new SingleFileAudioPlayer(
//						"D:\\Study\\20191\\OOP\\data\\pronounceTTS_wav3\\" + filter.getWordOrPhrase(i), Type.WAVE);
//				// attach the audioplayer
//				voice.setAudioPlayer(audioPlayer);
//				voice.speak(filter.getWordOrPhrase(i));
//				/*
//				 * Clean up and leave.
//				 */
//				// helloVoice.deallocate();
//				// don't forget to close the audioplayer otherwise file will not be saved
//				audioPlayer.close();
//				System.out.println(filter.getWordOrPhrase(i));
//			} catch (Exception e) {
//				continue;
//			}
//
//		}

		System.exit(0);
	}
}
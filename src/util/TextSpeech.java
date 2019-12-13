package util;

import java.util.Locale;

import javax.sound.sampled.AudioFileFormat.Type;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;

import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

public class TextSpeech {

	public TextSpeech() {
	}

	public void play(String wordOrPhrase) {
		try {
			// Set property as Kevin Dictionary
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us" + ".cmu_us_kal.KevinVoiceDirectory");

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
			synthesizer.deallocate();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		TextSpeech textSpeech = new TextSpeech();
//		textSpeech.play("Vacancy");
//	}

}

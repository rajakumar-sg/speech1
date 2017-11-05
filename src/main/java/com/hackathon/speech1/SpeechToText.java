package com.hackathon.speech1;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.linguist.dictionary.Word;
import edu.cmu.sphinx.result.WordResult;
import edu.cmu.sphinx.util.TimeFrame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SpeechToText {
    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();

        //configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        //configuration.setAcousticModelPath("resource:/models/en-us/en-us");
        configuration.setAcousticModelPath("resource:/models/vox/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");

        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

        StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(configuration);
        InputStream stream = new FileInputStream(new File("woman1_converted.wav"));

        recognizer.startRecognition(stream);

        SpeechResult result;
        /*while ((result = recognizer.getResult()) != null) {
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
        }*/

        while ((result = recognizer.getResult()) != null) {
            //System.out.format("Hypothesis: %s\n", result.getHypothesis());
            for (WordResult wordResult : result.getWords()) {
                TimeFrame timeFrame = wordResult.getTimeFrame();
                Word word = wordResult.getWord();
                System.out.println("Start :" + timeFrame.getStart() +
                        ", End :" + timeFrame.getEnd() +
                        ", Duration :" + timeFrame.length() +
                        ", Word :" + word.getSpelling());

            }
        }

        recognizer.stopRecognition();
    }
}

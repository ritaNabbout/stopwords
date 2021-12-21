/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stopwordsfilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StopWordsFilter {

    public static void main(String[] args) throws IOException {

        File stopWordsFile = new File("resources/inforet/stopwords.txt");
        List<String> stopWords = StopWordsUtilities.readStopWordsFile(stopWordsFile);

        File inputFiles = new File("resources/inforet/shorttexts");
        File outputSTPDirectory = new File("resources/outputSTP");

        File[] dataFiles = inputFiles.listFiles();
        for (File f : dataFiles) {
            System.out.println("File " + f.getName());
        }

//        File doc = dataFiles[0];
//        InverseDocumentFrequency ifsDoc = new InverseDocumentFrequency(doc);
//        System.out.println("Frequencies: ");
//        ifsDoc.getWordsFrequency().forEach((k, v)
//                -> System.out.format("key: %s, value: %d%n", k, v)
//        );
//        
//        for (File file : dataFiles) {
//            List<String> filteredText = StopWordsUtilities.fileToListWithoutStopWords(file, stopWords);
//            StopWordsUtilities.printToFile(filteredText,
//                    outputSTPDirectory.getPath() + "/" + file.getName());
//        }

        System.out.println("--------------------- Project 1  STOP WORDS ---------------------------");
        // File inputFiles2 = new File("C:/Users/user/Desktop/outputSTP");
        File outputSFXDirectory = new File("resources/outputSFX");
        File[] stpFiles = outputSTPDirectory.listFiles();

        for (File file : stpFiles) {
            //   System.out.println(" 2 "+file.getName());
            List<String> tempList = StopWordsUtilities.fileToList("resources/outputSTP/" + file.getName());

            List<String> sfxResult = completeStem(tempList);
            StopWordsUtilities.printToFile(sfxResult,
                    outputSFXDirectory.getPath() + "/" + file.getName());
        }

        System.out.println("-------------------------- Project 2 Stemmer---------------------------");
        /*
        if (inputFileOrDirectory != null && inputFileOrDirectory.exists()) {
            if (inputFileOrDirectory.isDirectory() && inputFileOrDirectory.listFiles().length > 0) {
            	
                for (File inputFile : inputFileOrDirectory.listFiles()) {
                    List<String> filteredText = StopWordsUtilities.fileToListWithoutStopWords(inputFile, stopWords);
                    StopWordsUtilities.printToFile(filteredText,
                            outputSTPDirectory.getPath() + "/filtered_" + inputFile.getName());
                }
            } else {
                List<String> filteredText = StopWordsUtilities.fileToListWithoutStopWords(inputFileOrDirectory, stopWords);
                StopWordsUtilities.printToFile(filteredText,
                        outputSTPDirectory.getPath() + "/filtered_" + inputFileOrDirectory.getName());
            }
        }
         */

    }

    public static List<String> completeStem(List<String> tokens1) {
        //Porter Algorithm
        Stemmer pa = new Stemmer();
        List<String> arrString = new ArrayList<>();
        for (String i : tokens1) {
            String s1 = pa.step1(i);
            String s2 = pa.step2(s1);
            String s3 = pa.step3(s2);
            String s4 = pa.step4(s3);
            String s5 = pa.step5(s4);
            arrString.add(s5);
        }

        return arrString;
    }
}

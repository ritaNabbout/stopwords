/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author
 */
public class InverseDocumentFrequency {

    private List<FileWordFrequency> fileWordFrequencies;
    private Set<String> wordsSet;

    public InverseDocumentFrequency(List<File> documents) {
        fileWordFrequencies = new ArrayList<>();
        wordsSet = new HashSet<>();
        
        if (documents != null && !documents.isEmpty()) {
            initiateWordsSet(documents);
            documents.forEach(document -> {
                wordsSet.stream().forEach(word
                        -> fileWordFrequencies.add(new FileWordFrequency(document, word)));
            });
        }
    }

    public InverseDocumentFrequency(String documentsPath) {
        this(Arrays.asList(new File(documentsPath).listFiles()));
    }

    private void initiateWordsSet(List<File> documents) {
        documents.stream().forEach(file -> {
            try {
                wordsSet.addAll(getWordsSetFromFile(file));
            } catch (FileNotFoundException ex) {
                System.err.println("ERROR! - " + ex.getMessage());
            }
        });
    }

    private Set<String> getWordsSetFromFile(File file) throws FileNotFoundException {
        Set<String> words = new HashSet<>();
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            words.add(sc.next());
        }

        return words;
    }

    private int getWordFrequencyInFile(String word, File file) {
        return fileWordFrequencies.stream()
                .filter(fwf -> (fwf.getFileName().equals(file.getName())
                && fwf.getWord().equals(word)))
                .findFirst()
                .map(FileWordFrequency::getFrequency)
                .orElse(0);
    }
    
    public int getWordFrequency(String word) {
        int wordFrequency = fileWordFrequencies.stream()
                .map(fwf -> getWordFrequencyInFile(word, fwf.getFile()))
                .reduce(0, Integer::sum);
        return wordFrequency;
    }

    public List<FileWordFrequency> getFileWordFrequencies() {
        return fileWordFrequencies;
    }
    
    public List<File> getFiles() {
        if (fileWordFrequencies != null && !fileWordFrequencies.isEmpty()) {
            Set<File> filesSet = fileWordFrequencies.stream()
                    .map(fwf -> fwf.getFile()).collect(Collectors.toSet());
            return new ArrayList<>(filesSet);
        } else {
            return null;
        }
    }
    
}

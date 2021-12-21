/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author gh_ma
 */
public class FileWordFrequency {
    private File file;
    private String word;
    private int frequency;
    
    public FileWordFrequency(String filePath, String word) {
        this(new File(filePath), word);
    }
    
    public FileWordFrequency(File file, String word) {
        this.file = file;
        this.word = word;
        initiateFrequency();
    }
    
    private void initiateFrequency() {
        try {
            Scanner scanner = new Scanner(this.file);
            while(scanner.hasNext()) {
                if (word.equalsIgnoreCase(scanner.next())) {
                    frequency++;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("ERROR! - " + ex.getMessage());
        }
    }
    
    
    public File getFile() {
        return file;
    }

    public String getFileName() {
        return file.getName();
    }
    
    public String getWord() {
        return word;
    }

    public int getFrequency() {
        return frequency;
    }
    
    public boolean wordExists() {
        return getFrequency() > 0;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idf;

import java.io.File;

/**
 *
 * @author gh_ma
 */
public class CalculateIDF {
    public static void main(String[] args) {
       File outputSTPDirectory = new File("resources/outputSTP");
       
       InverseDocumentFrequency idf = new InverseDocumentFrequency("resources/outputSTP");
       idf.getFileWordFrequencies().stream()
               .forEach(fwf -> {
                   System.out.format("<%s, %s> -> %d\n", 
                           fwf.getFileName(), 
                           fwf.getWord(),
                           fwf.getFrequency());
               });
    }
}

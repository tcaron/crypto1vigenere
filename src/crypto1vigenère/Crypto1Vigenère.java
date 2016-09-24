/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypto1vigenère;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author thomas
 */
public class Crypto1Vigenère {

    public String test ="";
    public String loadFile() {
        
        try {

            FileReader fr = new FileReader("text.txt");
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                
                this.test += br.readLine();
            }      
              
        } catch (IOException e) {

            System.out.println("Erreur lors de la lecture : " + e.getMessage());
        }
      return test;
    }

    //retourne le texte contenu dans le fichier
    public String getText(){
       
       
        return loadFile().toLowerCase().replaceAll("[^\\p{L}]", "");
    }
    
    public String cryptage(String chaine, String cle) {

        StringBuilder stringB = new StringBuilder();

        for (int i = 0; i < chaine.toUpperCase().length(); i++) {

            char encyphered = chaine.toUpperCase().charAt(i) + getDecalage(cle, i) > 90 ? (char) ((chaine.toUpperCase().charAt(i) + getDecalage(cle, i)) - 26) : (char) (chaine.toUpperCase().charAt(i) + getDecalage(cle, i));

            stringB.append(encyphered);

        }
        return stringB.toString();
    }

    public String decrypt(String chaine, String cle) {

        StringBuilder stringB = new StringBuilder();

        for (int i = 0; i < chaine.length(); i++) {
        char decrypter=chaine.charAt(i) - getDecalage(cle, i) < 65 ? (char) ((chaine.charAt(i) - getDecalage(cle, i)) + 26) : (char) (chaine.charAt(i) - getDecalage(cle, i));

            stringB.append(decrypter);

        }

        return stringB.toString();

    }
    private static int getDecalage(String cle, int i) {

        return ((int) cle.toUpperCase().charAt(i % cle.toUpperCase().length())) - 65;

    }
     int sum =0;
    public int getNbLettres(){
        
       
        for(int i=0; i< test.length();i++)
        {
            sum++;
        }
        
        return sum;
    }
    //compte le nombre d'occurence pour chaque lettre
    String chaineTest="aabbcdeeeeefghijfofijijfpdsfojvm";
    public int[] occurence() {
       
	int[] occ = new int[26];
        System.out.println(getText().length());
	for (int i = 0; i < sum ; i++)
        { occ[getText().charAt(i) - (int)'a']++;
          
        }
        for (int o : occ){
            System.out.println(o);
        }
       return occ;
    }
    
    public double indiceCoincidence(){
        
        int[] tabOccurence = occurence(); 
        double ic = 0.0;
        int sum = 0;
        for (int i = 0; i<tabOccurence.length;i++)
        {
           sum = sum+tabOccurence[i];     
        } 
        System.out.println(sum);
        int sumVal = sum;
        for(int j = 0; j<tabOccurence.length;j++)
        {
           ic += (double)(tabOccurence[j]*(tabOccurence[j]-1)) / (double)(sumVal*(sumVal-1));
   
        }
        System.out.println(ic);
        return ic;
    }

    
    
    

    
    public static void main(String[] args) {
        
        Crypto1Vigenère crpTest = new Crypto1Vigenère();
        crpTest.loadFile();
        crpTest.getText();
        crpTest.getNbLettres();
        crpTest.occurence();
       
    }
    
}

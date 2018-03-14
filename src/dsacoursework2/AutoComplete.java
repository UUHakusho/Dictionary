/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsacoursework2;

import dsacoursework2.*;
import static dsacoursework2.DictionaryFinder.readWordsFromCSV;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.NavigableSet;
import java.util.Scanner;

/**
 *
 * @author pdu15gsu
 */
public class AutoComplete {

        
    
    static String outputProb(String prefix, Trie trie,Map<String,Integer> map){
        StringBuilder out= new StringBuilder();
        Trie sub = trie.getSubTrie(prefix);
        double sum = 0;

        for(String s : sub.getAllWords(sub.getRoot(), "")){
            sum+=map.get(prefix + s.trim());
        }

        out .append(prefix);

        HashMap< Double, String> probMap = new HashMap();

        for(String s : sub.getAllWords(sub.getRoot(), "")){
            double prob = map.get(prefix + s.trim())/sum;
            probMap.put(prob,s);
        }

        TreeMap< Double, String> treeMap = new TreeMap<>(probMap);
        int count = 0;
        for(Double p : treeMap.descendingKeySet()){
            count++;
            if(count > 3) break;
            out.append(",").append(prefix).append(treeMap.get(p))
                    .append(",").append(String.format("%.6f", p));
        }
        return out.toString();
    }
    

    /**
     * @param args the command line arguments
     * 
     * 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        DictionaryFinder df=new DictionaryFinder();
        df.setList(readWordsFromCSV("lotr.csv"));
        df.formDictionary();
        readWordsFromCSV("lotrQueries.csv");
        HashMap<String,Integer> map = df.getMap();
        
        Trie trie = new Trie();
        for(String word : map.keySet()){
            trie.add(word);   
        }
        
        try (Scanner scan = new Scanner(new File("lotrQueries.csv"))) {
            while(scan.hasNextLine()){
                System.out.println(outputProb(scan.next(), trie,map));
            }
        }
        
        FileWriter fw = new FileWriter( "output.txt" );
        //fw.write(out);
        fw.close();
    }
    
}

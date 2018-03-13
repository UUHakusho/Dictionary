
package dsacoursework2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author ajb
 */
public class DictionaryFinder {
    private HashMap<String, Integer> map;
    ArrayList<String> list;
    
    public DictionaryFinder(){
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }
    
    public void setList(ArrayList<String> newList){
        this.list = newList;
    }
  
/**
 * Reads all the words in a comma separated text document into an Array
 * @param f 
 */   
    public static ArrayList<String> readWordsFromCSV(String file) throws FileNotFoundException {
        Scanner sc=new Scanner(new File(file));
        sc.useDelimiter(" |,");
        ArrayList<String> words=new ArrayList<>();
        String str;
        while(sc.hasNext()){
            str=sc.next();
            str=str.trim();
            str=str.toLowerCase();
            words.add(str);
        }
        return words;
    }
     public static void saveCollectionToFile(Collection<?> c,String file) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
         for(Object w: c){
            printWriter.println(w.toString());
         }
        printWriter.close();
     }
     public void formDictionary(){
         
         for(String word : list){
             Integer freq = this.getMap().get(word);
             if(freq == null){
                 this.getMap().put(word,1);
             } 
             else{
                 this.getMap().put(word, freq+1);
             }   
         }
     }
     
    @Override
     public String toString(){
         StringBuilder str = new StringBuilder();
         Map<String, Integer> treeMap = new TreeMap<>(this.getMap());
         for(Object word : treeMap.keySet()){
             str.append(word).append(" ").append(getMap().get(word));
             str.append(System.getProperty("line.separator"));
         }
         return str.toString();
     }
     
     public void saveToFile() throws FileNotFoundException, IOException{
        FileWriter fw = new FileWriter( "output.txt" );
        fw.write(this.toString());
        fw.close();
     }
         
    public static void main(String[] args) throws Exception {
        DictionaryFinder df=new DictionaryFinder();
        
        ArrayList<String> in=readWordsFromCSV("testDocument.txt");
        df.setList(in);
        
        df.formDictionary();
        System.out.println(df.toString());
        df.saveToFile();
        
       
    }

    /**
     * @return the map
     */
    public HashMap<String, Integer> getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsacoursework2;

import java.util.Arrays;

/**
 *
 * @author pdu15gsu
 */
public class Trie extends TrieNode{
    private TrieNode root;
    
    public Trie(){
        this.root = new TrieNode();
        
    }
    
    public TrieNode getRoot(){
        return root;
    }
    
    public void setRoot(TrieNode node){
        this.root = node;
    }
    
    public void add(String key){
        TrieNode node = root;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if(node.getChild(c) == null){
                node.addChild(c);
            }
            if(i==key.length()-1){
                node.setComplete(true);
            }
            node= node.getChild(c);
        }
    }
    
    public boolean contains(String key){
        TrieNode node = root;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if(node.getChild(c) == null){
                return false;
            }
            if(node.isComplete()){
                return true;
            }
            node = node.getChild(c);
        }
        return false;        
    }
    
    public String outputBreadthFirstSearch(){
        return null;
    }
    
    public Trie getSubTrie(String prefix){
        TrieNode node = root;
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(node.getChild(c) == null){
                return null;
            }
            else{
                node = node.getChild(c);
            }
            
        }
        Trie trie = new Trie();
        trie.setRoot(node);
        return trie;
    }
    
    @Override
    public String toString(){
        String newLine = System.getProperty("line.separator");
        StringBuilder str = new StringBuilder();
        str.append("ROOT NODE").append(newLine);
        str.append(root.toString()).append(newLine);
        str.append("OFFSPRING");
        for(TrieNode node : this.root.getOffspring()){
            if(node != null){
                str.append(newLine);
                str.append(node.toString());
            }      
        }
        return str.toString();
    }
    
    public static void main(String[] args) throws Exception {
        Trie trie = new Trie();
        trie.add("cheers");
        trie.add("cheese");
        trie.add("chat");
        trie.add("cat");
        trie.add("bat");
        //System.out.println(trie.toString());
        System.out.println(trie.getSubTrie("ch").toString());
        
    }
}

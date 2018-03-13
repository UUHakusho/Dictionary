/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsacoursework2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
            node.getChild(c).setParent(node);
            node = node.getChild(c);
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
        StringBuilder str = new StringBuilder();
        Queue<TrieNode> l = new LinkedList();
        l.add(this.root);
        while(!l.isEmpty()) {
                TrieNode child = l.peek().getUnvisitedChildNode();
                if(child != null) {
                        child.setVisited(true);
                        l.add(child);
                }
                else {
                        str.append(l.remove().toString());
                }
        }
        return str.toString();
    }
    
    public String outputDepthFirstSearch(){
        StringBuilder str = new StringBuilder();
        Stack<TrieNode> s = new Stack();
        s.push(this.root);
        while(!s.isEmpty()) {
                TrieNode child = s.peek().getUnvisitedChildNode();
                if(child != null) {
                        child.setVisited(true);
                        s.push(child);
                }
                else {
                        str.append(s.pop().toString());
                }
        }
        return str.toString();
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
        System.out.println("bfs: " + trie.outputBreadthFirstSearch());
        System.out.println("dfs: " + trie.outputBreadthFirstSearch());
        
    }
}

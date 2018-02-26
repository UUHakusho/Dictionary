/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsacoursework2;

import java.util.List;

/**
 *
 * @author pdu15gsu
 */
public class TrieNode {
    private char c;
    private TrieNode[] offspring;
    private TrieNode parent;
    private boolean complete;
    
    public TrieNode(){
        this.c = '\u0000';
        this.offspring = new TrieNode[26];
        this.complete = false;
        this.parent = null;
    }
    
    public TrieNode(char c, TrieNode[] list, boolean flag, TrieNode parent){
        this.c = c;
        this.offspring = list;
        this.complete = flag;
        this.parent = parent;
    }
    
    public void addChild(char nodeChar){
        int index = getIndex(nodeChar);
        TrieNode node = getChild(nodeChar);

            this.offspring[index] = new TrieNode();
            this.offspring[index].setC(nodeChar);

    }
    
    @Override
    public String toString(){
        String newLine = System.getProperty("line.separator");
        StringBuilder str = new StringBuilder();
        str.append("Current Node: ");
        if(this.c == '\u0000')
            str.append("null");
        else{
            str.append(this.c);
        }
        str.append(newLine).append("Offspring: ");
        for(TrieNode node : this.offspring){
            if(node != null){
                str.append(node.getC());                
            }
        }
        str.append(newLine);
        return str.toString();
    }
    
    /**
     * @return the offspring
     */
    public TrieNode[] getOffspring() {
        return offspring;
    }
    
    public TrieNode getChild(char c){
        return this.offspring[(int)(c)-97];
    }
    
    /**
     * @return the parent
     */
    public TrieNode getParent() {
        return parent;
    }
    
    /**
     * @param offspring the offspring to set
     */
    public void setOffspring(TrieNode[] offspring) {
        this.offspring = offspring;
    }
    
    /**
     * @param parent the parent to set
     */
    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    /**
     * @return the complete
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * @param complete the complete to set
     */
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    
    /**
     * @return the c
     */
    public char getC() {
        return c;
    }
    public static int getIndex(char c){
        return (int)(c)-97;
    }
    /**
     * @param c the c to set
     */
    public void setC(char c) {
        this.c = c;
    }
    
    
    public static void main(String[] args) throws Exception {
       TrieNode node = new TrieNode();
       node.setC('b');
       node.addChild('c');
       node.addChild('z');
       
       System.out.println(node.toString());
       
       System.out.println(node.offspring[2].toString());
    }
}

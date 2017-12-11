/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

/**
 *
 * @author Dave
 */
public class Node
{
    public int iData; // data item (key)
    public String sData; // data item
    public Node leftChild; // this node’s left child
    public Node rightChild; // this node’s right child
    public int intData=  1;
    
    public Node(String sD)
    {
       sData = sD;
    }

    public Node(int count) 
    {
        intData = count;
    }
}
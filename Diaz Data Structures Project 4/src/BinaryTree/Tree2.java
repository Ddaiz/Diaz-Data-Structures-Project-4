/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

import java.io.*;
import java.util.*; // for Stack class
public class Tree2 
{
    private Node root; // first node of tree

    public Tree2() // constructor
    { 
        root = null;
    }
    
    public Node find(String key) // find node with given key
    {   // (assumes non-empty tree)
        Node current = root; // start at root
        while(current.sData.compareTo(key) == 0) // while no match,
        {
            if(current.sData.compareTo(key) < 0) // go left
                current = current.leftChild;
            else if (current.sData.compareTo(key) > 0)// or go right?
                current = current.rightChild;
            else if (current.sData.compareTo(key) == 0)
                current.intData += 1;
            if(current == null) // if no child,
                return null; // didn’t find it
        }
        return current; // found it
    }
    
    public void insert(String Sd)
    {
        Node newNode = new Node(Sd); // make new node
        newNode.sData = Sd;
        if(root==null) // no node in root
            root = newNode;
        else // root occupied
        {
            Node current = root; // start at root
            Node parent;
            while(true) // (exits internally)
            {
                parent = current;
                if(current.sData.compareTo(Sd) < 0) // go left?
                {
                    current = current.leftChild;
                    if(current == null) // if end of the line,
                    { // insert on left
                        parent.leftChild = newNode;
                        return;
                    }
                 } 
                else if (current.sData.compareTo(Sd) > 0) 
                {
                    current = current.rightChild;
                    if(current == null) // if end of the line
                    { // insert on right
                        parent.rightChild = newNode;
                        return;
                    }
                } // end else go right
            } // end while
        } // end else not root
    } // end insert()
    
    public boolean delete(int key) // delete node with given key
    { // (assumes non-empty list)
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        
    while(current.iData != key) // search for node
    {
        parent = current;
        if(key < current.iData) // go left?
        {
            isLeftChild = true;
            current = current.leftChild;
        }
        else // or go right?
        {
            isLeftChild = false;
            current = current.rightChild;
        }
        if(current == null) // end of the line,
            return false; // didn’t find it
    } // end while
    // found node to delete
    
    // if no children, simply delete it
    if(current.leftChild==null && current.rightChild==null)
    {
        if(current == root) // if root,
            root = null; // tree is empty
        else if(isLeftChild)
            parent.leftChild = null; // disconnect
        else // from parent
            parent.rightChild = null;
        }
    
        // if no right child, replace with left subtree
        else if(current.rightChild==null)
            if(current == root)
                root = current.leftChild;
            else if(isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        
        // if no left child, replace with right subtree
        else if(current.leftChild==null)
            if(current == root)
                root = current.rightChild;
            else if(isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        else // two children, so replace with inorder successor
        {
            // get successor of node to delete (current)
            Node successor = getSuccessor(current);
            
            // connect parent of current to successor instead
            if(current == root)
                root = successor;
            else if(isLeftChild)
                parent.leftChild = successor;
            else
                parent.rightChild = successor;
            
            // connect successor to current’s left child
            successor.leftChild = current.leftChild;
            } // end else two children
         // (successor cannot have a left child)
        return true; // success
    } // end delete()
    
    private Node getSuccessor(Node delNode)
    {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild; // go to right child
        
        while(current != null) // until no more
        {   // left children,
            successorParent = successor;
            successor = current;
            current = current.leftChild; // go to left child
        }
        
        // if successor not
        if(successor != delNode.rightChild) // right child,
        {   // make connections
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
    
    private String inOrder(Node localRoot)
    {
        String output = "";
        while (localRoot != null)
        {
            inOrder(localRoot.leftChild);
            output += (localRoot.sData + "\t\t" + localRoot.intData + "\n");
            inOrder(localRoot.rightChild);
            output += (localRoot.sData + "\t\t" + localRoot.intData + "\n");
        }
        return output;
    }
    
    public String traverse()
    {
        return inOrder(root);
    }
    
    
    public String displayTree()
{
    String output = "";
    Stack globalStack = new Stack();
    globalStack.push(root);
    int nBlanks = 32;
    boolean isRowEmpty = false;
    
    while(isRowEmpty==false)
    {
        Stack localStack = new Stack();
        isRowEmpty = true;
        for(int j=0; j<nBlanks; j++)
            output = " ";
        while(globalStack.isEmpty()==false)
        {
            Node temp = (Node)globalStack.pop();
            if(temp != null)
            {
                output = (temp.sData);
                localStack.push(temp.leftChild);
                localStack.push(temp.rightChild);
                if(temp.leftChild != null ||
                    temp.rightChild != null)
                isRowEmpty = false;
            }
            else
            {
                localStack.push(null);
                localStack.push(null);
            }
            for(int j=0; j<nBlanks*2-2; j++)
            output = " ";
         } // end while globalStack not empty
        System.out.println();
        nBlanks /= 2;
        while(localStack.isEmpty()==false)
        globalStack.push( localStack.pop() );
        } // end while isRowEmpty is false
    return output;
    } // end displayTree()    
}

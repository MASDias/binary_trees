/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

/**
 *
 * @author DEI-ESINF
 * @param <E>
 */
public class AVL<E extends Comparable<E>> extends BST<E> {

    private int balanceFactor(Node<E> node) {
        return height(node.getRight()) - height(node.getLeft());
    }

    private Node<E> rightRotation(Node<E> node) {
        Node<E> leftSon = node.getLeft();
        node.setLeft(leftSon.getRight());
        leftSon.setRight(node);
        node = leftSon;
        return node;
    }

    private Node<E> leftRotation(Node<E> node) {
        Node<E> rightSon = node.getRight();
        node.setRight(rightSon.getLeft());
        rightSon.setLeft(node);
        node = rightSon;
        return node;
    }

    private Node<E> twoRotations(Node<E> node) {
        if (balanceFactor(node) < 0) {
            node.setLeft(leftRotation(node.getLeft()));
            node = rightRotation(node);
        } else {
            node.setRight(rightRotation(node.getRight()));
            node = leftRotation(node);
        }
        return node;
    }

    private Node<E> balanceNode(Node<E> node) {
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) <= 0) {
                node = rightRotation(node);
            } else {
                node = twoRotations(node);
            }
        }
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.getRight()) >= 0) {
                node = leftRotation(node);
            } else {
                node = twoRotations(node);
            }
        }
        return node;
    }

    @Override
    public void insert(E element) {
        root = insert(element, root);
    }

    private Node<E> insert(E element, Node<E> node) {
        if (node == null) {                      //Verifica se o node pretendido existe 
            return new Node(element, null, null);                                            //Se não existir, cria uma folha na árvore
        }
        if (node.getElement().compareTo(element) == 0) {            // replace existing element
            node.setElement(element);
        } else if (element.compareTo(node.getElement()) < 0) {      //se o elemento a ser inserido for menor, insere um elemento a esquerda
            node.setLeft(insert(element, node.getLeft()));
            node = balanceNode(node);
        } else {                                                    //se o elemento a ser inserido for maior, insere um elemento a direita
            node.setRight(insert(element, node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }

    @Override
    public void remove(E element) {
        root = remove(element, root());
    }

    private Node<E> remove(E element, BST.Node<E> node) {
        if (node == null) {
            return null;    //throw new IllegalArgumentException("Element does not exist");
        }
        if (element.compareTo(node.getElement()) == 0) {
            // node is the Node to be removed
            if (node.getLeft() == null && node.getRight() == null) { //node is a leaf (has no childs)
                return null;
            }
            if (node.getLeft() == null) {   //has only right child
                return node.getRight();
            }
            if (node.getRight() == null) {  //has only left child
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove(min, node.getRight()));
            node = balanceNode(node);
        } else if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(remove(element, node.getLeft()));
            node = balanceNode(node);
        } else {
            node.setRight(remove(element, node.getRight()));
            node = balanceNode(node);
        }
        return node;
    }

    public boolean equals(AVL<E> second) {
        return equals(root, second.root);
    }

    public boolean equals(Node<E> root1, Node<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft()) && equals(root1.getRight(), root2.getRight());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    public Node<E> find(Node<E> node, E element){
        return find(element, node);
    }

    public Node<E> getRoot(){
        return root;
    }
}

package PL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author DEI-ESINF
 */
public class TREE_WORDS extends BST<TextWord> {

    public void createTree() throws FileNotFoundException {
        Scanner readfile = new Scanner(new File("src/PL/xxx.xxx"));
        while (readfile.hasNextLine()) {
            String[] pal = readfile.nextLine().split("(\\,)|(\\s)|(\\.)");
            for (String word : pal) {
                if (word.length() > 0) {
                    insert(new TextWord(word, 1));
                }
            }
        }
        readfile.close();
    }

    /**
     * Inserts a new word in the tree, or increments the number of its
     * occurrences.
     *
     * @param element
     */
    @Override
    public void insert(TextWord element) {
        root = insert(element, root);
    }

    private Node<TextWord> insert(TextWord element, Node<TextWord> node) {
        if (node == null || element == null) {                      //Verifica se o node pretendido existe 
            return null;                                            //Se não existir, cria uma folha na árvore
        }
        if (node.getElement().compareTo(element) == 0) {            // replace existing element
            node.getElement().incOcorrences();
            return node;
        }
        insert(element, node.getLeft());
        insert(element, node.getRight());
        return node;
    }

    /**
     * Returns a map with a list of words for each occurrence found.
     *
     * @return a map with a list of words for each occurrence found.
     */
    public Map<Integer, List<String>> getWordsOccurrences() {
        Map<Integer, List<String>> map = new HashMap<>();
        for (TextWord textWord : this.inOrder()) {
            if (map.containsKey(textWord.getOcorrences())) {
                map.get(textWord.getOcorrences()).add(textWord.getWord());
            } else {
                map.put(textWord.getOcorrences(), null);
                map.get(textWord.getOcorrences()).add(textWord.getWord());
            }
        }
        return map;
    }

}

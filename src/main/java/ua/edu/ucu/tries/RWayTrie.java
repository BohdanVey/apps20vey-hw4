package ua.edu.ucu.tries;

import ua.edu.ucu.collections.Queue;

import java.util.ArrayList;
import java.util.List;

public class RWayTrie implements Trie {
    private static final int VERTEX_ZERO = 0;
    private static final int VERTEX_USED = 1;
    private static final int VERTEX_WORD = 2;


    private static class Node extends Object {

        public int weight; //0 - we don't have such vertex, 1 - we have vertex, but don't have word, we have word
        public String word;
        public final Node[] next = new Node[256]; // To be sure that we can use any char(lowercase and uppercase)
        public Node(){
            this.weight = 0;
        }
    }


    private Node root = new Node();
    private Node prev = root;
    Character prev_char = 'a';
    @Override
    public void add(Tuple t) {
        String word = t.term;
        if (this.contains(word)) return;
        int i = 0;
        Node now = root;
        while (i <= word.length()) {
            if(now == null){
                now = new Node();
                prev.next[prev_char] = now;
            }
            if (i == word.length()) {
                now.weight = VERTEX_WORD;
                now.word = word;
                return;
            }
            if (now.weight == VERTEX_ZERO) {

                now.weight = VERTEX_USED;
            }
            prev = now;
            prev_char = word.charAt(i);
            now = now.next[word.charAt(i)];

            i += 1;
        }
    }

    public Node get(String word) {
        int i = 0;
        Node now = root;
        while (i < word.length()) {
            now = now.next[word.charAt(i)];
            if (now == null || now.weight == VERTEX_ZERO) return now;
            i += 1;
        }

        return now;

    }

    @Override
    public boolean contains(String word) {
        Node node = get(word);
        return node != null && node.weight == VERTEX_WORD;
    }

    @Override
    public boolean delete(String word) {
        Node now = get(word);
        if (now.weight == 2) {
            now.weight = 1;
            return true;
        }
        return false;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Queue q = new Queue();
        List<String> allElements = new ArrayList();
        Node now = get(s);
        q.enqueue(now);
        while (!q.empty()) {
            now = (Node) q.dequeue();
            if(now.weight == 2){
                allElements.add(now.word);
            }
            for (int i = 0; i < 256; i++) {
                if (now.next[i]!= null && now.next[i].weight > 0) {
                    q.enqueue(now.next[i]);
                }
            }
        }
        return allElements;
    }

}

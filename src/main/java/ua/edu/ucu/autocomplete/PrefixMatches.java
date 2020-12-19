package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches() {
        trie = new RWayTrie();
    }

    public PrefixMatches(Trie trie) {
        this.trie = new RWayTrie();

        Iterable<String> words = trie.words();
        for (String word : words) {
            this.load(word);
        }
    }

    public int load(String... strings) {
        int words = 0;
        for (String sentence : strings) {
            for (String word : sentence.split(" ")) {
                if (word.length() > 2) {
                    trie.add(new Tuple(word, word.length()));
                    words++;
                }
            }
        }

        return words;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);

    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        Iterable<String> allWords = this.wordsWithPrefix(pref);
        List<String> correctWords = new ArrayList();
        String prev = "";
        if (k <= 0) {
            return correctWords;
        }
        for (String word : allWords) {
            if (prev.length() != word.length()) {
                k--;
            }
            if (k < 0) {
                break;
            }
            correctWords.add(word);
            prev = word;
        }
        return correctWords;
    }

    public int size() {
        return trie.size();
    }
}

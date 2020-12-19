import ua.edu.ucu.autocomplete.PrefixMatches;
import ua.edu.ucu.tries.RWayTrie;

public class Main {
    public static void main(String[] args) {
        PrefixMatches pm = new PrefixMatches(new RWayTrie());
        pm.load("abce", "abc abc abce a", "abcd", "abcde", "abcdef");
        pm.wordsWithPrefix("");
        Iterable<String> all = pm.wordsWithPrefix("ab",2);
        for(String word : all){
            System.out.println(word);
        }
    }
}

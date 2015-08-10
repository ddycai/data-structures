package frigid.rain;

import frigid.rain.tree.Trie;

/**
 * Mainly for debugging purposes.
 */
public class Main {

  public static void main(String[] args) {
    Trie<Integer> dict = new Trie<>();
    String[] text = { "she", "sells", "sea", "shells", "by", "the", "seashore" };
    for (int i = 0; i < text.length; i++) {
      dict.put(text[i], i);
    }
    Iterable<String> result = dict.keysWithPrefix("sh");
    System.out.println(result);
  }
}

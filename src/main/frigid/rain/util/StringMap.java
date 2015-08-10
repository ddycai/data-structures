package frigid.rain.util;

/**
 * Maps Strings to a value.
 */
public interface StringMap<V> extends Map<String, V> {
  /**
   * All keys with the given prefix.
   */
  public Iterable<String> keysWithPrefix(String prefix);

  /**
   * The longest key that is a prefix of the given String.
   */
  public String longestPrefixOf(String s);
}

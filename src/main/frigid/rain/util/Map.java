package frigid.rain.util;

/**
 * Simple map interface.
 */
public interface Map<K, V> {
  public void put(K key, V value);
  
  public V get(K key);

  public void remove(K key);

  public boolean containsKey(K key);

  public boolean containsValue(V value);

  public int size();
}

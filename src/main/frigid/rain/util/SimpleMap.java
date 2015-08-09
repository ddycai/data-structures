package frigid.rain.util;

/**
 * Simple map interface that does not support deletion.
 */
public interface SimpleMap<K, V> {
  public void put(K key, V value);
  
  public V get(K key);

  public boolean containsKey(K key);

  public boolean containsValue(V value);

  public int size();
}

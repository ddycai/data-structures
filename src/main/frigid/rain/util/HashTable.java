package frigid.rain.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Implementation of a map as a hash table.
 *
 * Based off implementation from Algorithms by Sedgewick and Wayne.
 *
 * (1) Compute a hash function that transforms the key into an array index.
 * (2) Collision-resolution: separate chaining or linear probing.
 *
 * This hash table is an array list of linked lists.
 * I'm using the Java standard library for {@link ArrayList} and {@link LinkedList}.
 */

public class HashTable<K, V> implements SimpleMap<K, V> {

  private List<List<Entry<K, V>>> table;

  // This is the size of the hash table.
  private int M;

  // This is the number of keys contained.
  private int size;

  /**
   * @param M the size of the hash table (should be prime number)
   */
  public HashTable(int M) {
    this.M = M;
    table = new ArrayList<>(M);
    for (int i = 0; i < M; i++) {
      table.add(new LinkedList<Entry<K, V>>());
    }
  }

  public void put(K key, V value) {
    List<Entry<K, V>> list = table.get(hash(key));
    for (Entry<K, V> entry : list) {
      if (entry.getKey().equals(key)) {
        entry.setValue(value);
      }
    }
    list.add(new Entry<K, V>(key, value));
    size++;
  }

  public V get(K key) {
    Iterable<Entry<K, V>> list = table.get(hash(key));
    for (Entry<K, V> entry : list) {
      if (entry.getKey().equals(key)) {
        return entry.getValue();
      }
    }
    return null;
  }

  public boolean containsKey(K key) {
    Iterable<Entry<K, V>> list = table.get(hash(key));
    for (Entry<K, V> entry : list) {
      if (entry.getKey().equals(key)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Requires a linear search through the table.
   */
  public boolean containsValue(V value) {
    for (List<Entry<K, V>> list : table) {
      for (Entry<K, V> entry : list) {
        if (entry.getValue().equals(value)) {
          return true;
        }
      }
    }
    return false;
  }

  public int size() {
    return size;
  }

  private int hash(K key) {
    // The bit mask here is to remove the sign bit.
    return (key.hashCode() & 0x7fffffff) % M;
  }

  private static class Entry<K, V> {
    K key;
    V value;

    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }
  } 
}

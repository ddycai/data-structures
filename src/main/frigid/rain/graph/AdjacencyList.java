package frigid.rain.graph;

import java.util.List;
import java.util.LinkedList;
import java.lang.Iterable;

import frigid.rain.util.HashTable;

/**
 * Graph implemented by an adjacency list.
 *
 * I am using my own implementation of hash table as the adjacency list so that
 * I can have a general graph of any objects.
 */
public class AdjacencyList<V> implements Graph<V> {

  private HashTable<V, List<V>> adj;

  public AdjacencyList(int M) {
    adj = new HashTable<>(M);
  }

  public void addEdge(V v, V w) {
    check(v);
    check(w);
    adj.get(v).add(w);
    adj.get(w).add(v);
  }

  public boolean hasEdge(V v, V w) {
    check(v);
    check(w);
    return adj.get(v).contains(w);
  }

  public Iterable<V> neighbours(V v) {
    check(v);
    return adj.get(v);
  }

  public int degree(V v) {
    check(v);
    return adj.get(v).size();
  }

  public int size() {
    return adj.size();
  }

  private void check(V v) {
    if (!adj.containsKey(v)) {
      adj.put(v, new LinkedList<V>());
    }
  }
}

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
    addIfNotExists(v);
    addIfNotExists(w);
    adj.get(v).add(w);
    adj.get(w).add(v);
  }

  public boolean hasEdge(V v, V w) {
    errorIfNotExists(v);
    errorIfNotExists(w);
    return adj.get(v).contains(w);
  }

  public Iterable<V> neighbours(V v) {
    errorIfNotExists(v);
    return adj.get(v);
  }

  public int degree(V v) {
    errorIfNotExists(v);
    return adj.get(v).size();
  }

  public int size() {
    return adj.size();
  }

  private void addIfNotExists(V v) {
    if (!adj.containsKey(v)) {
      adj.put(v, new LinkedList<V>());
    }
  }

  private void errorIfNotExists(V v) {
    if (!adj.containsKey(v)) {
      throw new IllegalArgumentException(
          String.format("Vertex [%s] is not in the graph.", v.toString()));
    }
  }
}

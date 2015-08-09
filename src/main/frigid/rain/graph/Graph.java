package frigid.rain.graph;

import java.lang.Iterable;

/**
 * Simple undirected graph interface.
 */
public interface Graph<V> {
  public void addEdge(V v, V w);

  public boolean hasEdge(V v, V w);

  public Iterable<V> neighbours(V v);

  public int degree(V v);

  public int size();
}

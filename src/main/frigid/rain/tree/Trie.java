package frigid.rain.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Implementation of a trie.
 *
 * Based on implementation from Algorithms by Sedgewick and Wayne.
 */
public class Trie<V> implements StringMap<V> {
  // The size of the alphabet.
  private static int N = 256;
  private Node<V> root;
  private int size = 0;

  public Trie() {
    root = new Node<V>();
  }

  @Override
  public void put(String key, V value) {
    put(root, key, value, 0);
  }

  /**
   * This put method returns the next node back to the parent so that the parent
   * can set its pointer in the event that the next node is null.
   *
   * For example, we call put on a null node (this means a new node needs to be
   * created in the trie). The child method creates a new node and returns it to
   * the parent. The parent sets its pointer to the new node.
   */
  private Node<V> put(Node<V> x, String key, V value, int d) {
    // We've reached a null node so this key is not in the tree. We create a new
    // node here.
    if (x == null) {
      x = new Node<V>();
    }
    // Base case: we have reached the leaf so set the value.
    if (key.length() == d) {
      // If x.value at this point is null, it means it's a new node.
      if (x.value == null) {
        size++;
      }
      x.value = value;
      return x;
    }
    char c = key.charAt(d);
    // Recursive step: call put on the children.
    Node<V> child = put(x.next.get(c), key, value, d + 1);
    child.c = c;
    x.next.set(c, child);
    return x;
  }

  @Override
  public V get(String key) {
    Node<V> x = get(root, key, 0);
    return x == null ? null : x.value;
  }

  /**
   * Recursively calls get on its children to find the node that corresponds to
   * the given key.
   */
  private Node<V> get(Node<V> x, String key, int d) {
    // Base case: we've reached a null node so this key is not in the trie.
    if (x == null) {
      return null;
    }
    // Base case: we've reached where the string should be.
    if (key.length() == d) {
      return x;
    }
    // Recursive step: call get on the children.
    return get(x.next.get(key.charAt(d)), key, d + 1);
  }

  /**
   * (1) Find the node corresponding to the given prefix.
   * (2) Run collect on that node.
   */
  @Override
  public Iterable<String> keysWithPrefix(String prefix) {
    List<String> strings = new LinkedList<>();
    Node<V> x = get(root, prefix, 0);
    collect(x, prefix, strings);
    return strings;
  }

  @Override
  public String longestPrefixOf(String s) {
    // Not implemented.
    return null;
  }

  /**
   * Perform a DFS on the trees, saving the current path in {@code str}. When we
   * get to a leaf, we save {@code str} in a list.
   *
   * This "collects" all the strings for a given subtree rooted at {@code x}.
   */
  private void collect(Node<V> x, String str, List<String> strings) {
    if (x == null) {
      return;
    }
    if (x.value != null) {
      strings.add(str);
    }
    for (Node<V> y : x.next) {
      if (y != null) {
        collect(y, str + y.c, strings);
      }
    }
  }

  @Override
  public boolean containsKey(String key) {
    return get(key) != null;
  }

  @Override
  public boolean containsValue(V value) {
    return dfs(root, value);
  }

  /**
   * DFS for the given target value.
   */
  private boolean dfs(Node<V> x, V target) {
    if (x.value == target) {
      return true;
    }
    for (Node<V> y : x.next) {
      if (y != null && dfs(y, target)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void remove(String key) {
    remove(root, key, 0);
  }

  /**
   * We do a search for the key. When we are backtracking in the recursion, we
   * unset the pointers along the path until we hit a node that contains a
   * value. We stop removing pointers after this pointer because the path
   * belongs to another word which is a prefix of the key we are trying to
   * remove.
   *
   * In the backtracking phase, remove returns if the child wants the parent to
   * remove its pointer.
   */
  private boolean remove(Node<V> x, String key, int d) {
    if (x == null) {
      return false;
    }
    // Base case: found the key, now tell parent to remove the link.
    if (key.length() == d) {
      size--;
      return true;
    }
    // Recursive step: keep looking.
    // If the child returns true, remove its pointer.
    char c = key.charAt(d);
    if (remove(x.next.get(c), key, d + 1)) {
      x.next.set(c, null);
      // If this node is empty, tell parent to remove this pointer.
      if (x.value == null) {
        return true;
      }
    }
    // If the child tells you not to remove, then propogate up.
    return false;
  }

  @Override
  public int size() {
    return size;
  }

  /**
   * BFS on the trie and dump out results for debugging.
   */
  public void dump() {
    Queue<Node<V>> q = new LinkedList<>();
    q.add(root);

    while (!q.isEmpty()) {
      Node<V> x = q.poll();
      if (x.c != 0) {
        System.out.format("%c: ", x.c);
      } else {
        System.out.print("root: ");
      }
      for (Node<V> y : x.next) {
        if (y != null) {
          System.out.format("%c ", y.c);
          q.add(y);
        }
      }
      System.out.println();
    }
  }

  private static class Node<V> {
    char c;
    V value;
    List<Node<V>> next;

    Node() {
      this.value = null;
      next = new ArrayList<Node<V>>(N);
      // Set all the children to null:
      while (next.size() < N) {
        next.add(null);
      }
    }
  }
}

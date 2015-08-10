package frigid.rain.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;

import frigid.rain.graph.AdjacencyList;

/** Unit tests for {@link AdjacencyList}. */
@RunWith(JUnit4.class)
public class AdjacencyListTest {

  AdjacencyList<Integer> adj;

  @Before public void setUp() {
    adj = new AdjacencyList<>(31);
    adj.addEdge(1, 2);
    adj.addEdge(1, 5);
    adj.addEdge(2, 3);
    adj.addEdge(2, 4);
    adj.addEdge(3, 4);
    adj.addEdge(5, 3);
  }

  @Test public void testHasEdge() {
    assertTrue(adj.hasEdge(1, 2));
    assertTrue(adj.hasEdge(2, 1));

    assertTrue(adj.hasEdge(1, 5));
    assertTrue(adj.hasEdge(5, 1));

    assertTrue(adj.hasEdge(2, 3));
    assertTrue(adj.hasEdge(3, 2));

    assertTrue(adj.hasEdge(2, 4));
    assertTrue(adj.hasEdge(4, 2));

    assertTrue(adj.hasEdge(3, 4));
    assertTrue(adj.hasEdge(4, 3));

    assertTrue(adj.hasEdge(5, 3));
    assertTrue(adj.hasEdge(3, 5));

    assertFalse(adj.hasEdge(5, 2));
    assertFalse(adj.hasEdge(2, 5));
  }

  @Test public void testSize() {
    assertEquals(5, adj.size());
    adj.addEdge(5, 6);
    assertEquals(6, adj.size());
  }

  @Test public void testDegree() {
    assertEquals(2, adj.degree(1));
    assertEquals(3, adj.degree(2));
    assertEquals(0, adj.degree(6));
    adj.addEdge(5, 6);
    assertEquals(1, adj.degree(6));
  }

  @Test public void testNeighbours() {
    assertEquals(newArrayList(2, 5), adj.neighbours(1));
    assertEquals(newArrayList(1, 3, 4), adj.neighbours(2));
    assertEquals(newArrayList(2, 4, 5), adj.neighbours(3));
    assertEquals(newArrayList(2, 3), adj.neighbours(4));
    assertEquals(newArrayList(1, 3), adj.neighbours(5));
  }

  private ArrayList<Integer> newArrayList(int ... values) {
    ArrayList<Integer> list = new ArrayList<>();
    for (int v : values) {
      list.add(v);
    }
    return list;
  }

}

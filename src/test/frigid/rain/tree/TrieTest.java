package frigid.rain.tree;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Unit tests for {@link Trie}. */
@RunWith(JUnit4.class)
public class TrieTest {

  public Trie<Integer> dict;
  final static String[] text = { "she", "sells", "sea", "shells", "by", "the", "seashore" };

  @Before
  public void setUp() {
    dict = new Trie<>();
    for (int i = 0; i < text.length; i++) {
      dict.put(text[i], i);
    }
  }

  @Test
  public void testGet() {
    assertEquals(new Integer(0), dict.get("she"));
    assertEquals(new Integer(3), dict.get("shells"));
    assertEquals(new Integer(2), dict.get("sea"));
    assertEquals(new Integer(1), dict.get("sells"));
    assertEquals(new Integer(6), dict.get("seashore"));
  }

  @Test
  public void testPutReplace() {
    assertEquals(new Integer(3), dict.get("shells"));
    dict.put("shells", 99);
    assertEquals(new Integer(99), dict.get("shells"));
  }

  @Test
  public void testKeysWithPrefix() {
    assertEquals(newArrayList("she", "shells"), dict.keysWithPrefix("sh"));
    assertEquals(newArrayList("sea", "seashore", "sells", "she", "shells"),
        dict.keysWithPrefix("s"));
    assertEquals(newArrayList("the"), dict.keysWithPrefix("the"));
    assertEquals(newArrayList("the"), dict.keysWithPrefix("th"));
    assertEquals(newArrayList(), dict.keysWithPrefix("p"));
    assertEquals(newArrayList(), dict.keysWithPrefix("bye"));
  }

  @Test
  public void testContainsValue() {
    for (int i = 0; i < text.length; i++) {
      assertTrue(dict.containsValue(i));
    }
    assertFalse(dict.containsValue(text.length));
    assertFalse(dict.containsValue(-1));
  }

  @Test
  public void testContainsKey() {
    for (int i = 0; i < text.length; i++) {
      assertTrue(dict.containsKey(text[i]));
    }
    assertFalse(dict.containsKey("shesells"));
    assertFalse(dict.containsKey("seas"));
  }

  @Test
  public void testRemove() {
    assertTrue(dict.containsKey("sea"));
    dict.remove("sea");
    assertFalse(dict.containsKey("sea"));

    assertFalse(dict.containsKey("seas"));
    dict.remove("seas");
    assertFalse(dict.containsKey("seas"));
  }

  @Test
  public void testSize() {
    dict = new Trie<>();
    assertEquals(0, dict.size());
    dict.put("she", 0);
    assertEquals(1, dict.size());
    dict.put("shells", 11);
    assertEquals(2, dict.size());
    dict.remove("she");
    assertEquals(1, dict.size());
    dict.remove("shell");
    assertEquals(1, dict.size());
  }

  private ArrayList<String> newArrayList(String ... values) {
    ArrayList<String> list = new ArrayList<>();
    for (String v : values) {
      list.add(v);
    }
    return list;
  }
}

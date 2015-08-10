package frigid.rain.util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import frigid.rain.util.HashTable;
import frigid.rain.util.Map;

/** Unit tests for {@link HashTable}. */
@RunWith(JUnit4.class)
public class HashTableTest {

  public Map<String, String> dict;

  @Before public void setUp() {
    dict = new HashTable<>(31);
    dict.put("hello", "world");
    dict.put("goodbye", "World");
  }

  @Test
  public void testGet() {
    assertEquals("world", dict.get("hello"));
    assertEquals("World", dict.get("goodbye"));
  }

  @Test
  public void testContainsKey() {
    assertTrue(dict.containsKey("hello"));
    assertTrue(dict.containsKey("goodbye"));
    assertFalse(dict.containsKey("foo"));
  }

  @Test
  public void testRemove() {
    assertTrue(dict.containsKey("hello"));
    dict.remove("hello");
    assertFalse(dict.containsKey("hello"));
  }

  @Test
  public void testContainsValue() {
    assertTrue(dict.containsValue("world"));
    assertTrue(dict.containsValue("World"));
    assertFalse(dict.containsValue("bar"));
  }

  @Test
  public void testPutReplace() {
    assertEquals("world", dict.get("hello"));
    dict.put("hello", "kitty");
    assertEquals("kitty", dict.get("hello"));
  }

  @Test
  public void testSize() {
    dict = new HashTable<>(31);
    assertEquals(0, dict.size());
    dict.put("duncan", "is a person.");
    assertEquals(1, dict.size());
    dict.put("one", "last time");
    assertEquals(2, dict.size());
    dict.remove("duncan");
    assertEquals(1, dict.size());
    dict.remove("duncan");
    assertEquals(1, dict.size());
  }
}

package org.cacheframework.memorystore.server;

import java.util.HashMap;
import java.util.Map;

/**
 * Key / Value holder
 * 
 * @author Vijay
 * @version 0.1
 * @sinice 1.8
 *
 */

public class ValueHolder {

  private static ValueHolder instance = null;
	
  private final Map<String, Object> state = new HashMap<>();
  
  private ValueHolder() {
	  // private constructor
  }
  
  /**
   * Returns Singleton instance of ValueHolder
   * @return ValueHolder
   */
  public static ValueHolder getInstance() {
	  if (instance == null) 
          instance = new ValueHolder(); 

      return instance; 
  }

  public Object getValue(String key) {
    return state.get(key);
  }

  public Object removeValue(String key) {
    return state.remove(key);
  }

  public void putValue(String key, Object value) {
    state.put(key, value);
  }

  public void clear() {
    state.clear();
  }
}

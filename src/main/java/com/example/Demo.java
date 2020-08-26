package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {

  private int transToInt(String key) {
    if ("J".equals(key)) {
      return 11;
    } else if ("Q".equals(key)) {
      return 12;
    } else if ("K".equals(key)) {
      return 13;
    } else if ("A".equals(key)) {
      return 14;
    } else if ("T".equals(key)) {
      return 10;
    }
    return Integer.parseInt(key);
  }

  private String transToString(int key) {
    if (key == 11) {
      return "Jack";
    } else if (key == 12) {
      return "Queen";
    } else if (key == 13) {
      return "King";
    } else if (key == 14) {
      return "Ace";
    }
    return String.valueOf(key);
  }

  public String playGame(String input) {

    String[] blackInput = input.substring(7, 22).split(" ");
    String[] whiteInput = input.substring(30).split(" ");
    Map<Integer, List<String>> blackMap =transToMap(blackInput);
    Map<Integer, List<String>> whiteMap =transToMap(whiteInput);
    Set<Integer> set = blackMap.keySet();
    List<Integer> black = new ArrayList<Integer>(set);
    Set<Integer> set2 = whiteMap.keySet();
    List<Integer> white = new ArrayList<Integer>(set2);
    String res = "equal";
    int highCart = -1;
    for (int i = 1; i < black.size(); i++) {
      if (black.get(black.size() - i) > white.get(black.size() - i)) {
        highCart = black.get(black.size() - i);
        res = "Black";
        break;
      } else if (black.get(black.size() - i) < white.get(black.size() - i)) {
        highCart = white.get(white.size() - i);
        res = "White";
        break;
      }
    }
    return res + " wins. - with high card: " + transToString(highCart);
  }

  private Map transToMap(String[] input) {
    Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
    for (int i = 0; i < input.length; i++) {
      List<String> value = new ArrayList<String>();
      value.add(input[i].substring(1));
      map.put(transToInt(input[i].substring(0, 1)), value);
    }
    return map;
  }

  public String run() {
    return "ABC";
  }
}

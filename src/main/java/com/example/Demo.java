package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {

  private int getRank(Map<Integer, List<String>> map) {
    Set<Integer> key = map.keySet();
    List<Integer> keys = new ArrayList<Integer>(key);
    int maxLengthOfValue = 0;
    for (Integer i : map.keySet()) {
      if (map.get(i).size() > maxLengthOfValue) {
        maxLengthOfValue = map.get(i).size();
      }
    }
    if (keys.size() == 4 && maxLengthOfValue == 2) {
      return 2;
    }
    if (keys.size() == 3 && maxLengthOfValue == 2) {
      return 3;
    }
    if (keys.size() == 3 && maxLengthOfValue == 3) {
      return 4;
    }
    if (keys.size() == 2 && maxLengthOfValue == 3) {
      return 7;
    }
    if (keys.size() == 2 && maxLengthOfValue == 4) {
      return 8;
    }
    if (isStraight(keys) && isFlush(map)) {
      return 9;
    }
    if (isStraight(keys)) {
      return 5;
    }
    if (isFlush(map)) {
      return 6;
    }
    return 1;
  }

  private boolean isFlush(Map<Integer, List<String>> map) {
    List<List<String>> list = new ArrayList<List<String>>(map.values());
    String init = list.get(0).get(0);
    for (int i = 1; i < list.size(); i++) {
      if (!init.equals(list.get(i).get(0))) {
        return false;
      }
    }
    return true;
  }

  private boolean isStraight(List<Integer> list) {
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i) + 1 != list.get(i + 1)) {
        return false;
      }
    }
    return true;
  }

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

  private String getMaxKey(List<Integer> black, List<Integer> white) {
    int highCart = -1;
    String res = "Tie";
    for (int i = 1; i < black.size(); i++) {
      if (black.get(black.size() - i) > white.get(black.size() - i)) {
        highCart = black.get(black.size() - i);
        return "Black" + " wins. - with high card: " + transToString(highCart);
      } else if (black.get(black.size() - i) < white.get(black.size() - i)) {
        highCart = white.get(white.size() - i);
        return "White" + " wins. - with high card: " + transToString(highCart);
      }
    }
    return res;
  }

  private String describeOfPair(Map<Integer, List<String>> map, String winner) {
    int maxLength = 0;
    List<Integer> maxNumber = new ArrayList<Integer>();
    for (Integer i : map.keySet()) {
      if (!(map.get(i).size() < maxLength)) {
        maxLength = map.get(i).size();
        maxNumber.add(i);
      }
    }
    if (maxLength == 3) {
      return winner + " wins. - with Three " + transToString(maxNumber.get(0));
    }
    if (maxLength == 2) {
      if (map.size() == 4) {
        return winner + " wins. - with pair of " + transToString(maxNumber.get(0));
      }
      return winner + " wins. - with two pairs of " + transToString(maxNumber.get(0)) + "&" + transToString(
          maxNumber.get(1));
    }
    return "Tie";
  }

  private String whenLengthEqual(Map<Integer, List<String>> blackMap, Map<Integer, List<String>> whiteMap) {
    Set<Integer> set = blackMap.keySet();
    Set<Integer> set2 = whiteMap.keySet();
    List<Integer> black = new ArrayList<Integer>(set);
    List<Integer> white = new ArrayList<Integer>(set2);
    if (whiteMap.size() == 5) {
      return getMaxKey(black, white);
    }
    if (whiteMap.size() == 3) {
      int maxLength = 0;
      String winner = "";
      List<Integer> maxNumber = new ArrayList<Integer>();
      for (Integer i : whiteMap.keySet()) {
        if (!(whiteMap.get(i).size() < maxLength)) {
          maxLength = whiteMap.get(i).size();
          maxNumber.add(i);
          winner = "White";
        }
      }
      for (Integer i : blackMap.keySet()) {
        if (!(blackMap.get(i).size() < maxLength)) {
          maxLength = blackMap.get(i).size();
          maxNumber.add(i);
          winner = "Black";
        }
      }
      if (maxLength == 3) {
        return winner + " wins. - with Three " + transToString(maxNumber.get(0));
      }
    }
    return null;
  }

  public String playGame(String input) {

    String[] blackInput = input.substring(7, 22).split(" ");
    String[] whiteInput = input.substring(30).split(" ");
    Map<Integer, List<String>> blackMap = transToMap(blackInput);
    Map<Integer, List<String>> whiteMap = transToMap(whiteInput);
    Set<Integer> set = blackMap.keySet();
    Set<Integer> set2 = whiteMap.keySet();

    int rankOfBlack = getRank(blackMap);
    int rankOfWhite = getRank(whiteMap);
    if (rankOfBlack > rankOfWhite) {
      return "Black" + getReason(rankOfBlack, blackMap);
    } else if (rankOfBlack < rankOfWhite) {
      return "White" + getReason(rankOfWhite, whiteMap);
    }
    return getReasonOfSameRank(rankOfBlack, blackMap, whiteMap);
  }

  private String getReasonOfSameRank(int rank, Map<Integer, List<String>> blackMap,
      Map<Integer, List<String>> whiteMap) {
    Set<Integer> set = blackMap.keySet();
    Set<Integer> set2 = whiteMap.keySet();
    List<Integer> black = new ArrayList<Integer>(set);
    List<Integer> white = new ArrayList<Integer>(set2);
    if (rank == 1) {
      return getMaxKey(black, white);
    }
    if (rank == 2) {
      int blackNumber = maxLengthNumber(blackMap, 2);
      int whiteNumber = maxLengthNumber(whiteMap, 2);
      return blackNumber > whiteNumber ? "Black wins. - with pair of " + blackNumber
          : "White wins. - with pair of " + whiteNumber;
    }
    if (rank == 4) {
      int blackNumber = maxLengthNumber(blackMap, 3);
      int whiteNumber = maxLengthNumber(whiteMap, 3);
      return blackNumber > whiteNumber ? "Black wins. - with Three " + blackNumber
          : "White wins. - with Three " + whiteNumber;
    }
    return null;
  }

  private String getReason(int rank, Map<Integer, List<String>> map) {
    if (rank == 2) {
      return " wins. - with pair of " + transToString(maxLengthNumber(map, 2));
    }
    if (rank == 3) {
      int maxLength = 0;
      List<Integer> maxNumber = new ArrayList<Integer>();
      for (Integer i : map.keySet()) {
        if (!(map.get(i).size() < maxLength)) {
          maxLength = map.get(i).size();
          maxNumber.add(i);
        }
      }
      return " wins. - with two pairs of " + transToString(maxNumber.get(0)) + "&" + transToString(
          maxNumber.get(1));
    }
    if (rank == 4) {
      return " wins. - with Three " + transToString(maxLengthNumber(map, 3));
    }
    if (rank == 5) {
      return " wins. - with Straight";
    }
    if (rank == 6) {
      return " wins. - with Flush";
    }
    if (rank == 7) {
      return " wins. - with full house: " + getFullHouse(map);
    }
    if (rank == 8) {
      return " wins. - with four of a kind";
    }
    if (rank == 9) {
      return " wins. - with straight flush";
    }
    return "";
  }

  private String getFullHouse(Map<Integer, List<String>> map) {
    int maxNumber = 0, minNumber = 0;
    for (Integer i : map.keySet()) {
      if (map.get(i).size() == 3) {
        maxNumber = i;
      } else {
        minNumber = i;
      }
    }
    return maxNumber + " over " + minNumber;
  }

  private int maxLengthNumber(Map<Integer, List<String>> map, int length) {
    for (Integer i : map.keySet()) {
      if (map.get(i).size() == length) {
        return i;
      }
    }
    return -1;
  }

  private Map transToMap(String[] input) {
    Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
    for (int i = 0; i < input.length; i++) {
      List<String> value = new ArrayList<String>();
      if (map.containsKey((transToInt(input[i].substring(0, 1))))) {
        value = map.get(transToInt(input[i].substring(0, 1)));
      }
      value.add(input[i].substring(1));
      map.put(transToInt(input[i].substring(0, 1)), value);
    }
    return map;
  }

  public String run() {
    return "ABC";
  }
}

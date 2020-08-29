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

  private boolean getMaxKey(List<Integer> black, List<Integer> white) {
    int maxOfBlack = black.get(black.size() - 1);
    int maxOfWhite = white.get(white.size() - 1);
    return maxOfBlack > maxOfWhite;
  }

  private String getTwoPairs(Map<Integer, List<String>> map) {
    String res = "";
    int count = 0;
    for (Integer i : map.keySet()) {
      if (map.get(i).size() == 2) {
        if (count > 0) {
          res += "&";
        }
        res += i;
        count++;
      }
    }
    return res;
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

  private String getReasonOfSameRank(int rank, Map<Integer, List<String>> blackMap, Map<Integer, List<String>> whiteMap) {
    List<Integer> black = new ArrayList<Integer>(blackMap.keySet());
    List<Integer> white = new ArrayList<Integer>(whiteMap.keySet());
    if (rank == 1) {
      return getMaxKey(black, white) ? "Black" + " wins. - with high card: " + transToString(black.get(black.size() - 1)) :
          "White" + " wins. - with high card: " + transToString(white.get(white.size() - 1));
    }
    if (rank == 2) {
      int blackNumber = maxLengthNumber(blackMap, 2);
      int whiteNumber = maxLengthNumber(whiteMap, 2);
      return blackNumber > whiteNumber ? "Black wins. - with pair of " + blackNumber
          : "White wins. - with pair of " + whiteNumber;
    }
    if (rank == 3) {
      int blackNumber = maxLengthNumber(blackMap, 2);
      int whiteNumber = maxLengthNumber(whiteMap, 2);
      return blackNumber > whiteNumber ? "Black wins. - with two pairs of " + getTwoPairs(blackMap)
          : "White wins. - with two pairs of " + getTwoPairs(whiteMap);
    }
    if (rank == 4) {
      int blackNumber = maxLengthNumber(blackMap, 3);
      int whiteNumber = maxLengthNumber(whiteMap, 3);
      return blackNumber > whiteNumber ? "Black wins. - with Three " + blackNumber
          : "White wins. - with Three " + whiteNumber;
    }
    if (rank == 5) {
      return getMaxKey(black, white) ? "Black wins. - with Straight and max number is: " + transToString(
          black.get(black.size() - 1)) :
          "White wins. - with Straight and max number is: " + transToString(white.get(white.size() - 1));
    }
    if (rank == 6) {
      return getMaxKey(black, white) ? "Black wins. - with Flush and max number is: " + transToString(
          black.get(black.size() - 1)) :
          "White wins. - with Flush and max number is: " + transToString(white.get(white.size() - 1));
    }
    if (rank ==8){
      int blackNumber = maxLengthNumber(blackMap, 4);
      int whiteNumber = maxLengthNumber(whiteMap, 4);
      return blackNumber > whiteNumber ? "Black wins. - with four of a kind"
          : "White wins. - with four of a kind";
    }
    if (rank == 9) {
      return getMaxKey(black, white) ? "Black wins. - with straight flush":
          "White wins. - with straight flush";
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
    int maxNumber = -1;
    for (Integer i : map.keySet()) {
      if (map.get(i).size() == length) {
        maxNumber = i;
      }
    }
    return maxNumber;
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

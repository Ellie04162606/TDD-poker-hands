package com.example;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DemoTest {

  private static Demo demo;

  @BeforeClass
  public static void setUp() {
    demo = new Demo();
  }

  @Test
  public void test01() {
    String result = demo.run();
    assertThat(result).isEqualTo("ABC");
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_2H_3D_5S_9C_KD_white_2C_3H_4S_8C_AH() {
    //given
    String input = "Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("White wins. - with high card: Ace", result);
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_2H_3D_5S_9C_KD_white_3C_3H_4S_8C_AH() {
    //given
    String input = "Black: 2H 3D 5S 9C KD  White: 3C 3H 4S 8C AH";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals(result, "White wins. - with pair of 3");
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_5H_3D_5S_9C_KD_white_3C_3H_4S_8C_AH() {
    //given
    String input = "Black: 5H 3D 5S 9C KD  White: 3C 3H 4S 8C AH";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals(result, "Black wins. - with pair of 5");
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_3H_3D_5S_9C_KD_white_3C_3H_3S_8C_AH() {
    //given
    String input = "Black: 3H 3D 5S 9C KD  White: 3C 3H 3S 8C AH";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("White wins. - with Three 3", result);
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_3H_3D_5S_9C_KD_white_3C_8H_3S_8C_AH() {
    //given
    String input = "Black: 3H 3D 5S 9C KD  White: 3C 8H 3S 8C AH";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("White wins. - with two pairs of 3&8", result);
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_3H_3D_5S_5C_KD_white_3C_3H_3S_8C_AH() {
    //given
    String input = "Black: 3H 3D 5S 5C KD  White: 3C 3H 3S 8C AH";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("White wins. - with Three 3", result);
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_3H_3D_3S_5C_KD_white_3C_4H_5S_6C_7H() {
    //given
    String input = "Black: 3H 3D 3S 5C KD  White: 3C 4H 5S 6C 7H";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("White wins. - with Straight", result);
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_3D_4D_6D_5D_KD_white_3C_4H_5S_6C_7H() {
    //given
    String input = "Black: 3D 4D 6D 5D KD  White: 3C 4H 5S 6C 7H";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("Black wins. - with Flush", result);
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_3D_4D_6D_5D_KD_white_3C_3H_5S_3C_5H() {
    //given
    String input = "Black: 3D 4D 6D 5D KD  White: 3C 3H 5S 3C 5H";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("White wins. - with full house: 3 over 5", result);
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_3C_3H_3H_3D_KD_white_3C_3H_5S_3C_5H() {
    //given
    String input = "Black: 3C 3H 3H 3D KD  White: 3C 3H 5S 3C 5H";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("Black wins. - with four of a kind", result);
  }

  @Test
  public void should_return_white_wins_when_play_game_given_black_3C_3H_3H_3D_KD_white_4D_5D_6D_7D_8D() {
    //given
    String input = "Black: 3C 3H 3H 3D KD  White: 4D 5D 6D 7D 8D";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("White wins. - with straight flush", result);
  }
}
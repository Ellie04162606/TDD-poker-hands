package com.example;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

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
  public void should_return_white_wins_when_play_game_given_black_2H_2D_5S_9C_KD_white_3C_3H_4S_8C_AH(){
    //given
    String input = "Black: 2H 2D 5S 9C KD  White: 3C 3H 4S 8C AH";

    //when
    String result = demo.playGame(input);

    //then
    assertEquals("White wins. - with pair of 3", result);
  }
}
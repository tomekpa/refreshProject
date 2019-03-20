Feature: Is it Friday yet?
  Everybody wants to know when it's Friday

  Scenario Outline: Friday is Friday, and other days are not Friday
    Given today is "<day>"



    When I ask whether it's Friday yet



    Then I should be told "<answer>"






  Examples:
  | day            | answer |
  | null           | Nope   |
  | Friday         | TGIF   |
  | Sunday         | Nope   |
  | anything else! | Nope   |

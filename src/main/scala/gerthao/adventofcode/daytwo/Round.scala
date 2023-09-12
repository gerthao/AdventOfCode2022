package gerthao.adventofcode.daytwo

enum Round(val score: Int):
  case Lose extends Round(0)
  case Draw extends Round(3)
  case Win extends Round(6)
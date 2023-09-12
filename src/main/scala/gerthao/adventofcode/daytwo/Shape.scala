package gerthao.adventofcode.daytwo

enum Shape(val score: Int):
  case Rock extends Shape(1)
  case Paper extends Shape( 2)
  case Scissors extends Shape(3)
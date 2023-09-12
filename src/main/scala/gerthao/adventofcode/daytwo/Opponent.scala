package gerthao.adventofcode.daytwo

case class Opponent():
  def chooseShape(letter: String): Shape =
    letter match
      case "A" => Shape.Rock
      case "B" => Shape.Paper
      case "C" => Shape.Scissors
end Opponent

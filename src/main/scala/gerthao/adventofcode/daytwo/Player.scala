package gerthao.adventofcode.daytwo

case class Player():
  def chooseShape(letter: String): Shape =
    letter match
      case "X" => Shape.Rock
      case "Y" => Shape.Paper
      case "Z" => Shape.Scissors

  def chooseRound(letter: String): Round =
    letter match
      case "X" => Round.Lose
      case "Y" => Round.Draw
      case "Z" => Round.Win
end Player
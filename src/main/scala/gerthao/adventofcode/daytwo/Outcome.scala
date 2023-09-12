package gerthao.adventofcode.daytwo

case class Outcome():
  def decideRound(opponentShape: Shape, playerShape: Shape): Round =
    (opponentShape, playerShape) match {
      case (_, _) if opponentShape == playerShape => Round.Draw
      case (Shape.Rock, Shape.Paper)              => Round.Win
      case (Shape.Rock, Shape.Scissors)           => Round.Lose
      case (Shape.Paper, Shape.Scissors)          => Round.Win
      case (Shape.Paper, Shape.Rock)              => Round.Lose
      case (Shape.Scissors, Shape.Rock)           => Round.Win
      case (Shape.Scissors, Shape.Paper)          => Round.Lose
    }

  def decideShape(opponentShape: Shape, playerDesiredOutcome: Round): Shape =
    (opponentShape, playerDesiredOutcome) match {
      case (_, Round.Draw)              => opponentShape
      case (Shape.Rock, Round.Win)      => Shape.Paper
      case (Shape.Rock, Round.Lose)     => Shape.Scissors
      case (Shape.Paper, Round.Win)     => Shape.Scissors
      case (Shape.Paper, Round.Lose)    => Shape.Rock
      case (Shape.Scissors, Round.Win)  => Shape.Rock
      case (Shape.Scissors, Round.Lose) => Shape.Paper
    }

package gerthao.adventofcode.daytwo

import gerthao.adventofcode.PipeOps.*

import scala.io.Source

object DayTwo:
  type Decision = (String, String)
  type Score    = Int
  type Game     = List[Decision]
  def main(args: Array[String]): Unit =
    playGame(Opponent(), Player(), Outcome(), readGame("Game"))
    playGamePartTwo(Opponent(), Player(), Outcome(), readGame("Game"))
  end main

  private def playGame(opponent: Opponent, player: Player, outcome: Outcome, game: Game): Score =
    game
      .foldLeft(0) { case (currentScore, (o, p)) =>
        val opponentShape = o |> opponent.chooseShape
        val playerShape   = p |> player.chooseShape
        val round         = (opponentShape, playerShape) |> outcome.decideRound
        val newScore      = currentScore + round.score + playerShape.score

        println("------------------------------------------------------------------------------")
        println(s"Opponent chooses: $opponentShape | Player chooses: $playerShape")
        println(s"Outcome: $round")
        println(s"Total Score: $newScore")
        println("------------------------------------------------------------------------------")
        println()

        newScore
      }
  end playGame

  private def playGamePartTwo(opponent: Opponent, player: Player, outcome: Outcome, game: Game): Score =
    game
      .foldLeft(0) { case (currentScore, (o, p)) =>
        val opponentShape = o |> opponent.chooseShape
        val round         = p |> player.chooseRound
        val playerShape   = (opponentShape, round) |> outcome.decideShape
        val newScore      = currentScore + round.score + playerShape.score

        println("------------------------------------------------------------------------------")
        println(s"Opponent chooses: $opponentShape | Outcome player wants: $round")
        println(s"Player choose: $playerShape")
        println(s"Total Score: $newScore")
        println("------------------------------------------------------------------------------")
        println()

        newScore
      }
  end playGamePartTwo

  private def readGame(fileName: String): Game =
    val reader = Source.fromFile(s"src/main/resources/day_two/$fileName.txt")
    val decisions = reader.getLines()
      .map(_.split(" "))
      .map { case Array(opponent, player) => opponent -> player }
      .toList

    reader.close()
    decisions
  end readGame










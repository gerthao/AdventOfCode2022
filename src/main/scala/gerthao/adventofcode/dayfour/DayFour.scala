package gerthao.adventofcode.dayfour

import gerthao.adventofcode.PipeOps.*
import gerthao.adventofcode.FileHelper

object DayFour:
  type AssignmentPair          = (Assignment, Assignment)
  type AssignmentCountCriteria = Iterable[AssignmentPair] => Int

  @main def run(): Unit =
    lazy val data = FileHelper.getLines("src/main/resources/dayfour/input.txt")
    val mainLogic = data |> parseData |> (_: AssignmentCountCriteria)

    val firstCriteria: AssignmentCountCriteria  = _.count { case (first, second) => (first contains second) || (second contains first) }
    val secondCriteria: AssignmentCountCriteria = _.count { _ overlaps _ }

    firstCriteria  |> mainLogic |> (result => println(s"[Part One] Assignment Count: $result"))
    secondCriteria |> mainLogic |> (result => println(s"[Part Two] Assignment Count: $result"))

  private def parseData(data: Iterable[String]): Iterable[AssignmentPair] =
    data
      .map { _.split(",") }
      .map { case Array(first, second) => (first.split("-"), second.split("-")) }
      .map { case (Array(firstMin, firstMax), Array(secondMin, secondMax)) =>
        (Assignment(firstMin.toInt, firstMax.toInt), Assignment(secondMin.toInt, secondMax.toInt))
      }
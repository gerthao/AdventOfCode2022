package gerthao.adventofcode.daythree

import gerthao.adventofcode.FileHelper
import gerthao.adventofcode.PipeOps.*

object DayThree:
  @main def run(): Unit =
    val priorityMap             = createPriorityMap
    lazy val prioritySumPartOne = priorityMap |> getPrioritySumPartOne
    lazy val prioritySumPartTwo = priorityMap |> getPrioritySumPartTwo

    println(s"[Pt. 1] Sum of priorities = $prioritySumPartOne")
    println(s"[Pt. 2] Sum of priorities = $prioritySumPartTwo")

  private def getPrioritySumPartOne(priorityMap: Map[Char, Int]): Int = FileHelper.read("src/main/resources/daythree/input.txt")(
    _.getLines()
      .map { line => line.splitAt(line.length / 2) }
      .map { case (first, second) => (first.toSet intersect second.toSet).headOption }
      .map { _.fold(0)(priorityMap.getOrElse(_, 0))  }
      .sum
  )

  private def getPrioritySumPartTwo(priorityMap: Map[Char, Int]): Int = FileHelper.read("src/main/resources/daythree/input.txt")(
    _.getLines()
      .grouped(3)
      .map { _.map(_.toSet).reduce(_ intersect _).headOption }
      .map { _.fold(0)(priorityMap.getOrElse(_, 0)) }
      .sum
  )

  private def createPriorityMap: Map[Char, Int] =
    val first          = 'a' to 'z'
    val firstPriority  = 1 to 26
    val second         = 'A' to 'Z'
    val secondPriority = 27 to 52

    val f = first zip firstPriority
    val s = second zip secondPriority

    (f ++ s).toMap

end DayThree


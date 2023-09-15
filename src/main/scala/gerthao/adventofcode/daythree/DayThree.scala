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


  private def getPrioritySumPartOne(priorityMap: Map[Char, Int]): Int = FileHelper.read("src/main/resources/day_three/input.txt")(
    _.getLines()
      .map { line => line.splitAt(line.length / 2) }
      .map { case (first, second) => (first.toSet intersect second.toSet).head }
      .map { char => priorityMap.getOrElse(char, 0) }
      .toVector
  ).sum

  private def getPrioritySumPartTwo(priorityMap: Map[Char, Int]): Int = FileHelper.read("src/main/resources/day_three/input.txt")(
    _.getLines()
      .grouped(3)
      .map (_.map(_.toSet).reduce(_ intersect _).head)
      .map { char => priorityMap.getOrElse(char, 0) }
      .toVector
  ).sum
  private def createPriorityMap: Map[Char, Int] =
    val first           = 'a' to 'z'
    val first_priority  = 1 to 26
    val second          = 'A' to 'Z'
    val second_priority = 27 to 52

    val f = first zip first_priority
    val s = second zip second_priority

    (f ++ s).toMap

end DayThree


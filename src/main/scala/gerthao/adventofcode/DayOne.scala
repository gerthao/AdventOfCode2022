package gerthao.adventofcode

import gerthao.adventofcode.PipeOps.*

import scala.io.Source
object DayOne:
  def main(args: Array[String]): Unit =
    loadFoodGroupedByElf |>
      findSumOfCaloriesByElf |>
      takeHighestCaloriesByElf(3) |>
      (_.sum) |>
      println

  private def loadFoodGroupedByElf: List[List[String]] =
    def extractValues(list: Iterator[String], acc: List[List[String]]): List[List[String]] =
      val (left, right) = list.span(_.nonEmpty)

      if right.nonEmpty
      then extractValues(right.drop(1), acc :+ (left.toList))
      else acc :+ (left.toList)
    end extractValues

    val reader = Source.fromFile("src/main/resources/ElfFoodCalorieList.txt")
    val result = extractValues(reader.getLines(), List.empty)

    reader.close()
    result
  end loadFoodGroupedByElf

  private def findSumOfCaloriesByElf(food: List[List[String]]): List[Int] =
    food
      .map(_.map(_.toInt))
      .map(_.sum)

  private def takeHighestCaloriesByElf(times: Int)(list: List[Int]): List[Int] =
    list.sorted(Ordering.Int.reverse).take(times)
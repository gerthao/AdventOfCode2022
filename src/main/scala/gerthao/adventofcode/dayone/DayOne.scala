package gerthao.adventofcode.dayone

import gerthao.adventofcode.PipeOps.*

import scala.annotation.tailrec
import scala.io.Source
object DayOne:
  def main(args: Array[String]): Unit =
    loadFoodGroupedByElf |>
      findSumOfCaloriesByElf |>
      takeHighestCaloriesByElf(3) |>
      (_.sum) |>
      println

  private def loadFoodGroupedByElf: List[List[Int]] =
    @tailrec
    def extractValues(list: Iterator[String], acc: List[List[Int]] = List.empty): List[List[Int]] =
      val (left, right) = list.span(_.nonEmpty)

      if right.nonEmpty
      then extractValues(right.drop(1), acc :+ left.map(_.toInt).toList)
      else acc :+ left.map(_.toInt).toList
    end extractValues

    val reader = Source.fromFile("src/main/resources/day_one/ElfFoodCalorieList.txt")
    val result = extractValues(reader.getLines)

    reader.close
    result
  end loadFoodGroupedByElf

  private def findSumOfCaloriesByElf(food: List[List[Int]]): List[Int] =
    food.map(_.sum)

  private def takeHighestCaloriesByElf(times: Int)(list: List[Int]): List[Int] =
    list.sorted(Ordering.Int.reverse).take(times)
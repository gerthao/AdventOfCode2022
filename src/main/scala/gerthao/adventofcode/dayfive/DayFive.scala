package gerthao.adventofcode.dayfive

import gerthao.adventofcode.PipeOps.|>

import scala.collection.mutable.Stack
import gerthao.adventofcode.FileHelper

import scala.io.BufferedSource
object DayFive:
  type CraneAction = Seq[Char] => Seq[Char]
  type Moves       = Seq[String]
  type BoxStack    = Seq[Char]
  type Boxes       = Seq[BoxStack]
  @main def run(): Unit =
    FileHelper.read("src/main/resources/dayfive/input.txt")(source =>
      val (boxes, moves) = parseData(source)

      val crateMover9000Action: CraneAction = _.reverse
      val crateMover9001Action: CraneAction = identity

      crateMover9000Action |> runMoves(boxes, moves) |> (result => println(s"[Pt 1] Result of Mover 9000: ${result.mkString("")}"))
      crateMover9001Action |> runMoves(boxes, moves) |> (result => println(s"[Pt 2] Result of Mover 9001: ${result.mkString("")}"))
    )

  private def parseData(source: BufferedSource): (Boxes, Moves) =
    lazy val generateIndices: Int => LazyList[Int] = (start: Int) => start #:: generateIndices(start + 4)
    val (boxes, moves) = source.getLines().splitAt(8)
    val parsedBoxes = boxes.map { b =>
        generateIndices(1)
          .takeWhile(_ < b.length)
          .map(b.charAt)
          .map(c => if c == ' ' then None else Some(c))
      }.map { b => if b.length >= 9 then b else b ++ Seq.fill(9 - b.length)(None) }
      .toSeq
      .transpose
      .map(_.flatten)

    (parsedBoxes , moves.drop(2).toSeq)
  end parseData

  private def runMoves(boxes: Boxes, moves: Moves)(craneAction: CraneAction): BoxStack =
    moves.foldLeft(boxes) {
      case (acc, s"move $amount from $source to $target") =>
        val first  = acc(source.toInt - 1)
        val second = acc(target.toInt - 1)
        val (chunkOne, chunkTwo) = first.splitAt(amount.toInt)

        acc
          .updated(source.toInt - 1, chunkTwo)
          .updated(target.toInt - 1, craneAction(chunkOne) ++ second)
    }.map(_.head)
  end runMoves
end DayFive

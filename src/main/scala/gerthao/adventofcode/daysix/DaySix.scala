package gerthao.adventofcode.daysix

import gerthao.adventofcode.FileHelper
import gerthao.adventofcode.PipeOps.|>

object DaySix:
  @main def run(): Unit =
    val startOfPacketMarker = getMarker(4)
    val startOfMessageMarker = getMarker(14)
    println(s"Start of packet marker result: $startOfPacketMarker")
    println(s"Start of packet message result: $startOfMessageMarker")

  private def getMarker(chunkSize: Int): Option[(Seq[Char], Int)] =
    val markerCriteria: Seq[Char] => Boolean = l => l.length == l.toSet.size
    FileHelper.read("src/main/resources/daysix/input.txt"): source =>
      source.sliding(chunkSize)
        .zipWithIndex
        .find { case (chunk, _) => chunk |> markerCriteria }
        .map { case (chunk, index) => (chunk, index + chunk.length) }
end DaySix


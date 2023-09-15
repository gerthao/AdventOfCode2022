package gerthao.adventofcode

import scala.io.{BufferedSource, Source}
import scala.util.{Failure, Success, Try}
object FileHelper:

  def read[T](filePath: String)(operation: BufferedSource => T): T =
    val file   = Source.fromFile(filePath)
    val result = operation(file)

    file.close()
    result

  def getLines(filePath: String): Seq[String] =
    val file = Source.fromFile(filePath)
    val result = file.getLines().toSeq

    file.close()
    result

package gerthao.adventofcode.dayfour

case class Assignment(min: Int, max: Int)
extension (assignment: Assignment)
  def contains(other: Assignment): Boolean =
    assignment.min <= other.min && assignment.max >= other.max

  def overlaps(other: Assignment): Boolean =
    val cond = (first: Assignment, second: Assignment) => first.max >= second.min && first.max <= second.max
    cond(assignment, other) || cond(other, assignment)


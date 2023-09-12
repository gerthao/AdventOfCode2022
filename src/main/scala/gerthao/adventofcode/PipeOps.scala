package gerthao.adventofcode

object PipeOps:
  extension [T] (x: T)
    def |>[U] (f: T => U) = f(x)

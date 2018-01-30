import scala.io.Source

object week5 {
  def removeAt[T](n: Int, xs: List[T]) = (xs take n) ::: (xs drop n + 1)

  def flatten(xs: List[Any]): List[Any] = xs match {
    case Nil => Nil
    case List() => List()
    case (head: List[Any]) :: tail => flatten(head) ::: flatten(tail)
    case head :: tail => head :: flatten(tail)
  }

  def msort(xs: List[Int]): List[Int] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[Int], ys: List[Int]): List[Int] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (x > y) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val (fst, snd) = xs splitAt n
      merge(msort(fst), msort(snd))
    }
  }

  def msort[T](xs: List[T])(lt: (T, T) => Boolean): List[T] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
        case (Nil, ys) => ys
        case (xs, Nil) => xs
        case (x :: xs1, y :: ys1) =>
          if (lt(x, y)) x :: merge(xs1, ys)
          else y :: merge(xs, ys1)
      }

      val (fst, snd) = xs splitAt n
      merge(msort(fst)(lt), msort(snd)(lt))
    }
  }

  val listaEnteros = List(2, -4, 5, 7, 1)
  //  msort(listaEnteros)((x:Int, y: Int) => x < y)

  def square(x: Int): Int = x * x

  def squareList(xs: List[Int]): List[Int] =
    xs match {
      case Nil => Nil
      case y :: ys => square(y) :: squareList(ys)
    }

  def squareList2(xs: List[Int]): List[Int] =
    xs map square

  def pack[T](xs: List[T]): List[List[T]] = xs match {
    case Nil => Nil
    //    case x :: _ => xs.takeWhile((y)=>x==y)::pack(xs.dropWhile((y)=>x==y))
    case x :: xs1 =>
      val (first, rest) = xs span (y => y == x)
      first :: pack(rest)
  }

  def encode[T](xs: List[T]): List[(T, Int)] =
    pack(xs) map (ys => (ys.head, ys.length))

  def mapFun[T, U](xs: List[T], f: T => U): List[U] =
    (xs foldRight List[U]())(f(_)::_)

  def lengthFun[T](xs: List[T]): Int =
    (xs foldRight 0) ((_, x:Int)=> x+1)
}


week5.removeAt(2, List(1, 2, 3, 4))
week5.flatten(List(List(1, List(9, 99)), 2, List(3, List(5, 8))))
week5.msort(List(2, -4, 5, 7, 1))
week5.squareList(List(1, 2, 3))
week5.squareList2(List(1, 2, 3))
week5.pack(List("a", "a", "a", "b", "c", "c", "a"))
week5.encode(List("a", "a", "a", "b", "c", "c", "a"))
week5.mapFun(List(1,2,3,4), (x:Int) => x*x)
week5.lengthFun(List(99))
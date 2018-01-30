package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    println(countChange(4,List(2,1)))
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) {
      1
    } else {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
    * Exercise 2
    */
  def balance(chars: List[Char]): Boolean = balance(chars, 0)

  def balance(chars: List[Char], counter: Int): Boolean = {
    if (counter >= 0) {
      if (chars.isEmpty && counter == 0) {
        true
      } else if (chars.isEmpty && counter != 0) {
        false
      } else if (chars.head == '(') {
        balance(chars.tail, counter + 1)
      } else if (chars.head == ')') {
        balance(chars.tail, counter - 1)
      } else {
        balance(chars.tail, counter)
      }
    } else {
      false
    }
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {
    countChangeWithCoinsSorted(money, coins.sorted)
  }

  def countChangeWithCoinsSorted(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1
    } else if (money < 0 || coins.isEmpty) {
      0
    } else if (coins.isEmpty && money >= 1) {
      0
    } else {
      countChangeWithCoinsSorted(money, coins.tail) + countChangeWithCoinsSorted(money - coins.head, coins)
    }
  }
}

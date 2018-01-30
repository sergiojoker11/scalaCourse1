package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains_is implemented_") {
    assert(contains(x => true, 100))
  }

  test("contains_negative numbers_itFindsIt") {
    assert(contains(singletonSet(-1), -1) == true)
  }

  test("contains_a number that is not in the set_itDoesntFindIt") {
    assert(contains(singletonSet(4), 1) == false)
  }

  test("contains_a number that is in the set_itFindsIt") {
    assert(contains(singletonSet(4), 4) == true)
  }

  test("set_creates a set from a list_itFindsAllTheElements") {
    assert(contains(set(List(1,2,3)), 1) == true)
    assert(contains(set(List(1,2,3)), 2) == true)
    assert(contains(set(List(1,2,3)), 3) == true)
  }

  test("set_creates a set from a list_itDoesntFindElementsNotPresent") {
    assert(contains(set(List(1,2,3)), 8) == false)
    assert(contains(set(List(1,2,3)), 6) == false)
    assert(contains(set(List(1,2,3)), -1) == false)
  }

  test("union_2SingletonSets_itFindsThose2Elements") {
    assert(contains(union(singletonSet(4), singletonSet(5)), 4) == true)
    assert(contains(union(singletonSet(4), singletonSet(5)), 5) == true)
  }

  test("union_2SingletonSets_itDoesntFindAnythingElse") {
    assert(contains(union(singletonSet(4), singletonSet(5)), -2) == false)
    assert(contains(union(singletonSet(4), singletonSet(5)), 7) == false)
  }

  test("intersect_2IntersectingSets_itFindsTheElement") {
    assert(contains(intersect(set(List(1,2,3)), set(List(3,4,5))), 3) == true)
  }

  test("intersect_2NotIntersectingSets_itDoesntEvenFindTheClosestPoints") {
    assert(contains(intersect(set(List(1,2,3)), set(List(5,6))), 3) == false)
    assert(contains(intersect(set(List(1,2,3)), set(List(5,6))), 5) == false)
  }

  test("diff_2IntersectingSets_itFindsOnlyElementsThatBelongToA&NotToB") {
    assert(contains(diff(set(List(1,2,3)), set(List(3,4,5))), 2) == true)
  }

  test("diff_2IntersectingSets_itDoesntFindElementsOutsideTheDiff") {
    assert(contains(diff(set(List(1,2,3)), set(List(3,4,5))), -1) == false)
    assert(contains(diff(set(List(1,2,3)), set(List(3,4,5))), 3) == false)
  }

  test("filter_2IntersectingSets_itFindsTheElement") {
    assert(contains(intersect(set(List(1,2,3)), set(List(3,4,5))), 3) == true)
  }

  test("filter_2NotIntersectingSets_itDoesntEvenFindTheClosestPoints") {
    assert(contains(filter(set(List(1,2,3)), set(List(5,6))), 3) == false)
    assert(contains(filter(set(List(1,2,3)), set(List(5,6))), 5) == false)
  }

  test("forall_setAndConditionInsideBoundaries_OK") {
    assert(forall(set(List(1,2)), x => x < 2000) == true)
  }

  test("forall_setAndConditionInsideAndPastTheBoundaries_ConditionSatisfiedWithSomeIrrelevantElementsOutsideTheBoundaries") {
    assert(forall(set(List(998,1001)), x => x < 2000) == true)
  }

  test("forall_setAndConditionWithinTheBoundaries_AllTheElementsContained") {
    assert(forall(set(List(1,2,3,4)), x => x < 3 && x > 1) == true)
  }

  test("forall_setOverpassesTheBoundariesAndConditionBeingSatisfied_ConditionSatisfiedButSetOutsideTheBoundaries") {
    assert(forall(set(List(2000,2005)), x => x < 3000) == true)
  }

  test("forall_notIntersectingAtAll_ConditionSatisfiedButSetOutsideTheBoundaries") {
    assert(forall(set(List(200,205)), x => x < 100) == true)
  }

  test("exists_setAndConditionInsideBoundaries_OK") {
    assert(exists(set(List(1,2)), x => x == 2) == true)
  }

  test("exists_setAndConditionInsideAndPastTheBoundaries_TheConditionSatisfiedAtLeastOnceWithSomeIrrelevantElementsOutsideTheBoundaries") {
    assert(exists(set(List(998,1001)), x => x == 998) == true)
  }

  test("exists_setInsideAndPastTheBoundariesAndConditionOutside_FalseThereIsntEvenOneElementWithinTheBoundaries") {
    assert(exists(set(List(998,1001)), x => x == 1001) == false)
  }

  test("exists_setOverpassesTheBoundariesAndConditionBeingSatisfied_ThereIsn'tEvenAnElementWithinTheBoundaries") {
    assert(exists(set(List(2000,2005)), x => x < 3000) == false)
  }

  test("exists_notIntersectingAtAll_ConditionSatisfiedButSetOutsideTheBoundaries") {
    assert(exists(set(List(200,205)), x => x < 100) == false)
  }

  test("map_wholeSetInsideBoundaries_functionIncrementApplied") {
    assert(contains(map(set(List(1,2)), x => x+1), 2) == true)
    assert(contains(map(set(List(1,2)), x => x+1), 3) == true)
  }

  test("map_setInsideAndOutsideBoundaries_functionIncrementAppliedOnlyToOneElement") {
    assert(contains(map(set(List(999,1000)), x => x+1), 1000) == true)
    assert(contains(map(set(List(999,1000)), x => x+1), 1001) == false)
  }

  test("map_wholeSetOutsideBoundaries_functionIncrementAppliedOnlyToOneElement") {
    assert(contains(map(singletonSet(1001), x => x+1), 1002) == false)
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }


}

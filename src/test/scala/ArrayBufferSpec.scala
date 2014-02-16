import org.scalatest.FlatSpec
import org.scalatest.Matchers

/** Spec-based tests for ArrayBuffer. */
class ArrayBufferSpec extends FlatSpec with Matchers {

  behavior of "ArrayBuffer"
  it should "throw IndexOutOfBoundsException as appropriate" in {
    val ab = new ArrayBuffer[Int]
    intercept[IndexOutOfBoundsException] {
      ab(-1)
    }
    intercept[IndexOutOfBoundsException] {
      ab(0)
    }
    ab.append(1)
    intercept[IndexOutOfBoundsException] {
      ab(-1)
    }
    ab(0)
    intercept[IndexOutOfBoundsException] {
      ab(1)
    }
  }

  behavior of "ArrayBuffer"
  it should "work as advertised for short sequences" in {
    val ab = new ArrayBuffer[Int]
    ab.append(1)
    ab.append(2)
    ab.append(4)
    ab(0) should equal { 1 }
    ab(1) should equal { 2 }
    ab(2) should equal { 4 }
  }

  behavior of "ArrayBuffer"
  it should "work as advertised for long sequences" in {
    val ab = new ArrayBuffer[Int]
    val range = (0 to 10000)
    range.foreach { i => ab.append(i + 3) }
    range.foreach { i => ab(i) should equal { i + 3 } }
  }

}

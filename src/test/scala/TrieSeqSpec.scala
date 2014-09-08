import org.scalatest.FlatSpec
import org.scalatest.Matchers

/** Spec-based tests for ArrayBuffer. */
class TrieSeqSpec extends FlatSpec with Matchers {

  behavior of "TrieSeq"
  it should "throw IndexOutOfBoundsException as appropriate" in {
    var ts = TrieSeq[Int]()
    intercept[IndexOutOfBoundsException] {
      ts(-1)
    }
    intercept[IndexOutOfBoundsException] {
      ts(0)
    }
    ts = ts.append(1)
    intercept[IndexOutOfBoundsException] {
      ts(-1)
    }
    ts(0)
    intercept[IndexOutOfBoundsException] {
      ts(1)
    }
    ts = ts.append(2)
    intercept[IndexOutOfBoundsException] {
      ts(-1)
    }
    ts(0)
    ts(1)
    intercept[IndexOutOfBoundsException] {
      ts(2)
    }
  }

  behavior of "TrieSeq"
  it should "work as advertised for short sequences" in {
    var ts = TrieSeq[Int]()
    ts = ts.append(1)
    ts = ts.append(2)
    ts = ts.append(4)
    ts(0) should equal { 1 }
    ts(1) should equal { 2 }
    ts(2) should equal { 4 }
  }

  behavior of "TrieSeq"
  it should "work as advertised for long sequences" in {
    var ts = TrieSeq[Int]()
    val range = (0 to 10000)
    range.foreach { i => ts = ts.append(i + 3) }
    range.foreach { i => ts(i) should equal { i + 3 } }
  }

}

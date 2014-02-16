object TrieSeq {

  def apply[A](): TrieSeq[A] = new TrieSeq[A](new Node[A])

  private class Node[A] (val buff: Array[Any] = Array()) {

    val MaxNodeSize = 32

    def append(a: A): Node[A] = {
      new Node(buff :+ a)
    }

    def apply(i: Int): A = {
      checkBounds(i)
      buff(i).asInstanceOf[A]
    }

    def update(i: Int, a: A): Node[A] = {
      checkBounds(i)
      new Node(buff.updated(i, a))
    }

    private def checkBounds(i: Int): Unit = {
      if (i < 0 || i >= buff.size) {
        throw new IndexOutOfBoundsException(i.toString)
      }
    }

  }

}

/** An immutable sequence that mimics scala.collection.immutable.Vector.
  * 
  * Only has append, update and apply operations.
  * 
  * Built to demonstrate effective constant time append operation.
  */
class TrieSeq[A] private (private val root: TrieSeq.Node[A]) extends Seq[A] {

  def append(a: A): TrieSeq[A] = new TrieSeq(root.append(a))

  def update(i: Int, a: A): TrieSeq[A] = new TrieSeq(root.update(i, a))

  def apply(i: Int): A = root.apply(i)

}

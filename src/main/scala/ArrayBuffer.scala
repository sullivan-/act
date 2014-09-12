/** A mutable sequence that mimics scala.collection.mutable.ArrayBuffer.
  *
  * Only has append, update and apply operations.
  *
  * Built to demonstrate amortized constant time append operation.
  */
class ArrayBuffer[E] extends Buffer[E] {

  private val StartSize = 16

  private var size = 0
  private var buff = new Array[Any](StartSize)

  def append(e: E): Unit = {
    expandBufferIfFull()
    buff.update(size, e)
    size += 1
  }
  
  private def expandBufferIfFull(): Unit = {
    if (buff.length == size) {
      val newbuff = new Array[Any](size * 2)
      Array.copy(buff, 0, newbuff, 0, size)
      buff = newbuff
    }
  } 

  def apply(i: Int): E = {
    checkBounds(i)
    buff(i).asInstanceOf[E]
  }

  def update(i: Int, e: E): Unit = {
    checkBounds(i)
    buff(i) = e
  }

  private def checkBounds(i: Int): Unit = {
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException(i.toString)
    }
  }

  def unappend(): E = {
    checkBounds(size - 1)
    size = size - 1
    val e = buff(size).asInstanceOf[E]
    buff(size) = null
    contractBufferIfWastedSpace()
    e
  }

  private def contractBufferIfWastedSpace(): Unit = {
    if (buff.length >= size * 2) {
      val newbuff = new Array[Any](size)
      Array.copy(buff, 0, newbuff, 0, size)
      buff = newbuff          
    }
  }

}

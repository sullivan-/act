/** A mutable sequence that mimics scala.collection.mutable.ArrayBuffer.
  *
  * Only has append, update and apply operations.
  *
  * Built to demonstrate amortized constant time append operation.
  */
class ArrayBuffer[A] extends Buffer[A] {

  private val StartSize = 16

  private var size = 0
  private var buff = new Array[Any](StartSize)

  def append(a: A): Unit = {
    expandBufferIfFull()
    buff.update(size, a)
    size += 1
  }
  
  private def expandBufferIfFull(): Unit = {
    if (buff.length == size) {
      val newbuff = new Array[Any](size * 2)
      Array.copy(buff, 0, newbuff, 0, size)
      buff = newbuff
    }
  } 

  def apply(i: Int): A = {
    checkBounds(i)
    buff(i).asInstanceOf[A]
  }

  def update(i: Int, a: A): Unit = {
    checkBounds(i)
    buff(i) = a
  }

  private def checkBounds(i: Int): Unit = {
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException(i.toString)
    }
  }
 
  def removeFromEnd(): A = {
    checkBounds(size - 1)
    size = size - 1
    val a = buff(size).asInstanceOf[A]
    buff(size) = null
    contractBufferIfWastedSpace()
    a
  }

  private def contractBufferIfWastedSpace(): Unit = {
    if (buff.length >= size * 2) {
      val newbuff = new Array[Any](size)
      Array.copy(buff, 0, newbuff, 0, size)
      buff = newbuff          
    }
  }

}

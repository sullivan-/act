class ExpandingArrayBuffer[E] extends Buffer[E] {

  private val StartSize = 16
  private val ExpansionRatio = 2

  private var size = 0
  private var buff = new Array[Any](StartSize)

  def append(e: E): Unit = {
    expandBufferIfFull()
    buff.update(size, e)
    size += 1
  }

  def unappend(): E = {
    checkNonEmpty()
    size = size - 1
    val e = buff(size).asInstanceOf[E]
    buff(size) = null
    e
  }

  def update(i: Int, e: E): Unit = {
    checkBounds(i)
    buff(i) = e
  }

  def apply(i: Int): E = {
    checkBounds(i)
    buff(i).asInstanceOf[E]
  }
  
  private def expandBufferIfFull(): Unit = {
    if (buff.length == size) {
      val newbuff = new Array[Any](size * ExpansionRatio)
      Array.copy(buff, 0, newbuff, 0, size)
      buff = newbuff
    }
  } 

  private def checkNonEmpty(): Unit = {
    if (size == 0) {
      throw new NoSuchElementException()
    }
  }

  private def checkBounds(i: Int): Unit = {
    if (i < 0 || i >= size) {
      throw new IndexOutOfBoundsException(i.toString)
    }
  }

}

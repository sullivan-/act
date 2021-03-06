class NaiveArrayBuffer[E] extends Buffer[E] {

  private val StartSize = 16

  private var size = 0
  private var buff = new Array[Any](StartSize)

  def append(e: E): Unit = {
    buff(size) = e
    size += 1
  }

  def unappend(): E = {
    checkNonEmpty()
    size -= 1
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

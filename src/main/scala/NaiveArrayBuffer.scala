
class NaiveArrayBuffer[E] extends Buffer[E] {

  private val StartSize = 16

  private var size = 0
  private var buff = new Array[Any](StartSize)

  def append(e: E): Unit = {
    buff.update(size, e)
    size += 1
  }

  def unappend(): E = {
    size = size - 1
    val e = buff(size).asInstanceOf[E]
    buff(size) = null
    e
  }

  def update(i: Int, e: E): Unit = {
    buff(i) = e
  }

  def apply(i: Int): E = {
    buff(i).asInstanceOf[E]
  }

}

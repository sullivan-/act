/** an efficient mutable sequence. all operations are
  * constant time or amortized constant time */
trait Buffer[E] {
  
  /** adds a new element to the end */
  def append(e: E): Unit
  
  /** removes and returns an element from the end */
  @throws[NoSuchElementException]("if the buffer is empty")
  def unappend(): E
  
  /** replaces the element stored at the index with a new
    * element */
  @throws[IndexOutOfBoundsException](
    "if the index is negative or greater than or equal " +
    "to the size of the buffer")
  def update(i: Int, e: E): Unit

  /** retrieves the element stored at the given index */
  @throws[IndexOutOfBoundsException](
    "if the index is negative or greater than or equal " +
    "to the size of the buffer")
  def apply(i: Int): E                                                                                                               
}

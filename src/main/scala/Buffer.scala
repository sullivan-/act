/** an efficient mutable sequence. all operations are constant time or
  * amortized constant time */
trait Buffer[A] {

  /** adds a new element to the end */
  def append(a: A): Unit

  /** removes an element from the end */
  def unappend(): A

  /** retrieves the element stored at the given index */
  def apply(i: Int): A

  /** replaces the element stored at the index with a new element */
  def update(i: Int, a: A): Unit
}

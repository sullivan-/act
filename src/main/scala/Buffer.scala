/** an efficient mutable sequence. all operations are constant time or
  * amortized constant time */
trait Buffer[E] {

  /** adds a new element to the end */
  def append(e: E): Unit

  /** removes an element from the end */
  def unappend(): E

  /** replaces the element stored at the index with a new element */
  def update(i: Int, e: E): Unit

  /** retrieves the element stored at the given index */
  def apply(i: Int): E
}

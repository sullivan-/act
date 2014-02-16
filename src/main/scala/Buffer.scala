trait Buffer[A] {
  def append(a: A): Unit
  def update(i: Int, a: A): Unit
  def apply(i: Int): A
}

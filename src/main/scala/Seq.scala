trait Seq[A] {
  def append(a: A): Seq[A]
  def update(i: Int, a: A): Seq[A]
  def apply(i: Int): A
}

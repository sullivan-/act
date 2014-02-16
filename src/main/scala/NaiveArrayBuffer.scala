
    class NaiveArrayBuffer[A] {

      private val StartSize = 16

      private var size = 0
      private var buff = new Array[Any](StartSize)

      def append(a: A): Unit = {
        buff.update(size, a)
        size += 1
      }

      def update(i: Int, a: A): Unit = {
        checkBounds(i)
        buff(i) = a
      }

      def apply(i: Int): A = {
        checkBounds(i)
        buff(i).asInstanceOf[A]
      }

      private def checkBounds(i: Int): Unit = {
        if (i < 0 || i >= size) {
          throw new IndexOutOfBoundsException(i.toString)
        }
      }

    }

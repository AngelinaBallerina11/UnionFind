import org.apache.commons.lang3.time.StopWatch
import kotlin.random.Random
import kotlin.test.assertTrue

fun main() {
    println("Hello, Algorithms 1 course: Quick Find Example")

    val sw = StopWatch().apply { start() }
    weighted(10)
    println()
    println(sw.formatTime())
}

private fun weighted(num: Int) {
    val w = WeightedUnionFind(num)
    with(w) {
        repeat(num) {
            union(Random.nextInt(num), Random.nextInt(num))
        }
        /* union(4, 3)
         union(3, 8)
         union(6, 5)
         union(9, 4)
         union(2, 1)
         union(5, 0)
         union(7, 2)
         union(6, 1)
         union(7, 3)*/
    }

   /* try {
        assertTrue { w.isConnected(3, 4) }
        assertTrue { w.isConnected(3, 8) }
        assertTrue { w.isConnected(3, 9) }
    } catch (ex: Throwable) {
        println("ERROR")
        w.print()
        println()
    }*/
    w.print()
}

private fun quickUnion() {
    val qu = QuickUnion(100)

    with(qu) {
        union(4, 3)
        union(3, 8)
        union(6, 5)
        union(9, 4)
    }

    //assertTrue { qu.isConnected(3, 4) }
}

private fun quickFind() {
    val uf = UnionFind(10)

    with(uf) {
        union(0, 5)
        union(5, 6)
        union(1, 2)
        union(2, 7)
        union(8, 3)
        union(3, 4)
        union(4, 9)
    }

    assertTrue { uf.isConnected(0, 6) }
    assertTrue { uf.isConnected(1, 7) }
    assertTrue { uf.isConnected(8, 9) }
    assertTrue { uf.isConnected(3, 9) }
    assertTrue { uf.isConnected(4, 8) }
}

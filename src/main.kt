import org.apache.commons.lang3.time.StopWatch
import kotlin.random.Random
import kotlin.test.assertTrue

fun main() {
    println("Hello, Algorithms 1 course: Quick Find Example")

    val sw = StopWatch()
    val N = 64000
    val algorithm = weighted(N)
    println()
    //println(sw.formatTime())

    val times = mutableListOf<Long>()
    repeat(200) {
        sw.reset()
        sw.start()
        algorithm.isConnected(Random.nextInt(N), Random.nextInt(N))
        sw.stop()
        times.add(sw.nanoTime)
    }

    println("Average time for N = $N is ${times.average()} ns")
}

private fun weighted(num: Int): WeightedUnionFind {
    val w = WeightedUnionFind(num)
    with(w) {
        repeat(num) {
            union(Random.nextInt(num), Random.nextInt(num))
        }
    }
    //w.print()
    return w
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

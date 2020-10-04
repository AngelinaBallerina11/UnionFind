import WeightedUnionFind.Root
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class WeightedUnionFindTest {
    lateinit var w: WeightedUnionFind

    @BeforeEach
    fun setUp() {
        w = WeightedUnionFind(10)
    }

    @Test
    fun `when all are roots`() {
        repeat(5) { N ->
            assertEquals(Root(N, 0), w.getRoot(N))
        }
    }

    @Test
    fun `when tree size is 1`() {
        with(w) {
            union(4, 3)
        }

        assertEquals(Root(4, 1), w.getRoot(3))
    }

    @Test
    fun `when tree size is 2`() {
        with(w) {
            union(4, 3)
            union(3, 8)
        }

        assertEquals(Root(4, 2), w.getRoot(3), "added 3")
        assertEquals(Root(4, 2), w.getRoot(8), "added 8")
    }

    @Test
    fun `count children when all are roots`() {
        val list = arrayListOf(0, 1, 2, 3, 4, 5)
        repeat(5) {
            assertEquals(0, w.countChildren(list, it))
        }
    }

    @Test
    fun `count children when there is one tree with 1 child - depth 1`() {
        val list = arrayListOf(0, 1, 2, 4, 4, 5)
        assertEquals(1, w.countChildren(list, 4))
    }

    @Test
    fun `count children when there is one tree with 2 kids - depth 1`() {
        val list = arrayListOf(0, 1, 2, 4, 4, 5, 4)
        assertEquals(2, w.countChildren(list, 4))
    }

    @Test
    fun `count children when there is one tree with 3 kids - depth 1`() {
        val list = arrayListOf(1, 1, 1, 1, 4, 5, 4)
        assertEquals(3, w.countChildren(list, 1))
    }

    @Test
    fun `count children when there is one tree with 3 kids - depth 2`() {
        val list = arrayListOf(6, 2, 6, 4, 4, 6, 6, 2, 4, 4)
        assertEquals(5, w.countChildren(list, 6))
    }

    @Test
    fun `count children when there is one tree with 6 kids - depth 3`() {
        val list = arrayListOf(6, 2, 6, 7, 4, 6, 6, 2, 4, 4)
        assertEquals(6, w.countChildren(list, 6))
    }

    @Test
    fun `count children when there is one tree with 7 kids - depth 4`() {
        val list = arrayListOf(6, 2, 6, 7, 4, 6, 6, 2, 3, 4)
        assertEquals(7, w.countChildren(list, 6))
    }

}
class WeightedUnionFind(private val count: Int) : IUnionFind {

    val points = arrayListOf<Int>().apply { repeat(count) { add(it) } }

    /* ID 1 becomes root of ID 2 */
    override fun union(id1: Int, id2: Int) {
        // println("union($id1, $id2)")
        if (!isConnected(id1, id2)) {
            val root1 = getRoot(id1)
            val root2 = getRoot(id2)
            if (points.count { it == root1 } >= points.count { it == root2 }) {
                points.forEachIndexed { index, i ->
                    if (i == root2) points[index] = root1
                }
            } else {
                points.forEachIndexed { index, i ->
                    if (i == root1) points[index] = root2
                }
            }
        }
    }

    /* have the same root */
    override fun isConnected(id1: Int, id2: Int): Boolean {
        val root1 = getRoot(id1)
        val root2 = getRoot(id2)
        return root1 == root2
    }

    fun isRoot(id: Int): Boolean = points[id] == id

    /**
     * @return tempId is root id
     */
    fun getRoot(id: Int): Int {
        var tempId: Int = id
        while (tempId != points[id]) {
            tempId = points[id]
        }
        return tempId
    }

    fun countChildren(points: List<Int>, rootId: Int): Int {
        var size = 0
        var children = mutableListOf<Int>()
        points.forEachIndexed { index, i ->
            if (i == rootId && index != rootId) {
                children.add(index)
            }
        }
        if (children.size > 0) {
            size += children.size
            do {
                val grandChildren = find(points, children)
                size += grandChildren.size
                children = grandChildren
            } while (grandChildren.size > 0)
        }
        return size
    }

    private fun find(
            bigList: List<Int>,
            children: List<Int>,
            grandChildren: MutableList<Int> = arrayListOf<Int>()
    ): MutableList<Int> {
        children.forEachIndexed { index, potentialRootId ->
            bigList.forEachIndexed { index, i ->
                if (i == potentialRootId && index != potentialRootId) {
                    // it is a child
                    grandChildren.add(index)
                }
            }
        }
        return grandChildren
    }

    fun print() {
        points.forEachIndexed { index, i -> print("($index)$i, ") }
    }
}
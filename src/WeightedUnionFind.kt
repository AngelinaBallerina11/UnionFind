class WeightedUnionFind(private val count: Int) : IUnionFind {

    val points = arrayListOf<Int>().apply { repeat(count) { add(it) } }
    val size = arrayListOf<Int>().apply { repeat(count) { add(1) } }

    /* ID 1 becomes root of ID 2 */
    override fun union(id1: Int, id2: Int) {
        // println("union($id1, $id2)")
        if (!isConnected(id1, id2)) {
            val root1 = getRoot(id1)
            val root2 = getRoot(id2)
            if (root1 == root2) return
            if (size[root1] < size[root2]) {
                points[root1] = root2
                size[root2] += size[root1]
            } else {
                points[root2] = root1
                size[root1] += size[root2]
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
        while (tempId != points[tempId]) {
            tempId = points[tempId]
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
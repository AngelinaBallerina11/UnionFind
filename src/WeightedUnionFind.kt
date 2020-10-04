class WeightedUnionFind(private val count: Int) : IUnionFind {

    val points = arrayListOf<Int>().apply { repeat(count) { add(it) } }

    /* ID 1 becomes root of ID 2 */
    override fun union(id1: Int, id2: Int) {
        println("union($id1, $id2)")
        if (!isConnected(id1, id2)) {
            val root1 = getRoot(id1)
            val root2 = getRoot(id2)
            if (root1.numOfKids >= root2.numOfKids) {
                points[root2.rootId] = root1.rootId
            } else {
                points[root1.rootId] = root2.rootId
            }
        }
    }

    /* have the same root */
    override fun isConnected(id1: Int, id2: Int): Boolean {
        val root1 = getRoot(id1).rootId
        val root2 = getRoot(id2).rootId
        return root1 == root2
    }

    fun isRoot(id: Int): Boolean = points[id] == id

    /**
     * @return tempId is root
     * @return count tree size
     */
    fun getRoot(id: Int): Root {
        var tempId: Int = id
        var size = 0
        while (tempId != points[id]) {
            tempId = points[id]
        }
        if (size == 0) {
            val kids = countChildren(points, tempId)
            size = if (kids > 0) kids else 0
        }
        return Root(tempId, size)
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

    fun find(
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
        points.forEach { print("$it, ") }
    }

    data class Root(
            val rootId: Int,
            val numOfKids: Int
    )
}
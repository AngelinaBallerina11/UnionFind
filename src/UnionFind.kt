class UnionFind(numberOfPoints: Int) : IUnionFind {

    private var minValue = 0
    private var points: MutableList<Int> = generateSequence {
        (minValue++).takeIf { it < numberOfPoints }
    }.toMutableList()

    /* too slow N^2 time */
    override fun union(id1: Int, id2: Int) {
        if (!isConnected(id1, id2)) {
            val newLink = points[id1]
            val oldLink = points[id2]
            points.replaceAll { if (it == oldLink) newLink else it }
        }
    }

    override fun isConnected(id1: Int, id2: Int): Boolean {
        return points[id1] == points[id2]
    }
}
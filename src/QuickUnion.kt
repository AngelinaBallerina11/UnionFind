/**
 * Tree implementation
 */
class QuickUnion(numberOfPoints: Int): IUnionFind {

    private var minValue = 0
    private var points: MutableList<Int> = generateSequence {
        (minValue++).takeIf { it < numberOfPoints }
    }.toMutableList()

    /* id 2 will be higher (root) in the tree */
    override fun union(id1: Int, id2: Int) {
        if (!isConnected(id1, id2)) {
            if (isRoot(id2))
                points[id1] = points[id2]
            else
                points[id1] = findRoot(id2)
        }
    }

    private fun findRoot(id: Int): Int {
        var possibleRoot = id
        while (!isRoot(possibleRoot)) {
            possibleRoot = points[points[id]]
        }
        return possibleRoot
    }

    private fun isRoot(id: Int): Boolean {
        return id == points[id]
    }

    override fun isConnected(id1: Int, id2: Int): Boolean {
        return points[id1] == points[id2]
    }
}
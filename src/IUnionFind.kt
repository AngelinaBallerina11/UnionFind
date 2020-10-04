interface IUnionFind {
    fun union(id1: Int, id2: Int)
    fun isConnected(id1: Int, id2: Int): Boolean
}
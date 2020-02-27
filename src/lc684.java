/**
 * @author qhhu
 * @date 2020/2/26 - 10:15
 *
 * [684] 冗余连接
 *
 * 题目: 有一系列的边连成的图, 找出一条边, 移除它之后该图能够成为一棵树.
 *
 * 难度: medium
 *
 * 思路: 并查集.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int[] findRedundantConnection(int[][] edges) {
        int len = edges.length;
        UnionFind uf = new UnionFind(len);
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (uf.isSameSet(u, v)) {
                // 两相邻的节点 u 和 v 有有共同的 head, 说明在连通图中, 形成了环, 即边 e 为需要删除的边.
                return e;
            }
            //并集, 将 u 连到 v 上, 方便下次快速寻找 head.
            uf.union(u, v);
        }
        return new int[]{-1, -1};
    }

    private class UnionFind {

        private int[] father;

        UnionFind(int len) {
            // 初始化每个节点的 head 为自己.
            father = new int[len + 1];
            for (int i = 1; i <= len; i++) {
                father[i] = i;
            }
        }

        void union(int a, int b) {
            int aHead = findHead(a);
            int bHead = findHead(b);
            if (aHead != bHead) {
                father[aHead] = bHead;
            }
        }

        int findHead(int num) {
            int f = father[num];
            if (f != num) {
                f = findHead(f);
            }
            // 压缩路径
            father[num] = f;
            return f;
        }

        boolean isSameSet(int u, int v) {
            return findHead(u) == findHead(v);
        }
    }
}

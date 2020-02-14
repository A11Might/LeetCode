import java.util.PriorityQueue;

/**
 * @author qhhu
 * @date 2020/2/13 - 10:22
 *
 * [378] 有序矩阵中第K小的元素
 *
 * 题目: 给定一个n * n的矩阵, 其中每行和每列元素的是升序排列, 返回矩阵中第k小的元素.
 *
 * 难度: medium
 *
 * 思路: 1. 使用小根堆, 去掉 k - 1 个堆顶元素, 此时堆顶元素就是第 k 的数
 *      2. 二分搜索, 矩阵最左上角为最小值, 最右下角为最大值, 我们在此范围内进行二分搜索.
 *         (有点类似找出数组中出现的次数超过数组长度的一半的数字中, 使用快排的partition分割数组, 分割位置为n / 2时, 找到该数字)
 */
class Solution {
    /**
     * 时间复杂度: O(m * n * n) (遍历所有节点m * n; 优先队列类似选择排序, 每次出队时选出最小的n)
     * 空间复杂度: O(n) (队列中的元素个数为列数)
     */
    public int kthSmallest(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length;
        PriorityQueue<Tuple> pq = new PriorityQueue<>(
                (o1, o2) -> o1.val - o2.val
        );
        for (int j = 0; j < cols; j++) {
            pq.offer(new Tuple(0, j, matrix[0][j]));
        }
        // 使用小根堆, 去掉 k - 1 个堆顶元素, 此时堆顶元素就是第 k 的数
        for (int i = 0; i < k - 1; i++) {
            Tuple t = pq.poll();
            // 当前元素为当前剩余元素中的最小值, 出队后. 当前最小值在右侧或下侧, 放入优先队列中比较.
            if (t.row == rows - 1) continue;
            pq.offer(new Tuple(t.row + 1, t.col, matrix[t.row + 1][t.col]));
        }

        return pq.poll().val;
    }

    // 用于保存节点位置和值的信息
    private class Tuple {
        public int row, col, val;
        public Tuple(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(1)
     */
    public int kthSmallest2(int[][] matrix, int k) {
        int rows = matrix.length, cols = matrix[0].length;
        int lo = matrix[0][0], hi = matrix[rows - 1][cols - 1];
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            int cnt = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (matrix[i][j] > mid) break;
                    cnt++;
                }
            }
            if (cnt < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }
}

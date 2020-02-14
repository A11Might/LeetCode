/**
 * @author qhhu
 * @date 2020/2/13 - 10:22
 *
 * [566] 重塑矩阵
 *
 * 题目: 给定一个矩阵matrix以及两个整数r和c, 分别表示原矩阵和想要重构的矩阵的行数与列数. 重构后的矩阵需要将原矩阵的所有元素
 *      以相同的行遍历顺序`填充`. 如果具有给定参数的reshape操作是可行且合理的, 则输出新的重塑矩阵; 否则, 输出原始矩阵.
 *
 * 难度: 简单
 *
 * 思路: 1. 使用队列, 以行方式读取原矩阵所有元素并存入队列中, 后遍历队列再按逐行排列成为新矩阵
 *      2. 按行遍历原矩阵的同时逐行排列新矩阵
 *          a. 在将数字放入结果数组时, 我们固定一个特定的行, 并继续增加列数, 直到我们到达c指示的所需列的末尾. 此时, 我们通过
 *             递增来更新行索引, 并将列索引重置为从0开始.
 *          b. 使用count变量, 该变量对于遍历的每个元素都会递增. 但是, 要将count转换回列数为转换回列数为c的二维矩阵索引, 我们
 *             可以获得的二维矩阵索引, 我们可以获得res[count / c][count ％ c]的索引, 其中的索引, 其中count / c是行号
 *             和count ％ c是列数字
 */
class Solution {
    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(1)
     */
    public int[][] matrixReshape2(int[][] nums, int r, int c) {
        int rows = nums.length, cols = nums[0].length;
        if (rows * cols != r * c) return nums;
        int[][] newMatrix = new int[r][c];
        // 当前元素在新数组中的位置newMatrix[cr][cc]
        int cr = 0, cc = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                newMatrix[cr][cc++] = nums[i][j];
                // 新数组当前位置到达最后一列, 换行
                if (cc == c) {
                    cc = 0;
                    cr++;
                }
            }
        }

        return newMatrix;
    }

    /**
     * 时间复杂度: O(m * n)
     * 空间复杂度: O(1)
     */
    public int[][] matrixReshape3(int[][] nums, int r, int c) {
        int rows = nums.length, cols = nums[0].length;
        if (rows * cols != r * c) {
            return nums;
        }
        int[][] newMatrix = new int[r][c];
        int cnt = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // 根据cnt值, 计算出当前元素在新数组中的位置
                newMatrix[i][j] = nums[cnt / cols][cnt % cols];
                cnt++;
            }
        }
        return newMatrix;
    }
}

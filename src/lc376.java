import java.util.Arrays;

/**
 * @author qhhu
 * @date 2019/11/27 - 9:31
 *
 * [376] 摆动序列
 *
 * 题目：如果连续数字之间的差值严格地在正数和负数之间交替，则该序列成为摆动序列，少于两个元素的序列也是摆动序列。
 *      给定一个整数序列，返回作为摆动序列的最长子序列的长度。
 *
 * 难度：medium
 *
 * 思路：动态规划。
 */
class Solution {
    /**
     * 划分型动态规划：有一种将当前元素接在 dp[i] 后面变成一段这种状态转移的思想。
     * 状态表示：up[i] 表示以第 i 个元素结尾的上升摆动序列的长度，down[i] 表示以第 i 个元素结尾的下降摆动序列的长度。
     * 状态转移方程：如下。
     * 初始状态：每个元素都是长度为 1 的摆动序列。
     * 时间复杂度：O(n ^ 2)
     * 空间复杂度：O(n)
     */
    public int wiggleMaxLength1(int[] nums) {
        if (nums.length == 0) return 0;
        int len = nums.length;
        int[] up = new int[len];
        int[] down = new int[len];

        // 决策边界
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        // 状态转移
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    up[i] = Math.max(up[i], down[j] + 1);
                } else if (nums[i] < nums[j]) {
                    down[i] = Math.max(down[i], up[j] + 1);
                }
            }
        }

        // 关键：
        // 最后一个元素的 up, down 序列是最大值。代码中其实说清楚了。up[i] 一定是大于等于 down[i-1]。down[i] 一定是大于等于 up[i-1]。
        // 另外，从逻辑上来说：对于一个队列，我们在队列后加入一个新元素，新的最长摆动队列长度只会更长，最差也是与之前相等。
        return Math.max(up[len - 1], down[len - 1]);
    }

    /**
     * 划分型动态规划：有一种将当前元素接在 dp[i] 后面变成一段这种状态转移的思想。
     * 状态表示：up[i] 表示以第 i 个元素结尾的上升摆动序列的长度，down[i] 表示以第 i 个元素结尾的下降摆动序列的长度。
     * 状态转移方程：最后一个元素的 up, down 序列是最大值。代码中其实说清楚了。up[i] 一定是大于等于 down[i-1]。down[i] 一定是大于等于
     *             up[i-1]。外，从逻辑上来说：对于一个队列，我们在队列后加入一个新元素，新的最长摆动队列长度只会更长，最差也是与之前相等。
     *             所以如下直接通过前一个 dp 值更新当前 dp 值。
     * 初始状态：每个元素都是长度为 1 的摆动序列。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length < 2) return nums.length;
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }

        return Math.max(down, up);
    }
}

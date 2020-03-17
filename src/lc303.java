/**
 * @author qhhu
 * @date 2019/12/7 - 14:46
 *
 * [303] 区域和检索 - 数组不可变
 *
 * 题目：给定一个整数数组nums，返回数组 [i, j] 范围内元素的总和。
 *
 * 难度：easy
 *
 * 思路：一维前缀和，[i, j] 内元素总和等于前 j + 1 项和减去前 i 项和。
 */
class NumArray {

    private int[] sum;

    public NumArray(int[] nums) {
        int len = nums.length;
        // sum[i] 表示前 i 项和
        sum = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
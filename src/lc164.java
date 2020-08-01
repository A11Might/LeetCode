/**
 * @author qhhu
 * @date 2020/7/29 - 14:49
 *
 * 题目：在给定的无序数组中，找到排序后相邻元素之间最大的差值
 *
 * 难度：hard
 *
 * 思路：桶和鸽巢原理，建立 n + 1 个桶，将数组中的 n 个数放入桶中，因为桶的数量是大于数的数量，所以肯定有一个空桶，
 *      这就保证最大的差值的两个数不在同一个桶中，在每个桶中我们只用保存最大值和最小值，然后计算两个相邻的非空桶中相邻元素的最大差值
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        int n = nums.length;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        if (min == max) return 0;
        // 创建 n + 1 个桶，每个桶只存储两个数，当前桶中的最大值和最小值
        // 创建 n + 1 个桶，是为了保证最大差值不在同一个桶中，所以每个桶中我们只用保存最大值和最小值
        boolean[] hasNum = new boolean[n + 1];
        int[] mins = new int[n + 1];
        int[] maxs = new int[n + 1];
        for (int num : nums) {
            // 当前元素所在桶的位置，(num - min) * n 可能会溢出
            int idx = (int)(((long)(num - min) * n) / (long)(max - min));
            mins[idx] = hasNum[idx] ? Math.min(mins[idx], num) : num;
            maxs[idx] = hasNum[idx] ? Math.max(maxs[idx], num) : num;
            hasNum[idx] = true;
        }
        // 计算两个相邻的非空桶中相邻元素的最大差值
        // 也就是后一个桶中的最小值减去前一个桶中的最大值
        int lastMax = maxs[0], ans = 0;
        for (int i = 1; i <= n; i++) {
            if (hasNum[i]) {
                ans = Math.max(ans, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }
        return ans;
    }
}
import java.util.HashMap;

/**
 * @author qhhu
 * @date 2020/2/14 - 8:53
 *
 * [697] 数组的度
 *
 * 题目: 给定一个非空且只包含非负数的整数数组nums, 数组的度为数组内任意元素出现频率的最大值, 返回与nums有相同大小的度的最短连续子数组的长度
 *
 * 难度: easy
 *
 * 思路: 计算数组nums的度数, 比较所有与数组度数相同的连续子数组的长度, 取最短值.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int findShortestSubArray(int[] nums) {
        int len = nums.length;
        HashMap<Integer, Integer> numsCnt = new HashMap<>(); // (num, time)
        HashMap<Integer, Integer> numsFirstIndex = new HashMap<>(); // (num, firstIndex)
        HashMap<Integer, Integer> numsLastIndex = new HashMap<>(); // (num, lastIndex)
        for (int i = 0; i < len; i++) {
            numsCnt.put(nums[i], numsCnt.getOrDefault(nums[i], 0) + 1);
            if (!numsFirstIndex.containsKey(nums[i])) {
                numsFirstIndex.put(nums[i], i);
            }
            numsLastIndex.put(nums[i], i);
        }

        // 因为可能有多个与最大度相同的连续子数组, 需比较选出最短的
        int maxCnt = 0;
        for (int num : nums) {
            maxCnt = Math.max(maxCnt, numsCnt.get(num));
        }

        int ret = nums.length;
        for (int num : nums) {
            int cnt = numsCnt.get(num);
            if (cnt != maxCnt) continue;
            ret = Math.min(ret, numsLastIndex.get(num) - numsFirstIndex.get(num) + 1);
        }
        return ret;
    }
}

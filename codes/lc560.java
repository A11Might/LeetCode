import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=560 lang=java
 *
 * [560] 和为K的子数组
 * 
 * 题目：在整数数组中找到和为k的连续子数组的个数
 * 
 * 思路：使用累加和原型，(sum - k) + k = sum
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // (sum, sumAppearTimes)
        map.put(0, 1); // 不加入会导致无法计算从0开始符合条件的子数组  (sum - k) + k = sum, sum == k
        int sum = 0;
        int res = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}


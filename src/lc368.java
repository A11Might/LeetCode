import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/12/12 - 10:25
 *
 * [368] 最大整除子集
 *
 * 题目: 给定无重复正整数集合, 返回其中最大的整除子集(子集中任意一对a, b都要满足a % b == 0 或者 b % a == 0)
 *       (如果有多个目标子集，返回其中任何一个均可)
 *
 * 难度: medium
 *
 * 思路: 动态规划记录过程, f(n)为以i结尾的最大整除子集的长度
 *       状态转移方程: f(n) = max(f(i) + 1) if nums[n] % nums[i] == 0 0 <= i < n and sort(nums)
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(n)
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len]; // dp[i]为以i结尾的最大整除子集的长度
        int[] pre = new int[len]; // 以i结尾的最大整除子集中排在在i之前的元素的位置
        int maxSize = 0; // 最大整除子集的长度
        int index = -1; // 当前最大整除子集的最后一个元素位置
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            dp[i] = 1; // 实例[1]
            pre[i] = -1; // 不用再外部全部一起初始化, 可以在循环内一个一个初始化
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    // 实时更新per数组和index用于之后得到最大整除子集
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        pre[i] = j;
                    }
                }
            }
            // 实时更新per数组和index用于之后得到最大整除子集
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                index = i;
            }
        }

        // 根据pre数组和index值得到最大整除子集
        List<Integer> ans = new ArrayList<>();
        while (index != -1) {
            ans.add(nums[index]);
            index = pre[index];
        }

        return ans;
    }
}

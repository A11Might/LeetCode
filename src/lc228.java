import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2020/1/11 - 10:13
 *
 * [228] 汇总区间
 *
 * 题目: 给定一个无重复元素的有序整数数组, 返回数组区间范围的汇总
 *
 * 难度: medium
 *
 * 思路: 遍历数组, 使用双指针范围找到左右端点
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public List<String> summaryRanges(int[] nums) {
        int len = nums.length;
        List<String> ans = new ArrayList<>();
        if (len == 0) return ans;
        for (int i = 0, j = 0; j < len; j++) {
            i = j; // [i, j]
            // 不断移动右端点, 找到区间范围
            while (j + 1 < len && nums[j + 1] - nums[j] == 1) {
                j++;
            }
            if (i == j) {
                ans.add(nums[i] + "");
            } else {
                ans.add(nums[i] + "->" + nums[j]);
            }
        }

        return ans;
    }
}
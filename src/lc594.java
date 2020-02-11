import java.util.HashMap;

/**
 * @author qhhu
 * @date 2020/2/11 - 9:10
 *
 * [594] 最长和谐子序列
 *
 * 题目: 给定整数数组, 返回其所有子序列中最长的和谐子序列的长度
 *      (和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1)
 *
 * 难度: easy
 *
 * 思路: 子序列所以元素不需要连续, 求和谐子序列因为要求其最大值和最小值之间的差别正好是1, 则只要求是否有比当前元素有正好大1的元素(或者小1也行.
 *      相当于从小到大或从大到谢遍历, 找比当前数字大一或小一的元素组成最长和谐子序列), 然后个数求和, 再取最大值即可.
 *      用一个哈希映射(HashMap)来存储每个数出现的次数, 这样就能在O(1)的时间内得到 x 和 x + 1 出现的次数.
 *      遍历一遍数组, 得到哈希映射. 随后遍历哈希映射, 设当前遍历到的键值对为 (x, value), 那么我们就查询 x + 1 在哈希映射中对应的值,
 *      就得到了 x 和 x + 1 出现的次数
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> dict = new HashMap<>();
        for (int num : nums) {
            dict.put(num, dict.getOrDefault(num, 0) + 1);
        }

        int ret = 0;
        for (int num : nums) {
            // 求是否有比当前元素有正好大1的元素(或者小1也行. 相当于从小到大或从大到谢遍历, 找比当前数字大一或小一的元素组成最长和谐子序列)
            if (dict.containsKey(num + 1)) {
                ret = Math.max(ret, dict.get(num) + dict.get(num + 1));
            }
        }

        return ret;
    }
}

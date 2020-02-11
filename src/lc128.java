import java.util.HashSet;

/**
 * @author qhhu
 * @date 2020/2/11 - 9:10
 *
 * [128] 最长连续序列
 *
 * 题目: 给定一个未排序的整数数组, 返回最长连续序列的长度. [100, 4, 200, 1, 3, 2]最长连续序列为[1, 2, 3, 4].
 *      (要求时间复杂度: O(n))
 *
 * 难度: hard
 *
 * 思路: 一个连续序列是从nums数组中的一个数字num开始的, 这个数字num是当前连续序列中最小的元素, 所以使用num - 1是否存在来
 *       判断该数字是否是序列的开始, 然后依次寻找num + 1, num + 2,...来找到以当前数组开头的连续序列的长度.
 *       将nums中的数字用一个HashSet保存, 可以实现 O(1)时间的查询. 同时, 我们只对当前数字num - 1不在哈希表里的数字,
 *      作为连续序列的第一个数字去找对应的最长序列，这是因为其他数字一定已经出现在了某个序列里.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> dict = new HashSet<>();
        for (int num : nums) {
            dict.add(num);
        }
        int longest = 0;
        for (int num : nums) {
            if (dict.contains(num - 1)) continue;
            int cnt = 1;
            while (dict.contains(++num)) {
                cnt++;
            }
            longest = Math.max(longest, cnt);
        }

        return longest;
    }
}
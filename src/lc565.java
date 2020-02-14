/**
 * @author qhhu
 * @date 2020/2/14 - 8:54
 *
 * [565] 数组嵌套
 *
 * 题目: 索引从0开始长度为n的数组a, 包含0到n - 1的所有整数. 返回最大的集合s, s[i] = {a[i], a[a[i]], a[a[a[i]]],...}.
 *      (假设选择索引为i的元素A[i]为S的第一个元素, S的下一个元素应该是A[A[i]], 之后是A[A[A[i]]]...以此类推, 不断添加直到S出现重复的元素)
 *
 * 难度: medium
 *
 * 思路: 将不含重复元素的数组看成若干个环装的链表, 题意即寻找节点数最多环状链表的节点个数.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int arrayNesting(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            // 从当前节点出发, 统计当前节点所在的环状链表的节点个数
            int cnt = 0;
            for (int j = i; nums[j] != -1;) {
                cnt++;
                int temp = nums[j];
                nums[j] = -1;
                j = temp;
            }
            max = Math.max(max, cnt);
        }

        return max;
    }
}

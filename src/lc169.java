import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 求众数
 *
 * 题目：给定一个大小为 n 的数组, 找到其中的出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 难度：easy
 *
 * 思路：1。排序后，中间元素出现次数一定多于 n / 2。
 *      2。数组排序后位于数组中间的数字（数组中第 n / 2 大的数字）一定为数组中出现的次数超过数组长度的一半的数字。
 *         使用快速排序每次可以将数组划分小于等于 a[j] 的部分 a[lo ... j - 1] 和大于等于 a[j] 的部分 a[j + 1 ... hi]，此时 a[j]
 *         就是数组的第 j 大元素. 可以利用这个特性找出数组的第 n / 2 个元素.
 *      3。多数投票问题：使用 cnt 来统计一个元素出现的次数，当遍历到的元素和统计元素相等时，令 cnt++，否则令 cnt--。如果前面查找了 i 个
 *         元素，且 cnt == 0，说明前 i 个元素没有 majority，或者有 majority，但是出现的次数少于 i / 2，因为如果多于 i / 2 的话
 *         cnt 就一定不会为 0. 此时剩下的 n - i 个元素中，majority 的数目依然多于 (n - i) / 2，因此继续查找就能找出 majority。
 */
class Solution {
    /**
     * 时间复杂度: O(n * logn)
     * 空间复杂度: O(1)
     */
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int majorityElement2(int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length;
        return quickSort(nums, 0, n - 1, (n + 1) / 2);
    }

    private int quickSort(int[] nums, int l, int r, int k) {
        if (l >= r) return nums[l];
        int i = l - 1, j = r + 1, x = nums[l + r >> 1];
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j) {
                int tmp = nums[j];
                nums[j] = nums[i];
                nums[i] = tmp;
            }
        }
        int ls = j - l + 1;
        if (k <= ls) return quickSort(nums, l, j, k);
        else return quickSort(nums, j + 1, r, k - ls);
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int majorityElement3(int[] nums) {
        int cnt = 0, cur = 0;
        for (int num : nums) {
            if (cnt == 0) cur = num;
            if (num == cur) cnt++;
            else cnt--;
        }
        return cur;
    }
}

import java.util.Arrays;

/*
 * @lc app=leetcode.cn id=169 lang=java
 *
 * [169] 求众数
 *
 * 题目: 给定一个大小为 n 的数组, 找到其中的出现次数大于 ⌊ n/2 ⌋ 的元素.
 *
 * 难度: easy
 *
 * 思路: 1. 排序后, 中间元素出现次数一定多于 n / 2.
 *      2. 数组排序后位于数组中间的数字(数组中第 n / 2 大的数字)一定为数组中出现的次数超过数组长度的一半的数字.
 *         快速排序的 partition() 方法, 会返回一个整数 j 使得 a[lo ... j - 1] 小于等于 a[j], 且 a[j + 1 ... hi] 大于等于 a[j],
 *         此时 a[j] 就是数组的第 j 大元素. 可以利用这个特性找出数组的第 n / 2 个元素.
 *      3. 多数投票问题, 可以利用 Boyer-Moore Majority Vote Algorithm 来解决这个问题, 使得时间复杂度为 O(n).
 *         使用 cnt 来统计一个元素出现的次数, 当遍历到的元素和统计元素相等时, 令 cnt++, 否则令 cnt--. 如果前面查找了 i 个元素,
 *         且 cnt == 0, 说明前 i 个元素没有 majority, 或者有 majority, 但是出现的次数少于 i / 2, 因为如果多于 i / 2 的话
 *         cnt 就一定不会为 0. 此时剩下的 n - i 个元素中, majority 的数目依然多于 (n - i) / 2, 因此继续查找就能找出 majority.
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
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int lo = 0, hi = len - 1;
        while (lo <= hi) {
            int index = partition(nums, lo, hi);
            if (index == len / 2) {
                // 找到位于数组中间的的元素
                return nums[index];
            } else if (index < len / 2) {
                lo = index + 1;
            } else {
                hi = index - 1;
            }
        }

        throw new IllegalArgumentException("No Solution");
    }

    // 将当前数组区间分为小于 nums[end] 部分和 大于等于 nums[end] 部分.
    private int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int smaller = start - 1;
        while (start < end) {
            if (nums[start] < pivot) {
                swap(nums, start++, ++smaller);
            } else {
                start++;
            }
        }
        swap(nums, end, ++smaller);
        return smaller;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int majorityElement3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int cnt = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (cnt == 0) pre = nums[i];
            if (nums[i] == pre) {
                cnt++;
            } else {
                cnt--;
            }
        }

        return pre;
    }
}

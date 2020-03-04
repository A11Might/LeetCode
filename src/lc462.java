import java.util.Arrays;

/**
 * @author qhhu
 * @date 2020/3/3 - 9:45
 *
 * [462] 最少移动次数使数组元素相等 II
 *
 * 题目: 给定一个非空整数数组, 返回使所有数组元素相等所需的最小移动数, 其中每次移动可将选定的一个元素加 1 或减 1.
 *
 * 难度: medium
 *
 * 思路: 这是个典型的相遇问题, 移动距离最小的方式是所有元素都移动到中位数.
 *      假设 m 为中位数. a 和 b 是 m 两边的两个元素, 且 b > a. 要使 a 和 b 相等, 它们总共移动的次数为 b - a,
 *      这个值等于 (b - m) + (m - a), 也就是把这两个数移动到中位数的移动次数.
 */
class Solution {
    /**
     * 先排序
     * 时间复杂度: O(n * logn)
     * 空间复杂度: O(1)
     */
    public int minMoves21(int[] nums) {
        Arrays.sort(nums);
        int lo = 0, hi = nums.length - 1;
        int cnt = 0;
        while (lo < hi) {
            cnt += nums[hi] - nums[lo];
            lo++;
            hi--;
        }

        return cnt;
    }

    /**
     * 使用快速选择找到中位数
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int minMoves2(int[] nums) {
        int cnt = 0;
        int median = findKthSmallest(nums, nums.length / 2);
        System.out.println(median);
        for (int num : nums) {
            cnt += Math.abs(num - median);
        }
        return cnt;
    }

    private int findKthSmallest(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int index = partition(nums, lo, hi);
            if (index == k) {
                return nums[index];
            } else if (index < k) {
                lo = index + 1;
            } else {
                hi = index - 1;
            }
        }

        throw new IllegalArgumentException("No Solution");
    }

    private int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int smaller = start - 1;
        while (start < end) {
            if (arr[start] < pivot) {
                swap(arr, start++, ++smaller);
            } else {
                start++;
            }
        }
        swap(arr, end, ++smaller);
        return smaller;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}
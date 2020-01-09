/*
 * @lc app=leetcode.cn id=189 lang=java
 *
 * [189] 旋转数组
 *
 * 题目: 将给定数组中的元素向右移动k个位置, 其中k是非负数
 *       (三种解决方案; 原地算法)
 *
 * 难度: easy
 *
 * 思路: 1. 一步一步向右移动数组, 一共移动k次
 *          Original List                   : 1 2 3 4 5 6 7
 *          After move one time             : 7 1 2 3 4 5 6
 *          After move two times            : 6 7 1 2 3 4 5
 *          ...
 *          After move k times              : 5 6 7 1 2 3 4 --> Result
 *       2. 三次翻转操作
 *          Original List                   : 1 2 3 4 5 6 7
 *          After reversing all numbers     : 7 6 5 4 3 2 1
 *          After reversing first k numbers : 5 6 7 4 3 2 1
 *          After revering last n-k numbers : 5 6 7 1 2 3 4 --> Result
 *       3. 循环替换, 直接把每一个数字放到它最后的位置, 把被替换的数字保存在变量temp里面
 *          https://leetcode-cn.com/problems/rotate-array/solution/xuan-zhuan-shu-zu-by-leetcode/
 *       4. 使用额外的数组, 索引i用i+k替换, 然后把组织好的数组复制过去: temp[i] = nums[(i + k) % len]
 */
class Solution {
    /**
     * 时间复杂度: O(k * n)
     * 空间复杂度: O(1)
     */
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        if (len < 2) return;
        k %= len;
        for (int i = 0; i < k; i++) {
            int temp = nums[len - 1];
            for (int j = len - 2; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = temp;
        }
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        if (len < 2) return;
        k %= len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public void rotate3(int[] nums, int k) {
        int len = nums.length;
        k = k % len;
        int count = 0;
        for (int start = 0; count < len; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % len;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

}


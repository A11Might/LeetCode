/*
 * @lc app=leetcode.cn id=268 lang=java
 *
 * [268] 缺失数字
 * 
 * 1、排序后遍历数组，差谁缺谁
 * 2、求和nums数组，然后和1到n项的数列和对比，相差的数就是缺的这个数(使用加减防止溢出)
 * 3、使用异或运算，进行抵消，剩下的数字就是缺失的
 * 
 */
class Solution {
    public int missingNumber1(int[] nums) {
        java.util.Arrays.sort(nums);
        if (nums[0] != 0) {
            return 0;
        }
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] - 1 != nums[i - 1]) {
                return nums[i] - 1;
            }
        }
        return nums[nums.length - 1] + 1;
    }

    public int missingNumber2(int[] nums) {
        int res = nums.length; // 先加上n，因为遍历只能到n - 1
        for (int i = 0; i < nums.length; ++i) {
            // 将两和相减，拆分开来防止溢出
            res += i;
            res -= nums[i];
        }
        return res;
    }

    public int missingNumber(int[] nums) {
        int res = nums.length; // 初值设为n，因为遍历只能到n - 1
        for (int i = 0; i < nums.length; ++i) {
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }
}


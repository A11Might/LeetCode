/*
 * @lc app=leetcode.cn id=55 lang=java
 *
 * [55] 跳跃游戏
 * 
 * 题意：一个非负整数数组，数组中的每个元素大小代表最多可以跳跃的步数，判断能否从第一个位置跳到最后一个位置
 * 
 * 思路：
 * 1、找到所有0，看能不能跳过它，如果不能跳过看它能不能跳到其上并判断它是不是最后一个
 * 2、贪心：从后往前遍历，使用dummyLast记录最后位置，如果当前位置能能跳到dummyLast则更新它为当前位置
 *         (当前位置若可到达最后位置，则如果能到达当前位置就一定能到达最后位置)
 */
class Solution {
    public boolean canJump1(int[] nums) {
        if (nums == null || nums.length < 2) { // []、[0]
            return true;
        }

        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            // 当前位置为0
            if (nums[i] == 0 && !canSkip(nums, n, i)) {
                return false;
            }
        }

        return true;
    }

    private boolean canSkip(int[] nums, int n, int index) {
        for (int i = 0; i < index; ++i) {
            // 当前位置可以跳过0
            if (nums[i] > index - i) {
                return true;
            // 当前位置可以跳到0上
            } else if (nums[i] == index - i && index == n - 1) { // [2, 0, 0]
                return true;
            }
            // 当前位置跳不过0
        }
        
        return false;
    }

    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int dummyLast = n - 1;
        for (int i = n - 1; i >= 0; --i) {
            if (i + nums[i] >= dummyLast) {
                dummyLast = i;
            }
        }

        return dummyLast == 0;
    }
}


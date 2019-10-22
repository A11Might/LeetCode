import java.util.Arrays;
import java.util.Random;

/*
 * @lc app=leetcode.cn id=384 lang=java
 *
 * [384] 打乱数组
 * 
 * 遍历数组，将当前位置的数与之前随机一个位置交换
 */
class Solution {

    private int[] orgin;

    public Solution(int[] nums) {
        orgin = nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return orgin;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int n = orgin.length;
        int[] res = Arrays.copyOf(orgin, n);
        Random rand = new Random();
        for (int i = 0; i < res.length; ++i) {
            int index = rand.nextInt(i + 1); // [0,i + 1)
            swap(res, index, i);
        }
        return res;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */


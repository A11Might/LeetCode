/*
 * @lc app=leetcode.cn id=410 lang=java
 *
 * [410] 分割数组的最大值
 * 
 * 题意：将一个非负整数数组分成m个非空的连续子数组，要求这m个子数组各自和的最大值最小。
 * 
 * https://www.bilibili.com/video/av48806706
 * 思路：使用二分查找
 *      将nums分为一组时和的最大值的最大值为hi，将nums分为nums.length组时和的最大值的最小值为lo
 *      在lo到hi之间二分查找最小的可以分m组的mid
 */
class Solution {
    public int splitArray(int[] nums, int m) {
        int max = 0, sum = 0;
        for (int num : nums) {
            max = Math.max(max, num);
            sum += num;
        }

        int lo = max, hi = sum;
        // 二分查找，寻找满足条件的最小情况(将lo和hi之间分为<和>=两个区间) ????????
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            int pieces = split(nums, mid); // 当和的最大值为mid时，最少可以分pieces份
            if (pieces > m) { // 当分的数组个数大于m时，说明mid值小了
                lo = mid + 1;
            } else { // res可能是mid，保留
                hi = mid;
            }
        }

        return lo;
    }

    private int split(int[] nums, int validMaxSum) {
        int pieces = 1; // 初始nums为一个完整的数组
        int curSum = 0; // 分组时当前组中元素的和

        for (int num : nums) {
            // 当前组中元素的和加上当前元素的值，超出合法和最大值，当前组满了需另外分组
            if (curSum + num > validMaxSum) {
                curSum = num;
                pieces++;
            // 否则将当前元素放入当前组中
            } else {
                curSum += num;
            }
        }

        return pieces;
    }
}


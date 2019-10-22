import java.util.HashMap;
import java.util.TreeSet;

/*
 * @lc app=leetcode.cn id=220 lang=java
 *
 * [220] 存在重复元素 III
 * 
 * 题目：在给定的整数数组中，判断能否找到两个不同索引的相同的数，要求索引最大差值的绝对值为k，值的最大差值为t
 * 
 * 思路：滑动窗口+查找表，
 *               1、查找表中注册元素有序
 *               2、桶排序，查找表为一个个大小为t + 1的桶
 */
class Solution {
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        TreeSet<Integer> record = new TreeSet<>(); // 使用平衡二叉搜素树注册元素，查找表内元素有序
        int l = 0, r = -1; // 滑动窗口[l, r]
        while (r + 1 < nums.length) {
            r++;
            // 窗口中的元素都满足索引差值的绝对值不超过k
            // 若当前窗口中最接近nums[r]的元素差值都大于t，则当前窗口中的值都无满足值的最大差值为t
            // 滑动窗口中，succ是 >= nums[r] 的最小元素
            Integer succ = record.ceiling(nums[r]); 
            if (succ != null && (long) succ - nums[r] <= t) { // (long)防止整数越界，实例[-1,2147483647]\n1\n2147483647
                return true;
            }
            // 滑动窗口中，<= nums[r] 的最大元素
            Integer pre = record.floor(nums[r]);
            if (pre != null && (long) nums[r] - pre <= t) {
                return true;
            }
            // 注册当前元素
            record.add(nums[r]);
            // 当前窗口已经扩到最大，在下次循环前需要缩小窗口，保证下次窗口扩大时，窗口中元素都没过期
            if (r - l == k) {
                record.remove(nums[l++]);
            }
        }

        return false;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) { // 实例[-1,-1]\n1\n-1
            return false;
        }
        HashMap<Integer, Integer> buckets = new HashMap<>();
        int w = t + 1; // 桶的大小为t + 1，桶中元素最大差值为t
        int l = 0, r = -1;
        while (r + 1 < nums.length) {
            // 窗口中的元素都满足索引差值的绝对值不超过k
            r++;
            // 当前元素所在桶号
            int id = getId(nums[r], w);
            // 若两个元素在同一个桶中，差值不超过t
            if (buckets.containsKey(id)) {
                return true;
            }
            // 相邻桶中的的元素差值可能不超过t
            if (buckets.containsKey(id - 1) && (long) nums[r] - buckets.get(id - 1) <= t) {
                return true;
            }
            if (buckets.containsKey(id + 1) && (long) buckets.get(id + 1) - nums[r] <= t) {
                return true;
            }
            // 不相邻的桶中的元素差值一定超过t，不同判断
            // 注册当前元素
            buckets.put(id, nums[r]);
            // 当前窗口已经扩到最大，在下次循环前需要缩小窗口，保证下次窗口扩大时，窗口中元素都没过期
            if (r - l == k) {
                buckets.remove(getId(nums[l++], w));
            }
        }

        return false;
    }

    private int getId(int num, int w) {
        // Get the ID of the bucket from element value x and bucket width w
        // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
        // num > 0, [0, 1, 2, 3, 4] / w
        // num < 0, [-5, -4, -3, -2, -1] -> [-4, -3, -2, -1, 0] /2 - 1
        return num < 0 ? (num + 1) / w - 1 : num / w;
    }
}


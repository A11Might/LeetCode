/*
 * @lc app=leetcode.cn id=287 lang=java
 *
 * [287] 寻找重复数
 *
 * 题目: 给定一个包含n + 1个整数的数组 nums, 其数字都在1到n之间(包括 1 和 n), 假设有且只有一个重复的整数, 找出这个重复的数
 *      (要求: 不能更改原数组(假设数组是只读的);
 *             只能使用额外的O(1)的空间, 时间复杂度小于O(n ^ 2);
 *             数组中只有一个重复的数字, 但它可能不止重复出现一次)
 *
 * 难度: medium
 *
 * 思路: 1. 类二分查找, 将数组分为含*一定*包含重复元素部分和*一定不*包含重复元素部分:
 *          将区间[1, n]从中间数字m处, 拆分成两部分[1, m]和[m + 1, n], 分别将nums中的数字放入对应区间中
 *          若[1, m]区间内包含nums数组中数字的数目超过m, 则重复数字在[1, m]范围内
 *          否则重复数字在[m + 1, n]范围内, 然后继续把一定含有重复数字的区间一分为二, 直至找到一个重复数字
 *      2. 将数组看成链表, val是结点值也是下个节点的索引, 转换成找到环形链表入环节点问题
 *          (数组元素个数位n + 1, 而数组中的数字的范围为[1, n], 所以不会出现数组索引越界)
 *      nums = [2, 5, 9, 6, 9, 3, 8, 9, 7, 1], 构造成链表就是: 2->[9]->1->5->3->6->8->7->[9]，1为入环节点，在[9]处循环
 */
class Solution {
    /**
     * 时间复杂度: O(n * logn)
     * 空间复杂度: O(1)
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int left = 1, right = len - 1; // 在[1, n]中使用二分查找
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int count = countRange(nums, left, mid);
            // 区间只剩下一个元素, 判断其是否为重复元素
            if (left == right) {
                return left;
            }
            // [left, mid]中含有重复元素
            if (count > (mid - left + 1)) {
                right = mid;
            // [mid + 1, right]中含有重复元素
            } else {
                left = mid + 1;
            }
        }

        throw new IllegalArgumentException("No solution");
    }

    // 返回数组nums有多少个元素在区间[start, end]内
    private int countRange(int[] nums, int start, int end) {
        int ans = 0;
        for (int num : nums) {
            if (start <= num && num <= end) {
                ans++;
            }
        }

        return ans;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int findDuplicate2(int[] nums) {
        // 快慢指针从0开始进入链表, 链表值在[1, n]范围内不会出现循环链表(值既为数组中的元素, 也是下标, 除了0)
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow) {
                fast = 0;
                while (fast != slow) { // 找到fast和slow在入环节点前的一个节点，即为重复元素
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return fast;
            }
        }
    }
}


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
 * 思路: 1. 类二分查找, 将数组分为含*一定*包含重复元素部分和*一定不*包含重复元素部分, 如下:
 *          将区间[1, n]从中间数字m处, 拆分成两部分[1, m]和[m + 1, n], 分别统计nums中的数字在对应区间中的个数,
 *          若[1, m]区间内包含nums数组中数字的数目超过m, 则重复数字在[1, m]范围内;
 *          否则重复数字在[m + 1, n]范围内, 然后继续把一定含有重复数字的区间一分为二, 直至找到一个重复数字.
 *      2. 将数组看成链表, 索引值index为当前节点, val为下个节点的索引, 其结点值为val, 则原问题转换成找到环形链表入环节点问题.
 *          (数组元素个数位n + 1, 而数组中的数字的范围为[1, n], 所以不会出现数组索引越界)
 *      nums = [2, 5, 9, 6, 9, 3, 8, 9, 7, 1], 构造成链表就是: 2->[9]->1->5->3->6->8->7->[9], nums[1]为入环节点.
 */
class Solution {
    /**
     * 时间复杂度: O(n * logn) (二分查找总比较次数为logn, 每次比较需要遍历数组, 数组大小为n)
     * 空间复杂度: O(1)
     */
    public int findDuplicate(int[] nums) {
        int len = nums.length;
        int lo = 1, hi = len - 1; // 在[1, n]中使用二分查找
        while (lo <= hi) {
            int mid = lo + ((hi - lo) >> 1);
            int cnt = countRange(nums, lo, mid);
            // 区间只剩下一个元素, 即为重复元素
            if (lo == hi) {
                return lo;
            }
            // [lo, mid]中含有重复元素
            if (cnt > (mid - lo + 1)) {
                hi = mid;
            // [mid + 1, hi]中含有重复元素
            } else {
                lo = mid + 1;
            }
        }

        throw new IllegalArgumentException("No solution");
    }

    // 返回数组nums有多少个元素在区间[start, end]内
    private int countRange(int[] nums, int start, int end) {
        int cnt = 0;
        for (int num : nums) {
            if (start <= num && num <= end) {
                cnt++;
            }
        }

        return cnt;
    }

    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     */
    public int findDuplicate2(int[] nums) {
        // 快慢指针必须同时从起点开始走, 不然后续会找不到入环节点(出现死循环important)
        int slow = nums[0], fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        // 找入环节点, 即为重复元素
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}


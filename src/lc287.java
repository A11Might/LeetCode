/*
 * @lc app=leetcode.cn id=287 lang=java
 *
 * [287] 寻找重复数
 * 
 * 题目：给定一个包含n + 1个整数的数组 nums，其数字都在1到n之间(包括 1 和 n)，假设只有一个重复的整数，找出这个重复的数
 * 
 * 要求：不能更改原数组(假设数组是只读的)，只能使用额外的 O(1) 的空间，时间复杂度小于 O(n ^ 2)
 * 
 * 思路：将数组看成链表，val是结点值也是下个节点的地址，转换成找到环形链表入环节点问题(其实是入环节点前的一个节点)
 * nums = [2, 5, 9, 6, 9, 3, 8, 9, 7, 1]，构造成链表就是：2->[9]->1->5->3->6->8->7->[9]，1为入环节点，在[9]处循环
 */
class Solution {
    public int findDuplicate(int[] nums) {
        int fast = 0, slow = 0;
        while (true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if (fast == slow) {
                fast = 0;
                while (nums[fast] != nums[slow]) { // 找到fast和slow在入环节点前的一个节点，即为重复元素
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[fast];
            }
        }
    }
}


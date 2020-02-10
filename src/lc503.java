import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author qhhu
 * @date 2020/2/10 - 9:17
 *
 * [503] 下一个更大元素 II
 *
 * 题目: 给定一个循环数组, 输出每个元素的下一个更大元素. 若不存在则输出-1.
 *
 * 难度: medium
 *
 * 思路: 单调栈, 与[739] Daily Temperatures 不同的是, 数组是循环数组, 并且最后要求的不是距离而是下一个元素
 *      遍历两边数组, 在第一遍遍历数组时用栈把数组中的数存起来, 如果当前遍历的数比栈顶元素来的大, 说明栈顶元素的下一个比它大的数就是当前元素.
 *      第二遍遍历只为找到最后一个元素的下一个更大元素.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ret = new int[len];
        // 若不存下一个更大的元素则输出-1
        Arrays.fill(ret, -1);
        for (int i = 0; i < 2 * len; i++) {
            // 当前元素的索引
            int index = i % len;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                ret[stack.pop()] = nums[index];
            }
            // 将元素的索引压入栈中, 用于存储元素
            // 第一遍循环时才将元素的索引压入栈中, 第二遍遍历只为找到最后一个元素的下一个更大元素.
            if (i < len) stack.push(i);
        }

        return ret;
    }
}
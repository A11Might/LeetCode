import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode.cn id=739 lang=java
 *
 * [739] 每日温度
 * 
 * 题目: 根据每日气温列表, 请重新生成一个列表, 对应位置的输入是你需要再等待多久温度才会升高超过该日的天数
 *       如果之后都不会升高, 请在该位置用 0 来代替.
 *
 * 难度: medium
 *
 * 思路: 单调栈, 在遍历数组时用栈把数组中的数存起来, 如果当前遍历的数比栈顶元素来的大, 说明栈顶元素的下一个比它大的数就是当前元素.
 */
class Solution {
    /**
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int preIndex = stack.pop();
                ret[preIndex] = i - preIndex;
            }
            // 将元素的索引压入栈中, 用于存储和计算距离
            stack.push(i);
        }

        return ret;
    }
}


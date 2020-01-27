import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2020/1/27 - 11:45
 *
 * [241] 为运算表达式设计优先级
 *
 * 题目: 给定一个含有数字和运算符的字符串, 为表达式添加括号, 改变其运算优先级以求出不同的结果, 返回所有可能组合的结果
 *      (有效运算符包含'+', '-', '*')
 *
 * 难度: medium
 *
 * 思路: 树型DP, 以每个运算符为界, 左侧所有组合的结果和右侧所有组合的结果再运算, 即为所有情况
 */
class Solution {
    /**
     * 时间复杂度: O()
     * 空间复杂度: O()
     */
    public List<Integer> diffWaysToCompute(String input) {
        int len = input.length();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            char cur = input.charAt(i);
            // 以每个符号为界
            if (cur == '+' || cur == '-' || cur == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i)); // 当前符号左侧所有组合的结果
                List<Integer> right = diffWaysToCompute(input.substring(i + 1, len)); // 当前符号右侧所有组合的结果
                // 当前符合左侧所有的结果和右侧所有的结果再运算
                for (int l : left) {
                    for (int r : right) {
                        if (cur == '+') {
                            ans.add(l + r);
                        } else if (cur == '-') {
                            ans.add(l - r);
                        } else {
                            ans.add(l * r);
                        }
                    }
                }
            }
        }

        // input中没符号为纯数字, 不会经过上述计算, 若不单独记录会丢失
        if (ans.size() == 0) ans.add(Integer.valueOf(input));

        return ans;
    }
}

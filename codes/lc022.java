import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode.cn id=22 lang=java
 *
 * [22] 括号生成
 * 
 * 1、暴力：生成所有括号，取出其中合法的部分
 * 2、回溯：通过跟踪到目前为止放置的左括号和右括号的数目来判断序列是否仍然保持有效，然后才添加 '(' or ')'
 *    如果我们还剩一个位置，我们可以开始放一个左括号。 如果它不超过左括号的数量，我们可以放一个右括号
 * 3、闭合数：https://leetcode-cn.com/problems/generate-parentheses/solution/gua-hao-sheng-cheng-by-leetcode/
 */
class Solution {
    public List<String> generateParenthesis1(int n) {
        List<String> res = new ArrayList<>();
        generateAll(res, new char[n * 2], 0);
        return res;
    }

    private void generateAll(List<String> res, char[] cur, int index) {
        if (index == cur.length) {
            // 每递归生成一个括号就判断其合法性
            if (isValid(cur)) {
                res.add(new String(cur));
            }
        } else {
            // 这个先 '(' 和 ')' 无所谓，最后isValid剔除不合法的
            cur[index] = '(';
            generateAll(res, cur, index + 1);
            cur[index] = ')';
            generateAll(res, cur, index + 1);
        }
    }

    private boolean isValid(char[] cur) {
        int balance = 0;
        for (char chr : cur) {
            if (chr == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }

    public List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        process2(res, "", 0, 0, n);
        return res;
    }

    private void process2(List<String> res, String cur, int countLeft, int countRight, int n) {
        if (cur.length() == n * 2) {
            res.add(cur);
            return;
        }
        // 先加"(", 当右括号的数量小于左括号时， 再加")", 保证括号的合法性
        if (countLeft < n) {
            process2(res, cur + "(", countLeft + 1, countRight, n);
        }
        if (countRight < countLeft) {
            process2(res, cur + ")", countLeft, countRight + 1, n);
        }
    }
}


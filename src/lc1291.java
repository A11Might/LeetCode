import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2019/12/16 - 14:54
 *
 * [1291] 顺次数
 *
 * 题目: 返回给定范围[low, high]中所有顺次数组成的有序列表(从小到大排列)
 *       (顺次数: 每一位上的数字都比前一位上的数字大1的整数, 如123)
 *
 * 难度: medium
 *
 * 思路: 生成所有顺次数, 从中找到符合要求的
 */
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> list = new ArrayList<>();
        String num = "123456789";
        for (int i = 2; i <= 9; i++) {
            for (int j = 0; j <= num.length() - i; j++) {
                list.add(Integer.valueOf(num.substring(j, j + i)));
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (low <= list.get(i) && list.get(i) <= high) {
                ans.add(list.get(i));
            }
        }

        return ans;
    }
}

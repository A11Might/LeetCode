import java.util.ArrayList;
import java.util.List;

/**
 * @author qhhu
 * @date 2020/1/17 - 16:55
 *
 * [524] 通过删除字母匹配到字典里最长单词
 *
 * 题目: 给定一个字符串和一个字符串字典, 找到字典内最长的字符串, 该字符串可以通过删除给定字符串的某些字符来得到
 *      (返回长度最长且字典序最小的字符串, 若不存在, 则返回空字符串)
 *
 * 难度: medium
 *
 * 思路: 双指针, 判断一个字符串是否为另一个字符串的子序列
 */
class Solution {
    /**
     * 时间复杂度: O(m * n) (m为字典的大小, n为给定字符串的长度)
     * 空间复杂度: O(1)
     */
    public String findLongestWord(String s, List<String> d) {
        String ans = "";
        for (String str : d) {
            int l1 = ans.length(), l2 = str.length();
            // 通过当前字符串与当前获取的结果比较, 跳过不是结果的字符串
            if (l1 > l2 || (l1 == l2 && ans.compareTo(str) < 0)) {
                continue;
            }
            // 判断当前字符串是否是给定字符串的子串, 若是则其为当前的结果(通过上述条件的字符串长度大于当前结果, 或者字典序大于当前结果)
            if (isSubstr(s, str)) {
                ans = str;
            }
        }
        return ans;
    }

    // 判断当前字符串是否是给定字符串的子串
    private boolean isSubstr(String s, String str) {
        int i = 0, j = 0;
        while (i < s.length() && j < str.length()) {
            if (s.charAt(i) == str.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == str.length();
    }

    /**
     * My solution: 找到字典中所有是给定字符串子串的字符串, 排序后获得结果
     * 时间复杂度: O(m * n + k * log(k)) (m为字典的大小, n为给定字符串的长度, k为字典中字符串的长度)
     * 空间复杂度: O(m)
     */
    public String findLongestWord2(String s, List<String> d) {
        List<String> ans = new ArrayList<>();
        // 找到所有符合题意的字符串
        d.forEach(
                (str) -> {
                    // 从后往前比较(与从前往后比较相同)
                    int index1 = s.length() - 1, index2 = str.length() - 1;
                    while (index1 >= 0 && index2 >= 0) {
                        if (s.charAt(index1) == str.charAt(index2)) {
                            index2--;
                        }
                        index1--;
                        if (index2 < 0) {
                            ans.add(str);
                        }
                    }
                }
        );

        // 排序所有符合题意的字符串
        ans.sort(
                (str1, str2) -> {
                    if (str1.length() == str2.length()) {
                        return str1.compareTo(str2);
                    } else {
                        return str2.length() - str1.length();
                    }
                }
        );
        return ans.size() == 0 ? "" : ans.get(0);
    }
}

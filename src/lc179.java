import java.util.Arrays;

/**
 * @author qhhu
 * @date 2020/8/1 - 9:52
 *
 * [179] 最大数
 *
 * 题目：重新排列一组非负整数使之组成一个最大的整数
 *
 * 难度：medium
 *
 * 思路：
 */
class Solution {
    /**
     * 时间复杂度：O(n * logn)
     * 空间复杂度：O(n)
     */
    public String largestNumber(int[] nums) {
        if (nums.length == 0) return "";
        int n = nums.length;
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) strs[i] = nums[i] + "";
        Arrays.sort(strs, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (str.equals("0") && sb.length() == 0) continue;
            sb.append(str);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}

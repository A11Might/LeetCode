/**
 * @author qhhu
 * @date 2020/7/29 - 9:31
 *
 * 题目：比较给定的两个版本号 version1 和 version2，如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1，否则返回 0
 *      版本号为非空字符串，并且只包含数字和 . 字符，. 字符用于分隔数字序列
 *
 * 难度：中等
 *
 * 思路：分别比较每一级版本的大小
 */
class Solution {
    /**
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int compareVersion(String version1, String version2) {
        String[] ver1 = version1.split("\\."), ver2 = version2.split("\\."); // \ 和 . 都是特殊字符
        int n1 = ver1.length, n2 = ver2.length;
        int n = Math.max(n1, n2);
        for (int i = 0; i < n; i++) {
            // 版本级数不够就补 0，然后进行比较
            int v1 = i < n1 ? Integer.valueOf(ver1[i]) : 0;
            int v2 = i < n2 ? Integer.valueOf(ver2[i]) : 0;
            if (v1 > v2) return 1;
            else if (v1 < v2) return -1;
        }
        return 0;
    }
}
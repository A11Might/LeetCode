import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=387 lang=java
 *
 * [387] 字符串中的第一个唯一字符
 * 
 * 1、暴力两层迭代
 * 2、用哈希表注册完，再遍历一遍，取出第一个个数为一的
 * 3、字符串只包含小写字母，可以用长度为26的数组替代哈希表
 */
class Solution {
    public int firstUniqChar2(String s) {
        char[] chrs = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char chr : chrs) {
            if (!map.containsKey(chr)) {
                map.put(chr, 1);
            } else {
                map.put(chr, map.get(chr) + 1);
            }
        }
        for (int i = 0; i < chrs.length; ++i) {
            if (map.get(chrs[i]) == 1) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar3(String s) {
        int[] flag = new int[26];
        char[] chrs = s.toCharArray();
        for (char chr : chrs) {
            int index = chr - 'a'; // 将26个字母对应到0-25上
            flag[index]++;
        }
        for (int i = 0; i < chrs.length; ++i) {
            int index = chrs[i] - 'a'; 
            if (flag[index] == 1) {
                return i;
            }
        }
        return -1;
    }
}


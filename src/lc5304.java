/**
 * @author qhhu
 * @date 2020/1/5 - 15:59
 *
 * [5304] 子数组异或查询
 *
 * 题目: 给定一个正整数数组arr和一个查询数组queries, 其中queries[i] = [Li, Ri], 每个查询i为计算Li, Ri的xor值
 *      (即arr[Li] ^ arr[Li + 1] ^ ... ^ arr[Ri]), 返回所有查询结果
 *
 * 难度: medium
 *
 * 思路: 前缀和, query[l, r] = xor[0, l - 1] ^ xor[0, r]     (利用a ^ b ^ a = b的性质)
 */
class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        // 因为a - 1, 所以将dp数组多加一个位置, 用于处理边界条件
        // 计算前缀和
        int[] xor = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            xor[i] = xor[i - 1] ^ arr[i - 1];
        }

        // 利用前缀和求解
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = xor[queries[i][0]] ^ xor[queries[i][1] + 1];
        }

        return ans;
    }
}
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @lc app=leetcode.cn id=406 lang=java
 *
 * [406] 根据身高重建队列
 * 
 * 题目: 有一群人乱序组成的队列，每个人由一对整数(h, k)表示，h表示身高，k表示这个人前面身高大于等于h的人数，重建这个队列
 *
 * 难度: medium
 * 
 * 思路: 按身高h降序, 个数k值升序排序, 然后依次将学生插入队列的第k个位置中, 这样身高较矮的学生插入不会影响到身高较高的同学
 *      (因为个子矮的人相对于个子高的人是"看不见"的, 所以可以先安排个子高的人)
 *
 *      // 先排序
 *      // [7,0], [7,1], [6,1], [5,0], [5,2], [4,4]
 *      // 再一个一个插入
 *      // [7,0]
 *      // [7,0], [7,1]
 *      // [7,0], [6,1], [7,1]
 *      // [5,0], [7,0], [6,1], [7,1]
 *      // [5,0], [7,0], [5,2], [6,1], [7,1]
 *      // [5,0], [7,0], [5,2], [6,1], [4,4], [7,1]
 */
class Solution {
    /**
     * 时间复杂度: O(n ^ 2)
     * 空间复杂度: O(n)
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0) return new int[][] {};
        // 按照身高降序, k升序排序(身高相同时k升序)
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        List<int[]> ans = new ArrayList<>();
        // k值定义为: 排在h前面且身高大于或等于h的人数
        // 因为从身高降序开始插入，此时所有人身高都大于等于h
        // 因此k值即为需要插入的位置
        //
        // 因为先排列身高高的人
        // 所以再排列身高低的人时对之前排列的结果没有影响
        for (int[] arr : people) {
            ans.add(arr[1], arr);
        }

        return ans.toArray(new int[ans.size()][]);
    }
}


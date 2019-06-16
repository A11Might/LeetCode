import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode.cn id=207 lang=java
 *
 * [207] 课程表
 * 
 * 题意：n门课，在选修某些课程之前需要一些先修课程，[0,1]表示学习课程0需先完成课程1，判断是否可能完成所有课程的学习
 * 
 * 思路：拓扑排序
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return true;
        }
        if (prerequisites.length == 0) {
            return true;
        }

        int[] inDegrees = new int[numCourses];
        // 计算每门课有多少对应的先修课程(相当于图的每个节点有多少入度)
        for (int[] prerequisite : prerequisites) { // [0, 1]表示0<--1
            inDegrees[prerequisite[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        // 没有先修课程的课加入队列，可以直接学习(入度为0的节点)
        for (int i = 0; i < numCourses; ++i) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }

        int res = 0; // 计算实际可以上几节课
        while (!queue.isEmpty()) {
            // 没有先修课程的课直接学习(入度为0的节点先访问)
            int curCourse = queue.poll();
            res++;
            // 每个以当前课为先修课程的课，先修课程数减一(当前节点访问完其对应节点入度减一)
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == curCourse) {
                    inDegrees[prerequisite[0]]--;
                    // 先修课程数减至0的课加入队列，可直接学习(入度为0的节点)
                    if (inDegrees[prerequisite[0]] == 0) {
                        queue.add(prerequisite[0]);
                    }
                }
            }
        }

        return res == numCourses;
    }
}


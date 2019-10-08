import java.util.*;

/**
 * @author qhhu
 * @date 2019/10/6 - 10:23
 *
 * [126] 单词接龙 II
 *
 * 题目：在给定字典中找到beginWord到endWord的所有最短转换序列
 *       (每次转换只能改变一个字母；转换过程中的中间单词必须是字典中的单词)
 *       (若不存在这样的转换序列，返回0；所有单词具有相同的长度，所有单词只有小写字母组成
 *       字典中不存在重复的单词；beginWord和endWord是非空并且不同的)
 *
 * 难度：hard
 *
 * 思路：对问题建模，整个问题转化为一个图论问题，
 *      1). Use BFS to find the shortest distance between start and end, tracing the distance of crossing nodes from start node to end node, and store node's next level neighbors to HashMap;
 *
 *      2). Use DFS to output paths with the same distance as the shortest distance from distance HashMap: compare if the distance of the next level node equals the distance of the current node + 1.
 */
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        ArrayList<List<String>> res = new ArrayList<List<String>>();
        if (!wordList.contains(endWord)) {
            return res;
        }
        HashSet<String> dict = new HashSet<String>(wordList);
        HashMap<String, ArrayList<String>> allNeighbors = new HashMap<String, ArrayList<String>>(); // Neighbors for every word
        HashMap<String, Integer> distance = new HashMap<String, Integer>(); // Distance of every node from the start node
        bfs(beginWord, endWord, dict, allNeighbors, distance);
        dfs(beginWord, endWord, allNeighbors, distance, new ArrayList<String>(), res);

        return res;
    }

    // BFS: Trace every node's distance from the start node (level by level).
    private void bfs(String beginWord, String endWord, HashSet<String> dict, HashMap<String, ArrayList<String>> allNeighbors, HashMap<String, Integer> distance) {
        Deque<String> queue = new ArrayDeque<String>();
        queue.offer(beginWord);
        distance.put(beginWord, 0);
        while (!queue.isEmpty()) {
            int curLevelSize = queue.size(); // 当前层所含节点数
            boolean findEndWord = false; // 是否找到最短路径
            // 遍历当前层的每一个节点(类似二叉树的按层打印)
            for (int i = 0; i < curLevelSize; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                // 当前节点所连接下一层节点
                ArrayList<String> neighbors = getNeighbors(dict, cur);
                allNeighbors.put(cur, neighbors);

                for (String neighbor : neighbors) {
                    if (!distance.containsKey(neighbor)) { // distance可以当visited使用
                        // 找到最短路径
                        if (neighbor.equals(endWord)) {
                            findEndWord = true;
                        }
                        queue.offer(neighbor);
                        distance.put(neighbor, curDistance + 1);
                    }
                }
            }

            // 找到最短路径并遍历完最短路径所在的最后一层即可返回(再往下遍历不可能是最短路径)
            if (findEndWord) {
                break;
            }
        }
    }

    // DFS: output all paths with the shortest distance
    private void dfs(String cur, String endWord, HashMap<String, ArrayList<String>> allNeighbors, HashMap<String, Integer> distance, ArrayList<String> solution, ArrayList<List<String>> res) {
        solution.add(cur);
        if (cur.equals(endWord)) {
            res.add(new ArrayList<String>(solution));
        } else {
            // 由于bfs找到最短路径，遍历完最短路径那一层就停了，所以那一层没有neighbors，使用getOrDefault防止空指针
            for (String neighbor : allNeighbors.getOrDefault(cur, new ArrayList<String>())) {
                // 一步一步进行dfs
                // 防止neighbor是当前节点的上一层节点(确保neighbor是当前节点的下一层节点)
                // in dfs , thereason for if (distance.get(next) == distance.get(cur) + 1) is
                // just in case that the next node is the next level of current node，
                // otherwise it can be one of the parent nodes of current node，or it is not the shortest node .
                if (distance.get(cur) + 1 == distance.get(neighbor)) {
                    dfs(neighbor, endWord, allNeighbors, distance, solution, res);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }

    // Find all next level nodes
    private ArrayList<String> getNeighbors(HashSet<String> dict, String word) {
        ArrayList<String> ans = new ArrayList<String>();
        int n = word.length();
        char[] chrs = word.toCharArray();
        // 每次替换一个位置为另一个字母，来寻找所有相邻的节点
        for (int i = 0; i < n; i++) {
            for (char chr = 'a'; chr <= 'z'; chr++) {
                if (chrs[i] == chr) continue;
                char oldChr = chrs[i];
                chrs[i] = chr;
                if (dict.contains(String.valueOf(chrs))) {
                    ans.add(String.valueOf(chrs));
                }
                chrs[i] = oldChr;
            }
        }

        return ans;
    }
}

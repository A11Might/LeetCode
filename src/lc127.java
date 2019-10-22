import javafx.util.Pair;

import java.util.*;

/**
 * @author qhhu
 * @date 2019/9/27 - 23:04
 *
 * [127] 单词接龙
 *
 * 题目：在给定字典中找到beginWord到endWord的最短转换序列的长度
 *      (每次转换只能改变一个字母；转换过程中的中间单词必须是字典中的单词)
 *      (若不存在这样的转换序列，返回0；所有单词具有相同的长度，所有单词只有小写字母组成
 *      字典中不存在重复的单词；beginWord和endWord是非空并且不同的)
 *
 * 难度：medium
 *
 * 思路：https://leetcode-cn.com/problems/word-ladder/solution/dan-ci-jie-long-by-leetcode/
 *      对问题建模，整个问题转化为一个图论问题，
 *      将问题抽象在一个无向无权图中，每个单词作为节点，差距只有一个字母的两个单词之间连一条边
 *      问题变成找到从起点到终点的最短路径，如果存在的话
 *      1、广度优先搜索方法
 *      2、双向广度优先搜索
 */
class Solution {
    // 与lc126方法统一的方法1
    public int ladderLengthI(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        HashSet<String> dict = new HashSet<String>(wordList);
        HashMap<String, Integer> distance = new HashMap<String, Integer>();
        Deque<String> queue = new ArrayDeque<String>();
        queue.offer(beginWord);
        distance.put(beginWord, 1);
        while (!queue.isEmpty()) {
            int curLevelSize = queue.size();
            // 遍历当前层的每一个节点(类似二叉树的按层打印)
            for (int i = 0; i < curLevelSize; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                // 当前节点所连接下一层节点
                ArrayList<String> neighbors = getNeighbors(dict, cur);
                for (String neighbor : neighbors) {
                    if (!distance.containsKey(neighbor)) { // distance可以当visited使用
                        // 找到最短路径
                        if (neighbor.equals(endWord)) {
                            return curDistance + 1;
                        }
                        queue.offer(neighbor);
                        distance.put(neighbor, curDistance + 1);
                    }
                }
            }
        }

        return 0;
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


    // 与lc126方法统一的方法2
    public int ladderLengthII(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        HashSet<String> dict = new HashSet<String>(wordList);
        HashMap<String, Integer> beginDistance = new HashMap<String, Integer>();
        HashMap<String, Integer> endDistance = new HashMap<String, Integer>();
        Deque<String> beginQueue = new ArrayDeque<String>();
        Deque<String> endQueue = new ArrayDeque<String>();
        beginQueue.offer(beginWord);
        endQueue.offer(endWord);
        beginDistance.put(beginWord, 1);
        endDistance.put(endWord, 1);
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            // begin to end
            int ans = visitedWordNode(dict, beginQueue, beginDistance, endDistance);
            if (ans != -1) {
                return ans;
            }
            // end to begin
            ans = visitedWordNode(dict, endQueue, endDistance, beginDistance);
            if (ans != -1) {
                return ans;
            }
        }

        return 0;
    }

    private int visitedWordNode(HashSet<String> dict, Deque<String> queue, HashMap<String, Integer> distance, HashMap<String, Integer> otherDistance) {
        int levelSize = queue.size();
        for (int i = 0; i < levelSize; i++) {
            String cur = queue.poll();
            int curDistance = distance.get(cur);
            ArrayList<String> neighbors = getNeighbors(dict, cur);
            for (String neighbor : neighbors) {
                if (otherDistance.containsKey(neighbor)) {
                    return curDistance + otherDistance.get(neighbor);
                }
                if (!distance.containsKey(neighbor)) {
                    queue.offer(neighbor);
                    distance.put(neighbor, curDistance + 1);
                }
            }
        }

        return -1;
    }

//    private ArrayList<String> getNeighbors(HashSet<String> dict, String word) {
//        int n = word.length();
//        char[] chrs = word.toCharArray();
//        ArrayList<String> ans = new ArrayList<String>();
//        for (int i = 0; i < n; i++) {
//            for (char chr = 'a'; chr < 'z'; chr++) {
//                if (chrs[i] == chr) continue;
//                char oldChr = chrs[i];
//                chrs[i] = chr;
//                if (dict.contains(String.valueOf(chrs))) {
//                    ans.add(String.valueOf(chrs));
//                }
//                chrs[i] = oldChr;
//            }
//        }
//
//        return ans;
//    }



    // leetcode官方解方法1
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // 每个单词一样长
        int n = beginWord.length();
        // 对wordlist进行预处理，方便快速查找
        HashMap<String, ArrayList<String>> allComboDict = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < n; i++) {
                String temp = word.substring(0, i) + "*" + word.substring(i + 1, n);
                ArrayList<String> list = allComboDict.getOrDefault(temp, new ArrayList<>());
                list.add(word);
                allComboDict.put(temp, list);
            }
        }

        // 图的广度优先遍历
        Deque<Pair<String, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair(beginWord, 1));
        HashSet<String> visited = new HashSet<>(); // set用于注册遍历过得单词
        while (!queue.isEmpty()) {
            Pair<String, Integer> pair = queue.poll();
            String curWord = pair.getKey();
            int step = pair.getValue();
            // 遍历所有相差一个字符的单词
            for (int i = 0; i < n; i++) {
                String temp = curWord.substring(0, i) + "*" + curWord.substring(i + 1, n);
                for (String word : allComboDict.getOrDefault(temp, new ArrayList<>())) {
                    // 遇到endWord，找到最短路径
                    if (word.equals(endWord)) {
                        return step + 1;
                    }
                    // 否则继续搜索
                    if (!visited.contains(word)) {
                        queue.offer(new Pair(word, step + 1));
                        visited.add(word);
                    }
                }
            }
        }

        return 0;
    }

    // leetcode官方解方法2
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        // 需特殊处理wordlist无endword的情况(若不处理，当wordlist中无endword时也会返回最短路径，是错误的)
        if (!wordList.contains(endWord)) {
            return 0;
        }
        // 对wordlist进行预处理，方便快速查找
        HashMap<String, ArrayList<String>> allComboDict = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String temp = word.substring(0, i) + "*" + word.substring(i + 1, word.length());
                ArrayList<String> list = allComboDict.getOrDefault(temp, new ArrayList<String>());
                list.add(word);
                allComboDict.put(temp, list);
            }
        }

        // beginQueue从begin to end 搜索使用的队列
        // endQueue从end to begin 搜索使用的队列
        // cur word -> cur step
        Deque<Pair<String, Integer>> beginQueue = new ArrayDeque<>();
        Deque<Pair<String, Integer>> endQueue = new ArrayDeque<>();
        beginQueue.offer(new Pair<String, Integer>(beginWord, 1));
        endQueue.offer(new Pair<String, Integer>(endWord, 1));
        // beginVisited从begin to end 搜索使用的注册map
        // endVisited从end to begin 搜索使用的注册map
        // cur word -> cur step
        HashMap<String, Integer> beginVisited = new HashMap<>();
        HashMap<String, Integer> endVisited = new HashMap<>();
        beginVisited.put(beginWord, 1);
        endVisited.put(endWord, 1);
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            // begin to end
            int ans = visitedWordNode(allComboDict, beginQueue, beginVisited, endVisited);
            if (ans > -1) {
                return ans;
            }
            // end to begin
            ans = visitedWordNode(allComboDict, endQueue, endVisited, beginVisited);
            if (ans > -1) {
                return ans;
            }
        }

        return 0;
    }

    /**
     * 从当前方向广度优先搜索(begin to end or end to begin)
     * @param allComboDict 将wordlist预处理成hashmap方便查找，相差一个字母的所有单词
     * @param queue 当前搜索方向的队列(begin to end is beginQueue or end to begin is endQueue)
     * @param visited 当前搜索方向的注册map
     * @param otherVisited 另一个方向的注册map
     * @return 如果找到最短路径返回最短路径长度，否则返回-1
     */
    private int visitedWordNode(HashMap<String, ArrayList<String>> allComboDict, Deque<Pair<String, Integer>> queue, HashMap<String, Integer> visited, HashMap<String, Integer> otherVisited) {
        Pair<String, Integer> pair = queue.poll();
        String curWord = pair.getKey();
        int step = pair.getValue();
        // 遍历所有相差一个字符的单词
        for (int i = 0; i < curWord.length(); i++) {
            String temp = curWord.substring(0, i) + "*" + curWord.substring(i + 1, curWord.length());
            for (String word : allComboDict.getOrDefault(temp, new ArrayList<>())) {
                // 双向搜索的相交，找到最短路径
                if (otherVisited.containsKey(word)) {
                    return step + otherVisited.get(word);
                }
                // 否则继续搜索
                if (!visited.containsKey(word)) {
                    queue.offer(new Pair<>(word, step + 1));
                    visited.put(word, step + 1);
                }
            }
        }

        return -1;
    }
}

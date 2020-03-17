import java.util.*;

/**
 * @author qhhu
 * @date 2019/9/27 - 23:04
 *
 * [127] 单词接龙
 *
 * 题目：在给定字典中找到 beginWord 到 endWord 的最短转换序列的长度。
 *      （每次转换只能改变一个字母；转换过程中的中间单词必须是字典中的单词）
 *      （若不存在这样的转换序列，返回 0；所有单词具有相同的长度，所有单词只有小写字母组成；
 *      字典中不存在重复的单词；beginWord 和 endWord 是非空并且不同的）
 *
 * 难度：medium
 *
 * 思路：1. 我们对问题进行抽象：将单词看做点，如果两个单词可以相互转化，则在相应的点之间连一条无向边。那问题就变成了求从起点到终点的最短路。
 *         由于边权都相等，所以可以用 BFS 求最短路。
 *      2. 双向广度优先搜索
 */
class Solution {
    /**
     * 时间复杂度：O(m * n) (m 为单词长度，n 为字典中单词书)
     * 空间复杂度：O(m * n)
     */
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        // 将列表中的单词放入 set 中方便查找。
        HashSet<String> dict = new HashSet<>();
        for (String word : wordList) dict.add(word);
        // 结束单词不在字典中无法完成转换。
        if (!dict.contains(endWord)) return 0;

        Queue<String> queue = new ArrayDeque<>();
        // 使用 hashmap 记录每个节点距离源点（beginWord）的距离，同时它也可以当做 visited 使用。
        HashMap<String, Integer> dist = new HashMap<>();
        queue.offer(beginWord);
        dist.put(beginWord, 1);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.equals(endWord)) return dist.get(endWord);
            for (String nextWord : getNextWords(cur, dict)) {
                if (dist.containsKey(nextWord)) continue;
                queue.offer(nextWord);
                dist.put(nextWord, dist.get(cur) + 1);
            }
        }

        return 0;
    }

    // 找到与节点 word 相邻的节点。
    private List<String> getNextWords(String word, HashSet<String> dict) {
        List<String> ret = new ArrayList<>();
        char[] chrs = word.toCharArray();
        for (int i = 0; i < chrs.length; i++) {
            // 每次替换一个位置为另一个字母，来寻找所有相邻的节点
            for (char chr = 'a'; chr <= 'z'; chr++) {
                if (chrs[i] == chr) continue;
                char oldChr = chrs[i];
                chrs[i] = chr;
                String newWord = new String(chrs);
                if (dict.contains(newWord)) ret.add(newWord);
                chrs[i] = oldChr;
            }
        }

        return ret;
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
            List<String> neighbors = getNextWords(cur, dict);
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
}

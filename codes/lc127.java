import java.util.LinkedList;
import java.util.List;

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
 * 思路：对问题建模，整个问题转化为一个图论问题，
 *       
 */
public class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        int end = wordList.indexOf(endWord);
        if(end == -1)
            return 0;

        if(!wordList.contains(beginWord))
            wordList.add(beginWord);
        int begin = wordList.indexOf(beginWord);

        int n = wordList.size();
        boolean[][] g = new boolean[n][n];
        for(int i = 0 ; i < n ; i ++)
            for(int j = 0 ; j < i ; j ++)
                g[j][i] = g[i][j] = similar(wordList.get(i), wordList.get(j));

        // bfs
        LinkedList<Integer> q = new LinkedList<>();
        int[] step = new int[n];

        q.addLast(begin);
        step[begin] = 1;
        while(!q.isEmpty()){

            int cur = q.removeFirst();

            for(int i = 0 ; i < n ; i ++)
                if(step[i] == 0 && g[cur][i]){
                    if(i == end)
                        return step[cur] + 1;
                    step[i] = step[cur] + 1;
                    q.addLast(i);
                }
        }

        return 0;
    }

    private boolean similar(String word1, String word2){
        int diff = 0;
        for(int i = 0 ; i < word1.length() ; i ++)
            if(word1.charAt(i) != word2.charAt(i)){
                diff ++;
                if(diff > 1)
                    return false;
            }
        return true;
    }
}

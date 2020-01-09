import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * @author qhhu
 * @date 2020/1/5 - 16:00
 *
 * [1311] 获取你好友已观看的视频
 *
 * 题目: 有 n 个人, 每个人都有一个 0到n - 1的唯一 id. 给你数组watchedVideos和friends, 其中watchedVideos[i]和friends[i]
 *      分别表示id = i的人观看过的视频列表和他的好友列表. Level1的视频包含所有你好友观看过的视频, level2的视频包含所有你
 *      好友的好友观看过的视频, 以此类推. 一般的, Level为k的视频包含所有从你出发, 最短距离为k的好友观看过的视频. 给定你的
 *      id和一个level值, 请你找出所有指定level的视频, 并将它们按观看频率升序返回. 如果有频率相同的视频, 请将它们按名字字
 *      典序从小到大排列
 *
 * 难度: medium
 *
 * 思路: 首先bfs取出第k层的好友, 再根据这些好友观看视频的次数和视频的名字进行排序
 */
class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        boolean[] visited = new boolean[friends.length];
        // bfs
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(id);
        visited[id] = true;
        while (level != 0) {
            int curLevelSize = queue.size();
            for (int i = 0; i < curLevelSize; i++) {
                int curFriendId = queue.poll();
                for (int friend : friends[curFriendId]) {
                    if (visited[friend]) {
                        continue;
                    }
                    queue.offer(friend);
                    visited[friend] = true;
                }
            }
            level--;
        }

        // 统计第k层好友观看视频及次数
        HashMap<String, Integer> freq = new HashMap<>();
        while (!queue.isEmpty()) {
            int friendId = queue.poll();
            for (String video : watchedVideos.get(friendId)) {
                freq.put(video, freq.getOrDefault(video, 0) + 1);
            }
        }

        // 排序返回
        return freq.entrySet().stream().sorted(
                (x, y) -> {
                    if (x.getValue() == y.getValue()) {
                        return x.getKey().compareTo(y.getKey());
                    } else {
                        return x.getValue() - y.getValue();
                    }
                }
        ).map(x -> x.getKey()).collect(Collectors.toList());
    }
}

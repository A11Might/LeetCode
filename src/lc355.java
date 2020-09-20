/**
 * [355] 设计推特
 * 
 * 题目：设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。
 * 
 * 难度：medium
 * 
 * 思路：多路归并
 */
class Twitter {

    private HashMap<Integer, List<int[]>> tweets;
    private HashMap<Integer, HashSet<Integer>> follows;
    private int ts; // 时间戳

    /** Initialize your data structure here. */
    public Twitter() {
        tweets = new HashMap<>();
        follows = new HashMap<>();
        ts = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (tweets.get(userId) == null) tweets.put(userId, new ArrayList<>());
        tweets.get(userId).add(new int[] {ts++, tweetId});
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        // 使用堆进行多路归并
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        if (follows.get(userId) == null) follows.put(userId, new HashSet<>());
        follows.get(userId).add(userId); // 在粉丝列表中加入自己，用于检索粉丝和用户自己发出的推特最新推特
        for (int followId : follows.get(userId)) {
            List<int[]> tweet = tweets.get(followId);
            if (tweet != null && tweet.size() != 0) {
                int idx = tweet.size() - 1;
                heap.offer(new int[] {tweet.get(idx)[0], tweet.get(idx)[1], idx, followId});
            }
        }

        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < 10 && heap.size() != 0; i++) {
            int[] lastestTweet = heap.poll();
            ret.add(lastestTweet[1]);
            int idx = lastestTweet[2] - 1;
            if (idx >= 0) {
                int author = lastestTweet[3];
                List<int[]> tweet = tweets.get(author);
                heap.offer(new int[] {tweet.get(idx)[0], tweet.get(idx)[1], idx, author});
            }
        }
        return ret;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (follows.get(followerId) == null) follows.put(followerId, new HashSet<>());
        follows.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (follows.get(followerId) == null) follows.put(followerId, new HashSet<>());
        follows.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
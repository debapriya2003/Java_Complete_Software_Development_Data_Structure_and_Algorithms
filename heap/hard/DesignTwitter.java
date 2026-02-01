package heap.hard;
import java.util.*;

/*
=====================================================================================
DESIGN TWITTER
-------------------------------------------------------------------------------------
Problem:
Design a simplified version of Twitter with the following functionalities:

1. postTweet(userId, tweetId)
   → A user posts a new tweet.

2. getNewsFeed(userId)
   → Retrieve the 10 most recent tweet IDs in the user's news feed.
     Tweets must be ordered from most recent to least recent.
     Tweets include:
       • User's own tweets
       • Tweets of users they follow

3. follow(followerId, followeeId)
   → Follower follows a followee.

4. unfollow(followerId, followeeId)
   → Follower unfollows a followee.

Constraints:
• Operations must be efficient
• Tweets are ordered by time
• Each tweet has a unique timestamp
=====================================================================================
*/

public class DesignTwitter {

    /*
    =====================================================================================
    INNER CLASS: Tweet
    -------------------------------------------------------------------------------------
    Represents a Tweet with:
    • tweetId     → unique identifier
    • timeStamp  → used for ordering tweets
    =====================================================================================
    */
    static class Tweet {
        int tweetId;
        int timeStamp;

        Tweet(int tweetId, int timeStamp) {
            this.tweetId = tweetId;
            this.timeStamp = timeStamp;
        }
    }

    // Global timestamp to simulate real-time ordering
    private int time;

    // userId → list of tweets posted by user
    private Map<Integer, List<Tweet>> userTweets;

    // userId → set of followees
    private Map<Integer, Set<Integer>> followMap;

    /*
    =====================================================================================
    CONSTRUCTOR
    -------------------------------------------------------------------------------------
    Initializes all required data structures.
    =====================================================================================
    */
    public DesignTwitter() {
        time = 0;
        userTweets = new HashMap<>();
        followMap = new HashMap<>();
    }

    /*
    =====================================================================================
    FUNCTION: postTweet
    -------------------------------------------------------------------------------------
    Allows a user to post a tweet.

    Time Complexity  : O(1)
    =====================================================================================
    */
    public void postTweet(int userId, int tweetId) {

        userTweets.putIfAbsent(userId, new ArrayList<>());
        userTweets.get(userId).add(new Tweet(tweetId, time++));
    }

    /*
    =====================================================================================
    FUNCTION: getNewsFeed
    -------------------------------------------------------------------------------------
    Returns the 10 most recent tweets from:
    • The user themselves
    • Users they follow

    DATA STRUCTURE USED:
    • Max Heap (Priority Queue) sorted by timestamp

    Time Complexity  : O((F + T) log (F + T))
    Space Complexity : O(F + T)

    Where:
    • F = number of followees
    • T = tweets per user (limited to recent ones)
    =====================================================================================
    */
    public List<Integer> getNewsFeed(int userId) {

        PriorityQueue<Tweet> maxHeap =
                new PriorityQueue<>((a, b) -> b.timeStamp - a.timeStamp);

        // Add user's own tweets
        if (userTweets.containsKey(userId)) {
            maxHeap.addAll(userTweets.get(userId));
        }

        // Add followees' tweets
        if (followMap.containsKey(userId)) {
            for (int followee : followMap.get(userId)) {
                if (userTweets.containsKey(followee)) {
                    maxHeap.addAll(userTweets.get(followee));
                }
            }
        }

        List<Integer> feed = new ArrayList<>();

        // Extract up to 10 most recent tweets
        while (!maxHeap.isEmpty() && feed.size() < 10) {
            feed.add(maxHeap.poll().tweetId);
        }

        return feed;
    }

    /*
    =====================================================================================
    FUNCTION: follow
    -------------------------------------------------------------------------------------
    Allows one user to follow another user.

    Time Complexity  : O(1)
    =====================================================================================
    */
    public void follow(int followerId, int followeeId) {

        if (followerId == followeeId) return;

        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    /*
    =====================================================================================
    FUNCTION: unfollow
    -------------------------------------------------------------------------------------
    Allows one user to unfollow another user.

    Time Complexity  : O(1)
    =====================================================================================
    */
    public void unfollow(int followerId, int followeeId) {

        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates Twitter functionality.
    =====================================================================================
    */
    public static void main(String[] args) {

        DesignTwitter twitter = new DesignTwitter();

        twitter.postTweet(1, 5);
        System.out.println("User 1 feed: " + twitter.getNewsFeed(1));

        twitter.follow(1, 2);
        twitter.postTweet(2, 6);

        System.out.println("User 1 feed after following 2: " + twitter.getNewsFeed(1));

        twitter.unfollow(1, 2);
        System.out.println("User 1 feed after unfollowing 2: " + twitter.getNewsFeed(1));
    }
}


/*
 * @lc app=leetcode.cn id=416 lang=java
 *
 * [416] 分割等和子集
 * 
 * 题目：判断一个包含正整数的非空数组，能否分割成两个元素和相等的子集
 * 
 * 思路：
 * 
 * 
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) return false;
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int i = sum; i >= 0; i--) {
                if (dp[i]) dp[i + num] = true;
            }
            if (dp[sum / 2]) return true;
        }
        return false;
    }
}

LeetCode416. 分割等和子集——记忆化深度优先搜索 + 动态规划方法演变详解

/**
大佬给出的一维数组动态规划有点懵逼。
这里先给出二维数组的动态规划，然后给出转化为一维数组的方法。理解起来相信非常容易。
所以这里会给出三个版本的代码：
二维数组动态规划
一维数组动态规划“二维转为一维的中间过程”
一维数组动态规划“最终版”
**/
// d(i, s) : 是否存在：nums区间[0, i] 中取一些元素，使其和为s
// d(i, s) = d(i-1, s){不取nums[i]} || d(i-1, s-nums[i]){取nums[i]}
// max(i) = nums.size()-1
// max(s) = sum(nums)/2
刚开始动态规划不太理解，后来发现：
我们求dp第i行的时候dp[i][?]，我们只需要知道dp的i-1行即可dp[i-1][?]。
也就是说，按照这个依赖关系，一直往下递推，只要得到第0行即可。
/*而第0行非常容易求。dp[0][s] = true当且仅当nums[0]==s*/
//图解：
//     s0 s1 s2 ...              ...sum 
// i-1 [  {s-nums[i]}  ...       s    ]
//   i [               ...       s    ]
//dp[i][s] = dp[i-1][s] || dp[i-1][s-nums[i]]
//这里要保证下标i-1>=0，所以第0行可以单独计算。
//计算方法：i==0时，s用j遍历[0, sum(nums)]区间
//发现nums[0]==s[j]，则dp[0][j]=true;
class Solution {
public:
  bool canPartition(vector<int>& nums) {
    int sum = 0;
    for(int e : nums) sum += e;
    if(sum & 1) return false;//奇数显然不符合条件
    vector<vector<bool>> d(nums.size(), vector<bool>((sum>>=1)+1, false));//sum/=2
    for(int i = 0 ; i < nums.size() ; i++){
      for(int s = 0 ; s <= sum ; s++){//s range [0, sum(nums)>>1]
        if(!i) d[i][s] = (nums[i]==s);//i==0要单独求{ nums[0]一个元素和为s }
        else d[i][s] = d[i-1][s] || (s-nums[i]>=0 ? d[i-1][s-nums[i]] : false);
      }
    }
    return d[nums.size()-1][sum];//[0,nums.size()-1]区间和为sum
  }
};
优化版本：
上面看到，我们求解dp第i行dp[i][?]的时候，只需要知道第i-1行dp[i-1][?]的值即可。
也就是说，我们没必要开这么大的二维数组空间，直接开一个一维数组空间保存前一行的值就ok了。
下面给出二维转一维的中间过程的代码。在最后会给出清晰的最终代码
class Solution {
public:
  bool canPartition(vector<int>& nums) {
    int sum = 0;
    for(int e : nums) sum += e;
    if(sum & 1) return false;
    vector<bool> d((sum>>=1)+1, false);//sum/=2
    for(int i = 0 ; i < nums.size() ; i++){
      for(int s = sum ; s >= 0 ; s--/*int s = 0 ; s <= sum ; s++*/){//从后往前，因为前面的元素我们已经求过了(i>0时确实已经求过了，i==0时我们求一遍即可，下面的代码也确实给出了i==0的情况)，可以直接用
        if(!i) d/*[i]*/[s] = (nums[i]==s);//i==0要单独求{ nums[0]一个元素和为s }
        else d/*[i]*/[s] = d/*[i-1]*/[s] || (s-nums[i]>=0 ? d/*[i-1]*/[s-nums[i]] : false);
      }
    }
    return d/*[nums.size()-1]*/[sum];//[0,nums.size()-1]区间和为sum
  }
};
/*最后，这里给出最简的一维数组动态规划代码*/
class Solution {
public:
  bool canPartition(vector<int>& nums) {
    int sum = 0;
    for(int e : nums) sum += e;
    if(sum & 1) return false;
    vector<bool> d((sum>>=1)+1, false);//sum/=2
    for(int i = 0 ; i < nums.size() ; i++){
      for(int s = sum ; s >= nums[i] ; s--){//从后往前，因为前面的元素我们已经求过了(i>0时确实已经求过了，i==0时我们求一遍即可，下面的代码也确实给出了i==0的情况)，可以直接用
        if(!i) d[s] = (nums[i]==s);//i==0要单独求{ nums[0]一个元素和为s }
        else d[s] = d[s] || d[s-nums[i]];
      }
    }
    return d[sum];
  }
};


/**
 * @param {number[]} nums
 * @return {boolean}
 * 背包问题：看数组中是否存在加起来为sum/2的数.
 * 背包容量（V） = sum/2
 * 每一个物品只能装一次,如果出现背包中重量等于sum/2则为true else false
 * dp[i]表示能否填满容量为i的背包
 * 状态转移方程为 dp[i] = dp[i-1] || nums[i]+dp[i-nums[j]]
 * 举例:v = sum/2 = 11   nums = [1,5,11,5]  1是true 0 是false
 *          0  1  2  3  4  5  6  7  8  9  10  11
 *  nums[0] 0  1  0  0  0  0  0  0  0  0   0   0
 *  nums[1] 0  1  0  0  0  1  1  0  0  0   0   0
 *  nums[2] 0  1  0  0  0  1  1  0  0  0   0   1
 *  nums[3] 0  1  0  0  0  1  1  0  0  0   0   1
 * 
 * 所以返回true，因为背包中nums[i]的状态都是由上一行决定的因此可以将二维转化为1维数组从尾部开始
 */
var canPartition = function (nums) {
    if (nums.length <= 1) return false
    let sum = nums.reduce((a, b) => { return a + b })
    if (sum % 2 !== 0) return false
    let capacity = sum / 2
    let n = nums.length
    let dp = new Array(capacity + 1).fill(0)
    for (let i = 0; i <= capacity; i++)
        dp[i] = nums[0] === i
    for (let i = 1; i < n; i++)
        for (let j = capacity; j >= nums[i]; j--) {
            dp[j] = dp[j] || dp[j - nums[i]]
        }
    return dp[capacity]
};

这道题是0-1背包问题 我用到了动态规划中备忘录的方法 通过记录每种子集的情况来解题(其实就是枚举了所有的情况 不过复杂度没有那么高)

首先进行两次特判 即原来数组长度为0,1,2时候的情况

0和1的时候不可能有子集，所以返回false

2的时候只需看两个元素是否相等就可以了

两次特判后我们对原数组求和 如果总和为奇数的话也说明不能分成俩相等的子集，返回false

接下来是动态规划备忘录的解法

首先开辟一个长度为 (原数组求和)/2 长度的bool类型vis数组表示访问状态，这个数组用来表示子集的合的可能性。

比如[1, 5, 11, 5] 当子集是[1,5]的时候，vis[6]就为1 记录下了这次子集的和 子集是[1,5,5]的时候 vis[11]就是1 以此类推(你可能还在代码中看到了max_mid max_max 待会就说)

然后两重循环

第一重遍历原来数组的全部元素，每次遍历都相当于向已经有的子集(也就是vis数组中值为1的地方)中添加了一个元素 第二层遍历vis,从0~max_mid，来遍历所有已经存在的子集。

max_mid是添加当前元素(nums[i])之前，上一轮循环中最大的子集 这样就可以保证我不会遍历0~mid的全部元素，减少了遍历次数 而max_mid的求法则是在遍历vis的时候用max_max获得添加当前元素(nums[i-1])时子集的最大值，然后在退出这个vis循环后用max_max更新mid_max的值 这样在添加下一个元素(nums[i])的时候就可以得到上一次子集的最大值了。

两重循环的目的我通过举一个例子来讲 比如对于[1, 5, 11, 5] nums[0]=1 因此最开始的时候是vis[1]=1 然后进入两重循环

第一重循环中 上一次循环的最大值就是nums[0]=1 因此遍历0~1 在vis[1]处发现vis[1]=1 判断1+5不会超过11(也就是原数组和的一半)，因此记录vis[1+5]=1 退出循环 更新max_mid = 6

第二重循环 元素为11 遍历vis中0~6 发现在vis[1]和vis[6]处都为1 但1+11>11，6+11>11因此不进行操作 max_mid 不更新

第三重循环 元素为5 遍历vis中0~6 发现在vis[1]和vis[6]处都为1 但1+5=6，6+5=11因此vis[6]=1 vis[11]=1 此时找到了合为11的子集 返回true

如果循环全部结束还没有找到子集 那就不存在了

 bool canPartition(vector<int>& nums) 
    {
        int len = nums.size();
        if(len==0||len == 1)return false;
        if(len==2)return nums[0]==nums[1];
        int mid = 0;
        for(int i =0;i<len;i++)
        {
            mid+=nums[i];
        }
        if(mid%2!=0)return false;
        else mid/=2;
        vector<bool>vis(mid+1);
        int max_mid = nums[0];
        int max_max = max_mid;
        vis[max_mid]=1;
        for(int i = 1;i<len;i++)
        {
            for(int j = 0;j<=max_mid;j++)
            {
              
                if(vis[j]&&j+nums[i]<=mid)
                {
                   // cout<<j<<" "<<nums[i]<<endl;
                    vis[j+nums[i]] = 1;
                    if(j+nums[i]>max_max)max_max = j+nums[i];
                }
                if(vis[mid]==1)return true;
            }
            max_mid = max_max;
        }
        return false;
    }

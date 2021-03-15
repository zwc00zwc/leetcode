package leetcode.dp;

import java.util.Arrays;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-16 18:15
 */
public class App {
    public static void main(String[] args){
        int[] array = new int[]{7,1,5,3,6,4};
        //int rs = lengthOfLongestSubstring("abba");
//        String[] array = new String[]{"10", "0001", "111001", "1", "0"};
//        int res = findMaxForm1(array,5,3);

        //int[] array = new int[]{1,1,1,1,1};
        maxProfit1(array);
//        int res = change(5,array);
        //int rs = findMaxForm(array,5,3);
        //int[] a = new int[]{1,3,6,7,9,4,10,5,6};
//        int[] a = new int[]{10,9,8,7};
//        int[] b = new int[]{5,6,7,8};
//        int[] c = new int[]{7,6,5,4,3,2,1};
//        int rs = countSubstrings("aaa");
        //int rs = findContentChildren(a,b);
        //int rs = lengthOfLIS(a);
//        int[] nums = new int[]{1,2,5};
//        boolean res = canPartition(nums);
        //int rs = uniquePaths(3,7);
//        int res = findTargetSumWays1(array,3);
//        System.out.println(res);
    }


    /**
     * 474 1和0 三维数组
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm(String[] strs, int m, int n){
        //定义的三维数组起始可以理解为两维数组，第一个下标是字符数组的子集推导，即从0-1子集，0-2子集一步一步到0-i子集
        //后面的两个下标定义的是背包的容量，即0，1能放多少的背包
        int[][][] dp = new int[strs.length+1][m+1][n+1];
        //使用三维数组状态转移必须依赖前一个子集，之前子集最多加上当前字符串即当前字符串下最大
        //当i下标的字符串放不下 0~(i-1)背包能放下的最大值 加上i能满足背包容量，即0~i背包能放下的最大值，一步一步知道整个数组

        for (int i = 1; i<=strs.length;i++){
            int[] count = strCount(strs[i-1]);
            for (int j = 0; j<=m;j++){
                for (int k = 0;k<=n;k++){
                    //当前字符串是否大于背包容量，如果大于背包容量(背包放不下了)，则继承之前子集的最大数量
                    if (count[0]>j || count[1]>k){
                        dp[i][j][k] = dp[i-1][j][k];
                    }else {
                        //如果当前字符串可以放进背包容量，则状态转移,之前子集最大的数量和
                        //之前子集减去当前字符串容量的加1（相当于把当前字符串放到之前子集中组成新的子集）比较最大值
                        dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-count[0]][k-count[1]]+1);
                    }
                }
            }
        }

        return dp[strs.length][m][n];
    }

    /**
     * 474 1和0 二维数组
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public static int findMaxForm1(String[] strs, int m, int n) {
        //定义dp数组，即背包，下标是背包容量指标下最大的子集数量
        int[][] dp = new int[m+1][n+1];

        //遍历字符串数组
        for (int i = 0;i<strs.length;i++){
            int[] temp = strCount(strs[i]);
            //状态转移，字符串数组中的结果只能放一次，只能进行倒叙遍历背包容量指标
            //dp[j][k] = Math.max(dp[j][k],dp[j-temp[0]][k-temp[1]]+1);
            for (int j = m;j>=temp[0];j--){
                for (int k = n;k>=temp[1];k--){
                    dp[j][k] = Math.max(dp[j][k],dp[j-temp[0]][k-temp[1]]+1);
                }
            }
        }
        return dp[m][n];
    }

    public static int[] strCount(String s){
        int[] temp = new int[2];
        for (int i = 0;i<s.length();i++){
            if (s.charAt(i)=='0'){
                temp[0]++;
            }
            if (s.charAt(i) == '1'){
                temp[1]++;
            }
        }
        return temp;
    }

    /**
     * 300最长递增子序列
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        // dp[i] 可能会是 nums[0]~nums[i-1]中任意的一个数组组成递增子序列
        if (nums.length<2){
            return nums.length;
        }
        int max = 1;
        //定义dp数组，下标为当前指针下自增的子序列长度
        int[] dp = new int[nums.length];
        dp[0]=1;
        for (int i = 1;i<nums.length;i++){
            //初始为1
            dp[i] = 1;
            //自增子序列开头值会变化，重新整理dp数组
            for (int j = 0;j<i;j++){
                //当前指针和前面指针值进行比较，计算当前指针之前的最长自增子序列
                //如果指针i大于j，只需将最长子序列+1，转换方程，dp[i] = Math.max(dp[i],dp[j]+1)
                if (nums[i]>nums[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                    max = Math.max(max,dp[i]);
                }
            }
        }

        return max;
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题
     * @param n
     * @return
     */
    public static int numWays(int n) {
        if (n<2){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    /**
     * 剑指 Offer 10- II. 青蛙跳台阶问题 转换背包问题解法
     * @param n
     * @return
     */
    public static int numWays1(int n) {
        //定义dp数组，背包容量为台阶数，整体定义为背包容量放满物品最多的排列数
        int[] dp = new int[n+1];
        //初始化，容量为0，组合次数为1
        dp[0] = 1;
        //跳台阶为物品排列，背包遍历放外面
        for (int j = 1;j<=n;j++){
            //物品为1和2
            for (int i = 1;i<=2;i++){
                if (j>=i){
                    //转换方程dp[j] = dp[j] + dp[j-i]
                    dp[j] = (dp[j]+dp[j-i]) % 1000000007;
                }
            }
        }
        return dp[n];
    }

    /**
     * 455. 分发饼干
     * 贪心算法，最大的饼干给胃口最大的孩子
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int k = g.length-1;

        for (int i = s.length-1;i>=0;i--){
            while (k>=0){
                if (s[i]>=g[k]){
                    res++;
                    k--;
                    break;
                }
                k--;
            }
        }
        return res;
    }

    /**
     * 121. 买卖股票的最佳时机
     * 动态规划解法利用两个动态数组记录0-i的最大收益和0-i的最小值
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int res = 0;
        if (prices.length<2){
            return 0;
        }
        //存储0-i的最大收益 转换方程是 0-i收益 = i 减去 (0-i)最小的值
        int[] dp = new int[prices.length];
        //存储0-i的最小值 转换方程是 0-i的最小值 = 0-(i-1)最小值 和当前值小的值
        int[] mimDp = new int[prices.length];
        mimDp[0] = prices[0];
        for (int i = 1;i<prices.length;i++){
            //0-i最小值转换方程
            mimDp[i] = Math.min(mimDp[i-1],prices[i]);
            //0-i最大收益转换方程
            dp[i] = Math.max(dp[i-1],prices[i] - mimDp[i]);
            res = Math.max(res,dp[i]);
        }
        return res;
    }

    /**
     * 122. 买卖股票的最佳时机 II
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        // cash：持有现金账户有多少钱
        // hold：持有股票账户有多少钱
        // 推导方程为现金账户等于前一天现金账户+前一天的股票账户(全卖)
        // 股票账户等于前一天的股票账户+前一天的现金账户(全买)
        int[] cash = new int[len];
        int[] hold = new int[len];

        //
        cash[0] = 0;
        //初始值，第一天持有股票当前账户等于是倒欠钱，现金账户为-prices[0]
        hold[0] = -prices[0];
        System.out.println("持有现金："+cash[0]);
        System.out.println("持有股票："+hold[0]);

        //7,1,5,3,6,4
        for (int i = 1; i < len; i++) {
            //推导方程，如果这一天所有的股票都卖出大于当前账户钱，就卖出，如果当前股票卖出
            //账户金额小于持有金额账户金额，说明股票价格低，不合适
            cash[i] = Math.max(cash[i - 1], hold[i - 1] + prices[i]);
            //持股账户金额等于持现金的账户金额-股票价格，判断是否要买股票，如果现金
            //买完股票账户金额小于了之前的持有股票账户金额，说明当前价格高不合适
            hold[i] = Math.max(hold[i - 1], cash[i - 1] - prices[i]);
            System.out.println("持有现金："+cash[i]);
            System.out.println("持有股票："+hold[i]);
        }
        return cash[len - 1];
    }

    /**
     * 647. 回文子串
     * 动态规划解法
     * @param s
     * @return
     */
    public static int countSubstrings(String s) {
        int res = 0;
        //定义动态数组指针之间是否为回文子串
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0;i<s.length();i++){
            dp[i][i] = true;
            res++;
            for (int k = 0;k<i;k++){
                //字符串相当需要判断是否是回文子串，不相等直接不是回文子串
                if (s.charAt(k) == s.charAt(i)){
                    //动态转换方程，dp[k][i] = dp[k+1][i-1]
                    //但需要一个条件k和i之间需要有字符串，即i-k>1
                    if (i-k>1){
                        dp[k][i] = dp[k+1][i-1];
                    }else {
                        dp[k][i] = true;
                    }
                }else {
                    dp[k][i] = false;
                }
                //是回文子串+1
                if (dp[k][i]){
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 62. 不同路径
     * 动态规划解法
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //边界只能有一条走法
        for (int i = 0; i < n; i++){
            dp[0][i] = 1;
        }
        //边界只能有一条走法
        for (int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        //转换方程打到下一个点只能走右或者下,所以获得所有走法有以下方程
        //f[i][j]=f[i-1][j]+f[i][j+1]
        //举例要到达f[3][3]点，所有走法 f[3][3]=f[2][3]+f[3][4]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        //数组从0开始，所以传参-1位实际位置
        return dp[m - 1][n - 1];
    }

    /**
     * 64. 最小路径和
     * 动态规划解法
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if (grid.length<1){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        //定义dp数组，两维数组m,n最小的和
        int[][] dp = new int[m][n];
        //设置初始值
        dp[0][0] = grid[0][0];
        //从左上角到右下角，只能向下或者向右，所以左边界和上边界只能有一种走法
        //初始左边界和
        for (int i = 1;i<m;i++){
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        //初始上边界和
        for (int i = 1;i<n;i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }

        //转换方程，dp[i][k] = Math.min(dp[i][k-1],dp[i-1][k]) + grid[i][k];
        //遍历所有元素，转换计算
        for (int i = 1;i<m;i++){
            for (int k = 1;k<n;k++){
                dp[i][k] = Math.min(dp[i][k-1],dp[i-1][k]) + grid[i][k];
            }
        }
        //返回结果
        return dp[m-1][n-1];
    }

    /**
     * 416. 分割等和子集
     * @param nums
     * 使用动态规划进行解题，转化为背包问题，只要判断容量为nums和一半的背包能够被放满即可
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i= 0;i<nums.length;i++){
            sum = sum + nums[i];
        }
        //总和不是偶数直接返回false
        if (sum % 2 >0){
            return false;
        }
        int target = sum/2;
        //定义dp数组，含义为dp容量的背包放进去的最大的子集和,dp数组大小只要数组和的一半即可
        int[] dp = new int[sum/2+1];

        //dp转换方程 dp[j] = Math.max(dp[j],dp[j-nums[i]]+num[i])
        for (int i = 0;i<nums.length;i++){
            //遍历背包空间进行状态转移，为什么使用倒叙，当前数组中的数字只能
            //放入背包一次，如果进行正序遍历，在转换方程中会将当前数字多次放入
            //背包，即dp[j-nums[i]]+num[i]会每次都加上nums[i]
            for (int j = target;j>=nums[i];j--){
                dp[j] = Math.max(dp[j],dp[j-nums[i]]+nums[i]);
            }
        }
        if (dp[target] == target){
            return true;
        }
        return false;
    }

    /**
     * 1049. 最后一块石头的重量 II
     * 此题可以理解为将数组分为两部分，两部分的和差最小
     * @param stones
     * @return
     */
    public static int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int i = 0;i<stones.length;i++){
            sum = sum+stones[i];
        }
        //总和的一半，即将数组分成和相等的两部分
        int target = sum / 2;

        int[] dp = new int[target+1];

        for (int i = 0;i<stones.length;i++){
            //遍历背包空间进行状态转移，为什么使用倒叙，当前数组中的数字只能
            //放入背包一次，如果进行正序遍历，在转换方程中会将当前数字多次放入
            //背包，即dp[j-nums[i]]+num[i]会每次都加上nums[i]
            for (int j = target;j>=stones[i];j--){
                dp[j] = Math.max(dp[j],dp[j-stones[i]]+stones[i]);
            }
        }
        return Math.abs(dp[target] - (sum - dp[target]));
    }

    /**
     * 322. 零钱兑换
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0){
            return 0;
        }
        //定义零钱背包，及dp下标金额所需要的最少硬币
        int[] dp = new int[amount+1];

        //该题为完全背包问题，遍历背包容量从小到大
        for (int i = 1;i<=amount;i++){
            for (int j = 0;j<coins.length;j++){
                //初始化dp
                if (i == coins[j]){
                    dp[i] = 1;
                    break;
                }
                //转换方程 dp[i] = Math.min(dp[i-coin] + 1)
                //需要调节dp[i-coin]是有值的，即i-coin是可以有硬币组成的
                if (i>=coins[j] && dp[i-coins[j]]>0){
                    //因为是取最小值 如果dp[i]为0，直接设置值，其余情况用转换方程设置
                    if (dp[i]==0){
                        dp[i] = dp[i-coins[j]]+1;
                    }else {
                        dp[i] = Math.min(dp[i],dp[i-coins[j]]+1);
                    }
                }
            }
        }
        return dp[amount] == 0?-1:dp[amount];
    }

    /**
     * 518. 零钱兑换 II
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
        //和爬楼梯一样
        //定义dp数组，下标最多的组合
        int[] dp = new int[amount+1];
        //该题和爬楼梯类似，设置初始值，dp[0]=1，即背包容量和放入背包相等组合也为1
        dp[0] = 1;
        //遍历循环，如果是硬币在外面，就是组合，如果是背包容量在外面，就是排列
        for (int j = 0;j<coins.length;j++){
            for (int i = 1;i <=amount;i++){
                if (i<coins[j]){
                    continue;
                }
                //转换方程，dp[i] = dp[i-coin]
                dp[i] = dp[i] + dp[i-coins[j]];
            }
        }

        return dp[amount];
    }

    /**
     * 494. 目标和
     * @param nums
     * @param S
     * @return
     */
    public static int findTargetSumWays(int[] nums, int S) {
        //动态规划解法，该题可以分解为将nums数组分为量部分，一部分是加法集合p，一部分为减法集合q
        //数组中总数为sum，加法集合总和为sum(p)，减法集合总和sum(q),sum=sum(p)+sum(q)
        //S=sum(p)-sum(q)进行加法运算 sum(p) = S + sum(q);
        // sum(p)+sum(p)=S + sum(q)+sum(p);2sum(p) = s + sum
        //得到sum(p) = (s+sum)/2
        //可以将题目转化为在数组中求有多少个子集总和为sum(p)
        //转化为01背包问题，即求背包容量为sum(p)的背包有几种方式可以装满

        int sum = 0;
        for (int i = 0;i<nums.length;i++){
            sum = sum + nums[i];
        }

        if (S>sum){
            return 0;
        }

        //总和不能整除2则背包不存在，即没有结果
        if ((S+sum)%2>0){
            return 0;
        }

        //定义dp数组，下标为背包容量，即该背包容量可以被装满的子集数
        int[] dp = new int[(S+sum)/2+1];

        //初始化dp，即背包容量为1，不放任何东西都一个填满背包，初始就是1
        dp[0] = 1;

        //物品在外，求组合，背包容量在外求排列
        for (int i = 0;i<nums.length;i++){
            //转换方程，dp[j] = dp[j] + dp[j-num];即背包容量为j的背包装满
            //只需要背包容量j-num的背包再放入一个num即可
            //此处需要倒叙遍历背包，倒叙每个物品只能放入一次
            for (int j = (S+sum)/2;j>=nums[i];j--){
                dp[j] = dp[j] + dp[j-nums[i]];
            }
        }

        return dp[(S+sum)/2];
    }

    public static int findTargetSumWays1(int[] nums, int S) {
        //动态规划解法，该题可以分解为将nums数组分为量部分，一部分是加法集合p，一部分为减法集合q
        //数组中总数为sum，加法集合总和为sum(p)，减法集合总和sum(q),sum=sum(p)+sum(q)
        //S=sum(p)-sum(q)进行加法运算 sum(p) = S + sum(q);
        // sum(p)+sum(p)=S + sum(q)+sum(p);2sum(p) = s + sum
        //得到sum(p) = (s+sum)/2
        //可以将题目转化为在数组中求有多少个子集总和为sum(p)
        //转化为01背包问题，即求背包容量为sum(p)的背包有几种方式可以装满

        int sum = 0;
        for (int i = 0;i<nums.length;i++){
            sum = sum + nums[i];
        }

        if (S>sum){
            return 0;
        }

        //总和不能整除2则背包不存在，即没有结果
        if ((S+sum)%2>0){
            return 0;
        }

        //定义dp二维数组
        //第一个下标是指0-i子集进行操作，第二个下标是背包容量
        //dp整体定义为是0-i子集中背包容量能放满的组合数
        int[][] dp = new int[nums.length+1][(S+sum)/2+1];

        //初始化dp，下标为背包容量，即该背包容量可以被装满的子集数
        dp[0][0] = 1;

        //推导过程，下标i表示0-i子集，子集i-1推导到子集i，所以每一个i都是依赖于i-1的子集
        //dp[i][j] = dp[i-1][j] i下标为num，如果背包容量j放不下num，则0-i子集背包能放满的组合数就是0-(i-1)子集的组合数
        //dp[i][j] = dp[i-1][j] + dp[i-1][i-num] 如果num能放入背包中，则0-i子集背包能放满的组合数为0-(i-1)子集的组合数加上0-(i-1)少num的组合数

        //物品在外，求组合，背包容量在外求排列
        for (int i = 1;i<=nums.length;i++){
            //转换方程，dp[i][j] = dp[i][j] + dp[i][j-num];即背包容量为j的背包装满
            //二维数组可以通过正序
            for (int j = 0;j<=(S+sum)/2;j++){
                //背包容量放不下物品，从i-1继承组合数(子集推导过程)
                if (nums[i-1]>j){
                    dp[i][j] = dp[i-1][j];
                }else {
                    //背包能放下物品，进行结果计算
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
                }
            }
        }

        return dp[nums.length][(S+sum)/2];
    }

    /**
     * 1553. 吃掉 N 个橘子的最少天数 (动态规划超时了)
     * @param n
     * @return
     */
    public static int minDays(int n) {
        //定义dp数组
        int[] dp = new int[n+1];

        //完全背包问题
        for (int i = 1;i<=n;i++){
            if (dp[i]==0){
                dp[i] = dp[i-1]+1;
            }else {
                dp[i] = Math.min(dp[i],dp[i-1]+1);
            }
            //物品有3个
            if (i%2==0){
                int temp = i/2;
                dp[i] = Math.min(dp[i],dp[i-temp]+1);
            }
            if (i%3==0){
                int temp = 2*(i/3);
                dp[i] = Math.min(dp[i],dp[i-temp]+1);
            }
        }

        return dp[n];
    }


    /**
     * 1155. 掷骰子的N种方法
     * @param d
     * @param f
     * @param target
     * @return
     */
    public static int numRollsToTarget(int d, int f, int target) {
        if (target < d || target > d * f) {
            return 0;
        }

        //解题为 有d个物品，重量随机在[1,f]，需要在背包容量为target中放满，且所有物品都要使用到
        //定义dp数组，放置n个物品的总重量，值为组合方式
        int[][] dp = new int[d+1][target+1];
        dp[0][0] = 1;
        int mod = 1000000007;
        //遍历物品数量
        for (int i =1;i<=d;i++){
            //遍历背包容量
            for (int j = 0;j<=target;j++){
                //遍历每个物品的容量
                for (int k = 1;k<=f;k++){
                    if (j>=k){
                        //dp转换方程 dp[i][j] = dp[i][j] + dp[i-1][j-k]
                        //因为每个物品都需要被放置
                        dp[i][j] = (dp[i][j] + dp[i - 1][j - k])%mod;
                    }
                }
            }
        }
        return dp[d][target];
    }

    /**
     * 面试题 01.05. 一次编辑
     * @param first
     * @param second
     * @return
     */
//    public boolean oneEditAway(String first, String second) {
//        //思路
//        //判断两个字符串是否是对方的子集
//        if (first.length() - second.length()>1){
//            return false;
//        }
//
//        //first字符串中和second相等的子集
//        int nums = second.length();
//        int[] dp = new int[nums+1];
//
//        for (int i = 0;i<)
//    }

    /**
     * 279. 完全平方数
     * @param n
     * @return
     */
    public int numSquares(int n) {
        //完全背包问题，物品是平方数
        int[] dp = new int[n+1];
        //遍历背包容量
        for (int i = 1;i<=n;i++){
            //遍历物品
            for (int j = 1;j*j<=n;j++){
                if (i>=j*j){
                    if (dp[i] == 0){
                        dp[i] = dp[i-j*j]+1;
                    }else {
                        dp[i] = Math.min(dp[i],dp[i-j*j]+1);
                    }
                }
            }
        }
        return dp[n];
    }

    /**
     * 5. 最长回文子串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        //动态规划解法
        //定义 boolean[][] dp = true 为指针范围是否为回文子串，
        //dp数组关系 如果 s[j] == s[i] dp[j+1][i-1]==true 则说明i-j为回文子串
        //aba也会回文子串，还有一个条件为 i-j >= 2
        if (s==null || s.length()<1){
            return "";
        }
        int max = 1;
        String res = s.substring(0,1);
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = 0;i<s.length();i++){
            dp[i][i] = true;
            for (int j = 0;j<=i;j++){
                //如果两边指针字符相等，需要判断是否为回文子串
                if (s.charAt(j) == s.charAt(i)){
                    if (i-j<=2){
                        dp[j][i] = true;
                    }else {
                        dp[j][i] = dp[j+1][i-1];
                    }
                }else {
                    dp[j][i] = false;
                }

                if (i-j+1>max && dp[j][i]){
                    max = i-j+1;
                    res = s.substring(j,i+1);
                }
            }
        }

        return res;
    }
}

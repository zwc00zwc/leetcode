package leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: siskin_zh
 * @Date: 2020 2020-12-15 09:29
 */
public class App {
    public static void main(String[] args){
        //List<List<Integer>> result = combine(4,2);

        int count = numberOfMatches(7);
        System.out.println(count);
    }

    /**
     * 组合（回溯算法）
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> v = new ArrayList<>();
        back(n,k,1,v,result);
        return result;
    }

    public static void back(int n,int k,int startIndex,List<Integer> v,List<List<Integer>> result){
        if (v.size() == k){
            List<Integer> a = new ArrayList();
            a.addAll(v);
            result.add(a);
            return;
        }
        for (int i=startIndex;i<=(n-(k-v.size())+1);i++){
            v.add(i);
            back(n,k,i+1,v,result);
            v.remove(v.size()-1);
        }
    }

//    public static List<List<String>> solveNQueens(int n) {
//        List<List<String>> result = new ArrayList<>();
//        List<String> v = new ArrayList<>();
//        backQueens()
//    }
//
//    public static void backQueens(int n,int k,int startIndex,List<String> v,List<List<String>> result){
//        if (v.size() == k){
//            for (int i=0;i<result.size();i++){
//                result.get(i).
//            }
//            List<Integer> a = new ArrayList();
//            a.addAll(v);
//            result.add(a);
//            return;
//        }
//        for (int i=startIndex;i<=(n-(k-v.size())+1);i++){
//            v.add(i);
//            back(n,k,i+1,v,result);
//            v.remove(v.size()-1);
//        }
//    }

    /**
     * 回溯 比赛配对次数
     * @param n
     * @return
     */
    public static int numberOfMatches(int n) {
        List<Integer> list = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i=1;i<=n;i++){
            list.add(i);
        }
        int count = backMatches(list,temp);
        return count;
    }

    public static int backMatches(List<Integer> list,List<Integer> temp){
        int count = 0;
        if (list.size() == 1){
            return count;
        }
        if (list.size() == 2){
            count++;
            return count;
        }

        temp = new ArrayList<>();
        count = list.size()/2;
        for (int i=0;i<list.size();i++){
            temp.add(list.get(i));
            i++;
        }
        list = temp;
        count = count + backMatches(list,temp);
        return count;
    }
}

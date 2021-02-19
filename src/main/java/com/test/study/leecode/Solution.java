package com.test.study.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    //1431. Kids With the Greatest Number of Candies
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {

        List<Boolean> list = new ArrayList<>();
        int max = 0;
        for (int i : candies) {
            if (i >= max) {
                max = i;
            }
        }

        for (int i : candies) {
            if (i + extraCandies >= max) {
                list.add(true);
            } else {
                list.add(false);
            }
        }

        return list;
    }


/* 	public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt();
        return Arrays
					.stream(candies)
					.mapToObj(a -> (a+extraCandies >= max))
					.collect(Collectors.toList());
    } */


    // 1409. Queries on a Permutation With Key
    public int[] processQueries(int[] queries, int m) {
        int[] result = new int[queries.length];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            list.add(i);
        }

        for (int i = 0; i < queries.length; i++) {
            result[i] = list.indexOf(queries[i]);
            list.remove(result[i]);
            list.add(0, queries[i]);
        }

        return result;
    }

    // 1266. Minimum Time Visiting All Points
    public int minTimeToVisitAllPoints(int[][] points) {
        int[] before = points[0];
        int result = 0;
        for (int i = 1; i < points.length; i++) {
            int max = 0;
            if (Math.abs(points[i][0] - before[0]) > Math.abs(points[i][1] - before[1])) {
                max = Math.abs(points[i][0] - before[0]);
            } else {
                max = Math.abs(points[i][1] - before[1]);
            }
            result += max;
            before = points[i];
        }

        return result;
    }

    // 1389. Create Target Array in the Given Order
    public int[] createTargetArray(int[] nums, int[] index) {
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < index.length; i++) {
            list.add(index[i], nums[i]);
        }
        int[] result = new int[nums.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i);
        }

        return result;

    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = {1, 2, 3, 4};
        System.out.println(Arrays.toString(solution.decompressRLElist(ints)));
    }

    // 1365. How Many Numbers Are Smaller Than the Current Number
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = returnNum(nums[i], nums);
        }
        return result;
    }

    public int returnNum(int num, int[] nums) {
        int count = 0;
        for (int i : nums) {
            if (num > i) {
                count++;
            }
        }
        return count;

    }

    // 1323. Maximum 69 Number
    public int maximum69Number(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '6') {
                chars[i] = '9';
                break;
            }
        }

        return Integer.parseInt(new String(chars));
    }

    // 1281. Subtract the Product and Sum of Digits of an Integer
    public int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;
        do {
            int a = n % 10;
            n /= 10;
            product *= a;
            sum += a;

        } while (n > 0);

        return product - sum;
    }

    // 1295. Find Numbers with Even Number of Digits
    public int findNumbers(int[] nums) {
        int result = 0;
        for (int num : nums) {
            int even = 0;
            do {
                even++;
                num /= 10;
            } while (num > 0);
            if (even % 2 == 0) {
                result++;
            }
        }
        return result;
    }

    // 1313. Decompress Run-Length Encoded List
    public int[] decompressRLElist(int[] nums) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length / 2; i++) {
            gen(nums[2 * i], nums[2 * i + 1], list);
        }

        int[] num = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            num[i] = list.get(i);
        }
        return num;
    }

    public static void gen(int a, int b, ArrayList<Integer> arrayList) {
        for (int i = 0; i < a; i++) {
            arrayList.add(b);
        }
    }

    public int balancedStringSplit(String s) {
        int nums = 0;
        int i = 0;
        for (char c : s.toCharArray()) {
            i = 'L' == c ? i + 1 : i - 1;
            if (i == 0) {
                nums++;
            }
        }
        return nums;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (target == (nums[i] + nums[j])) {
                    int[] n = {i, j};
                    return n;
                }
            }
        }
        return null;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int i = 0;
        int val1 = 0;
        int val2 = 0;

        ListNode listNode = new ListNode(0);
        ListNode cur = listNode;
        while (null != l1 || null != l2 || 1 == i) {
            cur.next = new ListNode(0);
            cur = cur.next;
            val1 = null == l1 ? 0 : l1.val;
            val2 = null == l2 ? 0 : l2.val;
            if ((val1 + val2 + i) > 9) {
                cur.val = (val1 + val2 + i) % 10;
                i = 1;
            } else {
                cur.val = val1 + val2 + i;
                i = 0;
            }
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;

        }

        return listNode.next;
    }

    public static void test() {
        /*
         * ListNode l1=new ListNode(5); l1.next=new ListNode(4); l1.next.next=new
         * ListNode(3);
         *
         * ListNode l2=new ListNode(5); l2.next=new ListNode(6); l2.next.next=new
         * ListNode(4); Solution solution=new Solution();
         *
         * ListNode listNode=solution.addTwoNumbers(l1,l2);
         * System.out.println(listNode.val+""+listNode.next.val+listNode.next.next.val);
         */

        Solution solution = new Solution();
        solution.balancedStringSplit("RLRRLLRLRL");
    }


    //	995. K 连续位的最小翻转次数
    public int minKBitFlips(int[] A, int K) {
        return 0;
    }



}
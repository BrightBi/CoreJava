package chapter3;

import java.util.Arrays;

public class ForArray {

    public static void main(String[] args) {
        int [] num1 = new int [2];
        int [] num2 = {3, 8, 7, 1};
        int [] num3 = new int [] {1, 2};
        // 允许长度为 0 的数组
        int [] num4 = new int [0];
        System.out.println(num4.length);
        for (int i : num1) {
            System.out.println(i);
        }
        num3 = Arrays.copyOf(num2, 5);
        System.out.println(Arrays.toString(num3));
        Arrays.sort(num2);
        System.out.println(Arrays.toString(num2));
        System.out.println(Arrays.binarySearch(num2, 7));
        // Math.random() 返回一个 [0, 1) 之间的一个浮点数
        System.out.println((int)(Math.random() * 100));
        int [][] num5 = new int [2][2];
        System.out.println(Arrays.deepToString(num5));

    }

}

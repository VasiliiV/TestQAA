package ru.tetrasoft.rig.space.admin.tests;


public class test {
    public static void main(String[] args) {
        int[][] array = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }; //immediately declared the contents of a two-dimensional array
        int SmallNumberArray = minArray(array);
        System.out.println(SmallNumberArray);
    }

    public static int minArray(int[][] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] < min) {
                    min = array[i][j];
                }
            }
        }
        return min;
    }
}

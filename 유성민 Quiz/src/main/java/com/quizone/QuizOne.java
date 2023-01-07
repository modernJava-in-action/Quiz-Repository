package com.quizone;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class QuizOne {

  public static void main(String[] args) {
    int[] arr = new int[]{5, 3, 2, 1, 4, 9, 8};

    // 1-1
    int[] sorted = Arrays.stream(arr)
      .sorted()
      .toArray();

    System.out.println("Arrays.toString(sorted) = " + Arrays.toString(sorted));

    // 1-2
    int[] sortedReverse = Arrays.stream(arr)
      .boxed()
      .sorted(Collections.reverseOrder())
      .mapToInt(Integer::intValue)
      .toArray();

    System.out.println("Arrays.toString(sortedReverse) = " + Arrays.toString(sortedReverse));


    int[] arr2 = new int[]{2, 4, 6, 8, 10, 12, 15, 21, 1, 9};

    // 2-1
    Arrays.stream(arr2)
      .filter(a -> a % 2 == 0)
      .forEach(System.out::print);

    // 2-2
    boolean multipleThree = Arrays.stream(arr2)
      .anyMatch(a -> a % 3 == 0);
    System.out.println("multipleThree = " + multipleThree);

    // 2-3
    long count = Arrays.stream(arr2)
      .filter(a -> a % 3 == 0)
      .count();

    System.out.println("count = " + count);

    int sumOfThrees = Arrays.stream(arr2)
      .filter(a -> a % 3 == 0)
      .reduce(0, (a, b) -> a + b);

    System.out.println("sumOfThrees = " + sumOfThrees);

    // 2-4
    int max = Arrays.stream(arr2)
      .reduce(Integer::max)
      .getAsInt();

    System.out.println("max = " + max);

    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(3, 4, 5);

    // 3
    List<Integer[]> collect1 = numbers1.stream()
      .flatMap(a -> numbers2.stream().map(b -> new Integer[]{a, b}))
      .collect(Collectors.toList());

    for (Integer[] integers : collect1) {
      System.out.print(Arrays.toString(integers) + " ");
    }
    System.out.println();

    // 4
    int max2 = numbers1.stream()
      .flatMap(a -> numbers2.stream().map(b -> new Integer[]{a, b}))
      .mapToInt(a -> a[0] * a[1])
      .reduce(Integer::max)
      .orElse(0);

    System.out.println("max2 = " + max2);
  }
}

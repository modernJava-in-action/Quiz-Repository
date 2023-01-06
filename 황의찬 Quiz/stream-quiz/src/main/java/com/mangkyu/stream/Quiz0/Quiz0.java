package com.mangkyu.stream.Quiz0;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Quiz0 {

  public static void main(String[] args) {
    //문제 1.1
    int[] arr = new int[]{5, 3, 2, 1, 4, 9, 8};
    List<Integer> list = Arrays.stream(arr)
        .sorted()
        .boxed()
        .collect(Collectors.toList());
    System.out.println("오름차순 정렬" + list);

    //문제 1.2
    List<Integer> reverseList = Arrays.stream(arr)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());
    System.out.println("내림차순 정렬" + reverseList);

    //문제 2.1
    int[] array = new int[]{2, 4, 6, 8, 10, 12, 15, 21, 1 , 9};
    Arrays.stream(array)
        .filter(i -> i % 2 == 0)
        .boxed()
        .forEach(i -> System.out.print(i + " "));

    System.out.println();

    //문제 2.2
    boolean b = Arrays.stream(array)
        .boxed()
        .anyMatch(i -> i % 3 == 0);
    System.out.println(b);

    //문제 2.3 : 3의 배수의 개수와 합계
    int sum = Arrays.stream(array)
        .filter(i -> i % 3 == 0)
        .sum();
    System.out.println(sum);
    long count = Arrays.stream(array)
        .filter(i -> i % 3 == 0)
        .count();
    System.out.println(count);

    List<Integer> integerList = Arrays.stream(array)
        .boxed()
        .filter(i -> i % 3 == 0)
        .collect(Collectors.toList());
    System.out.println(integerList);
    System.out.println("개수 : " + integerList.size() + " 합계 : " + integerList.stream().mapToInt(Integer::intValue).sum());

    //문제 2.4
    int max = Arrays.stream(array)
        .max().orElse(0);
    System.out.println(max);

    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(3, 4, 5);

    //문제 3
    List<int[]> list1 = numbers1.stream()
        .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
        .collect(Collectors.toList());

    //문제 4
    int maxResult = numbers1.stream()
        .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
        .mapToInt(a -> a[0] * a[1])
        .max().orElse(0);
    System.out.println(maxResult);


  }

}

package com.mangkyu.stream.Quiz2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Quiz2 {

    private static List<String> WORDS = Arrays.asList("TONY", "a", "hULK", "B", "america", "X", "nebula", "Korea");

    // 2.1 List에 저장된 단어들의 접두사가 각각 몇개씩 있는지 Map<String, Integer>으로 변환하여라.
    // ex) ("T", 1), ("a", 2) ...

    public static void main(String[] args) {
        // 1번
        LinkedHashMap<String, Integer> quiz1Map = WORDS.stream()
            .map(w -> w.substring(0, 1)) // "T", "a", "h", "B", "a", "X", "n", "K"
            .collect(Collectors.toMap(prefix -> prefix, prefix -> 1, (oldValue, newValue) -> newValue += oldValue, LinkedHashMap::new));

        for (String key : quiz1Map.keySet()) {
            String value = String.valueOf(quiz1Map.get(key));
            System.out.println(String.format(key + ":" + value));
        }

        // 2번
        String quiz2Str = WORDS.stream()
            .filter(str -> str.length() >= 2)
            .map(String::toUpperCase)
            .map(w -> w.substring(0, 1))
            .collect(Collectors.joining(" "));
        System.out.println(quiz2Str);
    }

    public Map<String, Integer> quiz1() {
        return WORDS.stream()
            .map(w -> w.substring(0, 1)) // "T", "a", "h", "B", "a", "X", "n", "K"
            .collect(Collectors.toMap(prefix -> prefix, prefix -> 1,
                (oldValue, newValue) -> newValue += oldValue));
    }

    // 2.2 List에 저장된 단어들 중에서 단어의 길이가 2 이상인 경우에만, 모든 단어를 대문자로 변환하여 스페이스로 구분한 하나의 문자열로 합한 결과를 반환하여라.
    // ex) ["Hello", "a", "Island", "b"] -> “H I”
    public String quiz2() {

         return WORDS.stream()
            .filter(str -> str.length() >= 2)
            .map(String::toUpperCase)
            .map(w -> w.substring(0, 1))
            .collect(Collectors.joining(" "));
    }

}

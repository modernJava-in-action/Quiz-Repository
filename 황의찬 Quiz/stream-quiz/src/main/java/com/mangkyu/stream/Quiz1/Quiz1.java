package com.mangkyu.stream.Quiz1;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class Quiz1 {

    private static final String TARGET = "좋아";
    private static final int TARGET_LENGTH = TARGET.length();

    // 출력 테스트용 메인 클래스
    public static void main(String[] args) throws IOException {
        Quiz1 quiz1 = new Quiz1();
        List<String[]> csvLines = quiz1.readCsvLines();

        int i = 0;
        for (String[] csvLine : csvLines) {
            for (String s : csvLine) {
                System.out.print(s + "/");
            }
            System.out.println("Array" + i + "END");
            i++;
        }
        Map<String, Integer> quiz1Map = csvLines.stream()
            .map(line -> line[1].replaceAll("\\s", ""))
            .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
            .collect(Collectors.toMap(hobby -> hobby, hobby -> 1,
                (oldValue, newValue) -> newValue += oldValue)); //(T,T -> T)

        for (String key : quiz1Map.keySet()) {
            String value = String.valueOf(quiz1Map.get(key));
            System.out.println(String.format(key + ":" + value));
        }

        //BinaryOperator , (T,T) -> T
        BinaryOperator<Integer> binaryOperator = (oldValue, newValue) -> newValue += oldValue;
        Integer sum = binaryOperator.apply(10, 100);
        System.out.println(sum);

    }

    // 1.1 각 취미를 선호하는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz1() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream()
         .map(line -> line[1].replaceAll("\\s", ""))
            .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
            .collect(Collectors.toMap(hobby -> hobby, hobby -> 1, (oldValue, newValue) -> newValue += oldValue));
        //toMap Parameter : keyMapper, valueMapper, BinaryOperator<U> mergeFunction(T,T -> T)
    }

    // 1.2 각 취미를 선호하는 정씨 성을 갖는 인원이 몇 명인지 계산하여라.
    public Map<String, Integer> quiz2() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream()
            .filter(line -> line[0].startsWith("정"))
            .map(line -> line[1].replaceAll("\\s", ""))
            .flatMap(hobbies -> Arrays.stream(hobbies.split(":")))
            .collect(Collectors.toMap(hobby -> hobby, hobby -> 1,(oldValue, newValue) -> ++newValue));
    }

    // 1.3 소개 내용에 '좋아'가 몇번 등장하는지 계산하여라.
    public int quiz3() throws IOException {
        List<String[]> csvLines = readCsvLines();
        return csvLines.stream()
            .map(line -> countContains(line[2], 0))
            .reduce(0, Integer::sum);
    }
    private int countContains(String src, int fromIndex) {
        int index = src.indexOf(TARGET, fromIndex);
        if (index >= 0) {
            return 1 + countContains(src, index + TARGET_LENGTH);
        }
        return 0;
    }

    private List<String[]> readCsvLines() throws IOException {
        CSVReader csvReader = new CSVReader(new FileReader(getClass().getResource("/user.csv").getFile()));
        csvReader.readNext();
        return csvReader.readAll();
    }

}

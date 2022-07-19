package lambda;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author:wuhao
 * @description:map与flatMap https://www.cnblogs.com/wangjing666/p/9999666.html
 * @date:2019/12/12
 */
@Slf4j
public class MapFlatMap {
    public static void main(String[] args) {
        //
        String[] arrayOfWords = {"GoodBye", "World"};
        Stream<String> words = Arrays.stream(arrayOfWords);
        // map 传递给map方法的lambda为每个单词生成了一个String[](String列表)。因此，map返回的流实际上是Stream<String[]> 类型的
        List<String[]> results = words.map(s -> s.split("")).distinct().collect(toList());

        results.forEach(
                item -> {
                    System.out.println("map遍历后的结果是:" + item);
                });

        // flatMap 使用flatMap方法的效果是，各个数组并不是分别映射一个流，而是映射成流的内容，所有使用map(Array::stream)时生成的单个流被合并起来，即扁平化为一个流
        List<String> results2 = words.flatMap(s -> Stream.of(s.split(""))).distinct().collect(toList());
        results2.forEach(
                item -> {
                    System.out.println("flatMap遍历后的结果是:" + item);
                });
    }
}

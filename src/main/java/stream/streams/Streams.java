package stream.streams;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author:wuhao
 * @description:枚举类
 * @date:18/12/3
 */
public class Streams {
    private enum Status {
        OPEN, CLOSED
    }

    private static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.status = status;
            this.points = points;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s, %d]", status, points);
        }
    }

    public static void main(String[] args) {
        // Calculate total points of all active tasks using sum()
        Collection<Task> tasks = Arrays.asList(
                new Task(Status.OPEN, 5),
                new Task(Status.OPEN, 13),
                new Task(Status.CLOSED, 8));

        //在这个task集合中一共有多少个OPEN状态的点？在Java 8之前，要解决这个问题，则需要使用foreach循环遍历task集合；但是在Java 8中可以利用steams解决：
        // 包括一系列元素的列表，并且支持顺序和并行处理
        int totalPointsOfOpenTasks = tasks.stream().filter(s -> s.getStatus() == Status.OPEN).mapToInt(Task::getPoints).sum();
        System.out.println("Total points: " + totalPointsOfOpenTasks);

        // Calculate total points of all tasks
        double totalPoints = tasks.
                stream().
                parallel().
//                map(Task::getPoints).
        map(task -> task.getPoints())
//                .reduce(0, (x, y) -> x + y)
                .reduce(0, Integer::sum);
        System.out.println("Total points (all tasks): " + totalPoints);

        // Group tasks by their status
        final Map<Status, List<Task>> map = tasks.stream().collect(Collectors.groupingBy(Task::getStatus));
        System.out.println(map);

        // Calculate the weight of each tasks (as percent of total points) 如何计算集合中每个任务的点数在集合中所占的比重
        final Collection<String> result = tasks.stream().mapToInt(Task::getPoints)
                .asLongStream().mapToDouble(points -> points / totalPointsOfOpenTasks)
                .boxed().mapToLong(weight -> (long) (weight * 100)).mapToObj(percentage -> percentage + "%").collect(Collectors.toList());
        System.out.println(result);

        //Steam API不仅可以作用于Java集合，传统的IO操作（从文件或者网络一行一行得读取数据）可以受益于steam处理
        //Stream的方法onClose 返回一个等价的有额外句柄的Stream，当Stream的close（）方法被调用的时候这个句柄会被执行
        String fileName = "";
        final Path path = new File(fileName).toPath();
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.onClose(() -> System.out.println("Done!")).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

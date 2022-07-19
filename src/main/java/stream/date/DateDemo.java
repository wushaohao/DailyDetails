package stream.date;

import java.time.*;

/**
 * @author:wuhao
 * @description:日期操作
 * @date:18/12/3
 */
public class DateDemo {
    public static void main(String[] args) {
        // Get the system clock as UTC offset
        //Clock类使用时区来返回当前的纳秒时间和日期,Clock可以替代System.currentTimeMillis()和TimeZone.getDefault()
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.instant());
        System.out.println(clock.millis());

        // Get the local date and local time
        // LocalDate仅仅包含ISO-8601日历系统中的日期部分；LocalTime则仅仅包含该日历系统中的时间部分。这两个类的对象都可以使用Clock对象构建得到。
        LocalDate date = LocalDate.now();
        LocalDate dateFromClock = LocalDate.now(clock);
        System.out.println("date:" + date);
        System.out.println("dateFromClock:" + dateFromClock);

        // Get the local date/time LocalDateTime类包含了LocalDate和LocalTime的信息，但是不包含ISO-8601日历系统中的时区信息
        LocalDateTime dateTime = LocalDateTime.now();
        LocalDateTime localDateTime = LocalDateTime.now(clock);
        System.out.println("datetime:" + dateTime);
        System.out.println("localDateTime:" + localDateTime);

        // Get the zoned date/time-->如果你需要特定时区的data/time信息，则可以使用ZoneDateTime，它保存有ISO-8601日期系统的日期和时间，而且有时区信息
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        ZonedDateTime zonedDateTimeFromClock = ZonedDateTime.now(clock);
        ZonedDateTime zonedDateTimeFromZone = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        System.out.println("zonedDateTime:" + zonedDateTime);
        System.out.println("zonedDateTimeFromClock:" + zonedDateTimeFromClock);
        System.out.println("zonedDateTimeFromZone:" + zonedDateTimeFromZone);

        // Get duration between two dates-->Duration类，它持有的时间精确到秒和纳秒。这使得我们可以很容易得计算两个日期之间的不同
        LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        LocalDateTime to = LocalDateTime.of(2015, Month.APRIL, 16, 23, 59, 59);
        Duration duration = Duration.between(from, to);
        System.out.println("Duration in days:" + duration.toDays());
        System.out.println("Duration in hours:" + duration.toHours());

    }
}

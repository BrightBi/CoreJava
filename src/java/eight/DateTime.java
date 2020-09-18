package java.eight;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

/**
 * java.util.Date 和 SimpleDateFormatter 都不是线程安全的，
 * 而 LocalDate 和 LocalTime 和最基本的 String 一样，是不变类型，不但线程安全，而且不能修改。
 * LocalDate 无法包含时间，LocalTime 无法包含日期。当然，LocalDateTime 才能同时包含日期和时间。
 */
public class DateTime {

	public static void main(String[] args) {
		// 取当前日期：
		LocalDate today = LocalDate.now(); // -> 2014-12-24
		// 根据年月日取日期，12月就是12：
		LocalDate crischristmas = LocalDate.of(2014, 12, 25); // -> 2014-12-25
		// 根据字符串取：严格按照ISO yyyy-MM-dd验证，02写成2都不行，当然也有一个重载方法允许自己定义格式
		LocalDate endOfFeb = LocalDate.parse("2014-02-28");
		// LocalDate.parse("2014-02-29"); 无效日期无法通过：DateTimeParseException: Invalid date
		System.out.println(today + "|" + crischristmas + "|" + endOfFeb);

		// 取本月第1天：
		LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2014-12-01
		// 取本月第2天：
		LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2014-12-02
		// 取本月最后一天，再也不用计算是28，29，30还是31：
		LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth()); // 2014-12-31
		// 取下一天：
		LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1); // 变成了2015-01-01
		// 取2015年1月第一个周一，这个计算用 Calendar 要死掉很多脑细胞：
		LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); // 2015-01-05
		System.out.println(firstDayOfThisMonth + "|" + secondDayOfThisMonth + "|" + lastDayOfThisMonth + "|" + firstDayOf2015 + "|" + firstMondayOf2015);
		
		// LocalTime 包含毫秒
		LocalTime nowWithMS = LocalTime.now(); // 11:09:09.240
		// LocalTime 不包含毫秒
		LocalTime nowWithoutMS = LocalTime.now().withNano(0); // 11:09:09
		// 构造时间也很简单，时间也是按照ISO格式识别，但可以识别以下3种格式：12:00，12:01:02，12:01:02.345
		LocalTime zero = LocalTime.of(0, 0, 0); // 00:00:00
		LocalTime mid = LocalTime.parse("17:00:00"); // 17:00:00
		System.out.println(nowWithMS + "|" + nowWithoutMS + "|" + zero + "|" + mid);
	}
}

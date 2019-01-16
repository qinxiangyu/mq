package com.rabbit;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rabbit.beans.User;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MqApplicationTests {

	@Test
	public void contextLoads() {
		List<User> list = new ArrayList<>();
		list.add(new User("张三",23,"女"));
		list.add(new User("李四",20,"女"));
		list.add(new User("王五",25,"男"));
		list.add(new User("赵六",19,"男"));
		list.add(new User("田七",17,"女"));

//		System.out.println("result"+ JSONArray.toJSONString(beforeJava7(list)));
//		System.out.println("java8 result"+JSONArray.toJSONString(java8(list)));
//		System.out.println(JSONObject.toJSONString(filterOfStream(list)));
//		System.out.println(JSONObject.toJSONString(mapOfStream(list)));

		System.out.println(JSONObject.toJSONString(findAnyOfStream(list)));

	}

	private List<String> beforeJava7(List<User> list){
		List<User> tempList = new ArrayList<>();
		//年龄小于20的用户
		for(User user : list){
			if(user.getAge() <= 20){
				tempList.add(user);
			}
		}

		//排序
		Collections.sort(tempList, new Comparator<User>() {
			@Override
			public int compare(User o1, User o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		//取名字
		List<String> userNames = new ArrayList<>();
		for(User user:tempList){
			userNames.add(user.getName());
		}
		return userNames;

	}

	private List<String> java8(List<User> list){
		List<String> userNames = list.stream()
				.filter(user -> user.getAge() <= 20)
				.sorted(Comparator.comparing(User::getName))
				.map(User::getName)
				.collect(Collectors.toList());
		return userNames;
	}

	@Test
	public void testStream(){
		List<String> lists = Arrays.asList("java8","lambda","stream");
		Stream<String> stringStream = lists.stream();
		Consumer<String> consumer = (x) -> System.out.println(x);
		stringStream.forEach(consumer);
		//stream has already been operated upon or closed
		stringStream.forEach(consumer);
	}

	/**
	 * 把用户集合中性别是男的用户选择出来
	 * user1, users2, users3是三种不同的写法，结果都一样
	 * @param users 原始用户集合
	 * @return 性别是男性的用户
	 */
	public List<User> filterOfStream(List<User> users){
		List<User> users1 = users.stream().filter(User::isMen).collect(Collectors.toList());
		List<User> users2 = users.stream().filter(u -> u.getGender().equals("男")).collect(Collectors.toList());
		Predicate<User> predicate = (u) -> u.getGender().equals("男");
		List<User> users3 = users.stream().filter(predicate).collect(Collectors.toList());
		return users1;
		//return users2;
		//return users3;
	}

	/**
	 * distinct(): 去除流中重复的元素
	 * 打印集合中的偶数，并且不能重复
	 */
	@Test
	public void distinctOfStream(){
		List<Integer> lists = Arrays.asList(1, 2, 3, 4, 2, 6, 4, 7, 8, 7);
		//2468
		lists.stream().filter(x -> x % 2 == 0).distinct().forEach(System.out::print);

		System.out.println();
		// 另一种写法
		Consumer<Integer> consumer = (x) -> System.out.print(x);
		//2468
		lists.stream().filter(x -> x % 2 == 0).distinct().forEach(consumer);
	}

	/**
	 * limit()：返回流中指定长度的流
	 */
	@Test
	public void limitOfStream(){
		List<Integer> lists = Arrays.asList(1, 2, 3, 4, 2, 6, 4, 7, 8, 7);
		//获取　lists 中前三个元素, 有序
		// 123
		lists.stream().limit(3).forEach(System.out::print);
	}

	/**
	 * skip(n):跳过前n个元素，返回n后面的元素
	 */
	@Test
	public void skipOfStream(){
		List<Integer> lists = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		//跳过前5个元素，从第6个元素开始打印
		//678910
		lists.stream().skip(5).forEach(System.out::print);
	}

	/**
	 * @param users 用户集合
	 * @return 用户名字集合
	 */
	public List<String> mapOfStream(List<User> users){
		List<String> usersNames = users.stream().map(User::getName).collect(Collectors.toList());
		// 另一种写法
		Function<User, String> function = (user) -> user.getName();
		List<String> usersNames2 = users.stream().map(function).collect(Collectors.toList());
		//return usersNames2;

		// 获取每个用户的名字的长度
		// 写法一
		List<Integer> userNameLength = users.stream()
				.map(User::getName)// 获取用户名
				.map(String::length) // 获取每个用户名的长度
				.collect(Collectors.toList()); // 返回一个集合
		// 写法二
		Function<User, String> name = user -> user.getName();
		Function<String, Integer> len = s -> s.length();
		List<Integer> userNameLength2 = users.stream().map(name).map(len).collect(Collectors.toList());
		// 写法三
		List<Integer> userNameLength3 = users.stream().map(s -> s.getName()).map(s -> s.length()).collect(Collectors.toList());
		return usersNames;
	}

	/**
	 * anyMatch(): 流中是否有一个元素能匹配给定的谓词，只要有一个能够匹配，就返回 true
	 */
	@Test
	public void anyMatchOfStream(){
		List<Integer> lists = Arrays.asList(1, 2, 4, 5);
		Stream<Integer> stream = lists.stream();
		if (stream.anyMatch(i -> i == 3)){
			System.out.println("包含 3");
		}else{
			System.out.println("不包含 3");
		}
	}

	/**
	 * allMatch():检查流中的元素是否都能匹配给定的谓词
	 */
	@Test
	public void allMatch(){
		List<Integer> lists = Arrays.asList(3, 3);
		if (lists.stream().allMatch(i -> i == 3)){
			System.out.println("完全匹配");
		}else{
			System.out.println("不完全匹配");
		}
	}

	public Optional<User> findAnyOfStream(List<User> users){
		Optional<User> user =
				users.stream().filter(u -> u.getGender().equals("男")).findAny();
		return user;
	}

	/**
	 * reduce()
	 */
	@Test
	public void reduceOfStream(){
		List<Integer> lists = Arrays.asList(1, 2, 3, 3, 4, 5);
		// 元素的总和
		int sum = lists.stream().reduce(0, (x, y) -> x + y);
		Optional<Integer> sum2 = lists.stream().reduce(Integer::sum);
		System.out.println("sum = " + sum);
		System.out.println("sum2 = " + sum2.get());
		// 求最大值
		int max = lists.stream().reduce(0, (x, y) -> x > y ? x : y);
		Optional<Integer> max2 = lists.stream().reduce(Integer::max);
		System.out.println("max = " + max);
		System.out.println("max2 = " + max2.get());
		// 最小值
		int min = lists.stream().reduce(1, (x, y) -> x > y ? y : x);
		Optional<Integer> min2 = lists.stream().reduce(Integer::min);
		System.out.println("min = " + min);
		System.out.println("min2 = " + min2.get());
	}

}

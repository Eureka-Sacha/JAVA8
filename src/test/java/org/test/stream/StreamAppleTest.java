package org.test.stream;

import org.eureka.stream.Apple;
import org.eureka.stream.AppleListImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The type Stream apple test.
 * 测试
 */
public class StreamAppleTest {
    /**
     * 测试JAVA8新增的default关键词
     * default作用:可在接口中添加默认实现,引用接口后可无需实现即可调用
     */
    @Test
	public void testDefault(){
		AppleListImpl<Apple> apples=new AppleListImpl<>();
		int x=apples.defaultTest();
		System.out.println(x);
	}

    /**
     * Test stream api.
     */
    @Test
	public void testStreamAPI(){
		/*
		 *随便生成一点Apple对象  PS:可惜random是伪随机
		 */
		List<Apple> list=new ArrayList<>();
		for(int i=0;i<1000;i++){
			Random random=new Random();
			String color=randomChoice(new String[]{"green","red"});
			int weight=random.nextInt(999);
			Apple apple=new Apple();
			apple.setColor(color);
			apple.setWeight(weight);
			list.add(apple);
		}
        //使用lambda表达式创建filter并调用streamAPI对苹果进行筛选
		List<Apple> redAppls=list.stream().filter((Apple a)->a.getColor().equals("red")).collect(Collectors.toList());
        //
		List<Apple> weightAppls=list.stream().filter((Apple a)->a.getWeight()>500 && a.getColor().equals("red")).collect(Collectors.toList());
//		for (Apple apple : redAppls) {
//			System.out.println(apple.getColor());
//		}
//		System.out.println(redAppls.size());
		for(Apple apple:weightAppls){
			System.out.print(apple.getColor());
			System.out.print(" ");
			System.out.print(apple.getWeight());
			System.out.println("");
		}
		System.out.println(weightAppls.size());
	}

    /**
     * Test lambda.
     */
    @Test
	public void testLambda(){
		Predicate<Apple> apple=(Apple a)->a.getColor().equals("red");
	}

	private static String randomChoice(String[]strs){
		Random random=new Random();
		int x=random.nextInt(strs.length);
		return strs[x];
	}

}

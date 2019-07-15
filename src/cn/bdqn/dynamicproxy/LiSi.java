package cn.bdqn.dynamicproxy;

//创建李四类，实现吃东西的能力 
public class LiSi implements Eat {

	@Override
	public void eat(String food) {
		System.out.println("李四正在吃" + food);   //业务代码
	}
}
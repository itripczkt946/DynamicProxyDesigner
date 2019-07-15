package cn.bdqn.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//实现了InvocationHandler接口的类叫动态代理类。 LiSi就是被代理，LiSi被ProxyInvocationHandle代理
public class ProxyInvocationHandle implements InvocationHandler {

	
	private Eat eat;  //实现多态

	public ProxyInvocationHandle(Eat eat) { //以接口对象作为方法的形参来实现多态
		this.eat = eat;
	}

	/**
	 * LiSi 被代理对象
	 * proxy 生成的被代理类对象 (这里可以讲一下房东-中介-租客的例子)  LiSi对象
	 * method 被代理类的方法，即这里的eat()。
	 * args 被代理类方法的入参。 即eat()方法中的参数。
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		before();   //看成横切逻辑(增强处理)
		
		//执行了被代理对象中的eat方法，输出了李四正在吃东西
		Object object = method.invoke(eat, args);  //执行LiSi类中的eat方法。
		//new LiSi().eat();
		
		after();   //看成横切逻辑
		return object;  //object为返回值
	}
	
	
	public void before() {  //在....之前
		System.out.println("吃饭前洗手！");
	}
	
	public void after() {  //在....之前
		System.out.println("吃饭后撩妹！");
	}
	
	
	

	public static void main(String[] args) {
		Eat eat = (Eat)Proxy.newProxyInstance(ProxyInvocationHandle.class.getClassLoader(), 
				new Class[]{Eat.class}, 
				new ProxyInvocationHandle(new LiSi()));
		
		eat.eat("一坨");

	}
}

代理模式实现:
静态代理:代理和被代理对象在代理之前是确定的 他们都实现相同的接口或者继承相同的抽象类

实现类型:
  聚合 继承
  聚合优于继承



代理模式:
 所谓动态代理是这样一种class:
     a.它是在运行时生成的class
     b.该class需要实现一组interface
     c.使用动态代理类时，必须实现InvocationHandler接口
 实现步骤:
    1.创建一个实现接口InvocationHandler的类，它必须实现invoke方法(在里面实现业务逻辑)
    2.创建被代理的类以及接口
    3.调用Proxy的静态方法，创建一个代理类
      newProxyInstance(ClassLoader loader,Class[] interfaces,InvocationHandler h)
    4.通过代理调用方法

JDK动态代理:
    java动态代理位于java.lang.reflect包下，一般主要涉及一下两个类：
      1.Interface InvocationHandler:该接口中仅定义了一个方法
         public object invoke(Object obj,Method method,Object[] args)
         在实际使用时，第一个参数obj一般指被代理类，method是被代理的方法，args为该方法的参数数组，这个抽象方法在代理类中实现

      2.Proxy：该类即为动态代理类
         /**
         * loader:被代理类的类加载器
         * interfaces:被代理类实现的接口
         * h:代理处理类
         */
         static Object newProxyInstance(ClassLoader loader,Class[] interfaces,InvocationHandler h):返回代理类的一个实例，返回后的代理类可以当作被代理类使用(可
      使用被代理类的在接口中声明过的方法)

    动态代理实现思路:
      1.声明一段源码(动态产生代理)
      2.编译源码(JDK Compiler API),产生新的类(代理类)
      3.将这个类load到内存中，产生一个新的对象
      4.return 代理对象


Cglib动态代理:
    代理类实现MethodInterceptor,拦截所有目标类方法的调用
    Object intercept(Object arg0,Method arg1,Object[] arg2,MethodProxy arg3)
     arg0:目标类的实例
     arg1:目标方法的反射对象
     arg2:方法参数
     arg3:代理类的实例


JDK动态代理与Cglib动态代理区别:
 JDK动态代理:
   1.只能代理实现了接口的类
   2.没有实现接口的类不能实现JDK的动态代理

 Cglib动态代理:
 1.针对类来实现代理的
 2.对指定目标类产生一个子类，通过方法拦截技术拦截所有父类方法调用
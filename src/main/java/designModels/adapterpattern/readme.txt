适配器模式:
   适配器模式是将一个类的接口，转换成客户期望的另一个接口。使得原本由于接口不兼容而不能一起工作的那些类可以在一起工作


   构成:
   Client ------>   Target(目标接口)
                  ( Request)
                       👆
                    Adapter(适配器)
              (adapter=new Adapter()) ----------->Adapter(被适配类)
                 (Request():void)           (SpecificRequest():void)

   把"被适配者"作为一个对象组合到适配器类中,以修改目标接口包装被适配者
   1.组合
   组合方式适配器:采用组合方式的适配器称为  对象 适配器。
   特点:把"被适配者"作为一个对象组合到适配器类中，以修改目标接口包装被适配者

   2.继承
   采用继承方式的称为 类 适配器
   特点:通过多重继承不兼容接口，实现对目标接口的匹配，单一的为某个类而实现适配

   类适配器模式通过多重继承不兼容接口，实现对目标接口的匹配，单一的为某个类而实现适配的这样一种模式
   Client ------>   Target(目标接口)        Adapter(被适配类)
                   ( Request：void)      (SpecificRequest():void)
                                 👈        👉extends
                                 Adapter(适配器)    －－－XXXXX(不能适配其他,java不支持多继承)－－－>子类
                                 (Request():void)


   作用:
   1.透明
      通过适配器，客户端可以调用同一接口，因而对客户端来说是透明的。这样做更简单、更直接、更紧凑
   2.重用
      复用了现存的类，解决了现存类和复用环境要求不一致问题
   3.低耦合
      将目标类和适配者类解耦，通过引用一个适配器类重用现有的所有适配者类，而无需修改原有代码（遵循开闭原则，对扩展开放，对修改关闭）
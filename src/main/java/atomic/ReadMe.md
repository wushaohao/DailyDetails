1、AtomicInteger/AtomicLong/AtomicBoolean/AtomicReference是关于对变量的原子更新，

2、AtomicIntegerArray/AtomicLongArray/AtomicReferenceArray是关于对数组的原子更新

3、AtomicIntegerFieldUpdater<T>/AtomicLongFieldUpdater<T>/AtomicReferenceFieldUpdater<T,V>是基于反射的原子更新字段的值。
    通过调用AtomicReferenceFieldUpdater的静态方法newUpdater就能创建它的实例，该方法要接收三个参数： 
        包含该字段的对象的类 
        将被更新的对象的类 
        将被更新的字段的名称 
    AtomicIntegerFieldUpdater 的使用稍微有一些限制和约束，约束如下：
    （1）字段必须是volatile类型的，在线程之间共享变量时保证立即可见.eg:volatile int value = 3
    （2）字段的描述类型（修饰符public/protected/default/private）是与调用者与操作对象字段的关系一致。也就是说调用者能够直接操作对象字段，那么就可以反射进行原子操作。但是对于父类的字段，子类是不能直接操作的，尽管子类可以访问父类的字段。
    （3）只能是实例变量，不能是类变量，也就是说不能加static关键字。
    （4）只能是可修改变量，不能使final变量，因为final的语义就是不可修改。实际上final的语义和volatile是有冲突的，这两个关键字不能同时存在。
    （5）对于AtomicIntegerFieldUpdater和AtomicLongFieldUpdater只能修改int/long类型的字段，不能修改其包装类型（Integer/Long）。如果要修改包装类型就需要使用AtomicReferenceFieldUpdater。

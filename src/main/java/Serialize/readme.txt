对象序列化，反序列化
1.对象序列化，就是将Object转换成byte序列，反之叫对象反序列化
2.序列化流(ObectOutputStream),是(字节)过滤流--------->writeObject
  反序列化流(ObjectInputStream)------->readObject
3.序列化接口(Serialize)
  对象必须实现序列化接口，才能进行序列化，否则将抛出异常
  这个接口，没有任何方法，只是一个标准
4.transient关键字修饰的元素不会进行jvm默认的序列化,但是,也可以
      /**
       * 自身完成序列化操作
       * @param s
       * @throws IOException
       */
      private void writeObject(ObjectOutputStream s) throws IOException{
          //序列化jvm能够默认序列化元素进行徐立户
          s.defaultWriteObject();
          // 自己完成age序列化
          s.writeObject(age);
      }

      /**
       * 资深完成反序列化操作
       * @param s
       * @throws IOException
       * @throws ClassNotFoundException
       */
      private void readObject(ObjectInputStream s) throws IOException,ClassNotFoundException{
          // 把jvm虚拟机默认反序列化
          s.defaultReadObject();
          // 自己完成age的反序列化工作
          this.age = (int) s.readObject();
      }

5.一个类实现了序列化接口，那么其子类都可以进行序列化
  对子类对象进行反序列化操作时，如果其父类没有实现序列化接口，那么其父类的构造函数会被调用
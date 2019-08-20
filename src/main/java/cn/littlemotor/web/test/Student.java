package cn.littlemotor.web.test;

class Student implements Cloneable
{
  String name;
  int age;
  Student(String name,int age)
  {
    this.name=name;
    this.age=age;
  }
  public Object createClone()
  {
    Object o=null;
    try {
      o=(Student)clone();//Object 中的clone()识别出你要复制的是哪一个对象。
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }
    return o;
  }
}


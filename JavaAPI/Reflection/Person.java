package Reflection;

public class Person {

    private int age = 24;
    private String name = "Xing";
    public Person(){}
    public Person(String name){
        this.name = name;
    }
    public Person(int age){
        this.age = age;
    }
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
    public void set(String name){
        this.name = name;
    }
    public void set(int age){
        this.age = age;
    }
    public void set(String name, int age){
        this.age = age;
        this.name = name;
    }
    public String toString(){
        return "[age:"+age+","+"name:"+name+"]";
    }
}

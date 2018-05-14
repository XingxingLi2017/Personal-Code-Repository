package Reflection;

import java.lang.reflect.*;

public class demo1 {

    public static void main(String[] args){
        String className = "Reflection.Person";
        try{
            /*
            * generate instance from class object
            * */
            // get class file
            Class clazz = Class.forName(className);

            // new object based on the class file
            // can't add parameters in this way
            Person p0 = (Person)clazz.newInstance();
            System.out.println("p0="+p0);

            // get specified constructor and new object with parameters
            // primitive data types still have class files
            Constructor con1 = clazz.getConstructor(int.class);
            Person p2 = (Person)con1.newInstance(22);
            System.out.println("p2="+p2);

            // get all the constructors
            Constructor[] constructors = clazz.getConstructors();

            // traverse all the constructors
            for(int i = 0 ; i < constructors.length ; i++) {
                Constructor cons = constructors[i];
                System.out.println(cons);
                // get parameter types
                Class[] paramTypes = cons.getParameterTypes();
                Person p = null;
                if(paramTypes.length == 0){
                    p = (Person)cons.newInstance();
                    System.out.println(p);
                }
                else if(paramTypes.length == 1){
                    if(paramTypes[0].getName() == "int"){
                        p = (Person)cons.newInstance(34);
                    }
                    else if(paramTypes[0] == String.class){
                        p = (Person)cons.newInstance("hello");
                    }

                }else{
//                    System.out.println(paramTypes[0]+"||"+paramTypes[1]);
                    p = (Person)cons.newInstance("hello", 24);
                }
                System.out.println(p);
            }

            /*
            * modify values of fields
            * */
            // get specified private member
            Field f = clazz.getDeclaredField("name");
            System.out.println("Person.name = "+f);
            // change private field to accessible
            f.setAccessible(true);
            // set the field value for an object
            Person p3 = (Person)clazz.newInstance();
            f.set(p3, new String("field access"));
            System.out.println("p3 = "+ p3);


            /*
            * get method and invoke it
            * */
            Method[] methods = clazz.getMethods();
            System.out.println("==========get methods=============");
            for(Method temp: methods){
                System.out.println(temp);
            }
            System.out.println("==========use method=============");
            // get specified method
            Method m = clazz.getMethod("set",String.class, int.class);
            // invoke the method for specific instance
            Person p4 = new Person();
            m.invoke(p4, "li lei", 45);
            System.out.println("p4 = "+p4);




        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

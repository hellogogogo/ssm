package lytest1911;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class Test0504 {


    /**
     * lambda表达式
     */
    @Test
    public void testLamdba(){
        //匿名内部类，为了省去实现类而实现接口的方法
//        Runable rbe = new Runable() {
//            public void run() {
//                System.out.println("我在跑步！！！");
//            }
//        };
//        rbe.run();
        new Runable() {
            public void run() {
                System.out.println("我在跑步！！！");
            }
        }.run();

        //lambda写法
        Runable rbe = () ->{
            System.out.println("我在跑步！！！");
        };

        //参数类型可省略
        AddInterface addIf = (a,b)->{
            return a +b ;
        };
        System.out.println(addIf.add(1,2));
        //一个参数时，()省略 ,一条语句，｛｝省略
        AddInterface2 addIf2 = a -> a;
        log.info(String.valueOf(addIf2.add(666)));

        //方法引用 类名::方法名 写法  类似方法重写
        Test0504 test = new Test0504();
        AddInterface aaa = test::add;
        System.out.println(aaa.add(10,1));
        AddInterface bbb = test::add;
        System.out.println(bbb.add(10,2));

        //构造器引用 类名:new
//        DogInterface dog = ()-> new Dog();
        DogInterface dog = Dog::new;
        System.out.println(dog.getDog());
        //有参
        DogInterface2 dog2 = Dog::new;
        System.out.println(dog2.getDog("花花",1));

        System.out.println("*********************************");
        //排序
        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog("花花4",4));
        dogList.add(new Dog("花花3",3));
        dogList.add(new Dog("花花2",2));
        dogList.add(new Dog("花花1",1));
        dogList.sort((o1, o2) -> o1.getAge() - o2.getAge());
//        System.out.println(dogList);
        dogList.forEach(System.out::println);

        System.out.println("*********************************");
        List<Dog> dogList2 = dogList.stream().filter(item -> item.getAge() >2 && item.getAge() < 4).collect(Collectors.toList());
        dogList2.forEach(System.out::println);
    }

    interface Runable{
        public void run();
//        public int add(int a,int b);
    }

    interface AddInterface{
        public int add(int a,int b);
    }

    interface AddInterface2{
        public int add(int a);
    }

    interface DogInterface{
        Dog getDog();
    }
    interface DogInterface2{
        Dog getDog(String name, int age);
    }

    public int add(int a,int b){
        return a-b;
    }






















    public static void main(String[] args) {
        new Prin() {
            public void print1() {//实现的方法
                System.out.println("print1");//重写
            }
            public void print2() {
                System.out.println("print2");
            }

            public void printSelf() {
                System.out.println("此匿名内部类自己声明的方法");
            }
            {
                print1();
                print2();
                printSelf();
            }};
    }

    interface Prin {
        void print1();
        void print2();
    }
}

package cn.cychust;

/**
 * @program: JavaCourseDesign
 * @description:
 * @author: Yichao Chen
 * @create: 2019-04-25 16:09
 **/

public class Main {
    public static void main(String[] argvs) {
        Queue<Integer> integerQueue = new Queue<>();
        integerQueue.add(1);
        integerQueue.add(2);
        integerQueue.add(3);
        System.out.println(integerQueue.peek());
        System.out.println(integerQueue.poll());
        integerQueue.offer(2);
        integerQueue.offer(3);
        System.out.println(integerQueue.poll());
        System.out.println(integerQueue.poll());
    }
}

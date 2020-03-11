package jni;

/**
 * Created by wangzhaosheng on 2019-11-10
 * Description
 */
public class JniTest {

    static {
        System.loadLibrary("jni-test");
    }

    public static void test(){

        JniTest jniTest = new JniTest();

        String a= "abc";
        String set = jniTest.set(a);
        System.out.println("======a"+a);
        System.out.println("======set"+set);


    }

    public static void main(String args []){

        JniTest jniTest = new JniTest();

//        System.out.println(jniTest.get());

        String a= "abc";
        jniTest.set(a);
        System.out.println("======"+a);

    }


    public native String get();

    public native String set(String str);
}

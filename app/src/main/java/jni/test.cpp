//
// Created by 王兆胜 on 2019-11-10.
//
#include "jni_JniTest.h"
#include <stdio.h>
#include <android/log.h>

/*
 * Class:     com_example_myapplication_jni_JniTest
 * Method:    get
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jni_JniTest_get
  (JNIEnv *env, jobject thiz){//todo 参数名自己加的

    printf("invoke get in c++");
    return env->NewStringUTF("hello from jni");
  }

/*
 * Class:     com_example_myapplication_jni_JniTest
 * Method:    set
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT jstring JNICALL Java_jni_JniTest_set
  (JNIEnv *env , jobject thiz, jstring string){
  printf("invoke set in c++");
  char* str=(char*)env->GetStringUTFChars(string,NULL);//todo jni字符串转换成 c中的字符串. 这里经过测试是重新开辟了内存创建的c的字符串.不是在原内存.跟int数组不一样
  str[0]='Z';
   printf("%s\n",str);
    __android_log_print(ANDROID_LOG_ERROR,"System.out","我是你爷爷");//todo 打印日志
    jclass jclass1 =env->FindClass("jni/JniTest");// todo 必须用斜线,不能用逗号
//       env->ReleaseStringUTFChars(string,str);
    jbyteArray jbyteArray1;
    return env->NewStringUTF(str);


  }

//
//  /**
//   *
//   * 测试代码   jni数组转换成 c中的指针
//   * @param env
//   * @param thiz
//   * @param string
//   */
//JNIEXPORT void JNICALL Java_com_example_myapplication_jni_JniTest_test
//        (JNIEnv *env , jobject thiz, jstring string){
//    jintArray jintArray1 ;
//    int * arr=env->GetIntArrayElements(jintArray1, false);//todo 转换arry成为c的数组
//    int len=env->GetArrayLength(jintArray1);//todo 获取数组长度
//
//    //todo 数组转换成c的指针了,操作数组直接操作指针就可以,如果需要返回值的话直接返回原数组即可.因为指针直接操作的就是数组
//
//}


///**
// *
// * 测试代码
// * @param env
// * @param thiz
// * @param string
// */
//JNIEXPORT void JNICALL Java_com_example_myapplication_jni_JniTest_test
//        (JNIEnv *env , jobject thiz, jstring string){
//    jintArray jintArray1 ;
//    int * arr=env->GetIntArrayElements(jintArray1, false);//todo 转换arry成为c的数组
//    int len=env->GetArrayLength(jintArray1);//todo 获取数组长度
//
//    //todo 数组转换成c的指针了,操作数组直接操作指针就可以,如果需要返回值的话直接返回原数组即可.因为指针直接操作的就是数组
//
//}
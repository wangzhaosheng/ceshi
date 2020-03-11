LOCAL_PATH := $(call my-dir)
LOCAL_APPJNI_SRC_FILES := test.cpp


include $(CLEAR_VARS)
LOCAL_MODULE := jni-test #这个表示编出来的so的名字
#LOCAL_C_INCLUDES += $(LOCAL_PATH)/inc
LOCAL_SRC_FILES += $(LOCAL_APPJNI_SRC_FILES)
#link log library
#相当于  load libs liblog.so   库名字前面加-l
LOCAL_LDLIBS += -llog
#LOCAL_CFLAGS:= -Os -fvisibility=hidden -Wall -Wno-unused-function
include $(BUILD_SHARED_LIBRARY)
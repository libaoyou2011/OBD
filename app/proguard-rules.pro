# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#-dontwarn com.tencent.bugly.**
#-keep public class com.tencent.bugly.**{*;}



################ 如果有引用v4或者v7包，需添加 ###############
-keep public class * extends android.support.**

################ glide ###############
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class com.bumptech.glide.** { *; }
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}

# matisse 图片选择
#忽略某个包的警告
#use Glide as your image engine, ignor picasso
-dontwarn com.squareup.picasso.**
#-dontwarn com.squareup.okhttp.**

# Arouter跳转
 -keep public class com.alibaba.android.arouter.routes.**{*;}

################okhttp###############
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn com.squareup.okhttp.**

################ retrofit ###############
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

################Rxjava and RxAndroid###############
-dontwarn org.mockito.**
-dontwarn org.junit.**
-dontwarn org.robolectric.**

-keep class rx.** { *; }
-keep interface rx.** { *; }

-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-dontwarn okio.**
-keep interface com.squareup.okhttp.** { *; }
-dontwarn com.squareup.okhttp.**

-dontwarn rx.**
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}

-keep class sun.misc.Unsafe { *; }

-dontwarn java.lang.invoke.*

-keep class rx.schedulers.Schedulers {
    public static <methods>;
}
-keep class rx.schedulers.ImmediateScheduler {
    public <methods>;
}
-keep class rx.schedulers.TestScheduler {
    public <methods>;
}
-keep class rx.schedulers.Schedulers {
    public static ** test();
}
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    long producerNode;
    long consumerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

-dontwarn rx.internal.util.unsafe.**

################EventBus###############
-keepattributes *Annotation*
-keepclassmembers class ** {
@org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
<init>(java.lang.Throwable);
}

################ RxBus ###############
-keep class com.hwangjr.rxbus.** {*;}
-keep interface com.hwangjr.rxbus.** {*;}


################ rxpermissions ###############
-keep public class com.tbruyelle.rxpermissions2.**{*;}


################ RoundedImageView: 圆头像 ###############
-keep public class com.makeramen.roundedimageview.**{*;}

################ 高德地图相关 ###############
#3D 地图 V5.0.0之后：
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.**{*;}
-keep   class com.amap.api.trace.**{*;}

#定位
-keep class com.amap.api.location.**{*;}
-keep class com.amap.api.fence.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}

#搜索
-keep   class com.amap.api.services.**{*;}

#2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}

#导航
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}

################ 流式布局 ###############
-keep class com.nex3z.flowlayout..**{*;}

################ 下拉刷新 ###############
-keep class com.scwang.refreshlayout.activity.practice.BannerPracticeActivity$Movie {*;}

-keep class net.lucode.hackware..**{*;}

################ 日期选择 ###############
-keep class com.bigkoo.pickerview..**{*;}

##声明第三方jar包,不用管第三方jar包中的.so文件(如果有)
#-libraryjars libs/AudioEngineSDK.jar
#-libraryjars libs/HCNetSDK.jar
#-libraryjars libs/jna.jar
#-libraryjars libs/PlayerSDK.jar
#
################# 海康球机预览 ###############
#-keep class com.hikvision.audio.** {*;}
#-keep class com.hikvision.netsdk.** {*;}
#-keep class com.sun.jna.** {*;}
#-keep class com.hikvision.audio.** {*;}
#-keep class org.MediaPlayer.PlayM4.** {*;}


################ 图表 chart ###############
-keep class com.github.mikephil.charting.** { *; }


#-------------------------------------------1 定制化区域----------------------------------------------
#---------------------------------1.1实体类---------------------------------
#-keep class Bean的全路径(com.xxx.xxx.bean).** { *; }

################ gson ###############
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# Application classes that will be serialized/deserialized over Gson
-keep class com.baolong.obd.common.network.**{*;}
-keep class com.baolong.obd.login.model.** { *; }
-keep class com.baolong.obd.monitor.data.entity.**{*;}
-keep class com.baolong.obd.blackcar.data.entity.**{*;}
-keep class com.baolong.obd.execution.data.entity.**{*;}
-keep class com.baolong.obd.querycar.data.entity.**{*;}
-keep class com.baolong.obd.analysis.data.entity.**{*;}
-keep class com.baolong.obd.my.data.entity.**{*;}
#-------------------------------------------------------------------------


#---------------------------------1.2自定义控件-----------------------------

#-------------------------------------------------------------------------


#---------------------------------1.3第三方包-------------------------------

#-------------------------------------------------------------------------


#---------------------------------1.4与js互相调用的类------------------------

#-------------------------------------------------------------------------


#---------------------------------1.5反射相关的类和方法-----------------------
-dontwarn javax.inject.**
#--------------------------------------------------------------------------

#---------------------------------------------------------------------------------------------------



#-------------------------------------------2基本不用动区域--------------------------------------------
#---------------------------------2.1基本指令区----------------------------------
#指定压缩级别
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
#不跳过非公共的库的类成员
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-printmapping proguardMapping.txt
#混淆时采用的算法
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-keepattributes *Annotation*,InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
#----------------------------------------------------------------------------

#---------------------------------2.2默认保留区---------------------------------
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** { *; }
-keep interface android.support.** { *; }
-dontwarn android.support.**

-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
-keep class **.R$* {
 *;
}
-keepclassmembers class * {
    void *(**On*Event);
}
-keepclassmembers enum * {  # 使用enum类型时需要注意避免以下两个方法混淆，因为enum类的特殊性，以下两个方法会被反射调用，
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}
#----------------------------------------------------------------------------
#---------------------------------------------------------------------------------------------------



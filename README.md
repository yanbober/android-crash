# android-crash
一个 Android 平台支持捕获 Native(C/C++) 层和 Java 层崩溃异常的迷你库。

# 业余努力开发中......
后续计划支持 js、mapping自动化、obj so 自动化。

# 模块介绍

|模块名|说明|
|----|----|
|base-crash-core|设备信息获取、崩溃目录管理、崩溃通用上传处理。|
|native-crash-core|`C/C++` Native 层崩溃捕获处理，可单独使用。|
|java-crash-core|`java/kotlin` JVM 语言崩溃捕获处理，可单独使用。|
|android-crash-core|`C/C++` Native、`java/kotlin` JVM 语言崩溃统一处理。|

|test-demo-app| 测试 App，用来演示相关功能接入。|

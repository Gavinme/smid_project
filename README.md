# smid_project
该项目是『数盟可信id的获取』的样本。由于商业合作才可以拿到，一般在市面上鲜于见到。该样本出自一家接入过数盟解决方案的第三方公司APP，通过逆向的技术拿到了样本。

由于key是app唯一的，每次初始化请求会记一次，所以请手下留情！

### 关于数盟
官网传送门：https://www.shuzilm.cn

### 关于数盟ID
『数盟ID』：可信ID技术由数字联盟自主研发，用于准确描述移动设备，每个编码对象获得一个唯一的、不变的标识ID。可信ID能帮助APP公司在不同场景下确认设备唯一性，识别修改设备及复用、虚拟机刷量等行为，可以反作弊、防刷单，并通过数字联盟生成的设备ID和客户账户体系的关联，实时有效识别小号恶意注册等行为，并精确识别历史版本用户、弱账户APP的换机状态等。

可信ID不随任何非硬件信息变化而更改，是真正唯一、真实、安全、高可用的移动设备指纹。
### 说明
经过我的调研，发现该id并不依赖于传统的设备ID，如imei、mac地址、android_id等。在xpose劫持的情况修改，imei等参数并不会导致该ID修改。C方法拦截未测试。

在app第一次启动会初始化ID，并上传至数盟服务器(api.shuzilm.cn)。因为考虑到计费统计，所以可信ID的获取至少需要网络访问的权限。


``` <uses-permission android:name="android.permission.INTERNET" />```


![可信ID.png](https://upload-images.jianshu.io/upload_images/1689923-602a94b424380a6e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



可信ID的获取过程：

- 程序启动后在主进程中初始化SDK
- 注册传感器监听
- 获取网络权限成功后，尝试获取并散列88位可信ID
- 在sp中缓存以便下次进来使用
- 推送ID和相关信息至服务端接口api.shuzilm.cn

程序只有在第一次启动才会执行计算ID和计费的操作。


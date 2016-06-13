# Girls

# 简介

这是一个 [gank.io](http://gank.io/) 的简易客户端，自己用来练手用的。感谢 @代码家 提供的 API 和内容，也感谢其他的开源项目作者和组织，让我可以用到简单高性能的第三方库，免去了重复造轮子的烦恼，Thanks。

当然做的比较简陋，端午节放假第二天比较无聊，就花了一下午+一晚上撸了这个客户端，看起来简单，真正写起来还是有点麻烦的，尤其是针对我这种小菜鸟，欢迎大家 star 和 fork ，一起交流进步更快。
 
# 预览

![Girls](/preview/preview_girls.png) ![Girls](/preview/preview_girls_detail.png) 

![Android](/preview/preview_android.png) ![Android](/preview/preview_android_detail.png) 

另外一种样式：
在MainActivity中将mBottomBar.useFixedMode()这行注释去掉即可：

```java
    private void initBottomBar(Bundle savedInstanceState) {
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.noNavBarGoodness();
//        mBottomBar.useFixedMode(); // show all title and icon on bottom bar
        mBottomBar.setItems(R.menu.bottombar_menu);
```

![Android](/preview/preview_girls_all_items.png)

# 用到的库

``` java
    compile 'com.android.support:appcompat-v7:23.4.0'  
    compile 'com.roughike:bottom-bar:1.3.7'  
    compile 'org.greenrobot:eventbus:3.0.0'  
    compile 'com.jude:easyrecyclerview:4.0.2'  
    compile 'com.squareup.retrofit2:retrofit:2.0.2'  
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'  
    compile 'com.squareup.picasso:picasso:2.5.2'   
    compile 'com.android.support.constraint:constraint-layout:1.0.0-alpha1'  
    compile 'com.melnykov:floatingactionbutton:1.3.0'  
```

# Weibo：

[微博:高爷](http://weibo.com/gracker520)

[个人博客](http://androidperformance.com/)  

# API介绍（Copy form [gank.io/api](http://gank.io/api)）

### 2016-6-12 日更新：

#### 搜索 API

http://gank.io/api/search/query/listview/category/Android/count/10/page/1 
注：
category 后面可接受参数 all | Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
count 最大 50

### 2016-5-11 日更新:

#### 获取某几日干货网站数据:

http://gank.io/api/history/content/2/1

注： 2 代表 2 个数据，1 代表：取第一页数据
获取特定日期网站数据:

http://gank.io/api/history/content/day/2016/05/11

### 2016-2-28 日更新:

#### 获取发过干货日期接口:

http://gank.io/api/day/history 方式 GET

### 2016-2-27 日更新:

#### 支持提交干货到审核区:

https://gank.io/api/add2gank 方式: POST

请勿滥用此接口,不然我还得加身份校验代码,很麻烦的!!!
提交表单格式如下:

字段	描述	备注
url	想要提交的网页地址	
desc	对干货内容的描述	单独的文字描述
who	提交者 ID	干货提交者的网络 ID
type	干货类型	可选参数: Android | iOS | 休息视频 | 福利 | 拓展资源 | 前端 | 瞎推荐 | App
debug	当前提交为测试数据	如果想要测试数据是否合法, 请设置 debug 为 true! 可选参数: true | false
该 Api 玩儿法推荐:

直接在你的博客发表博文处嵌入该代码, 感觉不错的博文可以自动提交过来

写个 Chrome | Firefox | Safari 插件, 让更多人来分享干货, 妹子图和休息视频
分类数据: http://gank.io/api/data/数据类型/请求个数/第几页

数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
请求个数： 数字，大于0
第几页：数字，大于0
例：
http://gank.io/api/data/Android/10/1
http://gank.io/api/data/福利/10/1
http://gank.io/api/data/iOS/20/2
http://gank.io/api/data/all/20/2
每日数据： http://gank.io/api/day/年/月/日

例：
http://gank.io/api/day/2015/08/06
随机数据：http://gank.io/api/random/data/分类/个数

数据类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端
个数： 数字，大于0
例：
http://gank.io/api/random/data/Android/20

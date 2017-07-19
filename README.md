# ThirdPartyModule

本项目主要练习 Android 平台接入第三方平台 SDK，比如登录、分享、地图等等。

## 第三方登录

### 1、新浪微博登录

微博 Android 平台 SDK 为开发者接入微博服务提供简单易用 API 调用服务，使第三方客户端无需了解复杂验证机制即可进行微博授权登录，并提供微博分享功能。

集成微博 Android 平台 SDK 遵循以下步骤：

- [申请 AppKey](http://open.weibo.com/wiki/%E7%A7%BB%E5%8A%A8%E5%BA%94%E7%94%A8#.E7.A7.BB.E5.8A.A8.E5.AE.A2.E6.88.B7.E7.AB.AF.E6.8E.A5.E5.85.A5)

- 注册应用程序的包名和签名

	- 包名需要与 AndroidManifest.xml 文件 package 节点一致或者工程主 modules 目录下 build.gradle 文件中 applicationId 一致。

	- 签名可以使用官网提供的签名工具获取

- 集成 SDK（环境为 Android Studio）

	- 通过 gradle 引入微博 SDK 依赖。具体修改项目根目录 build.gradle 文件，添加微博依赖：

		```java
		buildscript {
			repositories {
				jcenter()
				maven { url "https://dl.bintray.com/thelasterstar/maven/" }
			}
	}
	
		allprojects {
			repositories {
				maven { url "https://dl.bintray.com/thelasterstar/maven/" }
			}
	}
		```
	
		修改主 modules 下 build.gradle 文件
	
		```java
		dependencies {
    		// 接入新浪微博 SDK
    		compile "com.sina.weibo.sdk:core:2.0.6:openDefaultRelease@aar"
	}
		```
	
- 认证授权

	- 在应用 Application 初始化 WbSdk 对象

		```java
		WbSdk.install(this, new AuthInfo(this,Constants.APP_KEY,Constants.REDIRECT_URL, Constants.SCOPE));
		```
	
		**APP_KEY：**平台唯一的标识。
	
		**REDIRECT_URL：**授权回调，默认值为 https://api.weibo.com/oauth2/default.html
	
		**SCODE：**需要请求的权限。
		
	- 初始化 SsoHandler 对象
		
		```java
		SsoHandler ssoHandler = new SsoHandler(getActivity());
		```
		
	- 实现 WbAuthListener 接口

		```java
		private class SelfWbAuthListener implements WbAuthListener {
		
			@Override
			public void onSuccess(Oauth2AccessToken oauth2AccessToken) {
				mView.showMessage("uid = " + oauth2AccessToken.getUid() + "\n" +
                    "access_token = " + oauth2AccessToken.getToken() + "\n" +
                    "expires_in = " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
                    new java.util.Date(oauth2AccessToken.getExpiresTime())) + "\n" +
                    "refresh_token = " + oauth2AccessToken.getRefreshToken() + "\n" +
                    "phone_num = " + oauth2AccessToken.getPhoneNum());
			}
			
			@Override
			public void cancel() {
			}
			
			@Override
			public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
				mView.showMessage(wbConnectErrorMessage.getErrorCode());
			}
    }
	```
	
	- 调用方法

		```java
		// 如果安装微博客户端则通过客户端授权；如果没有则通过 Web 授权
		ssoHandler.authorize(new SelfWbAuthListener());

		// 只通过微博客户端进行授权
	ssoHandler.authorizeClientSso(new SelfWbAuthListener());

		// 通过 SDK 自带的 WebView 打开 H5 页面进行授权
		ssoHandler.authorizeWeb(new SelfWbAuthListener());
		```
		
		注：以上三种授权需要在Activity的onActivityResult函数中，调用以下方法：
		
		```java
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			if (mSsoHandler != null) {
				mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
			}
}
		```
		
以上只是自己在参照官方文档写 demo 后的简要总结，具体还是得看官方文档：

[http://open.weibo.com/wiki/%E7%A7%BB%E5%8A%A8%E5%BA%94%E7%94%A8](http://open.weibo.com/wiki/%E7%A7%BB%E5%8A%A8%E5%BA%94%E7%94%A8)

[https://github.com/sinaweibosdk/weibo_android_sdk](https://github.com/sinaweibosdk/weibo_android_sdk)

[https://github.com/sinaweibosdk/weibo_android_sdk/blob/master/%E6%96%B0%E6%96%87%E6%A1%A3/%E5%BE%AE%E5%8D%9ASDK%204.0%E6%96%87%E6%A1%A3.pdf](https://github.com/sinaweibosdk/weibo_android_sdk/blob/master/%E6%96%B0%E6%96%87%E6%A1%A3/%E5%BE%AE%E5%8D%9ASDK%204.0%E6%96%87%E6%A1%A3.pdf)
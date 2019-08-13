# 微信点餐系统
## 简介
- Spring Boot + Mybatis 实现的微信点餐系统，包含买家系统与卖家系统。  
- 买家端在微信下访问url登陆后经授权验证，可进行下单、支付、退款等操作。
- 卖家端通过微信扫码登陆，可进行订单确认与取消、商品信息添加与修改等操作。
- 买家端前台使用Vue.js，通过json接口实现前后端分离。卖家端前台使用Bootstrap+FreeMarker生成页面。
- 买家端验证、支付及退款功能由微信公众平台API实现，卖家端扫码登录由微信开放平台API实现，并使用AOP+Redis实现统一身份验证。
- 数据库通过mysql主从配置实现读写分离。

## 上线网址
- [买家端](http://zhangfanglong.cn/)（需在微信下访问）
- [卖家端](http://zhangfanglong.cn/sell/seller/order/list)
## 项目目录
│      
├─java   
│　└─cn   
│　　└─zfl   
│　　　└─myshop   
│　　　　│　MyshopApplication.java   
│　　　　│    
│　　　　├─aspect
│　　　　│  DataSourceAop.java  
│　　　　│　SellerAuthorizeAspect.java　   
│　　　　│  Slave.java    
│　　　　│    
│　　　　├─config   
│　　　　│　CommonConfig.java  
│　　　　│  DataSourceConfig.java    
│　　　　│　DBContextHolder.java  
│　　　　│  MybatisConfig.java  
│　　　　│　MyRoutingDataSource.java  
│　　　　│　ProjectUrlConfig.java  
│　　　　│　WebSocketConfig.java  
│　　　　│　WechatAccountConfig.java   
│　　　　│　WechatMpConfig.java  
│　　　　│　WechatOpenConfig.java  
│　　　　│　WechatPayConfig.java  
│　　　　│  
│　　　　├─constant  
│　　　　│　CookieConstant.java  
│　　　　│　RedisConstant.java  
│　　　　│  
│　　　　├─controller  
│　　　　│　BuyerOrderController.java  
│　　　　│　BuyerProductController.java  
│　　　　│　PayController.java  
│　　　　│　SecKillController.java  
│　　　　│　SellerCategoryController.java  
│　　　　│　SellerOrderController.java  
│　　　　│　SellerProductController.java  
│　　　　│　SellerUserController.java  
│　　　　│　WechatController.java  
│　　　　│　WeixinController.java  
│　　　　│  
│　　　　├─converter  
│　　　　│　OrderForm2OrderDTO.java  
│　　　　│　OrderMaster2OrderDTO.java  
│　　　　│  
│　　　　├─dto  
│　　　　│　CartDTO.java  
│　　　　│　OrderDTO.java  
│　　　　│  
│　　　　├─enums  
│　　　　│　CodeEnum.java  
│　　　　│　DBTypeEnum.java  
│　　　　│　OrderStatusEnum.java  
│　　　　│　PayStatusEnum.java  
│　　　　│　ProductStatusEnum.java  
│　　　　│　ResultEnum.java  
│　　　　│  
│　　　　├─exception  
│　　　　│　SellerAuthorizeException.java  
│　　　　│　SellException.java  
│　　　　│  
│　　　　├─form  
│　　　　│　CategoryForm.java  
│　　　　│　OrderForm.java  
│　　　　│　ProductForm.java  
│　　　　│  
│　　　　├─handler  
│　　　　│　SellExceptionHandler.java  
│　　　　│  
│　　　　├─pojo  
│　　　　│　│　OrderDetail.java  
│　　　　│　│　OrderMaster.java  
│　　　　│　│　ProductCategory.java  
│　　　　│　│　ProductInfo.java  
│　　　　│　│　SellerInfo.java  
│　　　　│　│  
│　　　　│　└─mapper  
│　　　　│　　OrderDetailMapper.java  
│　　　　│　　OrderMasterMapper.java  
│　　　　│　　ProductCategoryMapper.java  
│　　　　│　　ProductInfoMapper.java  
│　　　　│　　SellerInfoMapper.java  
│　　　　│  
│　　　　├─service  
│　　　　│　│　BuyerService.java  
│　　　　│　│　CategoryService.java  
│　　　　│　│　OrderService.java  
│　　　　│　│　PayService.java  
│　　　　│　│　ProductService.java  
│　　　　│　│　PushMessageService.java  
│　　　　│　│　RedisLock.java  
│　　　　│　│　SecKillService.java  
│　　　　│　│　SellerService.java  
│　　　　│　│　WebSocket.java  
│　　　　│　│  
│　　　　│　└─impl  
│　　　　│　　BuyerServiceImpl.java  
│　　　　│　　CategoryServiceImpl.java  
│　　　　│　　OrderServiceImpl.java  
│　　　　│　　PayServiceImpl.java  
│　　　　│　　ProductServiceImpl.java  
│　　　　│　　PushMessageServiceImpl.java  
│　　　　│　　SecKillServiceImpl.java  
│　　　　│　　SellerServiceImpl.java  
│　　　　│  
│　　　　├─utils  
│　　　　│　│　CookieUtil.java  
│　　　　│　│　EnumUtil.java  
│　　　　│　│　JsonUtil.java  
│　　　　│　│　KeyUtil.java  
│　　　　│　│　ResultVOUtil.java  
│　　　　│　│  
│　　　　│　└─serializer  
│　　　　│　　　Date2LongSerializer.java  
│　　　　│  
│　　　　└─ViewObj  
│　　　　　ProductInfoVO.java  
│　　　　　ProductVO.java  
│　　　　　ResultVO.java  
│  
└─resources  
　│　application.yml  
　│　logback-spring.xml  
　│    
　├─static　  
　│　│  
　│　├─api  
　│　│　API.md  
　│　│　ratings.json  
　│　│　seller.json  
　│　│　SQL.md  
　│　│  
　│　├─css  
　│　│　style.css  
　│　│  
　│　└─mp3  
　│　　　song.mp3  
　│  
　└─templates  
　　├─category  
　　│　index.ftl  
　　│　list.ftl  
　　│  
　　├─common  
　　│　error.ftl  
　　│　header.ftl  
　　│　nav.ftl  
　　│　success.ftl  
　　│  
　　├─order  
　　│　detail.ftl  
　　│　list.ftl  
　　│  
　　├─pay  
　　│　create.ftl  
　　│　success.ftl  
　　│  
　　└─product  
　　　　index.ftl  
　　　　list.ftl  

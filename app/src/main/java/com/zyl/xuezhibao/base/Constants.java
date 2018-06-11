package com.zyl.xuezhibao.base;

/**
 * Created by qwe on 2017/5/22.
 */

public class Constants {
    public static class Url{
        //测试
//        public final static String domain = "http://192.168.0.118/";
//        public final static String domain = "http://192.168.0.122/";
//        public final static String address = "wa-tools-web/ws/";

        //正式
        public final static String domain = "http://app.yxfwl.com/";
        public final static String address = "wahtczyxf/ws/";
        public final static String head_url = domain + address;

        /**
         * 用户
         */
        public final static String Register_URL = head_url+ "login/register";//注册
        public final static String DirectLogin_URL = head_url + "login/directLogin";//无密码登录
        public final static String Login_URL = head_url + "login/login";//密码登录
        public final static String GetMemberInfo_URL = head_url + "memberInfo/get";//获取用户信息
        public final static String SendSms_URL = head_url + "sms/sendSms";//3.1.6获取短信验证码（注册时）
        public final static String SmsLogin_URL = head_url + "sms/smsLogin";//3.1.7无密码登录获取短信接口
        public final static String UpdateOrSaveAddress_URL = head_url + "address/updateOrSaveAddress";//3.1.8增删改查收货地址
        public final static String updatePerson_URL = head_url + "memberInfo/updatePerson";//修改用户信息
        public final static String smsPay_URL = head_url + "sms/smsPay";//扫码买单获得验证码
        public final static String balanceToPoint_URL = head_url + "memberInfo/balanceToPoint";//3.1.22余额转积分
        public final static String pointToOneChild_URL = head_url + "memberInfo/pointToOneChild";//3.1.23积分转给下级
        public final static String pointPayments_URL = head_url + "memberInfo/pointPayments";//3.1.25积分获得和消费列表
        public final static String pointPay_URL = head_url + "memberInfo/pointPay";//3.1.24积分支付
        public final static String xiangFuPay_URL = head_url + "memberInfo/xiangFuPay";//3.1.26想付支付

        /**
         * 市场
         */
        public final static String ProductList_URL = head_url + "market/productList";//市场产品列表
        public final static String ProductDetail_URL = head_url + "market/productDetail";//产品明细(市场、自营商城)
        public final static String MarketCreateOrder_URL = head_url + "market/createOrder";//市场产品创建订单
        public final static String StockOrderList_URL = head_url + "market/stockOrderList";//3.1.6股数订单列表
        public final static String orderDetail_URL = head_url + "market/orderDetail";//3.1.4市场产品的订单明细
        public final static String transactionPage_URL = head_url + "market/transactionPage";//3.1.5交易页面数据
        public final static String sellOut_URL = head_url + "market/sellOut";//3.1.7买入订单8%卖出
        public final static String cancelOrder_URL = head_url + "market/cancelOrder";//3.1.11取消订单
        public final static String merchantInfo_URL = head_url + "market/merchantInfo";//3.1.17市场商家
        public final static String operationCart_URL = head_url + "market/operationCart";//3.1.18增删改查购物车信息
        public final static String submitOrderPage_URL = head_url + "market/submitOrderPage";//3.1.15市场提交订单页面数据
        public final static String agentManagePage_URL = head_url + "market/agentManagePage";//3.1.21代理商管理界面
        public final static String agentMyOrderList_URL = head_url + "market/agentMyOrderList";//3.1.22代理商我的订单
        public final static String agentDraw_URL = head_url + "market/agentDraw";//3.1.23代理商一键提现
        public final static String agentDrawRecord_URL = head_url + "market/agentDrawRecord";//3.1.24代理商提现记录
        public final static String companyRedList_URL = head_url + "market/companyRedList";//3.1.25市场公司分红列表
        public final static String receiveCompanyRed_URL = head_url + "market/receiveCompanyRed";//3.1.26市场领取公司分红

        /**
         * 理财商城
         */
        public final static String Financing_ProductList_URL = head_url + "financing/productList";
        public final static String Financing_operationCart_URL = head_url + "financing/operationCart";
        public final static String Financing_ProductDetail_URL = head_url + "financing/productDetail";
        public final static String Financing_submitOrderPage_URL = head_url + "financing/submitOrderPage";
        public final static String Financing_createOrder_URL = head_url + "financing/createOrder";
        public final static String Financing_orderDetail_URL = head_url + "financing/orderDetail";
        public final static String Financing_productOrderList_URL = head_url + "financing/productOrderList";
        public final static String Financing_cancelOrder_URL = head_url + "financing/cancelOrder";
        //用户点击确认收货
        public final static String Financing_confirmReceipt_URL = head_url + "financing/confirmReceipt";

        /**
         * 物流管理
         */
        public final static String express_typeAll_URL = head_url + "express/typeAll";//3.1.1邮费计算的所有类型
        public final static String express_typeTemplate_URL = head_url + "express/typeTemplate";//3.1.2某一计算邮费类型的所有模板

        /**
         * 市场--我的
         */
        public final static String MemberMoney_URL = head_url + "memberInfo/getMemberMoney";//用户账号资金情况
        public final static String payOrder_URL = head_url + "memberInfo/payOrder";//3.1.9支付订单(非第三方支付)
        public final static String myCumulative_URL = head_url + "market/myCumulative";//我的各金额中左右金额累计--未出
        public final static String myDetail_URL = head_url + "market/myDetail";//收入支出金额明细
        public final static String productOrderList_URL = head_url + "market/productOrderList";//商品订单
        public final static String authenticationInfo_URL = head_url + "memberInfo/authenticationInfo";//3.1.12获取实名认证信息
        public final static String bankName_URL = head_url + "memberInfo/bankName";//3.1.13获取银行卡银行名称
        public final static String realNameAuthentication_URL = head_url + "memberInfo/realNameAuthentication";//3.1.13获取银行卡银行名称
        public final static String loginOut_URL = head_url + "login/loginOut";//退出
        public final static String createBalanceOrder_URL = head_url + "memberInfo/createBalanceOrder";//余额充值
        public final static String balanceWithdrawals_URL = head_url + "memberInfo/balanceWithdrawals";//余额提现
        public final static String receiveBonus_URL = head_url + "market/receiveBonus";//领取奖金
        public final static String receiveBonusMsg_URL = head_url + "market/receiveBonusMsg";//领取奖金的条件
        public final static String createQrCode_URL = head_url + "memberInfo/createQrCode";//刷新二维码
        public final static String receiveOrder_URL = head_url + "market/receiveOrder";//购物订单卖出领取
        public final static String handingCost_URL = head_url + "memberInfo/handingCost";//提现手续费
        public final static String productTypeList_URL = head_url + "market/productTypeList";//3.1.16市场产品类别列表
        public final static String confirmReceipt_URL = head_url + "market/agentConfirmReceipt";//3.1.16市场产品类别列表
        public final static String createFinancingOrder_URL = head_url + "market/createFinancingOrder";//3.1.16市场产品类别列表

        /**
         * 商家管理
         */
        public final static String redMerchantList_URL = head_url + "merchant/redMerchantList";//3.1.12我的分红商家列表
        public final static String merchantDetailList_URL = head_url + "merchant/merchantDetailList";//3.1.13商家分红明细列表
        public final static String redToBalance_URL = head_url + "merchant/redToBalance";//3.1.14分红提现到余额
        public final static String merchantOrderDetail_URL = head_url + "merchant/orderDetail";//3.1.11商家订单详情
        public final static String merchantPayOrder_URL = head_url + "merchant/payOrder";//3.1.7支付订单
        public final static String merchantCreateOrder_URL = head_url + "merchant/createOrder";//3.1.15创建商家订单


        /**
         * 文本管理
         */
        public final static String agreement_URL = head_url + "text/agreement";//3.1.1获取协议条款

        /**
         * 消息
         */
        public final static String messageCenter_URL = head_url + "extension/messageCenter";//3.1.6消息中心页面数据
        public final static String systemMessageList_URL = head_url + "extension/systemMessageList";//3.1.1系统消息列表
        public final static String personMessageList_URL = head_url + "extension/personMessageList";//3.1.2个人消息列表
        public final static String noticeInfoList_URL = head_url + "extension/noticeInfoList";//3.1.3公告列表
        public final static String systemMessageDetail_URL = head_url + "extension/systemMessageDetail";//3.1.4系统消息明细
        public final static String personMessageDetail_URL = head_url + "extension/personMessageDetail";//3.1.5个人消息明细
        public final static String noticeInfoDetail_URL = head_url + "extension/noticeInfoDetail";//3.1.7公告信息详情
        public final static String unReadMessage_URL = head_url + "extension/unReadMessage";//3.1.8是否有未读消息,

        /**
         * 附近
         */
        public final static String nearbyMerchant_Url = head_url + "merchant/nearby";//3.1.1附近商家
        public final static String categoryList_Url = head_url + "merchant/categoryList";//3.1.2商家类别
        public final static String merchantDetail_Url = head_url + "merchant/detail";//3.1.3商家详情
        public final static String merchantGainRed_Url = head_url + "merchant/gainRed";//3.1.4获取红包
        public final static String homeMerchant_Url = head_url + "merchant/homeMerchant";//首页商家
        public final static String homeRed_Url = head_url + "merchant/homeRed";//首页红包

        /**
         * 搜索
         */
        public final static String searchHot_Url = head_url + "search/hot";//3.1.1热门搜索
        public final static String searchRecord_Url = head_url + "search/searchRecord";//3.1.2用户搜索记录
        public final static String deleteUserRecord_Url = head_url + "search/deleteUserRecord";//3.1.3用户删除搜索记录

        /**
         * 阿里
         */
        public final static String imageParam_URl = head_url + "ali/imageParam";//3.1.2访问阿里云图片参数
        public final static String imageUrl_URl = head_url + "url/url";//3.1.1域名路径
        public final static String aliPay_URl = head_url + "ali/aliPay";//3.1.1调起支付宝支付参数

        /**
         * 微信
         */
        public final static String swiftWxPay_Url = head_url + "wx/swiftWxPay";//3.1.6微信第三方支付(威富通SDK)
        public final static String wxPay_Url = head_url + "wx/wxPay";//（微信SDK）
        public final static String wxParameter_Url = head_url + "wx/wxParameter";//微信基本参数


        /**
         *我的
         */
        public final static String invitationMerchant_Url = head_url + "merchant/invitation";//判断邀请商家的条件是否满足
        public final static String onlyPhoneMerchant_Url = head_url + "merchant/onlyPhone";//判断商家的手机号是否唯一
        public final static String applyMerchant_Url = head_url + "merchant/apply";//商家入驻申请

        /**
         * 自营商城
         */
        public final static String OneParent_URL = head_url+ "classification/oneParent";//3.1.1获取一级分类
        public final static String TwoParent_URL = head_url + "classification/twoParent";//3.1.2获取二级分类
        public final static String AttributeList_URL = head_url + "classification/attributeList";//3.1.3某一二级分类属性名和属性值
        public final static String ZYProductList_URL = head_url + "product/productList";//3.1.4获取某一二级分类下的产品列表
        public final static String ZYProductDetail_URL = head_url + "product/productDetail";//3.1.5商品详情
        public final static String OperationCart_URL = head_url + "cart/operationCart";//3.1.6增删该查购物车信息

        /**
         * 版本管理
         */
        public final static String apkVersion_URL = head_url + "version/apkVersion";

    }

    public static class IntentKey{
        public final static String SEARCH_STEING = "SEARCH_STEING";
        public static final String KEY_PICKED_CITY = "picked_city";
        public static final String STR_Phone = "strPhone";
        public static final String Product_Id = "product_id";
        public static final String Product_Name= "product_name";
        public static final String ProductSumPrice= "product_sum_price";
        public static final String ProductPrice= "product_price";
        public static final String OrderCode = "orderCode";
        public static final String Product_Info = "ProductInfo";
        public static final String Parent_Id = "ParentId";//商品类别一二级
        public static final String NUM = "num";
        public static final String Adress = "adress";
        public static final String Is_Red_Packet = "isRedPacket";
        public static final String Red_Packet = "RedPacket";
        public static final String Bazaar_Submit_Order = "Bazaar_Submit_Order";
        public static final String Message_Title = "message_title";
        public static final String Message_Type = "message_type";
        public static final String MessageSystem_Id = "messageSystem_id";
        public static final String MessagePerson_Id = "messagePerson_id";
        public static final String Notice_Id = "notice_id";
        public static final String Merchant_Id = "merchant_id";
        public static final String mMessageType = "mMessageType";
        public static final String RedRate = "redRate";//红包比例
        public static final String ImageUUID = "ImageUUID";
        public static final String isShow = "isShow";
        public static final String CartList = "CartList";
        public static final String cartIdStr = "cartIdStr";
        public static final String shareCode = "shareCode";
        public static final String redMerchantType = "redMerchantType";
        public static final String WxPayType = "WxPayType";
        public static final String FeaturedType = "FeaturedType";
        public static final String IntegralType = "IntegralOperationType";
        public static final String isOverflow = "isOverflow";
        public static final String AgentCanDrawPrice = "AgentCanDrawPrice";
        public static final String ExpressCode = "ExpressCode";
        public static final String ExpressENName = "ExpressENName";
        public static final String ExpressCHName = "ExpressCHName";
        public static final String isPay = "isPayToOrderDetail";
        public static final String isOrderList = "isOrderList";
        public static final String isMarketOrder = "isMarketOrder";
        public static final String AgreementType = "AgreementType";
    }

    public static class ResultKey{
        public static final int SEARCH_CODE = 1;
        public static final int CITY_CHOOSE = 2;
        public static final int REQUEST_CODE = 3;//扫码
        public static final int REQUEST_IMAGE = 4;//扫码图片解析
        public static final int CROP_SMALL_PICTURE = 5;//裁剪图片
        public static final int CHOOSE_ADDRESS = 6;//选择地址
        public static final int CHOOSE_PHONE_CODE = 8;//选择手机号
    }

    public static class AliOssSize{
        /**
         * 阿里云
         * 缩略图尺寸
         */
        public static String marketHomeImageSize = "?x-oss-process=image/resize,h_400,w_400";
        public static String marketDetailImageSize = "?x-oss-process=image/resize,h_300,w_300";
    }


}

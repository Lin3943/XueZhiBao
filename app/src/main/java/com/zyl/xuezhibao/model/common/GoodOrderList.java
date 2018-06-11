package com.zyl.xuezhibao.model.common;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Leo on 2017/6/23.
 */

public class GoodOrderList implements Serializable {


    /**
     * marketProductOrderList : [{"address":"观音山16号602J","area":"思明区","city":"厦门市","consignee":"詹弘","createdDate":1505633779000,"createdUser":"sys","id":831,"isPick":0,"lastModifiedDate":1505634261000,"lastModifiedUser":"sys","marketProductOrderDetailList":[{"createdDate":1505633779000,"createdUser":"sys","id":1220,"image":"03d17d9b95c84802a6beb95004ac3e97","name":"亞馬牛内裤","num":10,"order_id":831,"price":99,"product_id":96,"rate":50,"type":0}],"orderCode":"1505633779561","payDate":1505634261000,"payStatus":1,"payType":0,"phone":"14700063019","province":"福建省","redpacketPrice":0,"status":1,"totalnum":10,"totalprice":990,"type":0,"user_id":4},{"address":"观音山16号602J","area":"思明区","city":"厦门市","consignee":"詹弘","createdDate":1505633393000,"createdUser":"sys","id":830,"isPick":0,"lastModifiedDate":1505633458000,"lastModifiedUser":"sys","marketProductOrderDetailList":[{"createdDate":1505633393000,"createdUser":"sys","id":1218,"image":"3fba7852309f4def820b72c5b46bcd3e","name":"茶油精华洗衣液五包装","num":5,"order_id":830,"price":55,"product_id":66,"rate":40,"type":0},{"createdDate":1505633393000,"createdUser":"sys","id":1219,"image":"f3f250d2b0c5488e97c1f17c552785a7","name":"儿童本色纸巾 12包/提","num":5,"order_id":830,"price":59,"product_id":67,"rate":40,"type":0}],"orderCode":"1505633393555","payDate":1505633458000,"payStatus":1,"payType":0,"phone":"14700063019","province":"福建省","redpacketPrice":0,"remark":"你好","status":1,"totalnum":10,"totalprice":570,"type":0,"user_id":4}]
     * totalSize : 2
     * message : 获取订单列表成功
     * status : success
     */

    private int totalSize;
    private String message;
    private String status;
    private List<MarketProductOrderListBean> marketProductOrderList;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MarketProductOrderListBean> getMarketProductOrderList() {
        return marketProductOrderList;
    }

    public void setMarketProductOrderList(List<MarketProductOrderListBean> marketProductOrderList) {
        this.marketProductOrderList = marketProductOrderList;
    }

    public static class MarketProductOrderListBean {
        /**
         * address : 观音山16号602J
         * area : 思明区
         * city : 厦门市
         * consignee : 詹弘
         * createdDate : 1505633779000
         * createdUser : sys
         * id : 831
         * isPick : 0
         * lastModifiedDate : 1505634261000
         * lastModifiedUser : sys
         * marketProductOrderDetailList : [{"createdDate":1505633779000,"createdUser":"sys","id":1220,"image":"03d17d9b95c84802a6beb95004ac3e97","name":"亞馬牛内裤","num":10,"order_id":831,"price":99,"product_id":96,"rate":50,"type":0}]
         * orderCode : 1505633779561
         * payDate : 1505634261000
         * payStatus : 1
         * payType : 0
         * phone : 14700063019
         * province : 福建省
         * redpacketPrice : 0
         * status : 1
         * totalnum : 10
         * totalprice : 990
         * type : 0
         * user_id : 4
         * remark : 你好
         */

        private String address;
        private String area;
        private String city;
        private String consignee;
        private long createdDate;
        private String createdUser;
        private int id;
        private int isPick;
        private long lastModifiedDate;
        private String lastModifiedUser;
        private String orderCode;
        private long payDate;
        private int payStatus;
        private int payType;
        private String phone;
        private String province;
        private String redpacketPrice;
        private String pointPrice;
        private String status;
        private int totalnum;
        private String totalprice;
        private int type;
        private int user_id;
        private String remark;
        private String postage;//邮费
        private String expressENName;
        private String expressCHName;
        private String expressCode;
        private List<MarketProductOrderDetailListBean> marketProductOrderDetailList;

        public String getExpressENName() {
            return expressENName;
        }

        public void setExpressENName(String expressENName) {
            this.expressENName = expressENName;
        }

        public String getExpressCHName() {
            return expressCHName;
        }

        public void setExpressCHName(String expressCHName) {
            this.expressCHName = expressCHName;
        }

        public String getExpressCode() {
            return expressCode;
        }

        public void setExpressCode(String expressCode) {
            this.expressCode = expressCode;
        }

        public String getPostage() {
            return postage;
        }

        public void setPostage(String postage) {
            this.postage = postage;
        }

        public String getPointPrice() {
            return pointPrice;
        }

        public void setPointPrice(String pointPrice) {
            this.pointPrice = pointPrice;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public long getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(long createdDate) {
            this.createdDate = createdDate;
        }

        public String getCreatedUser() {
            return createdUser;
        }

        public void setCreatedUser(String createdUser) {
            this.createdUser = createdUser;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIsPick() {
            return isPick;
        }

        public void setIsPick(int isPick) {
            this.isPick = isPick;
        }

        public long getLastModifiedDate() {
            return lastModifiedDate;
        }

        public void setLastModifiedDate(long lastModifiedDate) {
            this.lastModifiedDate = lastModifiedDate;
        }

        public String getLastModifiedUser() {
            return lastModifiedUser;
        }

        public void setLastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public long getPayDate() {
            return payDate;
        }

        public void setPayDate(long payDate) {
            this.payDate = payDate;
        }

        public int getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(int payStatus) {
            this.payStatus = payStatus;
        }

        public int getPayType() {
            return payType;
        }

        public void setPayType(int payType) {
            this.payType = payType;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getRedpacketPrice() {
            return redpacketPrice;
        }

        public void setRedpacketPrice(String redpacketPrice) {
            this.redpacketPrice = redpacketPrice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(int totalnum) {
            this.totalnum = totalnum;
        }

        public String getTotalprice() {
            return totalprice;
        }

        public void setTotalprice(String totalprice) {
            this.totalprice = totalprice;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<MarketProductOrderDetailListBean> getMarketProductOrderDetailList() {
            return marketProductOrderDetailList;
        }

        public void setMarketProductOrderDetailList(List<MarketProductOrderDetailListBean> marketProductOrderDetailList) {
            this.marketProductOrderDetailList = marketProductOrderDetailList;
        }

        public static class MarketProductOrderDetailListBean {
            /**
             * createdDate : 1505633779000
             * createdUser : sys
             * id : 1220
             * image : 03d17d9b95c84802a6beb95004ac3e97
             * name : 亞馬牛内裤
             * num : 10
             * order_id : 831
             * price : 99
             * product_id : 96
             * rate : 50
             * type : 0
             */

            private long createdDate;
            private String createdUser;
            private int id;
            private String image;
            private String name;
            private int num;
            private int order_id;
            private String price;
            private int product_id;
            private int rate;
            private int type;

            public long getCreatedDate() {
                return createdDate;
            }

            public void setCreatedDate(long createdDate) {
                this.createdDate = createdDate;
            }

            public String getCreatedUser() {
                return createdUser;
            }

            public void setCreatedUser(String createdUser) {
                this.createdUser = createdUser;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getRate() {
                return rate;
            }

            public void setRate(int rate) {
                this.rate = rate;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}

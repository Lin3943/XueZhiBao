package com.zyl.xuezhibao.model.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Leo on 2017/6/23.
 */

public class DetailList implements Serializable {
    private String status;
    private String message;
    private ArrayList<Datail> detailList;
    private String totalSize;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Datail> getDetailList() {
        return detailList;
    }

    public void setDetailList(ArrayList<Datail> detailList) {
        this.detailList = detailList;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }


    @Override
    public String toString() {
        return "DetailList{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", detailList=" + detailList +
                ", totalSize='" + totalSize + '\'' +
                '}';
    }

    public class Datail
    {
        private String createdDate;
        private String price;
        private String remark;
        private String orderCode;
        private String count;
        private String isActivation;
        private String status;
        private String nickname;
        private String parentNickname;
        private String handlingCost;

        public String getHandlingCost() {
            return handlingCost;
        }

        public void setHandlingCost(String handlingCost) {
            this.handlingCost = handlingCost;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getParentNickname() {
            return parentNickname;
        }

        public void setParentNickname(String parentNickname) {
            this.parentNickname = parentNickname;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getIsActivation() {
            return isActivation;
        }

        public void setIsActivation(String isActivation) {
            this.isActivation = isActivation;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "Datail{" +
                    "createdDate='" + createdDate + '\'' +
                    ", price='" + price + '\'' +
                    ", remark='" + remark + '\'' +
                    ", orderCoe='" + orderCode + '\'' +
                    ", count='" + count + '\'' +
                    ", isActivation='" + isActivation + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}

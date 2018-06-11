package com.zyl.xuezhibao.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 *  写入和读取集合有两种方式,
 *  一种是写入类的相关信息,然后通过类加载器去读取,–> writeList | readList
 *  二是不用类相关信息,创建时传入相关类的CREATOR来创建 –>writeTypeList | readTypeList | createTypedArrayList
 *  第二种效率高一些
 *
 *  一定要注意如果有集合定义的时候一定要初始化
 *  like this –>public ArrayList authors = new ArrayList<>();
 *
 */
public class SchoolsInfo implements Parcelable {
    //学校id
    private int schoolId;
    //学校名称
    private String schoolName;
    //学校地址
    private String schoolAddr;
    //学校logo的url
    private String schoolLogo;
    //名师数量
    private int schoolTeachers;
    //教师代号
    private int teacherNum;
    //收藏总数
    private int collectionSum;
    //是否收藏
    private boolean isCollection;

    public SchoolsInfo(){

    }
    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolAddr() {
        return schoolAddr;
    }

    public void setSchoolAddr(String schoolAddr) {
        this.schoolAddr = schoolAddr;
    }

    public String getSchoolLogo() {
        return schoolLogo;
    }

    public void setSchoolLogo(String schoolLogo) {
        this.schoolLogo = schoolLogo;
    }

    public int getSchoolTeachers() {
        return schoolTeachers;
    }

    public void setSchoolTeachers(int schoolTeachers) {
        this.schoolTeachers = schoolTeachers;
    }

    public int getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(int teacherNum) {
        this.teacherNum = teacherNum;
    }

    public int getCollectionSum() {
        return collectionSum;
    }

    public void setCollectionSum(int collectionSum) {
        this.collectionSum = collectionSum;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    protected SchoolsInfo(Parcel in) {
        schoolId = in.readInt();
        schoolName = in.readString();
        schoolAddr = in.readString();
        schoolLogo = in.readString();
        schoolTeachers = in.readInt();
        teacherNum = in.readInt();
        collectionSum = in.readInt();
        isCollection = in.readByte() != 0;
    }

    public static final Creator<SchoolsInfo> CREATOR = new Creator<SchoolsInfo>() {
        @Override
        public SchoolsInfo createFromParcel(Parcel in) {
            return new SchoolsInfo(in);
        }

        @Override
        public SchoolsInfo[] newArray(int size) {
            return new SchoolsInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(schoolId);
        dest.writeString(schoolName);
        dest.writeString(schoolAddr);
        dest.writeString(schoolLogo);
        dest.writeInt(schoolTeachers);
        dest.writeInt(teacherNum);
        dest.writeInt(collectionSum);
        dest.writeByte((byte) (isCollection ? 1 : 0));
    }
}

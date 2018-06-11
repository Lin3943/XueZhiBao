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
public class TeachersInfo implements Parcelable {
    //教师id
    private int teacherId;
    //教师名称
    private String schoolName;
    //教师所属学校
    private String inSchool;
    //教师logo的url
    private String schoolLogo;
    //名师数量
    private int schoolTeachers;
    //学校代号
    private int teacherNum;
    //点赞总数
    private int zanSum;
    //是否收藏
    private boolean isCollection;

    public TeachersInfo(){

    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getInSchool() {
        return inSchool;
    }

    public void setInSchool(String inSchool) {
        this.inSchool = inSchool;
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

    public int getZanSum() {
        return zanSum;
    }

    public void setZanSum(int zanSum) {
        this.zanSum = zanSum;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    protected TeachersInfo(Parcel in) {
        teacherId = in.readInt();
        schoolName = in.readString();
        inSchool = in.readString();
        schoolLogo = in.readString();
        schoolTeachers = in.readInt();
        teacherNum = in.readInt();
        zanSum = in.readInt();
        isCollection = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(teacherId);
        dest.writeString(schoolName);
        dest.writeString(inSchool);
        dest.writeString(schoolLogo);
        dest.writeInt(schoolTeachers);
        dest.writeInt(teacherNum);
        dest.writeInt(zanSum);
        dest.writeByte((byte) (isCollection ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TeachersInfo> CREATOR = new Creator<TeachersInfo>() {
        @Override
        public TeachersInfo createFromParcel(Parcel in) {
            return new TeachersInfo(in);
        }

        @Override
        public TeachersInfo[] newArray(int size) {
            return new TeachersInfo[size];
        }
    };
}

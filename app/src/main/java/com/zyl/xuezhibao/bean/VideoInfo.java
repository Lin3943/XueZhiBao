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
public class VideoInfo implements Parcelable {
    //视频名称
    private String videoName;
    //视频地址
    private String videoUrl;
    //视频缓存地址
    private String videoCacheUrl;
    //视频缩略图地址
    private String videoImageUrl;
    //视频长度（播放时长）
    private long videoSize;
    //视频简介
    private String videoIntro;
    //视频代号
    private int videoNum;
    //收藏总数
    private int collectionSum;
    //是否收藏
    private boolean isCollection;
    //是否播放过
    private boolean isPlayed;

    public VideoInfo(){

    }

    protected VideoInfo(Parcel in) {
        videoName = in.readString();
        videoUrl = in.readString();
        videoCacheUrl = in.readString();
        videoImageUrl = in.readString();
        videoSize = in.readLong();
        videoIntro = in.readString();
        videoNum = in.readInt();
        collectionSum = in.readInt();
        isCollection = in.readByte() != 0;
        isPlayed = in.readByte() != 0;
    }

    public static final Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {
        @Override
        public VideoInfo createFromParcel(Parcel in) {
            return new VideoInfo(in);
        }

        @Override
        public VideoInfo[] newArray(int size) {
            return new VideoInfo[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(videoName);
        dest.writeString(videoUrl);
        dest.writeString(videoCacheUrl);
        dest.writeString(videoImageUrl);
        dest.writeLong(videoSize);
        dest.writeString(videoIntro);
        dest.writeInt(videoNum);
        dest.writeInt(collectionSum);
        dest.writeByte((byte) (isCollection ? 1 : 0));
        dest.writeByte((byte) (isPlayed ? 1 : 0));
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoCacheUrl() {
        return videoCacheUrl;
    }

    public void setVideoCacheUrl(String videoCacheUrl) {
        this.videoCacheUrl = videoCacheUrl;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        this.videoImageUrl = videoImageUrl;
    }

    public long getVideoSize() {
        return videoSize;
    }

    public void setVideoSize(long videoSize) {
        this.videoSize = videoSize;
    }

    public String getVideoIntro() {
        return videoIntro;
    }

    public void setVideoIntro(String videoIntro) {
        this.videoIntro = videoIntro;
    }

    public int getVideoNum() {
        return videoNum;
    }

    public void setVideoNum(int videoNum) {
        this.videoNum = videoNum;
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

    public boolean isPlayed() {
        return isPlayed;
    }

    public void setPlayed(boolean played) {
        isPlayed = played;
    }
}

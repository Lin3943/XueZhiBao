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

public class CollectionBean implements Parcelable {
    private String videoName;       //视频名称
    private String videoUrl;        //视频地址
    private int collectionSum;      //收藏总数
    private boolean isCollection;   //是否收藏
//    public Author author;
//    // ***** 注意: 这里如果是集合 ,一定要初始化 *****
//    public ArrayList<Author> authors = new ArrayList<>();

    public CollectionBean(){

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

    protected CollectionBean(Parcel in) {
        videoName = in.readString();
        videoUrl = in.readString();
        collectionSum = in.readInt();
        isCollection = in.readByte() != 0;     //isCollection == true if byte != 0

//        // 读取对象需要提供一个类加载器去读取,因为写入的时候写入了类的相关信息
//        author = in.readParcelable(Author.class.getClassLoader());
//        //读取集合也分为两类,对应写入的两类
//        //这一类需要用相应的类加载器去获取
//        //in.readList(authors, Author.class.getClassLoader()); // 对应writeList
//        //这一类需要使用类的CREATOR去获取
//        in.readTypedList(authors, Author.CREATOR); //对应writeTypeList
//        //authors = in.createTypedArrayList(Author.CREATOR);//对应writeTypeList
//        //这里获取类加载器主要有几种方式
//        getClass().getClassLoader();
//        Thread.currentThread().getContextClassLoader();
//        Author.class.getClassLoader();
    }

    public static final Creator<CollectionBean> CREATOR = new Creator<CollectionBean>() {
        @Override
        public CollectionBean createFromParcel(Parcel in) {
            return new CollectionBean(in);
        }

        @Override
        public CollectionBean[] newArray(int size) {
            return new CollectionBean[size];
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
        dest.writeInt(collectionSum);
        dest.writeByte((byte) (isCollection ? 1 : 0));     //if isCollection == true, byte == 1
//        //序列化一个String的集合
//        dest.writeStringList(tags);
//
//        // 序列化对象的时候传入要序列化的对象和一个flag,
//        // 这里的flag几乎都是0,除非标识当前对象需要作为返回值返回,不能立即释放资源
//        dest.writeParcelable(author, flags);
//
//        // 序列化一个对象的集合有两种方式,以下两种方式都可以
//
//        //这些方法们把类的信息和数据都写入Parcel，以使将来能使用合适的类装载器重新构造类的实例.所以效率不高
//        dest.writeList(authors);
//
//        //这些方法不会写入类的信息，取而代之的是：读取时必须能知道数据属于哪个类并传入正确的Parcelable.Creator来创建对象
//        // 而不是直接构造新对象。（更加高效的读写单个Parcelable对象的方法是：
//        // 直接调用Parcelable.writeToParcel()和Parcelable.Creator.createFromParcel()）
//        dest.writeTypedList(authors);
    }
}
package com.zyl.xuezhibao.map;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.zyl.xuezhibao.utils.Globals;

/**
 * Created by admin on 2016/5/23.
 */
public class LocationService {

    private static LocationService mInstance = null;
    private static BDLocationListener mBDLocationListener;
    private BDLocationService mLocationService;

    private LocationService(Context context) {
        try {
            mLocationService = new BDLocationService(context);
            mLocationService.registerListener(getListener());
            mLocationService.setLocationOption(mLocationService.getDefaultLocationClientOption());
            mLocationService.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized LocationService getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LocationService(context);
        }
        return mInstance;
    }

    public void start() {
        if (mLocationService != null) {
            try {
                mLocationService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        if (mLocationService != null) {
            try {
                mLocationService.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void release() {
        try {
            if (mInstance != null && mInstance.mLocationService != null) {
                mInstance.mLocationService.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (mInstance != null && mInstance.mLocationService != null) {
                mInstance.mLocationService.unregisterListener(mBDLocationListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mInstance = null;
    }

    private BDLocationListener getListener() {
        if (mBDLocationListener != null) {
            mBDLocationListener = null;
        }

        mBDLocationListener = new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                Globals.log("lo g 错误码",""+location.getLocType());
                Globals.log("log 对象location",""+location);
                    if (location.getCity() == null) {
                        UserLocation.setCity("所在城市");
                    }else {
                        Globals.log("log location.getLatitude()",""+location.getLatitude());
                        Globals.log("log location.getLongitude()",""+location.getLongitude());
                        UserLocation.setLatitude(location.getLatitude()+"");
                        UserLocation.setLongitude(location.getLongitude()+"");
                        UserLocation.setAddress(location.getAddrStr()+"");
                        UserLocation.setCity(location.getCity()+"");
                        if (myLocationListenner != null){
                            myLocationListenner.onMyLocation(location);
                        }
                    }
            }
        };

        return mBDLocationListener;
    }

    public interface MyLocationListenner {
        void onMyLocation(BDLocation location);
    }
    public static MyLocationListenner myLocationListenner;
    public static void setMyLocationListenner(MyLocationListenner myLocation) {
        myLocationListenner = myLocation;
    }
}

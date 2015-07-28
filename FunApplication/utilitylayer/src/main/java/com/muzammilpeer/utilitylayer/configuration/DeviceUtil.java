package com.muzammilpeer.utilitylayer.configuration;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import com.muzammilpeer.utilitylayer.logger.Log4a;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by muzammilpeer on 15/07/2015.
 */
public class DeviceUtil {

    private DeviceUtil() {
        throw new AssertionError();
    }

    public boolean isKindleFire() {
        return android.os.Build.MODEL.equalsIgnoreCase("kindle fire") ? true : false;
    }

    /**
     * Checks camera availability recursively based on API level.
     * <p>
     * TODO: change "android.hardware.camera.front" and "android.hardware.camera.any" to
     * {@link PackageManager#FEATURE_CAMERA_FRONT} and {@link PackageManager#FEATURE_CAMERA_ANY},
     * respectively, once they become accessible or minSdk version is incremented.
     *
     * @param context The context.
     * @return Whether camera is available.
     */
    public boolean hasCamera(Context context) {
        final PackageManager pm = context.getPackageManager();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)
                    || pm.hasSystemFeature("android.hardware.camera.front");
        }

        return pm.hasSystemFeature("android.hardware.camera.any");
    }

    public String getDeviceName(Context context) {
        String manufacturer = Build.MANUFACTURER;
        String undecodedModel = Build.MODEL;
        String model = null;

        try {
            Properties prop = new Properties();
            InputStream fileStream;
            // Read the device name from a precomplied list:
            // see http://making.meetup.com/post/29648976176/human-readble-android-device-names
            fileStream = context.getAssets().open("android_models.properties");
            prop.load(fileStream);
            fileStream.close();
            String decodedModel = prop.getProperty(undecodedModel.replaceAll(" ", "_"));
            if (decodedModel != null && !decodedModel.trim().equals("")) {
                model = decodedModel;
            }
        } catch (IOException e) {
            Log4a.printException(e);
        }

        if (model == null) {  //Device model not found in the list
            if (undecodedModel.startsWith(manufacturer)) {
                model = undecodedModel.toUpperCase();
            } else {
                model = manufacturer.toUpperCase() + " " + undecodedModel;
            }
        }
        return model;
    }

}

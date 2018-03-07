package com.zyjd.mips;

import android.app.Application;

import com.zyjd.mips.util.SPUtil;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 检查设备的唯一标示是否存在，如不存在则生成，保存在SharedPreference
        String uuid = SPUtil.getString(getApplicationContext(), "mips_device_uuid", null);
        if (uuid == null || uuid.equals("")) {
            // 36位随机uuid + timestamp
            uuid = java.util.UUID.randomUUID().toString() + "-" + System.currentTimeMillis();
            SPUtil.putString(getApplicationContext(), "mips_device_uuid", uuid);
        }
    }
}


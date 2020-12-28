package com.baolong.obd.common.sharepreferemces;

import com.baolong.obd.common.base.BaseApplication;

public class ServerHostSP {
    public static final String HOST_CACHE_PATH;

    static {
        final StringBuilder sb = new StringBuilder();
        sb.append(BaseApplication.getInstance().getCacheDir().getAbsolutePath());
        sb.append("/HostCache.txt");
        HOST_CACHE_PATH = sb.toString();
    }
}


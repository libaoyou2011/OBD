package com.baolong.obd.common.file.uploadFile;

import java.io.PrintStream;
import java.io.File;

import rx.Observer;

public abstract class FileUploadObserver<T> implements Observer<T> {
    public FileUploadObserver() {
        super();
    }

    public abstract void onProgress(final File p0, final int p1);

    public void onProgressChange(final File file, final long n, final long n2) {
        final PrintStream out = System.out;
        final StringBuilder sb = new StringBuilder();
        sb.append(n);
        sb.append(",");
        sb.append(n2);
        out.println(sb.toString());
        if (n != 0L && n2 != 0L) {
            this.onProgress(file, (int) (n * 100L / n2));
        }
    }
}

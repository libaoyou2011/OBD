package com.baolong.obd.common.file.uploadFile;

import okio.Buffer;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;
import okio.BufferedSink;

import java.io.IOException;

import okhttp3.MediaType;

import java.io.File;

import okhttp3.RequestBody;

public class UploadFileRequestBody extends RequestBody {
    private File file;
    private FileUploadObserver fileUploadObserver;
    private RequestBody mRequestBody;

    public UploadFileRequestBody(final File file, final FileUploadObserver fileUploadObserver) {
        this.mRequestBody = RequestBody.create(MediaType.parse("*/*"), file);
        this.fileUploadObserver = fileUploadObserver;
        this.file = file;
    }

    public long contentLength() throws IOException {
        return this.mRequestBody.contentLength();
    }

    public MediaType contentType() {
        return this.mRequestBody.contentType();
    }

    public void writeTo(BufferedSink buffer) throws IOException {
        buffer = Okio.buffer((Sink) new CountingSink((Sink) buffer));
        this.mRequestBody.writeTo(buffer);
        buffer.flush();
    }

    protected final class CountingSink extends ForwardingSink {
        private long bytesWritten;

        public CountingSink(final Sink sink) {
            super(sink);
            this.bytesWritten = 0L;
        }

        public void write(final Buffer buffer, final long n) throws IOException {
            super.write(buffer, n);
            this.bytesWritten += n;
            if (UploadFileRequestBody.this.fileUploadObserver != null) {
                UploadFileRequestBody.this.fileUploadObserver.onProgressChange(UploadFileRequestBody.this.file, this.bytesWritten, UploadFileRequestBody.this.contentLength());
            }
        }
    }
}

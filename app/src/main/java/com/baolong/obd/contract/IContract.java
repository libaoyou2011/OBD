package com.baolong.obd.contract;

public abstract interface IContract {

    public static abstract interface Presenter {
        public abstract void destroy();

        public abstract void getUnReadMsgs();

        public abstract void showTaskRedPoint();

        public abstract void start();
    }

    public static abstract interface View {
        public abstract void isShowTaskRed(boolean paramBoolean);
    }
}
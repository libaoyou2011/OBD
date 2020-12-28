package com.baolong.obd.common.presenter;

public abstract interface BasePresenter<T> {

    public abstract void dropView();

    public abstract void takeView(T paramT);
}

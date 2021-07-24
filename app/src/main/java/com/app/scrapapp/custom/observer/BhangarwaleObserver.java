package com.app.scrapapp.custom.observer;

import androidx.lifecycle.Observer;

import com.app.scrapapp.entity.contract.BhangarwaleResponseBody;

public abstract class BhangarwaleObserver<T extends BhangarwaleResponseBody> implements Observer<T> {

    @Override
    public final void onChanged(T t) {
        BhangarwaleResponseBody bhangarwaleResponseBody = t;
        switch (bhangarwaleResponseBody.getResponseCodeBody().getResponseCode()){
            case LOADING:
                onPreExecute();
                break;
            case SUCCESS:
                onSuccess(t);
                break;
            case FAIL:
                onFailure(t);
                break;
        }
    }

    protected abstract void onPreExecute();
    protected abstract void onSuccess(T t);
    protected abstract void onFailure(T t);

}

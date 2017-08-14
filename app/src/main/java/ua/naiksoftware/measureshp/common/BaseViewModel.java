package ua.naiksoftware.measureshp.common;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.CallSuper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by naik on 13.08.17.
 */

public class BaseViewModel extends ViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void manageDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @CallSuper
    @Override
    protected void onCleared() {
        compositeDisposable.dispose();
    }
}

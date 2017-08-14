package ua.naiksoftware.measureshp.common.rx;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Cancellable;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by naik on 14.08.17.
 */

public final class SingleCachedComplete<T> extends Single<T> {

    public static <T> Single<T> from(Single<T> single) {
        return new SingleCachedComplete<>(single);
    }

    private final Single<T> single;
    private T cachedValue;

    public SingleCachedComplete(Single<T> single) {
        this.single = single;
    }

    @Override
    protected void subscribeActual(SingleObserver<? super T> s) {
        Emitter<T> parent = new Emitter<T>(s);
        s.onSubscribe(parent);

        if (cachedValue != null) {
            try {
                parent.onSuccess(cachedValue);
            } catch (Exception ex) {
                Exceptions.throwIfFatal(ex);
                parent.onError(ex);
            }
        } else {
            parent.setDisposable(single.subscribe(response -> {
                cachedValue = response;
                parent.onSuccess(response);
            }, t -> {
                Exceptions.throwIfFatal(t);
                parent.onError(t);
            }));
        }
    }

    static final class Emitter<T>
            extends AtomicReference<Disposable>
            implements SingleEmitter<T>, Disposable {

        final SingleObserver<? super T> actual;

        Emitter(SingleObserver<? super T> actual) {
            this.actual = actual;
        }


        private static final long serialVersionUID = -2467358622224974244L;

        @Override
        public void onSuccess(T value) {
            if (get() != DisposableHelper.DISPOSED) {
                Disposable d = getAndSet(DisposableHelper.DISPOSED);
                if (d != DisposableHelper.DISPOSED) {
                    try {
                        if (value == null) {
                            actual.onError(new NullPointerException("onSuccess called with null. Null values are generally not allowed in 2.x operators and sources."));
                        } else {

                            actual.onSuccess(value);
                        }
                    } finally {
                        if (d != null) {
                            d.dispose();
                        }
                    }
                }
            }
        }

        @Override
        public void onError(Throwable t) {
            if (!tryOnError(t)) {
                RxJavaPlugins.onError(t);
            }
        }

        @Override
        public boolean tryOnError(Throwable t) {
            if (t == null) {
                t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (get() != DisposableHelper.DISPOSED) {
                Disposable d = getAndSet(DisposableHelper.DISPOSED);
                if (d != DisposableHelper.DISPOSED) {
                    try {
                        actual.onError(t);
                    } finally {
                        if (d != null) {
                            d.dispose();
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        @Override
        public void setDisposable(Disposable d) {
            DisposableHelper.set(this, d);
        }

        @Override
        public void setCancellable(Cancellable c) {
            setDisposable(new CancellableDisposable(c));
        }

        @Override
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}

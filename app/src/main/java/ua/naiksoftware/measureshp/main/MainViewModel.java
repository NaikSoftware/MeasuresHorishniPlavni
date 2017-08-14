package ua.naiksoftware.measureshp.main;

import android.databinding.ObservableField;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import ua.naiksoftware.measureshp.common.BaseViewModel;
import ua.naiksoftware.measureshp.common.rx.SingleCachedComplete;
import ua.naiksoftware.measureshp.model.Px500Photo;
import ua.naiksoftware.measureshp.network.RestClient;

/**
 * Created by naik on 13.08.17.
 */

public class MainViewModel extends BaseViewModel {

    public final ObservableField<String> randomBackground = new ObservableField<>();

    private final Random random = new Random();

    public MainViewModel() {
        manageDisposable(SingleCachedComplete.from(RestClient.getPx500Repository().search("ecology",
                "Abstract,Nature,City & Architecture,Urban Exploration,Journalism,Landscapes",
                1, Px500Photo.SIZE_1600, 1 + random.nextInt(50)))
                .map(px500SearchResponse -> px500SearchResponse.getPhotos().get(random.nextInt(px500SearchResponse.getPhotos().size())))
                .map(px500Photo -> px500Photo.getImages().get(0).getUrl())
                .repeatWhen(objectFlowable -> objectFlowable.delay(15, TimeUnit.SECONDS))
                .retryWhen(throwableFlowable -> throwableFlowable.delay(15, TimeUnit.SECONDS))
                .subscribe(randomBackground::set, Throwable::printStackTrace));
    }
}

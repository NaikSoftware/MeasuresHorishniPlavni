package ua.naiksoftware.measureshp.provider;

import android.support.annotation.StringRes;

import ua.naiksoftware.measureshp.provider.heatenergy.HeatEnergyProvider;
import ua.naiksoftware.measureshp.provider.water.WaterProvider;

/**
 * Created by savchenko_n on 14.08.17.
 */

public abstract class Provider {

    private static Provider[] providers;

    public static Provider[] getProviders() {
        if (providers == null) {
            providers = new Provider[] {
                    new HeatEnergyProvider(),
                    new WaterProvider()
            };
        }
        return providers;
    }

    @StringRes
    public abstract int getTitle();
}

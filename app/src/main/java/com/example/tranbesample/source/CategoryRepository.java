package com.example.tranbesample.source;

import com.example.tranbesample.datas.HomeCategory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import androidx.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class CategoryRepository implements CategoryDataSource{


    private final CategoryDataSource mCategoryRemoteDataSource;
    private final CategoryDataSource mCategoryLocalDataSource;
    private static final  Object mLock = new Object();
    private static CategoryRepository mInstance;
    Map<String, HomeCategory> mCachedCategories;
    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;
    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param categoryRemoteDataSource the backend data source
     * @param categoryLocalDataSource  the device storage data source
     * @return the {@link CategoryRepository} instance
     */

    public static CategoryRepository getInstance(@NonNull CategoryDataSource categoryRemoteDataSource,
                                                 @Nonnull CategoryDataSource categoryLocalDataSource){
        synchronized (mLock) {
            if (mInstance == null) {
                mInstance = new CategoryRepository(categoryRemoteDataSource, categoryLocalDataSource);
            }
            return mInstance;
        }
    }

    // Prevent direct instantiation.
    private CategoryRepository(@NonNull CategoryDataSource categoryRemoteDataSource,
                               @Nonnull CategoryDataSource categoryLocalDataSource) {
        this.mCategoryRemoteDataSource = checkNotNull(categoryRemoteDataSource);
        this.mCategoryLocalDataSource = checkNotNull(categoryLocalDataSource);
    }


    public static void destroyInstance() {
        mInstance = null;
    }


    @Override
    public void getCategories(@NonNull final LoadCallback callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (mCachedCategories != null && !mCacheIsDirty) {
            callback.onDataLoaded(new ArrayList<>(mCachedCategories.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getCategoryFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mCategoryLocalDataSource.getCategories(new LoadCallback() {

                @Override
                public void onDataLoaded(List<HomeCategory> categories) {
                    refreshCache(categories);
                    callback.onDataLoaded(new ArrayList<>(mCachedCategories.values()));
                }

                @Override
                public void onDataNotAvailable() {
                    getCategoryFromRemoteDataSource(callback);
                }
            });
        }
    }

    @Override
    public void deleteAllCategories() {
        mCategoryRemoteDataSource.deleteAllCategories();
        mCategoryLocalDataSource.deleteAllCategories();

        if (mCachedCategories == null) {
            mCachedCategories = new LinkedHashMap<>();
        }
        mCachedCategories.clear();

    }

    @Override
    public void saveCategory(HomeCategory category) {
        checkNotNull(category);
        mCategoryRemoteDataSource.saveCategory(category);
        mCategoryLocalDataSource.saveCategory(category);

        // Do in memory cache update to keep the app UI up to date
        if (mCachedCategories == null) {
            mCachedCategories = new LinkedHashMap<>();
        }
        mCachedCategories.put(category.name, category);
    }

    @Override
    public void refreshCategories() {
        mCacheIsDirty = true;
    }

    private void getCategoryFromRemoteDataSource(@NonNull final LoadCallback callback) {
        mCategoryRemoteDataSource.getCategories(new LoadCallback() {

            @Override
            public void onDataLoaded(List <HomeCategory> categories) {
                refreshCache(categories);
                refreshLocalDataSource(categories);
                callback.onDataLoaded(new ArrayList<>(mCachedCategories.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<HomeCategory> categories) {
        if (mCachedCategories == null) {
            mCachedCategories = new LinkedHashMap<>();
        }
        mCachedCategories.clear();
        for (HomeCategory category : categories) {
            mCachedCategories.put(category.name, category);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<HomeCategory> categories) {
        mCategoryLocalDataSource.deleteAllCategories();
        for (HomeCategory category : categories) {
            mCategoryLocalDataSource.saveCategory(category);
        }
    }
}

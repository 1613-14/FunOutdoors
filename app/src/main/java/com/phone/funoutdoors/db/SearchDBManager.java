package com.phone.funoutdoors.db;

import android.content.Context;

import com.phone.funoutdoors.bean.DaoMaster;
import com.phone.funoutdoors.bean.DaoSession;
import com.phone.funoutdoors.bean.SearchDB;
import com.phone.funoutdoors.bean.SearchDBDao;

import org.greenrobot.greendao.query.Query;

import java.util.List;

/**
 * Created by Lenovo-SXX on 2016/11/2.
 */
public class SearchDBManager {

    private static SearchDBManager searchDBManager;
    private final SearchDBDao searchDBDao;

    public SearchDBManager(Context context) {
        DaoSession session = DaoMaster.newDevSession(context, "search");
        searchDBDao = session.getSearchDBDao();
    }

    public static SearchDBManager getInstance(Context context) {
        if (searchDBManager == null) {
            searchDBManager = new SearchDBManager(context);
        }
        return searchDBManager;
    }


    public void insertSearch(SearchDB search) {
        searchDBDao.insertOrReplace(search);
    }

    public List<SearchDB> querySearch() {
        Query<SearchDB> build = searchDBDao.queryBuilder().build();
        List<SearchDB> list = build.list();
        return list;
    }

    public void deleteSearch() {
        searchDBDao.deleteAll();
    }

    public List<SearchDB> queryKey(String key) {
        Query<SearchDB> build = searchDBDao.queryBuilder().where(SearchDBDao.Properties.SeachFlag.eq(key)).build();
        List<SearchDB> list = build.list();
        return list;
    }

    public List<SearchDB> querySearchContent(String key) {
        Query<SearchDB> build = searchDBDao.queryBuilder().where(SearchDBDao.Properties.SearchContent.eq(key)).build();
        List<SearchDB> list = build.list();
        return list;
    }
}

package com.phone.funoutdoors.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Lenovo-SXX on 2016/11/2.
 */
@Entity
public class SearchDB {
    @Id(autoincrement = true)
    private long id;
    private String seachFlag;
    private String searchContent;

    public SearchDB(String seachFlag, String searchContent) {
        this.seachFlag = seachFlag;
        this.searchContent = searchContent;
    }

    @Keep
    public SearchDB(long id, String seachFlag, String searchContent) {
        this.id = id;
        this.seachFlag = seachFlag;
        this.searchContent = searchContent;
    }

    @Keep
    public SearchDB() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeachFlag() {
        return this.seachFlag;
    }

    public void setSeachFlag(String seachFlag) {
        this.seachFlag = seachFlag;
    }

    public String getSearchContent() {
        return this.searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

}

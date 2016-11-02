package com.phone.funoutdoors.db;

import android.content.Context;

import com.phone.funoutdoors.bean.DaoMaster;
import com.phone.funoutdoors.bean.DaoSession;
import com.phone.funoutdoors.bean.User;
import com.phone.funoutdoors.bean.UserDao;

import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
public class UserDBManager {
    private static UserDBManager userDBManager = null;
    private UserDao userDao;

    public UserDBManager(Context context) {
        DaoSession daoSession = DaoMaster.newDevSession(context, "quhuwai");
        userDao = daoSession.getUserDao();
    }

    public static UserDBManager getInstance(Context context) {
        if (userDBManager == null) {
            userDBManager = new UserDBManager(context);
        }
        return userDBManager;
    }

    public boolean insertUser(User user) {
        long l = userDao.insertOrReplace(user);
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取数据库中的userId
     *
     * @return
     */
    public long getId() {
        List<User> users = queryAll();
        return users.size() > 0 ? users.get(users.size() - 1).getUser_id() : 0;
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<User> queryAll() {
        QueryBuilder<User> builder = userDao.queryBuilder();
        Query<User> users = builder.build();
        return users.list();
    }

    /**
     * 根据登录账号查找用户
     *
     * @param name
     * @return
     */
    public List<User> findByUser(String name) {
        //userDao.queryRawCreate(name,n)
        QueryBuilder<User> builder = userDao.queryBuilder();
        builder.where(UserDao.Properties.Phone.eq(name));
        return builder.list();
    }

    /**
     * 根据用户查找用户
     *
     * @param id
     * @return
     */
    public List<User> findByUser(Long id) {
        QueryBuilder<User> builder = userDao.queryBuilder();
        builder.where(UserDao.Properties.User_id.eq(id));
        return builder.list();
    }

    public void update(User user) {
        userDao.update(user);
    }

}

package asange.room_test.other.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import asange.room_test.other.db.dao.UserDao;
import asange.room_test.other.db.entity.User;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.3.1
 * @Description
 * @Company Beijing icourt
 * @date createTime：2018/1/15
 */
@Database(entities = {User.class}, version = 1)
public abstract class UserDb extends RoomDatabase {
    public abstract UserDao userDao();
}

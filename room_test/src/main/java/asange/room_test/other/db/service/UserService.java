package asange.room_test.other.db.service;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import asange.room_test.other.db.UserDb;
import asange.room_test.other.db.model.User;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.3.1
 * @Description
 * @Company Beijing icourt
 * @date createTime：2018/1/15
 */
public class UserService {

    UserDb db;

    public UserService(Context context) {
        db = Room.databaseBuilder(context,
                UserDb.class, "database-user").build();
    }

    public List<User> query() {
        return db.userDao().getAll();
    }

    public void insert(User... users) {
        db.userDao().insertAll(users);
    }

}

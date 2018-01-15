package asange.dbtest;

import android.app.Application;

import io.realm.Realm;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.3.1
 * @Description
 * @Company Beijing icourt
 * @date createTime：2018/1/13
 */
public class RealmAppliction extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}

package asange.dbtest;

import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Random;

import asange.dbtest.model.User;
import asange.dbtest.view.UserAdapter;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    static final String realmName = "user.realm";
    UserAdapter userAdapter;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userAdapter = new UserAdapter();
        RecyclerView viewById = findViewById(R.id.recyclerView);
        viewById.setLayoutManager(new LinearLayoutManager(this));
        viewById.setAdapter(userAdapter);

        realm = Realm.getInstance(new RealmConfiguration.Builder()
                .name(realmName)
                .build());
    }

    public void onAddData(View v) {
        Debug.startMethodTracing("onAddData");
        realm.beginTransaction();
        User object = realm.createObject(User.class,String.valueOf(SystemClock.elapsedRealtimeNanos()));
        object.name = " name_" + String.valueOf(SystemClock.elapsedRealtime());
        object.age = new Random().nextInt(100);
        realm.commitTransaction();
        Debug.stopMethodTracing();
        onQueryData(v);
    }

    public void onDelData(View v) {
        final RealmResults<User> all = realm.where(User.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                all.deleteFromRealm(0);
            }
        });

        onQueryData(v);
    }

    public void onQueryData(View v) {
        final RealmResults<User> all = realm.where(User.class).findAll();
        userAdapter.bindData(true, all);
    }

}

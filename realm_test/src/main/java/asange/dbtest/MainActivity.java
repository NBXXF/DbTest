package asange.dbtest;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import asange.dbtest.model.User;
import asange.dbtest.view.UserAdapter;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    static final String realmName = "user3.realm";
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


        final List<User> list=new ArrayList<>();
        final User object = new User();
        object.id = (int) SystemClock.elapsedRealtime();
        object.name = " name_" + String.valueOf(SystemClock.elapsedRealtime());
        object.age = new Random().nextInt(100);


//        final User object2 = new User();
//        object2.id = (int) SystemClock.elapsedRealtime()+3;
//        object2.name = " name_" + String.valueOf(SystemClock.elapsedRealtime());
//        object2.age = new Random().nextInt(100);

        list.add(object);
        //list.add(object2);

        //Debug.startMethodTracing("onAddData");
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(list);
            }
        });
        //Debug.stopMethodTracing();
        //onQueryData(v);
    }

    public void onDelData(View v) {
        final RealmResults<User> all = realm.where(User.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                all.deleteFromRealm(0);
                all.deleteFromRealm(1);
                //all.deleteFromRealm(0);
            }
        });

        onQueryData(v);
    }

    public void onQueryData(View v) {
        final RealmResults<User> all = realm.where(User.class).findAll();
        userAdapter.bindData(true, all);

        realm.close();
    }

}

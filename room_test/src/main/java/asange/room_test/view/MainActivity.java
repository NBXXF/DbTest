package asange.room_test.view;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;
import java.util.concurrent.Callable;

import asange.room_test.R;
import asange.room_test.other.db.model.User;
import asange.room_test.other.db.service.UserService;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    UserAdapter userAdapter;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userAdapter = new UserAdapter();
        RecyclerView viewById = findViewById(R.id.recyclerView);
        viewById.setLayoutManager(new LinearLayoutManager(this));
        viewById.setAdapter(userAdapter);

        userService = new UserService(this);
    }

    public void onAddData(final View v) {
        // Caused by: java.lang.IllegalStateException: Cannot access database on the main thread since it may potentially lock the UI for a long period of time.
        Observable.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                User user = new User((int) SystemClock.elapsedRealtime(),
                        "fname_" + SystemClock.elapsedRealtime(),
                        "lname_" + SystemClock.elapsedRealtime());
                userService.insert(user);
                return user;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        onQueryData(v);
                    }
                });
    }

    public void onDelData(View v) {
        onQueryData(v);
    }

    public void onQueryData(View v) {
        Observable.fromCallable(new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return userService.query();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        userAdapter.bindData(true, users);
                    }
                });
    }
}

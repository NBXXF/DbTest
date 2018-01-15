package asange.dbtest.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.3.1
 * @Description
 * @Company Beijing icourt
 * @date createTime：2018/1/13
 */
public class User extends RealmObject {
    @PrimaryKey
    public String id;
    public String name;
    public int age;
    public String phone;

    public User() {
    }

    public User(String id, String name, int age, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }
}

package asange.dbtest.view;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.asange.recyclerviewadapter.BaseRecyclerAdapter;
import com.asange.recyclerviewadapter.BaseViewHolder;

import asange.dbtest.R;
import asange.dbtest.model.User;

/**
 * @author youxuan  E-mail:xuanyouwu@163.com
 * @version 2.3.1
 * @Description
 * @Company Beijing icourt
 * @date createTime：2018/1/13
 */
public class UserAdapter extends BaseRecyclerAdapter<User> {
    @Override
    public int bindView(int viewType) {
        return R.layout.adapter_item_user;
    }

    @Override
    public void onBindHolder(BaseViewHolder holder, @Nullable User user, int index) {
        TextView textView = holder.obtainView(R.id.userInfo);
        textView.setText(user.toString());
    }
}

package txs.com.chexian.Tools;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import txs.com.chexian.R;

/**
 * Created by Administrator on 2016/12/1.
 */

public class RigisterSuccessDialogFragment extends AppCompatDialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.success_register_layout,null);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle("注册成功，前往登录页面")
                .setPositiveButton("ok",null).create();
    }
}

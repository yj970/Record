package mvp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.Selection;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yj.record.R;

import java.io.Serializable;

import mvp.Constant;
import mvp.m.Record;
import utils.CommonUtil;

public class EditExperienceDialogFragment extends DialogFragment{
    Record record;

    public static EditExperienceDialogFragment getInstance(Record record) {
        EditExperienceDialogFragment fragment = new EditExperienceDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.RECORD, (Serializable) record);
        fragment.setArguments(bundle);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getArguments() != null) {
            record = (Record) getArguments().getSerializable(Constant.RECORD);
        }
        Dialog dialog = new Dialog(getContext(), R.style.Edit_experience_dialog);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_experience, null, false);
        final EditText etRemarks = (EditText) view.findViewById(R.id.et);
        etRemarks.setText(record.getMsg());
        etRemarks.setSelection(etRemarks.getText().length());
        Button btnSave = (Button) view.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                record.setMsg(etRemarks.getText().toString());
                boolean isSuccess = record.save();
                Toast.makeText(getContext(), isSuccess ? "保存成功":"保存失败", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        view.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        // 如果输入框内容为空字符，则弹出软键盘
        // 这里必须延迟弹出，否则无法弹出软键盘
        if (TextUtils.isEmpty(etRemarks.getText())) {
            etRemarks.postDelayed(new Runnable() {
                @Override
                public void run() {
                    InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.showSoftInput(etRemarks, 0);
                }
            }, 500);

        }


        //设置取消标题
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置点击外部不可以消失
        dialog.setCanceledOnTouchOutside(false);
        //设置即使点击返回键也不会退出
        setCancelable(false);

        dialog.setContentView(view);

        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置软键盘弹出模式
        dialogWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.gravity = Gravity.CENTER;
        //设置Dialog宽度匹配屏幕宽度
        lp.width = CommonUtil.dp2px(getContext(), 300);
        //设置Dialog高度自适应
        lp.height = CommonUtil.dp2px(getContext(), 400);
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);

        return dialog;
    }
}

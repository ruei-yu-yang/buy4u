package com.rueiyu.buy4u;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by RueiYu on 2018/2/5.
 */

public class GroupDialogFragment extends DialogFragment {

    public interface OnGroupNameListener{
        void groupNameInputCompleted(String name);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.group_name, null);
        final EditText groupName = view.findViewById(R.id.group_name);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = groupName.getText().toString();
                        OnGroupNameListener listener = (OnGroupNameListener) getActivity();
                        listener.groupNameInputCompleted(name);
                    }
                })
                .setNeutralButton("Cancel",null);
        return builder.create();
    }
}

package maxian.milos.activitystate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


public class TextEntryDialogFragment extends DialogFragment {

    public interface TextEntryDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog, String text);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // Use this instance of the interface to deliver action events
    TextEntryDialogListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (TextEntryDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + R.string.exceptionText);
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.textentry_dialog, null);

        builder.setView(dialogView)
                .setTitle(R.string.dialogTitle)
                .setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et = dialogView.findViewById(R.id.editText);
                        String text = et.getText().toString();
                        // Send the positive button event back to the host activity
                        mListener.onDialogPositiveClick(TextEntryDialogFragment.this, text);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Send the negative button event back to the host activity
                        mListener.onDialogNegativeClick(TextEntryDialogFragment.this);
                    }
                });

        return builder.create();
    }
}

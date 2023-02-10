package eg.gov.iti.jets.foodplanner;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class MyDialog {
    public static AlertDialog.Builder myDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage("Do you want to exit ?");

        builder.setTitle("Alert !");

        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
           // finish();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        return  builder;
    }
}

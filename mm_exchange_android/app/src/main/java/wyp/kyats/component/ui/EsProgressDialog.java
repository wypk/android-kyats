package wyp.kyats.component.ui;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;

import wyp.kyats.R;

public class EsProgressDialog {

    private static EsProgressDialog esProgressDialog;
    private Dialog mDialog;

    private EsProgressDialog() {
    }

    public static EsProgressDialog getInstance() {

        if (esProgressDialog == null) {
            esProgressDialog = new EsProgressDialog();
        }
        return esProgressDialog;
    }

    public void showProgressDialog(Context mContext) {

        mDialog = new Dialog(mContext);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.view_progress_dialog);

        LottieAnimationView lottieAnimationView = mDialog.findViewById(R.id.progressAnimation);
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(animation -> lottieAnimationView.setProgress((Float) animation.getAnimatedValue()));
        animator.start();

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    public void hideProgressDialog() {

        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}

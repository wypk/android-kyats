package wyp.kyats.component.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import wyp.kyats.component.android.NetworkStatusBroadcastReceiver;
import wyp.kyats.component.util.Logger;

public abstract class BaseActivity extends AppCompatActivity implements NetworkStatusBroadcastReceiver.NetworkStatusListener {

    private NetworkStatusBroadcastReceiver networkStatusBroadcastReceiver = new NetworkStatusBroadcastReceiver(this, this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(this.getLayoutFileId());
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ButterKnife.bind(this);
    }

    protected abstract int getLayoutFileId();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {

        super.onPause();
        this.networkStatusBroadcastReceiver.unregisterFromContext();
    }

    @Override
    protected void onResume() {

        super.onResume();
        this.networkStatusBroadcastReceiver.registerToContext();
    }

    @Override
    public void onInternetAvailable() {
        Logger.d(this.getClass(), "onInternetAvailable");
    }

    @Override
    public void onInternetUnavailable() {
        Logger.d(this.getClass(), "onInternetUnavailable");
    }
}

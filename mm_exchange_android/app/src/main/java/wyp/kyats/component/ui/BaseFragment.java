package wyp.kyats.component.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    private View fragmentView = null;
    private EsProgressDialog progressDialog = EsProgressDialog.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (null == this.fragmentView) {
            this.fragmentView = inflater.inflate(this.getLayoutXmlId(), container, false);
            ButterKnife.bind(this, this.fragmentView);
            this.createView();
        }
        return this.fragmentView;
    }

    protected abstract void createView();

    protected abstract int getLayoutXmlId();

    public View getFragmentView() {
        return this.fragmentView;
    }

    public void showProgressBar() {
        progressDialog.showProgressDialog(getActivity());
    }

    public void hideProgressBar() {
        progressDialog.hideProgressDialog();
    }

    public void onInternetAvailable(BaseActivity parentActivity) {
        parentActivity.onInternetAvailable();
    }

    public void onInternetUnavailable(BaseActivity parentActivity) {
        parentActivity.onInternetUnavailable();
    }
}

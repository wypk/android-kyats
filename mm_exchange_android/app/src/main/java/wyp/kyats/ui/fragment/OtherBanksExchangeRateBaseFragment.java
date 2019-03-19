package wyp.kyats.ui.fragment;

import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import wyp.kyats.R;
import wyp.kyats.component.ui.BaseFragment;

/**
 * Created by Wai Yan on 3/19/19.
 */
public abstract class OtherBanksExchangeRateBaseFragment extends BaseFragment {

    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.lblUpdatedDate)
    AppCompatTextView lblUpdatedDate;

    @BindView(R.id.rvExchange)
    RecyclerView rvExchange;

    @BindView(R.id.rlMainLayout)
    RelativeLayout rlMainLayout;

    @Override
    protected int getLayoutXmlId() {
        return R.layout.fragment_others_banks_exchange_rate;
    }

    @Override
    protected void createView() {

        this.getLatestExchangeRate();
        this.onRefresh();
        this.showOnUI();
    }

    private void onRefresh() {

        swipeContainer.setColorSchemeResources(
                android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark,
                android.R.color.holo_red_dark);

        swipeContainer.setOnRefreshListener(this::getLatestExchangeRate);
    }

    public void stopLoading() {
        swipeContainer.setRefreshing(false);
    }

    public abstract void getLatestExchangeRate();

    public abstract void showOnUI();
}

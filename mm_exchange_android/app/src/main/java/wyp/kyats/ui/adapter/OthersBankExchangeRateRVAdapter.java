package wyp.kyats.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.io.Serializable;

import androidx.appcompat.widget.AppCompatTextView;
import wyp.kyats.R;
import wyp.kyats.component.ui.BaseRVAdapter;

public class OthersBankExchangeRateRVAdapter
        extends BaseRVAdapter<OthersBankExchangeRateRVAdapter.Model> {

    private RateClickListener rateClickListener;

    public OthersBankExchangeRateRVAdapter(Context context,
            OnViewHolderClickListener<Model> viewHolderClickListener,
            RateClickListener rateClickListener) {
        super(context, viewHolderClickListener);
        this.rateClickListener = rateClickListener;
    }

    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater != null ?
                inflater.inflate(R.layout.item_view_other_banks_exchange_rate, viewGroup, false) :
                null;
    }

    @Override
    protected void bindView(BaseRVAdapter.ViewHolder viewHolder, Model item) {

        AppCompatTextView lblCurrency = viewHolder.itemView.findViewById(R.id.lblCurrency);
        AppCompatTextView txvBuyRate = viewHolder.itemView.findViewById(R.id.txvBuyRate);
        AppCompatTextView txvSellRate = viewHolder.itemView.findViewById(R.id.txvSellRate);

        lblCurrency.setText(item.currency);
        txvBuyRate.setText(item.buyRate);
        txvSellRate.setText(item.sellRate);

        LinearLayout llBuyRate = viewHolder.itemView.findViewById(R.id.llBuyRate);
        LinearLayout llSellRate = viewHolder.itemView.findViewById(R.id.llSellRate);

        llBuyRate.setOnClickListener(v -> this.rateClickListener.onItemClick(item.buyRate));

        llSellRate.setOnClickListener(v -> this.rateClickListener.onItemClick(item.sellRate));
    }

    public static class Model implements Serializable {

        public String currency;
        public String buyRate;
        public String sellRate;

        public Model(String currency,
                String buyRate,
                String sellRate) {

            this.currency = currency;
            this.buyRate = buyRate;
            this.sellRate = sellRate;
        }
    }

    public interface RateClickListener {

        void onItemClick(String rate);
    }
}

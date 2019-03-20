/*
 * Copyright 2019 Wai Yan - (09 97777 3 444).
 * All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package wyp.kyats.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import wyp.kyats.R;
import wyp.kyats.component.ui.BaseRVAdapter;

public class CentralBankExchangeRateRVAdapter
        extends BaseRVAdapter<CentralBankExchangeRateRVAdapter.Model> {

    public CentralBankExchangeRateRVAdapter(Context context,
            OnViewHolderClickListener<Model> viewHolderClickListener) {
        super(context, viewHolderClickListener);
    }

    @Override
    protected View createView(Context context, ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater != null ?
                inflater.inflate(R.layout.item_view_central_bank_exchange_rate, viewGroup, false) :
                null;
    }

    @Override
    protected void bindView(BaseRVAdapter.ViewHolder viewHolder, Model item) {

        AppCompatImageView imvFlag = viewHolder.itemView.findViewById(R.id.imvFlag);
        AppCompatTextView lblCurrency = viewHolder.itemView.findViewById(R.id.lblCurrency);
        AppCompatTextView lblExchangeRate = viewHolder.itemView.findViewById(R.id.txvExchangeRate);
        AppCompatTextView lblCurrencyLongTerm = viewHolder.itemView.findViewById(R.id.lblCurrencyLongTerm);

        imvFlag.setImageResource(item.flag);
        lblCurrency.setText(item.currency);
        lblExchangeRate.setText(String.valueOf(item.exchangeRate));
        lblCurrencyLongTerm.setText(item.currencyLongTerm);
    }

    public static class Model implements Serializable {

        public int flag;
        public String currency;
        public String exchangeRate;
        public String currencyLongTerm;

        public Model(@DrawableRes int flag,
                String currency,
                String exchangeRate,
                String currencyLongTerm) {

            this.flag = flag;
            this.currency = currency;
            this.exchangeRate = exchangeRate;
            this.currencyLongTerm = currencyLongTerm;
        }
    }
}

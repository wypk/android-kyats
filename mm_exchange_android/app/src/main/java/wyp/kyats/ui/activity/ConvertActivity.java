package wyp.kyats.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import wyp.kyats.R;
import wyp.kyats.cache.app.AppInfoStorage;
import wyp.kyats.component.ui.BaseActivity;
import wyp.kyats.ui.adapter.CentralBankExchangeRateRVAdapter;

public class ConvertActivity extends BaseActivity {

    private static final int MAX_CURRENCY_LENGTH = 15;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.btnDel)
    AppCompatImageButton btnDel;

    @BindView(R.id.imvOtherFlag)
    AppCompatImageView imvOtherFlag;

    @BindView(R.id.lblOtherCurrency)
    AppCompatTextView lblOtherCurrency;

    @BindView(R.id.lblOtherCurrencyLongTerm)
    AppCompatTextView lblOtherCurrencyLongTerm;

    @BindView(R.id.txvOtherExchangeRate)
    AppCompatTextView txvOtherExchangeRate;

    @BindView(R.id.txvMyanmarExchangeRate)
    AppCompatTextView txvMyanmarExchangeRate;

    @BindView(R.id.rlOtherCurrency)
    RelativeLayout rlOtherCurrency;

    @BindView(R.id.rlMyanmarCurrency)
    RelativeLayout rlMyanmarCurrency;

    private CentralBankExchangeRateRVAdapter.Model model;
    private BigDecimal rate;
    private String tempString = "";
    private Exchange exchange = Exchange.MM;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(findViewById(R.id.toolbar));
        toolbar.setNavigationIcon(R.drawable.ic_back_arrow);
        toolbar.setNavigationOnClickListener(view -> this.onBackPressed());

        model = (CentralBankExchangeRateRVAdapter.Model) getIntent().getSerializableExtra("model");

        imvOtherFlag.setImageResource(model.flag);
        lblOtherCurrency.setText(model.currency);
        lblOtherCurrencyLongTerm.setText(model.currencyLongTerm);

        this.setDefaultConverter();

        rlOtherCurrency.setOnClickListener(view -> this.switchOtherToMm());

        rlMyanmarCurrency.setOnClickListener(view -> this.switchMmToOther());

        btnDel.setOnLongClickListener(view -> {
            this.setDefaultConverter();
            return true;
        });
    }

    @Override
    protected int getLayoutFileId() {
        return R.layout.activity_convert;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.convert_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                this.resetConverter();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setDefaultConverter() {

        this.tempString = "";
        this.rate = new BigDecimal(model.exchangeRate.replaceAll(",", ""));
        this.switchOtherToMm();

        if (AppInfoStorage.getInstance().getDefaultConvertValue() != null) {
            txvOtherExchangeRate.setText(AppInfoStorage.getInstance().getDefaultConvertValue());
            txvMyanmarExchangeRate.setText(NumberFormat.getNumberInstance(Locale.US).format(this.rate.multiply(new BigDecimal(txvOtherExchangeRate.getText().toString().replaceAll(",", ""))).setScale(5, RoundingMode.CEILING)));
        } else {
            this.resetConverter();
        }
    }

    private void resetConverter() {
        txvOtherExchangeRate.setText("1.0");
        txvMyanmarExchangeRate.setText(model.exchangeRate);
        this.switchOtherToMm();
    }

    private void switchOtherToMm() {
        txvOtherExchangeRate.setTextColor(Color.parseColor("#f9a825"));
        txvMyanmarExchangeRate.setTextColor(Color.parseColor("#000000"));
        this.tempString = "";
        this.exchange = Exchange.OTHER;
    }

    private void switchMmToOther() {
        txvMyanmarExchangeRate.setTextColor(Color.parseColor("#f9a825"));
        txvOtherExchangeRate.setTextColor(Color.parseColor("#000000"));
        this.tempString = "";
        this.exchange = Exchange.MM;
    }

    public void onClickOne(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1) {
            this.changeText(tempString = tempString.substring(1) + "1");
        } else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "1");
    }

    public void onClickTwo(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1)
            this.changeText(tempString = tempString.substring(1) + "2");
        else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "2");
    }

    public void onClickThree(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1)
            this.changeText(tempString = tempString.substring(1) + "3");
        else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "3");
    }

    public void onClickFour(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1)
            this.changeText(tempString = tempString.substring(1) + "4");
        else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "4");
    }

    public void onClickFive(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1)
            this.changeText(tempString = tempString.substring(1) + "5");
        else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "5");
    }

    public void onClickSix(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1)
            this.changeText(tempString = tempString.substring(1) + "6");
        else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "6");
    }

    public void onClickSeven(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1)
            this.changeText(tempString = tempString.substring(1) + "7");
        else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "7");
    }

    public void onClickEight(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1)
            this.changeText(tempString = tempString.substring(1) + "8");
        else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "8");
    }

    public void onClickNine(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1)
            this.changeText(tempString = tempString.substring(1) + "9");
        else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "9");
    }

    public void onClickZero(View view) {
        if (tempString.startsWith("0") && tempString.length() == 1)
            this.changeText(tempString = "0");
        else if (tempString.length() <= MAX_CURRENCY_LENGTH) this.changeText(tempString += "0");
    }

    public void onClickPoint(View view) {
        if (!tempString.contains(".")) {
            if (tempString.isEmpty()) {
                this.changeText(tempString = "0.");
            } else if (tempString.length() <= MAX_CURRENCY_LENGTH) {
                this.changeText(tempString += ".");
            }
        }
        if (tempString.startsWith(".") || tempString.isEmpty()) {
            this.changeText(tempString = "0.");
        }
    }

    public void onClickDelete(View view) {
        if (!tempString.isEmpty()) {
            this.changeText(tempString = tempString.substring(0, tempString.length() - 1));
        }
        if (tempString.isEmpty()) {
            this.resetConverter();
        }
    }

    @SuppressLint("SetTextI18n")
    private void changeText(String str) {

        switch (exchange) {
            case MM:
                txvMyanmarExchangeRate.setText(str);
                if (!str.isEmpty())
                    txvOtherExchangeRate.setText(NumberFormat.getNumberInstance(Locale.US).format(new BigDecimal(txvMyanmarExchangeRate.getText().toString()).divide(this.rate, 5, RoundingMode.CEILING)));
                break;
            case OTHER:
                txvOtherExchangeRate.setText(str);
                if (!str.isEmpty())
                    txvMyanmarExchangeRate.setText(NumberFormat.getNumberInstance(Locale.US).format(this.rate.multiply(new BigDecimal(txvOtherExchangeRate.getText().toString().replaceAll(",", ""))).setScale(5, RoundingMode.CEILING)));
                break;
        }
    }

    private enum Exchange {
        OTHER, MM
    }

}

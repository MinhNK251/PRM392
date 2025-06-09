package com.example.retrofit;

import android.os.Bundle;
import android.widget.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.retrofit.api.ApiService;
import com.example.retrofit.model.Currency;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvTerms, tvPrivacy, tvTimestamp, tvSource, tvUsdVnd;
    private Button btnCallApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvTerms = findViewById(R.id.tvTerms);
        tvPrivacy = findViewById(R.id.tvPrivacy);
        tvTimestamp = findViewById(R.id.tvTimestamp);
        tvSource = findViewById(R.id.tvSource);
        tvUsdVnd = findViewById(R.id.tvUsdVnd);
        btnCallApi = findViewById(R.id.btnCallApi);
        btnCallApi.setOnClickListener(v -> {clickCallApi();});

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void clickCallApi(){
        // Link API:https://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
        ApiService.apiService.convertUsdToVnd("843d4d34ae72b3882e3db642c51e28e6", "VND", "USD", 1).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Toast.makeText(MainActivity.this, "Call API success", Toast.LENGTH_SHORT).show();
                Currency currency = response.body();
                if(currency != null && currency.isSuccess()){
                    tvTerms.setText(currency.getTerms());
                    tvPrivacy.setText(currency.getPrivacy());
                    tvTimestamp.setText(String.valueOf(currency.getTimestamp()));
                    tvSource.setText(currency.getSource());
                    tvUsdVnd.setText(String.valueOf(currency.getQuotes().getUsdVnd()));
                }
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Call API failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
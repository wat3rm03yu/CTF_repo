package com.example.ctf1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/* loaded from: classes.dex */
public class Main2Activity extends AppCompatActivity {
    String apiKey;
    String apiPath;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.apiKey = decrypt(getString(R.string.encrypted_api_key), "#TODO");
        this.apiPath = "/api.php?documentid={}&apikey=" + this.apiKey;
    }

    protected String decrypt(String encrypted_key, String encryption_key) {
        return "NOT_IMPLEMENTED";
    }
}
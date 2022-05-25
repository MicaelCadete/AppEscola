package com.example.escola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtN1, edtN2;
    TextView txtM, txtSit;
    LinearLayout layResult;
    ImageView imgSit;
    InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtN1 = findViewById(R.id.edtN1);
        edtN2 = findViewById(R.id.edtN2);
        txtM = findViewById(R.id.txtM);
        txtSit = findViewById(R.id.txtSit);
        layResult = findViewById(R.id.layResult);
        imgSit = findViewById(R.id.imgSit);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        layResult.setVisibility(View.INVISIBLE);
    }

    public void calcular(View view) {
        boolean ok = true;
        if (edtN1.getText().toString().trim().isEmpty()) {
            ok = false;
            edtN1.setError(getString(R.string.msgErro));
        }
        if (edtN2.getText().toString().trim().isEmpty()) {
            ok = false;
            edtN2.setError(getString(R.string.msgErro));
        }
        if (ok) {
            imm.hideSoftInputFromWindow(edtN1.getWindowToken(), 0);
            layResult.setVisibility(View.VISIBLE);
            // FAZENDO OS CALCULOS
            float n1 = Float.parseFloat(edtN1.getText().toString());
            float n2 = Float.parseFloat(edtN2.getText().toString());
            float m = (n1 + n2) / 2;

            // RESULTADO MÉDIA
            txtM.setText(String.format("%.1f", m));

            //RESULTADO SITUAÇÃO
            if (m < 5) {
                txtSit.setText(getString(R.string.strSitRep));
                txtSit.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgRp) , Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojireprovado);
            } else if (m < 7) {
                txtSit.setText(getString(R.string.strSitRec));
                txtSit.setTextColor(getResources().getColor(R.color.corRecuperacao));
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgRec) , Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.sad);
            } else {
                txtSit.setText(getString(R.string.strSitAp));
                txtSit.setTextColor(Color.parseColor("#0e801b"));
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgAp) , Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojifeliz);
            }
        }
    }
}
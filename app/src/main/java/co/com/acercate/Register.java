package co.com.acercate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {

    TextView tvBack;
    ProgressDialog progressDialog;
    Button bRegister;
    EditText etUser;
    EditText etPassword;
    EditText etId;
    CheckBox cbProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).hide();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.loading));
        progressDialog.setProgress(10);
        progressDialog.setMax(100);
        progressDialog.setMessage(getString(R.string.loading));
        new MyTask().execute();
        progressDialog.dismiss();

        tvBack = findViewById(R.id.tvBack);
        bRegister = findViewById(R.id.bRegister);
        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);
        etId = findViewById(R.id.etId);
        cbProvider = findViewById(R.id.cbProvider);

        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!etUser.getText().toString().equals("") && !etPassword.getText().toString().equals("") && !etId.getText().toString().equals("")) {
                    String url = "http://cocinaparaelalma.co/acercate/addusuario.php";

                    StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        String mensaje = obj.getString("mensaje");
                                        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                                        if (mensaje.equals("Nuevo usuario registrado")) {
                                            Register.this.finish();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d(getString(R.string.Error), error.toString());
                                    Toast.makeText(getApplicationContext(), "Se ha presentado un error y el usuario no se a registrado", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("usuario", etUser.getText().toString());
                            params.put("clave", etPassword.getText().toString());
                            params.put("razon_social", etId.getText().toString());
                            int provider = cbProvider.isChecked() ? 1 : 0;
                            params.put("prestador", "" + provider);
                            return params;
                        }
                    };

                    Volley.newRequestQueue(Register.this).add(strRequest);
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @SuppressLint("StaticFieldLeak")
    public class MyTask extends AsyncTask<Void, Void, Void> {
        public void onPreExecute() {
            progressDialog.show();
        }

        public Void doInBackground(Void... unused) {
            return null;
        }
    }
}
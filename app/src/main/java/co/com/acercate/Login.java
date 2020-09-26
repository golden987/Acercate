package co.com.acercate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Login extends AppCompatActivity {

    TextView tvRegister;
    Button bLogin;
    ProgressDialog progressDialog;
    EditText etUser;
    EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences("shared_login_data", Context.MODE_PRIVATE);
        String cod = prefs.getString("cod", "");
        String usuario = prefs.getString("usuario", "");
        String razon_social = prefs.getString("razon_social", "");

        Log.d("*****", cod + " - " + usuario + " - "+ razon_social);
        if (cod == null) {
            throw new AssertionError();
        }
        if (usuario == null) {
            throw new AssertionError();
        }
        if (razon_social == null) {
            throw new AssertionError();
        }
        if (!cod.equals("") && !usuario.equals("") && !razon_social.equals("")) {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Objects.requireNonNull(getSupportActionBar()).hide();


        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.loading));
        progressDialog.setProgress(10);
        progressDialog.setMax(100);
        progressDialog.setMessage(getString(R.string.loading));
        new MyTask().execute();
        progressDialog.dismiss();

        tvRegister = findViewById(R.id.tvRegister);
        bLogin = findViewById(R.id.bLogin);
        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPassword);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!etUser.getText().toString().equals("") && !etPassword.getText().toString().equals("")) {
                    String url = "http://cocinaparaelalma.co/acercate/loginusuario.php";

                    StringRequest strRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject obj = new JSONObject(response);
                                        JSONArray json_array = obj.optJSONArray("items");
                                        assert json_array != null;
                                        JSONObject data = json_array.getJSONObject(0);
                                        String cod = data.getString("cod");
                                        String clave = data.getString("clave");
                                        String usuario = data.getString("usuario");
                                        String razon_social = data.getString("razon_social");
                                        if (!cod.equals("") && !usuario.equals("") && !clave.equals("") && !razon_social.equals("")) {
                                            SharedPreferences prefs = getSharedPreferences("shared_login_data", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = prefs.edit();
                                            editor.putString("cod", cod);
                                            editor.putString("usuario", usuario);
                                            editor.putString("razon_social", razon_social);
                                            editor.apply();
                                            Intent intent = new Intent(Login.this, MainActivity.class);
                                            startActivity(intent);
                                            Login.this.finish();
                                        }
                                    } catch (JSONException e) {
                                        Log.d("*******", e.toString());
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d(getString(R.string.Error), error.toString());
                                    Toast.makeText(getApplicationContext(), "Se ha presentado un error, vuelve a intentarlo mas tarde.", Toast.LENGTH_SHORT).show();
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("usuario", etUser.getText().toString());
                            params.put("clave", etPassword.getText().toString());
                            return params;
                        }
                    };

                    Volley.newRequestQueue(Login.this).add(strRequest);
                } else {
                    Toast.makeText(getApplicationContext(), "Por favor ingrese todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
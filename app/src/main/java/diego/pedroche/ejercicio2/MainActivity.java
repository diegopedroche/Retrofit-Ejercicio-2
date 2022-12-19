package diego.pedroche.ejercicio2;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.Toast;


import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import diego.pedroche.ejercicio2.adapters.UsersAdapter;
import diego.pedroche.ejercicio2.conexiones.ApiConexiones;
import diego.pedroche.ejercicio2.conexiones.RetrofitObject;
import diego.pedroche.ejercicio2.databinding.ActivityMainBinding;
import diego.pedroche.ejercicio2.modelos.DataItem;
import diego.pedroche.ejercicio2.modelos.General;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<DataItem> users;
    private UsersAdapter adapter;
    private RecyclerView.LayoutManager la;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        users = new ArrayList<>();
        adapter = new UsersAdapter(users,this, R.layout.user_view_holder);
        la = new LinearLayoutManager(this);
        binding.contentMain.contenedor.setAdapter(adapter);
        binding.contentMain.contenedor.setLayoutManager(la);

        doGetUsers();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void doGetUsers() {
        Retrofit retrofit = RetrofitObject.getConnection();
        ApiConexiones api = retrofit.create(ApiConexiones.class);

        Call<General> getUsers = api.getUsers();
        getUsers.enqueue(new Callback<General>() {
            @Override
            public void onResponse(Call<General> call, Response<General> response) {
                if (response.code() == HttpURLConnection.HTTP_OK){
                    General temp = response.body();
                    users.addAll(temp.getData());
                    adapter.notifyItemRangeInserted(0,users.size());
                    Toast.makeText(MainActivity.this, "holaaa?"+users.size() ,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<General> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
                Log.e("Failure",t.getLocalizedMessage());
            }
        });
    }
}
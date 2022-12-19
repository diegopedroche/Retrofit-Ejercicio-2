package diego.pedroche.ejercicio2.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.HttpURLConnection;
import java.util.List;

import diego.pedroche.ejercicio2.R;
import diego.pedroche.ejercicio2.conexiones.ApiConexiones;
import diego.pedroche.ejercicio2.conexiones.RetrofitObject;
import diego.pedroche.ejercicio2.modelos.DataItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserVH> {
    private List<DataItem> objects;
    private Context context;
    private int resource;

    public UsersAdapter(List<DataItem> objects, Context context, int resource) {
        this.objects = objects;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public UsersAdapter.UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(context).inflate(resource,null);
        userView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new UserVH(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UserVH holder, int position) {
        DataItem a = objects.get(position);
        holder.lbUser.setText(a.getId()+"");
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class UserVH extends RecyclerView.ViewHolder{

        TextView lbUser;
        public UserVH(@NonNull View itemview) {
            super(itemview);
            lbUser = itemview.findViewById(R.id.lbUser);
        }
    }
}

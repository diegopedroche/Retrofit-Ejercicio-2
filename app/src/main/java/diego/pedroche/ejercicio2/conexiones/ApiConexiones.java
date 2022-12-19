package diego.pedroche.ejercicio2.conexiones;

import java.util.ArrayList;

import diego.pedroche.ejercicio2.modelos.DataItem;
import diego.pedroche.ejercicio2.modelos.General;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiConexiones {
    @GET("/api/users")
    Call<General> getUsers();

    @DELETE("/api/users/{id}")
    Call<DataItem> deleteUser(@Path("id") String idAlbum);
}

package diego.pedroche.ejercicio2.conexiones;

import diego.pedroche.ejercicio2.configuraciones.Constantes;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitObject {
    public static Retrofit getConnection(){
        return new Retrofit.Builder().baseUrl(Constantes.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }
}

package com.example.fmli_app.Fragment.Service;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fmli_app.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ServiceFragment extends Fragment {
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        textView = view.findViewById(R.id.textview);

        LoadUser loadUser = new LoadUser();
        loadUser.execute("http://192.168.43.94");

        return view;
    }

        class User {
            String name, info;
            int id, age;

            public User(int id, String name, int age, String info) {
                this.id = id;
                this.name = name;
                this.age = age;
                this.info = info;
            }

            @NonNull
            @Override
            public String toString() {
                return name + " " + age + " " + info;
            }
        }

        interface UserService{
            @GET("/index.php")
            Call<ArrayList<User>> getUsers();

        }

    class LoadUser extends AsyncTask<String, Integer, ArrayList<User>> {

        @Override
        protected ArrayList<User> doInBackground(String... strings) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(strings[0])
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UserService userService = retrofit.create(UserService.class);
            Call<ArrayList<User>> call = userService.getUsers();
            try {
                Response<ArrayList<User>> response = call.execute();
                return response.body();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<User> users) {
            super.onPostExecute(users);
            if (users != null) {
                String text = "";
                for (User user: users) {
                    text = text + "\n" + user.toString();
                }
                textView.setText(text);
            }
        }
    }


}
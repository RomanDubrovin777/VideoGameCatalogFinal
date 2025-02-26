package com.example.videogamescatalog.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.Navigation;
import com.example.videogamescatalog.Models.User;
import com.example.videogamescatalog.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mAuth = FirebaseAuth.getInstance();
    }

    public void Login(String email, String password, View view)
    {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "login ok", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_infoFragment);

                        } else {
                            Toast.makeText(MainActivity.this, "login failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void Register(String email, String password, String phone)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Register OK", Toast.LENGTH_LONG).show();
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            if (currentUser != null) {
                                String userIdString = currentUser.getUid();
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                                User newuser = new User(phone, email);
                                reference.child(userIdString).setValue(newuser)
                                        .addOnCompleteListener(task1 -> {
                                            if (task1.isSuccessful()) {
                                                Toast.makeText(MainActivity.this, "User data saved", Toast.LENGTH_SHORT).show();
                                            }
                                            else
                                            {
                                                Toast.makeText(MainActivity.this, "Failed to save user data", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "Register failed: ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
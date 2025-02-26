package com.example.videogamescatalog.Fragments;

import android.graphics.Paint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.videogamescatalog.Activities.MainActivity;
import com.example.videogamescatalog.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public LoginFragment() {}

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth=FirebaseAuth.getInstance();
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        EditText email=view.findViewById(R.id.TextViewEmail_FragmentLogin);
        EditText password=view.findViewById(R.id.TextViewPassword_FragmentLogin);
        Button buttonLoginToRegister=view.findViewById(R.id.ButtonLogin_FragmentLogin);
        TextView signUpTextView = view.findViewById(R.id.TextViewRegister_FragmentLogin);
        signUpTextView.setPaintFlags(signUpTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        signUpTextView.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment2);
        });
        buttonLoginToRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (email.getText().toString().isEmpty()||password.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "enter email and password please", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.Login(email.getText().toString(), password.getText().toString(), view);
                }
            }
        });

        return  view;
    }
}
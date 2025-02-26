package com.example.videogamescatalog.Fragments;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.videogamescatalog.Activities.MainActivity;
import com.example.videogamescatalog.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    private FirebaseAuth mAuth;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {}

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        Button buttonReg=(view).findViewById(R.id.ButtonRegister_Fragmentregister);
        TextView password=(view.findViewById(R.id.TextViewPassword_Fragmentregister));
        TextView email=(view.findViewById(R.id.TextViewEmail_Fragmentregister));
        TextView password_again=(view.findViewById(R.id.TextViewPasswordRe_Fragmentregister));
        TextView phone =(view.findViewById(R.id.TextViewPhone_Fragmentregister));
        buttonReg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if (!password.getText().toString().equals(password_again.getText().toString())) {
                    Toast.makeText(getContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                } else if (password.getText().toString().isEmpty() || email.getText().toString().isEmpty() || phone.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }else if (password.length() >20 ){
                    Toast.makeText(getContext(), "Password cannot be more then 20 chars", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (password.length() <6 ){
                    Toast.makeText(getContext(), "Password cannot be less then 6 chars", Toast.LENGTH_SHORT).show();
                    return;
                }else if (phone.length() < 10 || phone.length() > 10) {
                    Toast.makeText(getContext(), "Enter valid phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.Register(email.getText().toString(), password.getText().toString(), phone.getText().toString());
                    Navigation.findNavController(view).navigate(R.id.action_registerFragment2_to_loginFragment);
                }

            }
        });

        return  view;
    }
}
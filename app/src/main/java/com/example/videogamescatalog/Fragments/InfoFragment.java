package com.example.videogamescatalog.Fragments;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.videogamescatalog.Activities.MainActivity;
import com.example.videogamescatalog.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {

    ImageView profilePicture;
    Button buttonUploadPicture;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public InfoFragment() { }

    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();
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

        View view= inflater.inflate(R.layout.fragment_info, container, false);
        TextView user_email=view.findViewById(R.id.TextViewEmail_FragmentInfo);
        TextView user_phone=view.findViewById(R.id.TextViewPhone_FragmentInfo);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        profilePicture = view.findViewById(R.id.profilePicture);

        buttonUploadPicture = view.findViewById(R.id.buttonUploadProfilePicture_FragmentInfo);

        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);
            userRef.addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot)
                {
                    String email = dataSnapshot.child("m_email").getValue(String.class);
                    String phone = dataSnapshot.child("m_phone").getValue(String.class);
                    user_email.setText(email);
                    user_phone.setText(phone);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getContext(), "Failed to read user data", Toast.LENGTH_SHORT).show();
                }
            });

            Button buttonContinue = view.findViewById(R.id.ButtonContinue);
            buttonContinue.setOnClickListener(v -> {
                Navigation.findNavController(view).navigate(R.id.action_infoFragment_to_gamesRescyclerViewFragment);
            });
        }
        profilePicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        buttonUploadPicture.setOnClickListener(v -> {
            Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            galleryLauncher.launch(openGalleryIntent);
        });

        return view;
    }

    private ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    Uri imageUri = result.getData().getData();
                    profilePicture.setImageURI(imageUri);
                }
            }
    );
}
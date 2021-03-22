package com.sametkemalozdemir.instaclonefirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class FeedActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<User> userAccountInfo;
    ArrayList<String>userMail;
    ArrayList<String> userImages;
    ArrayList<String>userComment;
    FeedRecyclerAdapter feedRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        userAccountInfo=new ArrayList<>();
        userMail=new ArrayList<>();
        userImages=new ArrayList<>();
        userComment=new ArrayList<>();

        getDataFromFirestore();

        //Recycle View

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        feedRecyclerAdapter=new FeedRecyclerAdapter(userMail,userImages,userComment);
        recyclerView.setAdapter(feedRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.insta_options_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.add_post){
            Intent intentToUpload=new Intent(FeedActivity.this,UploadActivity.class);
            startActivity(intentToUpload);
        }
        else if(item.getItemId()==R.id.signout){
            firebaseAuth.signOut();
            Intent intentToSignUp=new Intent(FeedActivity.this,SignUpActivity.class);
            startActivity(intentToSignUp);
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    public void getDataFromFirestore(){

        CollectionReference collectionReference=firebaseFirestore.collection("Posts");

        //filterleme   (commenti sadece starry night olanları getirir)
       // collectionReference.whereEqualTo("comment","Starry Night").addSnapshotListener(new EventListener<QuerySnapshot>() {
        // en yenisini ilk başta gösterir azalan sıralamada yani DESCENDING
      //  collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if(error!=null){
                    Toast.makeText(FeedActivity.this,error.getLocalizedMessage().toString(),Toast.LENGTH_SHORT);
                }

                if(value !=null){


                    for(DocumentSnapshot snapshot:value.getDocuments()){
                        Map<String,Object> userData=snapshot.getData();
                        String mail=(String)userData.get("userEmail");
                        String downloadedUrl=(String)userData.get("downloadUrl");
                        String commentUser=(String)userData.get("comment");
                      //  userAccountInfo.add(new User (mail,downloadedUrl,commentUser));
                        userMail.add(mail);
                        userComment.add(commentUser);
                        userImages.add(downloadedUrl);

                        feedRecyclerAdapter.notifyDataSetChanged();

                    }
                }


            }
        });




    }

}
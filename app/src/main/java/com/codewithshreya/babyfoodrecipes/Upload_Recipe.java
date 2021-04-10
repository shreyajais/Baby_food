package com.codewithshreya.babyfoodrecipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class Upload_Recipe extends AppCompatActivity {
ImageView recipeImage;
Uri uri;
EditText txt_name_chef, txt_description, txt_location;
String imageUrl;//Image that we download from the gallery
    Uri urlImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__recipe);

        recipeImage = (ImageView)findViewById(R.id.iv_foodImage);
        //connecting xml to java

        txt_name_chef = (EditText)findViewById(R.id.txt_name_recipe);
        //txt_ingredients = (EditText)findViewById(R.id.txt_ingredients);
        txt_description = (EditText)findViewById(R.id.txt_description);
        txt_location = (EditText)findViewById(R.id.txt_location);

    }
public void btnSelectImage(View view)
{
    //selecting image from gallary to the app
    Intent photoPicker = new Intent(Intent.ACTION_PICK);
    photoPicker.setType("image/*");
    startActivityForResult(photoPicker, 1);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //setting data image selected to the image view
        if(resultCode == RESULT_OK)
        {
            uri = data.getData();
            recipeImage.setImageURI(uri);
        }
        else
            Toast.makeText(this, "You have not selected any image of your recipe", Toast.LENGTH_SHORT).show();
    }
    public void uploadImage()
    {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("RecipesImage").child(uri.getLastPathSegment());
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Uploading.....");
        progressDialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete())
                    urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();


            }
        });
    }

    public void btnUploadRecipe(View view) {
        uploadImage();
    }
    //upload and save recipe
    public void uploadRecipe()
    {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Upload");
        progressDialog.show();


        String myCurrentDataTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());


        FoodData foodData = new FoodData(txt_name_chef.getText().toString(),
        txt_description.getText().toString(),
        txt_location.getText().toString(),
        imageUrl);

        //String myChef = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
        FirebaseDatabase.getInstance().getReference("Recipe")
        .child(myCurrentDataTime).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Upload_Recipe.this, "Recipe Uploaded", Toast.LENGTH_SHORT);
                    progressDialog.dismiss();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Recipe.this,"Failed", Toast.LENGTH_SHORT);
                progressDialog.dismiss();
            }
        });
    }
}
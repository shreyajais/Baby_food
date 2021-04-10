package com.codewithshreya.babyfoodrecipes;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.codewithshreya.babyfoodrecipes.FoodData;
import com.codewithshreya.babyfoodrecipes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

//package com.codewithshreya.babyfoodrecipes;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import java.text.DateFormat;
//import java.util.Calendar;
//
//public class Update_Activity extends AppCompatActivity {
//    TextView chefDescription;
//
//    TextView ingredientsDescription;
//    TextView locationDescription;
//    TextView foodDescription;
//    ImageView foodImage;
//    String key = "";
//    String imageUrl = "";
//    ImageView recipeImage;
//    Uri uri;
//    String key, oldImageUrl;
//    DatabaseReference databaseReference;
//    StorageReference storageReference;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update_);
//        chefDescription = (TextView)findViewById(R.id.txtMomName);
//        ingredientsDescription = (TextView)findViewById(R.id.txtIngredient);
//        foodDescription = (TextView)findViewById(R.id.txtDescription);
//        foodImage = (ImageView)findViewById(R.id.ivImage2);
//        locationDescription = (TextView)findViewById(R.id.txtLocation);
//
//        Bundle bundle = getIntent().getExtras();
//        if(bundle!=null)
//        {
//            Glide.with(Update_Activity.this).load(bundle.get("oldImageUrl")).into(foodImage);
//            chefDescription.setText(bundle.getString("recipeNameKey"));
//            ingredientsDescription.setText(bundle.getString("ingredientsKey"));
//            foodDescription.setText(bundle.getString("descriptionKey"));
//            locationDescription.setText(bundle.getString("locationKey"));
//            key = bundle.getString("key");
//            oldImageUrl = bundle.getString("oldImageUrl");
//
//        }
//
//        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe").child(key);
//        storageReference  = FirebaseStorage.getInstance().getReference().child("RecipesImage").child(uri.getLastPathSegment());
//    }
//
//
//    public void btnSelectImage(View view) {
//        Intent photoPicker = new Intent(Intent.ACTION_PICK);
//        photoPicker.setType("image/*");
//        startActivityForResult(photoPicker, 1);
//    }
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        //setting data image selected to the image view
//        if(resultCode == RESULT_OK)
//        {
//            uri = data.getData();
//            recipeImage.setImageURI(uri);
//        }
//        else
//            Toast.makeText(this, "You have not selected any image of your recipe", Toast.LENGTH_SHORT).show();
//    }
//
//    public void btnUpdateRecipe(View view) {
//        String recipeName = chefDescription.getText().toString().trim();
//        String recipeDescription = foodDescription.getText().toString().trim();
//        String recipeIngredients = ingredientsDescription.getText().toString().trim();
//        String recipeLocation = locationDescription.getText().toString().trim();
//        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("RecipesImage").child(uri.getLastPathSegment());
//        final ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Recipe Updating.....");
//        progressDialog.show();
//
//        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                Uri urlImage;
//                while(!uriTask.isComplete())
//                    urlImage = uriTask.getResult();
//                imageUrl = urlImage.toString();
//                uploadRecipe();
//
//
//            }
//        });
//
//
//
//    }
//    public void uploadRecipe()
//    {
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Recipe Upload");
//        progressDialog.show();
//TextView txt_name_chef,txt_ingredients, txt_description,txt_location;
//
//        FoodData foodData = new FoodData(txt_name_chef.getText().toString(), txt_ingredients.getText().toString(),
//                txt_description.getText().toString(),
//                txt_location.getText().toString(),
//                imageUrl);
//
//        String myChef = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
//        FirebaseDatabase.getInstance().getReference("Recipe")
//                .child(myChef).setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if(task.isSuccessful())
//                {
//                    Toast.makeText(Upload_Recipe.this, "Recipe Uploaded", Toast.LENGTH_SHORT);
//                    progressDialog.dismiss();
//                    finish();
//                }
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(Upload_Recipe.this,"Failed", Toast.LENGTH_SHORT);
//                progressDialog.dismiss();
//            }
//        });
//    }
//}
public class Update_Activity extends AppCompatActivity {
    TextView chefDescription;

    //TextView ingredientsDescription;
    TextView locationDescription;
    TextView foodDescription;
    ImageView foodImage;
    String key = "";
    String imageUrl = "";
    ImageView recipeImage;
    Uri uri;
    String oldImageUrl;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String recipeName,recipeDescription,recipeLocation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_);
        chefDescription = (TextView)findViewById(R.id.txtMomName);
        //ingredientsDescription = (TextView)findViewById(R.id.txtIngredient);
        foodDescription = (TextView)findViewById(R.id.txtDescription);
        foodImage = (ImageView)findViewById(R.id.ivImage2);
        locationDescription = (TextView)findViewById(R.id.txtLocation);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            Glide.with(Update_Activity.this).load(bundle.get("oldImageUrl")).into(foodImage);
            chefDescription.setText(bundle.getString("recipeNameKey"));
            //ingredientsDescription.setText(bundle.getString("ingredientsKey"));
            foodDescription.setText(bundle.getString("descriptionKey"));
            locationDescription.setText(bundle.getString("locationKey"));
            key = bundle.getString("key");
            oldImageUrl = bundle.getString("oldImageUrl");

        }

        databaseReference = FirebaseDatabase.getInstance().getReference("Recipe").child(key);

    }


    public void btnSelectImage(View view) {
        Intent photoPicker = new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker, 1);
    }
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

    public void btnUpdateRecipe(View view) {
        recipeName = chefDescription.getText().toString().trim();
        recipeDescription = foodDescription.getText().toString().trim();
        //recipeIngredients = ingredientsDescription.getText().toString().trim();
        recipeLocation = locationDescription.getText().toString().trim();
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("RecipesImage").child(uri.getLastPathSegment());
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Updating.....");
        progressDialog.show();

        storageReference  = FirebaseStorage.getInstance().getReference().child("RecipesImage").child(uri.getLastPathSegment());

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                Uri urlImage = null;
                while(!uriTask.isComplete())
                    urlImage = uriTask.getResult();
                imageUrl = urlImage.toString();
                uploadRecipe();


            }
        });



    }
    public void uploadRecipe()
    {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Recipe Upload");
        progressDialog.show();


        TextView txt_name_chef,txt_description, txt_location;
        FoodData foodData = new FoodData(recipeName, recipeDescription,recipeLocation, imageUrl);

        databaseReference.setValue(foodData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                StorageReference storageReferenceNew = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageUrl);
                storageReferenceNew.delete();
                Toast.makeText(Update_Activity.this, "Recipe Updated", Toast.LENGTH_SHORT);
            }
        });


    }
}
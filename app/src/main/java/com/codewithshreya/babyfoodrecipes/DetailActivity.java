package com.codewithshreya.babyfoodrecipes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView chefDescription;

    //TextView ingredientsDescription;
    TextView locationDescription;
    TextView foodDescription;
    ImageView foodImage;
    String key = "";
    String imageUrl = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        chefDescription = (TextView)findViewById(R.id.txtMomName);
        //ingredientsDescription = (TextView)findViewById(R.id.txtIngredient);
        foodDescription = (TextView)findViewById(R.id.txtDescription);
        foodImage = (ImageView)findViewById(R.id.ivImage2);
        locationDescription = (TextView)findViewById(R.id.txtLocation);

        Bundle mBundle = getIntent().getExtras();
        if(mBundle!=null)
        {
            chefDescription.setText(mBundle.getString("Mom-Chef-Name"));
            //ingredientsDescription.setText(mBundle.getString("Ingredient"));
            foodDescription.setText(mBundle.getString("Description"));
            locationDescription.setText(mBundle.getString("Location"));
            foodImage.setImageResource(mBundle.getInt("Image"));
            Glide.with(this).load(mBundle.getString("Image")).into(foodImage);
        }
    }

   public void btnUpdateRecipe(View view) {
            startActivity(new Intent(getApplicationContext(), Update_Activity.class).putExtra("recipeNameKey", chefDescription.getText().toString())
                   .putExtra("descriptionKey", foodDescription.getText().toString())
            .putExtra("locationKey",locationDescription.getText().toString() ).putExtra("oldImageUrl", imageUrl).putExtra("key",key));

    }

}
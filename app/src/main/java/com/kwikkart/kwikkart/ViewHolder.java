package com.kwikkart.kwikkart;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 *  This class is intended to represent the items that will be held within the recycler view.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name;
    private TextView price;


    public ViewHolder(View view)
    {
        super(view);
        image = view.findViewById(R.id.itemImage);
        name = view.findViewById(R.id.itemName);
        price = view.findViewById(R.id.itemPrice);

    }

    public void initializeView(String name, String price, String imagePath)
    {
        this.name.setText(name);
        this.price.setText(price);

        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(imagePath);

        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>()  {

            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                if (task.isSuccessful())
                {
                     Glide.with(image.getContext()).load(task.getResult().toString()).into(image);
                }

            }

        });
    }


}

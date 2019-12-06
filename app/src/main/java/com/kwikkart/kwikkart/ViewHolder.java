package com.kwikkart.kwikkart;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.kwikkart.kwikkart.model.Item;

/**
 *  This class is intended to represent the items that will be held within the recycler view.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name;
    private TextView price;
    private Button addToCart;
    private Item item;

    public ViewHolder(View view)
    {
        super(view);
        image = view.findViewById(R.id.itemImage);
        name = view.findViewById(R.id.itemName);
        price = view.findViewById(R.id.itemPrice);
        addToCart = view.findViewById(R.id.addToCart);
    }

    public void initializeView(String name, String price, String imagePath, Item item)
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

        if (this.item == null) {
            this.item = item;
        }

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment.addItemToCart(getItem());
            }
        });

    }


    public void setItem(Item item)
    {
        this.item = item;
    }

    public Item getItem()
    {
        return item;
    }

    public String getName()
    {
        return name.getText().toString();
    }
}

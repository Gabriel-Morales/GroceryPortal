package com.kwikkart.kwikkart;

import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.kwikkart.kwikkart.model.Item;

public class CartViewHolder extends RecyclerView.ViewHolder {

    private ImageView image;
    private TextView name;
    private TextView price;
    private Button removeFromCart;
    private Item item;


    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.cartImage);
        name = itemView.findViewById(R.id.cartItemName);
        price = itemView.findViewById(R.id.cartItemPrice);
        removeFromCart = itemView.findViewById(R.id.removeItem);
    }

    public void initializeView(String name, String price, String imagePath, Item item)
    {
        this.name.setText(name);
        this.price.setText(price);
        this.item = item;



        //TODO: Attach action handler to the button here to remove from the ArrayList and update the recycler.
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

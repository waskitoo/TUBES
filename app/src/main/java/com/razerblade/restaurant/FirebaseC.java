package com.razerblade.restaurant;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by waski on 18/04/2018.
 */

public class FirebaseC {
    //Firebse Auth
    public final static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseUser currentUser;
    //Firebase Storage
    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    public static StorageReference storageRef = storage.getReference();
   //query
    public final static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public final static DatabaseReference refPhoto = database.getReference("makanan");
    public final static DatabaseReference refOrder = database.getReference("order");
    public final static DatabaseReference refBayar = database.getReference("bayar");
}

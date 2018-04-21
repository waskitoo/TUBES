package com.razerblade.restaurant.admin;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.razerblade.restaurant.FirebaseC;
import com.razerblade.restaurant.Login;
import com.razerblade.restaurant.R;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class Input extends Fragment {

    private Spinner mspinnerJenis;
    private FloatingActionButton mPostButton;
    private Button mPhotoButton;
    private ImageView imgPhoto;
    private TextInputEditText mNama,mDeskripsi;
    private boolean isPicChange = false;
    private ProgressDialog pbDialog;
    private StorageReference refPhoto;
    private Uri photoUrl;
    public Input() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmenView = inflater.inflate(R.layout.fragment_input, container, false);
        pbDialog = new ProgressDialog(getContext());
        mspinnerJenis=(Spinner)fragmenView.findViewById(R.id.spinnerJenis);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.jenis, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mspinnerJenis.setAdapter(adapter);
        imgPhoto = fragmenView.findViewById(R.id.imgPhoto);
        mNama = fragmenView.findViewById(R.id.editTextNama);
        mDeskripsi = fragmenView.findViewById(R.id.editTextDeskripsi);
        mPhotoButton = fragmenView.findViewById(R.id.btnChoose);
        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagepicker();
            }
        });
        mPostButton =fragmenView.findViewById(R.id.btnPost);
        mPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mNama.getText().toString().isEmpty()){
                    mNama.setError("Requied");
                    return;
                }if(mDeskripsi.getText().toString().isEmpty()){
                    mDeskripsi.setError("Requied");
                    return;
                }if(!isPicChange) {
                    Toast.makeText(getContext(), "Choose The Photo!", Toast.LENGTH_SHORT).show();
                    return;
                }
                pbDialog.setMessage("Uploading..");
                pbDialog.setIndeterminate(true);
                pbDialog.show();
                uploadData();

            }
        });


        return fragmenView;
    }
    private void imagepicker(){
        ImagePicker.create(Input.this)
                .returnMode(ReturnMode.ALL) // set whether pick and / or camera action should return immediate result or not.
                .folderMode(true) // folder mode (false by default)
                .toolbarFolderTitle("Folder") // folder selection title
                .toolbarImageTitle("Tap to select") // image selection title
                .toolbarArrowColor(Color.WHITE) // Toolbar 'up' arrow color
                .single() // single mode
                .limit(1) // max images can be selected (99 by default)
                .showCamera(true) // show camera or not (true by default)
                .imageDirectory("Camera") // directory name for captured image  ("Camera" folder by default)
                .enableLog(false) // disabling log
                .start(); // start image picker activity with request code
    }
    private void uploadData(){
        refPhoto = FirebaseC.storageRef.child("gambar/" + System.currentTimeMillis() + ".jpg");
        final StorageReference photoImagesRef = FirebaseC.storageRef.child("gambar/" + System.currentTimeMillis() + ".jpg");
        refPhoto.getName().equals(photoImagesRef.getName());
        refPhoto.getPath().equals(photoImagesRef.getPath());
        imgPhoto.setDrawingCacheEnabled(true);
        imgPhoto.buildDrawingCache();
        Bitmap bitmap = imgPhoto.getDrawingCache(); //convert imageview ke bitmap
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos); //convert bitmap ke bytearray
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = refPhoto.putBytes(data); //upload image yang sudah dalam bentuk bytearray ke firebase storage
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                photoUrl = taskSnapshot.getDownloadUrl();
                String key = FirebaseC.refPhoto.push().getKey();
                FirebaseC.refPhoto.child(key).setValue(new MAdminInput(
                        key,
                        photoUrl.toString(),
                        mNama.getText().toString(),
                        mDeskripsi.getText().toString(),
                        mspinnerJenis.getSelectedItem().toString()
                        ));
                pbDialog.dismiss();
                Toast.makeText(getContext(), "Uploaded!", Toast.LENGTH_SHORT).show();
                refreshq();
//                Intent intent = getActivity().getIntent();
//                getActivity().finish();
//                startActivity(intent);
            }
        });


    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) { // jika ada data dipilih
            Image image = ImagePicker.getFirstImageOrNull(data); //ambil first image
            File imgFile = new File(image.getPath()); // dapatkan lokasi gambar yang dipilih
            if(imgFile.exists()){ //jika ditemukan
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath()); //convert file ke bitmap
                imgPhoto.setImageBitmap(myBitmap); //set imageview dengan gambar yang dipilih
                isPicChange = true; // ubah state menjadi true untuk menandakan gambar telah dipilih
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void refreshq(){
        getFragmentManager().beginTransaction().detach(Input.this).attach(Input.this).commit();
        mNama.setText(null);
        mDeskripsi.setText(null);
    }

}

package com.example.biblio.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.*;

import java.io.IOException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.biblio.R;
import com.example.biblio.utils.Book;
import com.example.biblio.utils.TinyDB;
import com.example.biblio.utils.Upload;
import com.example.biblio.utils.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int PICK_IMAGE_REQUEST = 71;
    private final int PICK_FILE_REQUEST = 72;
    private Uri filePath, filePath1;
    Bitmap bitmap;
    private CircleImageView img_book;
    private FrameLayout frame;
    private StorageReference mStorageRef;
    private ProgressBar ProgressBar;
    Button upload, choose, btn;
    EditText title;
    TextView title_file;
    String url_file, url_file1;
    Book book;

    double progress;


    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStorageRef = FirebaseStorage.getInstance().getReference();


        img_book = findViewById(R.id.id_img_profile);
        frame = findViewById(R.id.id_frame);
        upload = findViewById(R.id.buttonUpload);
        choose = findViewById(R.id.btn_choose);
        title = findViewById(R.id.text_input_title);
        title_file = findViewById(R.id.tv_file);
        ProgressBar = findViewById(R.id.progressBar);

        btn = findViewById(R.id.btn);

        frame.setOnClickListener(this);
      //  upload.setOnClickListener(this);
        choose.setOnClickListener(this);
     //   btn.setOnClickListener(this);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               uploadFile("Image/", filePath1);
               uploadFile1("File/", filePath);

           }
       });


       upload.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               createBook(title.getText().toString(), new TinyDB(MainActivity.this).getString("file1"), new TinyDB(MainActivity.this).getString("file"));

               Toast.makeText(MainActivity.this, "Book Created Successfuly", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(MainActivity.this, QuestActivity.class));

           }
       });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
        } else if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath1 = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath1);
                img_book.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void getFile() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select file"), PICK_FILE_REQUEST);
        title_file.setVisibility(View.VISIBLE);
        title_file.setText(title.getText().toString() + ".pdf");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_frame:
                chooseImage();
                break;

            case R.id.btn_choose:
                getFile();

                break;
        }
    }


    private void uploadFile(String position, Uri file) {

        if (file != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            Toast.makeText(this, "" + title.getText().toString(), Toast.LENGTH_SHORT).show();
            final StorageReference ref = mStorageRef.child(position + title.getText().toString() + "." + getfil_extension(file));
            ref.putFile(file)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;
                                    url_file = downloadUrl.toString();
                                    new TinyDB(getApplicationContext()).putString("file1", downloadUrl.toString());
                                //    Toast.makeText(MainActivity.this, "" + downloadUrl.toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                            Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());

                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }

    private String getfil_extension(Uri uri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap min = MimeTypeMap.getSingleton();
        return min.getExtensionFromMimeType(c.getType(uri));

    }

    private void uploadFile1(String position, Uri file) {

        if (file != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            Toast.makeText(this, "" + title.getText().toString(), Toast.LENGTH_SHORT).show();
            final StorageReference ref = mStorageRef.child(position + title.getText().toString());
            ref.putFile(file)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Uri downloadUrl = uri;
                                    url_file1 = downloadUrl.toString();
                                    new TinyDB(getApplicationContext()).putString("file", downloadUrl.toString());
                                }
                            });
                            Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
    }

    private void createBook(String title, String url, String url1) {

        book = new Book(title, url, url1);
        String key = mDatabaseReference.push().getKey();
        new TinyDB(getApplicationContext()).putString("key_set", key);
        mDatabaseReference = mDatabase.getReference().child("books").child(key);
        mDatabaseReference.setValue(book);

    }
}

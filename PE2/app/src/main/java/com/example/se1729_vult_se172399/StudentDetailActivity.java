package com.example.se1729_vult_se172399;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.bumptech.glide.Glide;
import com.example.se1729_vult_se172399.database.DatabaseHelper;
import com.example.se1729_vult_se172399.model.Student;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StudentDetailActivity extends AppCompatActivity {
    
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;
    private static final int REQUEST_CAMERA_PERMISSION = 3;
    
    private ImageView ivStudentPhoto;
    private TextView tvStudentName, tvStudentId, tvStudentDate, tvStudentGender;
    private TextView tvStudentMajor, tvStudentPhone, tvStudentAddress;
    private Button btnEditStudent, btnDeleteStudent, btnViewOnMap, btnEditPhoto;
    
    private DatabaseHelper databaseHelper;
    private Student student;
    private int studentId;
    private SharedPreferences sharedPreferences;
    private String currentPhotoPath;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);
        
        initViews();
        setupDatabase();
        getStudentData();
        setupListeners();
    }
    
    private void initViews() {
        ivStudentPhoto = findViewById(R.id.ivStudentPhoto);
        tvStudentName = findViewById(R.id.tvStudentName);
        tvStudentId = findViewById(R.id.tvStudentId);
        tvStudentDate = findViewById(R.id.tvStudentDate);
        tvStudentGender = findViewById(R.id.tvStudentGender);
        tvStudentMajor = findViewById(R.id.tvStudentMajor);
        tvStudentPhone = findViewById(R.id.tvStudentPhone);
        tvStudentAddress = findViewById(R.id.tvStudentAddress);
        btnEditStudent = findViewById(R.id.btnEditStudent);
        btnDeleteStudent = findViewById(R.id.btnDeleteStudent);
        btnViewOnMap = findViewById(R.id.btnViewOnMap);
        btnEditPhoto = findViewById(R.id.btnEditPhoto);
    }
    
    private void setupDatabase() {
        databaseHelper = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
    }
    
    private void getStudentData() {
        studentId = getIntent().getIntExtra("STUDENT_ID", -1);
        if (studentId != -1) {
            student = databaseHelper.getStudent(studentId);
            if (student != null) {
                displayStudentInfo();
            } else {
                Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "Invalid student ID", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    
    private void displayStudentInfo() {
        tvStudentName.setText(student.getName());
        tvStudentId.setText("ID: #" + student.getId());
        tvStudentDate.setText(student.getDate());
        tvStudentGender.setText(student.getGender());
        tvStudentPhone.setText(student.getPhone());
        tvStudentAddress.setText(student.getAddress());
        
        String majorName = databaseHelper.getMajorName(student.getIdNganh());
        tvStudentMajor.setText(majorName);
        
        if (student.getImagePath() != null && !student.getImagePath().isEmpty()) {
            File imageFile = new File(student.getImagePath());
            if (imageFile.exists()) {
                Glide.with(this)
                        .load(imageFile)
                        .circleCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(ivStudentPhoto);
            } else {
                ivStudentPhoto.setImageResource(R.mipmap.ic_launcher);
            }
        } else {
            ivStudentPhoto.setImageResource(R.mipmap.ic_launcher);
        }
    }
    
    private void setupListeners() {
        btnEditStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLoggedIn()) {
                    Intent intent = new Intent(StudentDetailActivity.this, AddStudentActivity.class);
                    intent.putExtra("STUDENT_ID", studentId);
                    startActivity(intent);
                } else {
                    showLoginRequiredDialog();
                }
            }
        });
        
        btnDeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLoggedIn()) {
                    showDeleteConfirmationDialog();
                } else {
                    showLoginRequiredDialog();
                }
            }
        });
        
        btnViewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (student.getAddress() != null && !student.getAddress().isEmpty()) {
                    Intent intent = new Intent(StudentDetailActivity.this, StudentMapActivity.class);
                    intent.putExtra("STUDENT_ID", studentId);
                    intent.putExtra("STUDENT_NAME", student.getName());
                    intent.putExtra("STUDENT_ADDRESS", student.getAddress());
                    startActivity(intent);
                } else {
                    Toast.makeText(StudentDetailActivity.this, "No address available for this student", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        btnEditPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLoggedIn()) {
                    showImagePickerDialog();
                } else {
                    showLoginRequiredDialog();
                }
            }
        });
    }
    
    private boolean isUserLoggedIn() {
        String loggedInUser = sharedPreferences.getString("logged_in_user", null);
        if (loggedInUser != null) {
            btnEditPhoto.setVisibility(View.VISIBLE);
        } else {
            btnEditPhoto.setVisibility(View.GONE);
        }
        return loggedInUser != null;
    }
    
    private void showLoginRequiredDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Login Required");
        builder.setMessage("You need to login to perform this action. Do you want to login now?");
        
        builder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(StudentDetailActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        
        builder.show();
    }
    
    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Student");
        builder.setMessage("Are you sure you want to delete " + student.getName() + "?\nThis action cannot be undone.");
        
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteStudent();
            }
        });
        
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        
        builder.show();
    }
    
    private void deleteStudent() {
        try {
            databaseHelper.deleteStudent(studentId);
            
            if (student.getImagePath() != null && !student.getImagePath().isEmpty()) {
                File imageFile = new File(student.getImagePath());
                if (imageFile.exists()) {
                    imageFile.delete();
                }
            }
            
            Toast.makeText(this, "Student deleted successfully", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Error deleting student: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    
    private void showImagePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Image Source");
        builder.setItems(new String[]{"Camera", "Gallery"}, (dialog, which) -> {
            if (which == 0) {
                checkCameraPermissionAndTakePhoto();
            } else {
                openGallery();
            }
        });
        builder.show();
    }

    private void checkCameraPermissionAndTakePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, 
                new String[]{Manifest.permission.CAMERA}, 
                REQUEST_CAMERA_PERMISSION);
        } else {
            dispatchTakePictureIntent();
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
        } else {
            Toast.makeText(this, "No gallery app available", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, "Error creating image file: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (photoFile != null) {
                try {
                    Uri photoURI = FileProvider.getUriForFile(this, 
                            "com.example.se1729_vult_se172399.fileprovider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                } catch (Exception e) {
                    Toast.makeText(this, "Error setting up camera: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Could not create image file", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No camera app available, opening gallery instead", Toast.LENGTH_SHORT).show();
            openGallery();
        }
    }
    
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        
        if (storageDir != null && !storageDir.exists()) {
            storageDir.mkdirs();
        }
        
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                if (currentPhotoPath != null) {
                    updateStudentPhoto();
                }
            } else if (requestCode == REQUEST_IMAGE_GALLERY && data != null) {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    try {
                        copyImageToAppDirectory(selectedImageUri);
                        updateStudentPhoto();
                    } catch (IOException e) {
                        Toast.makeText(this, "Error processing image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        currentPhotoPath = selectedImageUri.toString();
                        updateStudentPhoto();
                    }
                }
            }
        }
    }

    private void updateStudentPhoto() {
        student.setImagePath(currentPhotoPath);
        databaseHelper.updateStudent(student);
        
        if (currentPhotoPath.startsWith("content://")) {
            Glide.with(this)
                    .load(Uri.parse(currentPhotoPath))
                    .circleCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivStudentPhoto);
        } else {
            Glide.with(this)
                    .load(new File(currentPhotoPath))
                    .circleCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .into(ivStudentPhoto);
        }
        
        Toast.makeText(this, "Photo updated successfully", Toast.LENGTH_SHORT).show();
    }

    private void copyImageToAppDirectory(Uri sourceUri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(sourceUri);
        File photoFile = createImageFile();
        FileOutputStream outputStream = new FileOutputStream(photoFile);
        
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }
        
        outputStream.close();
        inputStream.close();
        
        currentPhotoPath = photoFile.getAbsolutePath();
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                Toast.makeText(this, "Camera permission is required to take photos", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        if (studentId != -1) {
            student = databaseHelper.getStudent(studentId);
            if (student != null) {
                displayStudentInfo();
                isUserLoggedIn();
            }
        }
    }
} 
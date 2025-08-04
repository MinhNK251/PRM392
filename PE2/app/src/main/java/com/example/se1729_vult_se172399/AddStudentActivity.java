package com.example.se1729_vult_se172399;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.bumptech.glide.Glide;
import com.example.se1729_vult_se172399.database.DatabaseHelper;
import com.example.se1729_vult_se172399.model.Major;
import com.example.se1729_vult_se172399.model.Student;
import com.google.android.material.textfield.TextInputEditText;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddStudentActivity extends AppCompatActivity {
    
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_IMAGE_GALLERY = 2;
    private static final int REQUEST_CONTACTS_PERMISSION = 3;
    private static final int REQUEST_CAMERA_PERMISSION = 4;
    private static final int REQUEST_PICK_CONTACT = 5;
    
    private ImageView ivStudentPhoto;
    private TextInputEditText etStudentName, etStudentDate, etStudentAddress, etStudentPhone;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private Spinner spinnerMajor;
    private Button btnTakePhoto, btnChangePhoto, btnImportContacts, btnSaveStudent, btnCancel;
    
    private DatabaseHelper databaseHelper;
    private List<Major> majorList;
    private ArrayAdapter<Major> majorAdapter;
    private String currentPhotoPath;
    private Calendar calendar;
    private Student studentToEdit;
    private boolean isEditMode = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        
        initViews();
        setupDatabase();
        setupCalendar();
        loadMajors();
        checkEditMode();
        setupListeners();
    }
    
    private void initViews() {
        ivStudentPhoto = findViewById(R.id.ivStudentPhoto);
        etStudentName = findViewById(R.id.etStudentName);
        etStudentDate = findViewById(R.id.etStudentDate);
        etStudentAddress = findViewById(R.id.etStudentAddress);
        etStudentPhone = findViewById(R.id.etStudentPhone);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        spinnerMajor = findViewById(R.id.spinnerMajor);
        btnTakePhoto = findViewById(R.id.btnTakePhoto);
        btnChangePhoto = findViewById(R.id.btnChangePhoto);
        btnImportContacts = findViewById(R.id.btnImportContacts);
        btnSaveStudent = findViewById(R.id.btnSaveStudent);
        btnCancel = findViewById(R.id.btnCancel);
    }
    
    private void setupDatabase() {
        databaseHelper = new DatabaseHelper(this);
    }
    
    private void setupCalendar() {
        calendar = Calendar.getInstance();
    }
    
    private void loadMajors() {
        majorList = databaseHelper.getAllMajors();
        majorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, majorList);
        majorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMajor.setAdapter(majorAdapter);
    }
    
    private void setupListeners() {
        btnTakePhoto.setOnClickListener(v -> showImagePickerDialog());
        btnChangePhoto.setOnClickListener(v -> showImagePickerDialog());
        btnImportContacts.setOnClickListener(v -> checkContactsPermissionAndImport());
        etStudentDate.setOnClickListener(v -> showDatePicker());
        btnSaveStudent.setOnClickListener(v -> saveStudent());
        btnCancel.setOnClickListener(v -> finish());
    }
    
    private void checkEditMode() {
        int studentId = getIntent().getIntExtra("STUDENT_ID", -1);
        if (studentId != -1) {
            isEditMode = true;
            studentToEdit = databaseHelper.getStudent(studentId);
            if (studentToEdit != null) {
                populateFields();
                btnSaveStudent.setText("Update Student");
                btnTakePhoto.setVisibility(View.GONE);
                btnChangePhoto.setVisibility(View.VISIBLE);
            }
        }
    }
    
    private void populateFields() {
        etStudentName.setText(studentToEdit.getName());
        etStudentDate.setText(studentToEdit.getDate());
        etStudentAddress.setText(studentToEdit.getAddress());
        etStudentPhone.setText(studentToEdit.getPhone());
        
        if ("Male".equals(studentToEdit.getGender())) {
            rbMale.setChecked(true);
        } else {
            rbFemale.setChecked(true);
        }
        
        for (int i = 0; i < majorList.size(); i++) {
            if (majorList.get(i).getIdNganh() == studentToEdit.getIdNganh()) {
                spinnerMajor.setSelection(i);
                break;
            }
        }
        
        if (studentToEdit.getImagePath() != null && !studentToEdit.getImagePath().isEmpty()) {
            File imageFile = new File(studentToEdit.getImagePath());
            if (imageFile.exists()) {
                Glide.with(this)
                        .load(imageFile)
                        .circleCrop()
                        .placeholder(R.mipmap.ic_launcher)
                        .into(ivStudentPhoto);
                currentPhotoPath = studentToEdit.getImagePath();
            }
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
    
    private void checkContactsPermissionAndImport() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS_PERMISSION);
        } else {
            pickContact();
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
                    openGallery();
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
    
    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(pickContactIntent, REQUEST_PICK_CONTACT);
    }
    
    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, monthOfYear, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    etStudentDate.setText(dateFormat.format(calendar.getTime()));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        
        datePickerDialog.show();
    }
    
    private void saveStudent() {
        String name = etStudentName.getText().toString().trim();
        String date = etStudentDate.getText().toString().trim();
        String address = etStudentAddress.getText().toString().trim();
        String phone = etStudentPhone.getText().toString().trim();
        
        if (TextUtils.isEmpty(name)) {
            etStudentName.setError("Please enter student name");
            etStudentName.requestFocus();
            return;
        }
        
        if (TextUtils.isEmpty(date)) {
            etStudentDate.setError("Please select date of birth");
            etStudentDate.requestFocus();
            return;
        }
        
        if (rgGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (TextUtils.isEmpty(address)) {
            etStudentAddress.setError("Please enter address");
            etStudentAddress.requestFocus();
            return;
        }
        
        if (TextUtils.isEmpty(phone)) {
            etStudentPhone.setError("Please enter phone number");
            etStudentPhone.requestFocus();
            return;
        }
        
        String gender = rbMale.isChecked() ? "Male" : "Female";
        Major selectedMajor = (Major) spinnerMajor.getSelectedItem();
        
        if (selectedMajor == null) {
            Toast.makeText(this, "Please select a major", Toast.LENGTH_SHORT).show();
            return;
        }
        
        if (isEditMode) {
            studentToEdit.setName(name);
            studentToEdit.setDate(date);
            studentToEdit.setGender(gender);
            studentToEdit.setAddress(address);
            studentToEdit.setIdNganh(selectedMajor.getIdNganh());
            studentToEdit.setPhone(phone);
            studentToEdit.setImagePath(currentPhotoPath);
            
            int result = databaseHelper.updateStudent(studentToEdit);
            if (result > 0) {
                Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to update student", Toast.LENGTH_SHORT).show();
            }
        } else {
            Student student = new Student(name, date, gender, address, selectedMajor.getIdNganh(), phone);
            student.setImagePath(currentPhotoPath);
            
            long result = databaseHelper.addStudent(student);
            if (result != -1) {
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                if (currentPhotoPath != null) {
                    Glide.with(this).load(new File(currentPhotoPath)).circleCrop().placeholder(R.mipmap.ic_launcher).into(ivStudentPhoto);
                }
            } else if (requestCode == REQUEST_IMAGE_GALLERY && data != null) {
                Uri selectedImageUri = data.getData();
                if (selectedImageUri != null) {
                    try {
                        copyImageToAppDirectory(selectedImageUri);
                        Glide.with(this).load(new File(currentPhotoPath)).circleCrop().placeholder(R.mipmap.ic_launcher).into(ivStudentPhoto);
                    } catch (IOException e) {
                        Toast.makeText(this, "Error processing image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Glide.with(this).load(selectedImageUri).circleCrop().placeholder(R.mipmap.ic_launcher).into(ivStudentPhoto);
                        currentPhotoPath = selectedImageUri.toString();
                    }
                }
            } else if (requestCode == REQUEST_PICK_CONTACT) {
                Uri contactUri = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
                
                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String contactNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    
                    showImportContactDialog(contactName, contactNumber);
                    cursor.close();
                }
            }
        }
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
    
    private void showImportContactDialog(String contactName, String contactNumber) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Import Contact");
        builder.setMessage("Do you want to import:\nName: " + contactName + "\nPhone: " + contactNumber);
        
        builder.setPositiveButton("Import", (dialog, which) -> {
            if (!TextUtils.isEmpty(contactName)) {
                etStudentName.setText(contactName);
            }
            if (!TextUtils.isEmpty(contactNumber)) {
                etStudentPhone.setText(contactNumber);
            }
        });
        
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        switch (requestCode) {
            case REQUEST_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    Toast.makeText(this, "Camera permission is required to take photos", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CONTACTS_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickContact();
                } else {
                    Toast.makeText(this, "Contacts permission is required to import contacts", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
} 
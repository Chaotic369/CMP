package com.aicompile.app;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    // Supabase Configuration from Sovereign System
    private static final String SUPABASE_URL = "https://fxnrugbvbfyamtrfmjea.supabase.co";
    private static final String BUCKET_NAME = "docs";
    
    private EditText quickNoteInput;
    private TextView sbCount, sbStatus;
    private RecyclerView notesContainer, filesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Map UI Elements
        quickNoteInput = findViewById(R.id.quickNoteInput);
        sbCount = findViewById(R.id.sbCount);
        sbStatus = findViewById(R.id.sbStatus);
        notesContainer = findViewById(R.id.notesContainer);
        filesContainer = findViewById(R.id.filesContainer);

        // Map Buttons
        FloatingActionButton uploadBtn = findViewById(R.id.uploadSquircleBtn);
        ImageButton btnWallpapers = findViewById(R.id.btnWallpapers);
        ImageButton btnStorage = findViewById(R.id.btnStorage);

        // Upload Button Logic
        uploadBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Opening native file picker...", Toast.LENGTH_SHORT).show();
            // Implement Android Storage Access Framework (SAF) here
        });

        // Quick Note Input Logic
        quickNoteInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE || 
               (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                
                String noteText = quickNoteInput.getText().toString().trim();
                if (!noteText.isEmpty()) {
                    quickAddNoteNative(noteText);
                }
                return true;
            }
            return false;
        });

        btnWallpapers.setOnClickListener(v -> Toast.makeText(this, "Opening Wallpaper Gallery", Toast.LENGTH_SHORT).show());
        btnStorage.setOnClickListener(v -> Toast.makeText(this, "Checking Storage...", Toast.LENGTH_SHORT).show());

        loadCloudState();
    }

    private void quickAddNoteNative(String text) {
        sbStatus.setText("syncing...");
        sbStatus.setTextColor(android.graphics.Color.parseColor("#e3b341")); // Amber
        
        // Native HTTP POST to Supabase REST API goes here
        Toast.makeText(this, "Note Captured: " + text, Toast.LENGTH_SHORT).show();
        quickNoteInput.setText("");
        
        sbStatus.setText("ready");
        sbStatus.setTextColor(android.graphics.Color.parseColor("#39d353")); // Green
    }

    private void loadCloudState() {
        // Native HTTP GET to fetch notes and files from Supabase
        sbCount.setText("Synchronizing with cloud array...");
        // After fetch:
        // sbCount.setText(fileList.size() + " files");
    }
}

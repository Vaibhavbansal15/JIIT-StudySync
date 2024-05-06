package com.minorproject.jiitstudysync

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.minorproject.jiitstudysync.databinding.ActivitySubjectsPyqBinding
import java.io.File

class SubjectsPYQ : AppCompatActivity() {

    private val binding : ActivitySubjectsPyqBinding by lazy{
        ActivitySubjectsPyqBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val sName : TextView = binding.pyqSubName
        val sCode : TextView = binding.pyqSubCode
        var frontImageName : String? = ""
        var backImageName : String? = ""

        val bundle : Bundle? = intent.extras
        sName.text = bundle!!.getString("pyqSubName")
        sCode.text = bundle.getString("pyqSubCode")


        binding.searchPyqBtn.setOnClickListener {

            val year = binding.searchPyqYear.text.toString()
            val exam = binding.searchPyqExam.text.toString()

            if(year.isEmpty() || exam.isEmpty()) {
                frontImageName = ""
                backImageName = ""
                Toast.makeText(this, "please fill the details to search", Toast.LENGTH_SHORT).show()
            }
            else {

                val imageName1 =
                    sCode.text.toString() + "_" + exam + "_" + year + "_1"
                val imageName2 =
                    sCode.text.toString() + "_" + exam + "_" + year + "_2"
                frontImageName = imageName1
                backImageName = imageName2
                val storageRef1 =
                    FirebaseStorage.getInstance().reference.child("Stored_PYQs/${sCode.text}/$imageName1.jpg")

                val localFile1 = File.createTempFile("tempFrontImage", "jpg")
                storageRef1.getFile(localFile1)
                    .addOnSuccessListener {
                        binding.pyqFrontPage.setText(imageName1)
                    }.addOnFailureListener {
                        binding.pyqFrontPage.setText("No Data found")
                        Toast.makeText(
                            this@SubjectsPYQ,
                            "No front Page",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                val storageRef2 =
                    FirebaseStorage.getInstance().reference.child("Stored_PYQs/${sCode.text}/$imageName2.jpg")

                val localFile2 = File.createTempFile("tempBackImage", "jpg")
                storageRef2.getFile(localFile2)
                    .addOnSuccessListener {
                        binding.pyqBackPage.setText(imageName2)
                    }.addOnFailureListener {
                        binding.pyqBackPage.setText("No Data found")
                        Toast.makeText(
                            this@SubjectsPYQ,
                            "No Back Page",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }

        binding.frontPage.setOnClickListener{
            val intent = Intent(this, PYQFrontPageView::class.java)
            intent.putExtra("frontPage", frontImageName)
            intent.putExtra("subCode", sCode.text)
            startActivity(intent)
        }

        binding.backPage.setOnClickListener{
            val intent = Intent(this, PYQBackPageView::class.java)
            intent.putExtra("backPage", backImageName)
            intent.putExtra("subCode", sCode.text)
            startActivity(intent)
        }
    }
}
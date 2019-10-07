package com.example.practise

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

 object  FirebaseInstance {
     var ref : DatabaseReference = FirebaseDatabase.getInstance().reference.child("user")
}
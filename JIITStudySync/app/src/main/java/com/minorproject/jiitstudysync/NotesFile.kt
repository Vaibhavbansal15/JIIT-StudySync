package com.minorproject.jiitstudysync

import android.net.Uri

data class NotesFile(val downloadUrl : String, val fileName : String){
    constructor() : this("", "")
}

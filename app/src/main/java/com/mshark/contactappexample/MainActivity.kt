package com.mshark.contactappexample


import android.annotation.TargetApi
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mshark.contactappexample.business.ContactInteractor
import com.mshark.contactappexample.data.ContactsQuery
import com.mshark.contactappexample.data.ContactsRepository
import com.mshark.contactappexample.presentation.MainViewModel
import com.mshark.contactappexample.presentation.MainViewModelProvider
import com.mshark.contactappexample.presentation.recyclerview.ContactAdapter

class MainActivity : AppCompatActivity() {
    val ANDROID_PERMISSION_READ_CONTACTS = "android.permission.READ_CONTACTS"

    private lateinit var resolver: ContentResolver
    private lateinit var contactRepository: ContactsRepository
    private lateinit var contactInteractor: ContactInteractor
    private lateinit var viewModelProvider: MainViewModelProvider
    private lateinit var viewModel: MainViewModel

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFindContacts: Button
    private lateinit var mAdapter: ContactAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.recyclerView)
        mFindContacts = findViewById(R.id.contacts_btn)

        val recyclerViewLayoutManager = LinearLayoutManager(this.applicationContext)
        mRecyclerView.setLayoutManager(recyclerViewLayoutManager)

        mAdapter = ContactAdapter()
        mRecyclerView.setAdapter(mAdapter)

        setupViewModel()
        mFindContacts.setOnClickListener {
            viewModel.getContacts()
        }
    }

    private fun setupViewModel() {
        resolver = applicationContext.contentResolver
        contactRepository = ContactsRepository(resolver)
        contactInteractor = ContactInteractor(contactRepository)
        viewModelProvider = MainViewModelProvider(contactInteractor)
        viewModel = ViewModelProviders.of(this, viewModelProvider).get(MainViewModel::class.java)

        viewModel.contactList.observe(this, Observer {
            mAdapter.swapData(it)
        })
    }

    fun checkPermissions() {
        getPermissionToReadUserContacts()
        if (ContextCompat.checkSelfPermission(
                this,
                ANDROID_PERMISSION_READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            loaderManager.initLoader(ContactsQuery.QUERY_ID, null, this)
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private fun getPermissionToReadUserContacts() {
        if (ContextCompat.checkSelfPermission(
                this,
                "android.permission.READ_CONTACTS"
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            if (shouldShowRequestPermissionRationale(
                    "android.permission.READ_CONTACTS"
                )
            ) {
            }

            requestPermissions(
                arrayOf("android.permission.READ_CONTACTS"),
                READ_CONTACTS_PERMISSIONS_REQUEST
            )
        }
    }
}

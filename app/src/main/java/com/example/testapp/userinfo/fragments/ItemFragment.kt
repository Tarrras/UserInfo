package com.example.testapp.userinfo.fragments


 import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.support.v7.widget.Toolbar
import android.view.*
import com.example.testapp.userinfo.API.RetrofitBuilder

import com.example.testapp.userinfo.R
import com.example.testapp.userinfo.adapters.RecyclerAdapter
import com.example.testapp.userinfo.objects.ResponseResult
import com.example.testapp.userinfo.objects.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.list_item.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class ItemFragment : Fragment() {
    public val IMAGE_URL_EXTRA:String = "IMAGE_URL"
    public val NAME_EXTRA:String = "NAME_EXTRA"
    public val BIRTHDAY_EXTRA:String = "BIRTHDAY_EXTRA"
    public val EMAIL_EXTRA:String = "EMAIL_EXTRA"
    public val ADDRESS_EXTRA:String = "ADDRESS_EXTRA"
    public val PHONE_EXTRA:String = "PHONE_EXTRA"
    lateinit var userImage: ImageView
    var toolbar: Toolbar? = null
    lateinit var name_tv: TextView
    lateinit var birthday_tv: TextView
    lateinit var email_tv: TextView
    lateinit var address_tv: TextView
    lateinit var phone_tv: TextView
    lateinit var appBarLayout:AppBarLayout
    lateinit var collapsingToolbarLayout:CollapsingToolbarLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)
        userImage = view.find<ImageView>(R.id.profile_image)
        toolbar = view.find<Toolbar>(R.id.toolbar)
        name_tv = view.find<TextView>(R.id.aa_name)
        birthday_tv = view.find<TextView>(R.id.aa_age)
        email_tv = view.find<TextView>(R.id.aa_email)
        address_tv = view.find<TextView>(R.id.aa_address)
        phone_tv = view.find<TextView>(R.id.aa_phone)
        appBarLayout  = view.find<AppBarLayout>(R.id.appbarlayout_id)
        collapsingToolbarLayout = view.find<CollapsingToolbarLayout>(R.id.collapsingtoolbar_id)
        collapsingToolbarLayout.isTitleEnabled=true

        return view
    }

    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val imageUrl=arguments?.getString(IMAGE_URL_EXTRA)
        val name = arguments?.getString(NAME_EXTRA)
        val birthday = arguments?.getString(BIRTHDAY_EXTRA)
        val email = arguments?.getString(EMAIL_EXTRA)
        val address = arguments?.getString(ADDRESS_EXTRA)
        val phone = arguments?.getString(PHONE_EXTRA)

        Picasso.with(context).load(imageUrl).into(userImage)
        val emailTv:String = "Email: $email"
        val phoneTv:String = "Phone: $phone"
        name_tv.text = name
        birthday_tv.text = birthday
        email_tv.text = emailTv
        address_tv.text = address
        phone_tv.text = phoneTv
    }


//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        val list = activity?.supportFragmentManager?.findFragmentByTag("ListFragment")
//        when(item?.itemId){
//
//            android.R.id.home -> if(list is ListFragment && list.isVisible){
//                if(fragmentManager?.backStackEntryCount!=0){
//                    fragmentManager?.popBackStack()
//                } else{
//                   toast("LOL")
//                }
//            }
//
//        }
//        return super.onOptionsItemSelected(item)
//    }

}

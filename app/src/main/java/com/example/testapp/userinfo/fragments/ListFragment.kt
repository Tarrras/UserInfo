package com.example.testapp.userinfo.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.testapp.userinfo.API.RetrofitBuilder

import com.example.testapp.userinfo.R
import com.example.testapp.userinfo.adapters.RecyclerAdapter
import com.example.testapp.userinfo.objects.ResponseResult
import com.example.testapp.userinfo.objects.ResultsItem
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class ListFragment : Fragment() {



    lateinit var recyclerView: RecyclerView
    lateinit var recyclerViewAdapter: RecyclerAdapter
    lateinit var lstResult:List<ResultsItem>
//        private val onItemClickListener =
//                object : RecyclerAdapter.OnItemClickListener {
//                    override fun invoke(resultsItem: ResultsItem) {
//                        val bundle = Bundle()
//                        val fullName =
//                            "${resultsItem.name?.title} ${resultsItem.name?.first} ${resultsItem.name?.last}"
//                        val address =
//                            "state: ${resultsItem.location?.state}, city: ${resultsItem.location?.city}, street: ${resultsItem.location?.street}"
//                        val age =
//                        "${resultsItem.dob?.date}, ${resultsItem.dob?.age} years"
//                        bundle.putString(ItemFragment().EMAIL_EXTRA,resultsItem.email)
//                        bundle.putString(ItemFragment().IMAGE_URL_EXTRA,resultsItem.picture?.large)
//                        bundle.putString(ItemFragment().NAME_EXTRA,fullName)
//                        bundle.putString(ItemFragment().BIRTHDAY_EXTRA,age)
//                        bundle.putString(ItemFragment().ADDRESS_EXTRA,address)
//                        bundle.putString(ItemFragment().PHONE_EXTRA,resultsItem.phone)
//                        val itemFragment = ItemFragment()
//                        itemFragment.arguments = bundle
//                        val ft = supportFragmentManager.beginTransaction()
//                        ft?.replace(R.id.container_list_id,itemFragment)?.addToBackStack(null)?.commit()
//                    }
//                }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = view.find(R.id.recycler_id)
        return view
    }

    override fun onResume() {
        super.onResume()

        RetrofitBuilder.instance
            .userAPI
            .getUserList()
            .enqueue(object: Callback<ResponseResult> {
                override fun onResponse(call: Call<ResponseResult>, response: Response<ResponseResult>) {
                    if(response.isSuccessful){
                        val listResult = response.body()
                        lstResult = response.body()!!.results
                        recyclerViewAdapter = RecyclerAdapter(listResult!!){
                            val bundle = Bundle()
                            val fullName =
                                "${it.name?.title} ${it.name?.first} ${it.name?.last}"
                            val address =
                                "state: ${it.location?.state}, city: ${it.location?.city}, street: ${it.location?.street}"
                            val age =
                                "Age: ${it.dob?.age} years"
                            bundle.putString(ItemFragment().EMAIL_EXTRA,it.email)
                            bundle.putString(ItemFragment().IMAGE_URL_EXTRA,it.picture?.large)
                            bundle.putString(ItemFragment().NAME_EXTRA,fullName)
                            bundle.putString(ItemFragment().BIRTHDAY_EXTRA,age)
                            bundle.putString(ItemFragment().ADDRESS_EXTRA,address)
                            bundle.putString(ItemFragment().PHONE_EXTRA,it.phone)
                            val itemFragment = ItemFragment()
                            itemFragment.arguments = bundle
                            val ft = fragmentManager?.beginTransaction()
                            ft?.replace(R.id.container_list_id,itemFragment)?.addToBackStack("ListFragment")?.commit()
                        }
                        recyclerView.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        recyclerView.adapter = recyclerViewAdapter
                    }
                }

                override fun onFailure(call: Call<ResponseResult>, t: Throwable) {
                    toast(t.toString())
                    Log.d("AOA",t.toString())
                }
            })
    }


}

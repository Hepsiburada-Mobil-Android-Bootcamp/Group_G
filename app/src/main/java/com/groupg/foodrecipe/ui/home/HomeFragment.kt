package com.groupg.foodrecipe.ui.home

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.groupg.foodrecipe.adapter.FoodCardAdapter
import com.groupg.foodrecipe.databinding.FoodAddDialogBinding
import com.groupg.foodrecipe.databinding.FragmentHomeBinding
import java.net.URI

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private  lateinit var bindingAlert:FoodAddDialogBinding
    lateinit var  imageUri:Uri
    lateinit var dialog:Dialog

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.rv.layoutManager=GridLayoutManager(requireContext(),2)
        binding.rv.adapter=FoodCardAdapter("data")

        bindingAlert= FoodAddDialogBinding.inflate(inflater,container,false)

        /*bindingAlert.textViewUploadImage.setOnClickListener {


            selectImage()

        }*/

        bindingAlert.imageButtonDialogExit.setOnClickListener {

            dialog.cancel()
            dialog.dismiss()
            if(bindingAlert.root.parent !=null)
            {
                val view:View=bindingAlert.root
                val viewgroup:ViewGroup=bindingAlert.root.parent as ViewGroup
                viewgroup.removeView(view)




            }

        }
        bindingAlert.buttonAddFood.setOnClickListener{




        }

        binding.fabFoodAdd.setOnClickListener {

            showDialog()

        }



       binding.editTextFood.doAfterTextChanged {

           homeViewModel.searchFood(it.toString())


       }

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    fun showDialog()
    {

        dialog=Dialog(requireContext())
        dialog.setContentView(bindingAlert.root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.create()
        dialog.show()



    }

    private fun selectImage()
    {
        val bundle = Bundle()
        bundle.putInt("requestCode",100)
        var intent=Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent,100)




    }
    private fun addFood(){





    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==100 )
        {
          imageUri=data?.data!!
            bindingAlert.imageViewFoodImage.setImageURI(imageUri)


        }
    }



}
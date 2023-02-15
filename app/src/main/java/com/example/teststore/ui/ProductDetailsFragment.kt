package com.example.teststore.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.teststore.R
import com.example.teststore.adapter.NewProductAdapter
import com.example.teststore.databinding.FragmentCartBinding
import com.example.teststore.databinding.FragmentProductDetailsBinding
import com.example.teststore.model.Product
import com.example.teststore.roomdb.ProductEntity
import com.example.teststore.util.downloadFromUrl
import com.example.teststore.util.placeHolderProgressBar
import com.example.teststore.viewmodel.BasketViewModel
import com.example.teststore.viewmodel.HomeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : DialogFragment(R.layout.fragment_product_details) {

    private var fragmentBinding : FragmentProductDetailsBinding? = null
    private val args : ProductDetailsFragmentArgs by navArgs()
    private val newProductAdapter by lazy { NewProductAdapter() }
    private val viewModel: HomeViewModel by viewModels()
    private val basketViewModel: BasketViewModel by viewModels()

    lateinit var pName : String
    var qua : Int = 1
    var pPrice : Int = 0
    lateinit var pPid : String
    lateinit var pImage : String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentProductDetailsBinding.bind(view)
        fragmentBinding = binding

        val detail = args.product

        pImage = detail.productImage
        pName = detail.productName
        pPrice = detail.productPrice
        pPid = detail.productId.toString()

        with(fragmentBinding!!){

            productPriceProductDetailFrag.text = "$${detail.productPrice}"
            productNameProductDetailFrag.text = detail.productName
            productDesProductDetailFrag.text = detail.productDes
            productBrandProductDetailFrag.text = detail.productBrand
            productImageProductDetailPage.downloadFromUrl(detail.productImage,
                CircularProgressDrawable(requireContext())
            )
            ratingProductDetails.text = detail.productRating.toString()
            productRatingSingleProduct.rating = detail.productRating.toFloat()
        }

        binding.recomRecViewProductDetailPage.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        observeLiveData()

        binding.addToCartProductDetailsPage.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(
                requireContext(), R.style.BottomSheetDialogTheme
            )

            val bottomSheetView = LayoutInflater.from(context).inflate(
                R.layout.fragment_add_to_bag,
                null
            )

            bottomSheetView.findViewById<View>(R.id.addToCart_BottomSheet).setOnClickListener {
                pPrice *= bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).text.toString().toInt()
                addProductToBasket()
                bottomSheetDialog.dismiss()
            }

            bottomSheetView.findViewById<LinearLayout>(R.id.minusLayout).setOnClickListener{
                if (bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).text.toString()
                        .toInt() > 1){
                    qua--
                    bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).setText(qua.toString())
                }
            }

            bottomSheetView.findViewById<LinearLayout>(R.id.plusLayout).setOnClickListener {
                if(bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).text.toString()
                        .toInt() < 10){
                    qua++
                    bottomSheetView.findViewById<EditText>(R.id.quantityEtBottom).setText(qua.toString())
                }
            }

            bottomSheetDialog.setContentView(bottomSheetView)
            bottomSheetDialog.show()
        }
    }

    private fun observeLiveData() {
        with(fragmentBinding!!){
            viewModel.productsListNew.observe(viewLifecycleOwner, Observer {
                newProductAdapter.produts = it
                recomRecViewProductDetailPage.adapter = newProductAdapter
            })
        }
    }

    private fun addProductToBasket(){
        basketViewModel.insert(ProductEntity(pName,qua,pPrice,pPid,pImage))
    }
}
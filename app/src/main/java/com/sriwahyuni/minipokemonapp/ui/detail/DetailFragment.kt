package com.sriwahyuni.minipokemonapp.ui.detail

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sriwahyuni.minipokemonapp.R
import com.sriwahyuni.minipokemonapp.databinding.FragmentDetailBinding
import com.sriwahyuni.minipokemonapp.model.response.PokemonDetail.PokemonDetail
import com.sriwahyuni.minipokemonapp.model.response.PokemonList.PokemonItem
import com.sriwahyuni.minipokemonapp.utils.Helpers
import kotlinx.android.synthetic.main.dialog_add_pokemon.view.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_list.view.*
import kotlin.random.Random

class DetailFragment : Fragment(), DetailContract.View {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var presenter: DetailPresenter
    var progressDialog: Dialog? = null
    var item: PokemonItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as DetailActivity).toolbarDetail()

        initView()
        item = requireActivity().intent.extras!!.getParcelable("data")
        presenter = DetailPresenter(this)
        item?.name?.let { presenter.getDetail(it) }
        binding.button.setOnClickListener { showPopup() }
    }

    private fun showPopup() {
        val addPokemonDialog = context?.let { BottomSheetDialog(it) }
        val view = layoutInflater.inflate(R.layout.dialog_add_pokemon, null)


        view.button.setOnClickListener {
            if (view.nickName.text.toString().isEmpty()) {
                Toast.makeText(context, "Nickname Pokemon wajib diisi!", Toast.LENGTH_SHORT).show()
            } else {
                item?.let { it1 ->
                    context?.let { it2 ->
                        presenter.addPokemon(
                            it2, view.nickName.text.toString(),
                            it1
                        )
                    }
                }
                addPokemonDialog?.dismiss()
                requireActivity().finish()
            }

        }

        addPokemonDialog!!.setContentView(view)
        addPokemonDialog.show()

    }

    @SuppressLint("InflateParams")
    private fun initView() {
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    private fun initView(data: PokemonDetail) {
        binding.name.text = data.name
        binding.index.text = data.getIdString()
        binding.weight.text = data.getWeightString()
        binding.height.text = data.getHeightString()
        binding.progressHp.progress = Random.nextInt(maxHp)
        binding.progressAttack.progress = Random.nextInt(maxAttack)
        binding.progressDefense.progress = Random.nextInt(maxDefense)
        binding.progressSpeed.progress = Random.nextInt(maxSpeed)
        binding.progressExp.progress = Random.nextInt(maxExp)
        Glide.with(this)
            .load(item!!.url?.let { Helpers.getImageUrl(it) })
            .into(image)
    }

    override fun onDetailSuccess(data: PokemonDetail) {
        initView(data)
    }

    override fun onDetailFailed(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    companion object {
        const val maxHp = 300
        const val maxAttack = 300
        const val maxDefense = 300
        const val maxSpeed = 300
        const val maxExp = 1000
    }

}
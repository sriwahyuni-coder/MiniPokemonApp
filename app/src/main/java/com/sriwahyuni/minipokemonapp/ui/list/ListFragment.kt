package com.sriwahyuni.minipokemonapp.ui.list

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sriwahyuni.minipokemonapp.R
import com.sriwahyuni.minipokemonapp.databinding.FragmentListBinding
import com.sriwahyuni.minipokemonapp.model.response.PokemonList.PokemonItem
import com.sriwahyuni.minipokemonapp.ui.MainActivity
import com.sriwahyuni.minipokemonapp.ui.detail.DetailActivity

class ListFragment : Fragment(), ListContract.View, ListAdapter.Callback {
    private lateinit var binding: FragmentListBinding
    private lateinit var presenter: ListPresenter
    var progressDialog: Dialog? = null
    var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as MainActivity).toolbarList()

        initView()
        presenter = ListPresenter(this)
        presenter.getList()
        binding.swipeRefresh.setOnRefreshListener { presenter.getList() }
    }

    private fun initView() {
        bundle = Bundle()
        progressDialog = Dialog(requireContext())
        val dialogLayout = layoutInflater.inflate(R.layout.dialog_loader, null)
        progressDialog?.let {
            it.setContentView(dialogLayout)
            it.setCancelable(false)
            it.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }


    override fun onListSuccess(list: List<PokemonItem>) {
        binding.swipeRefresh.isRefreshing = false
        val adapter = ListAdapter(list, this)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layoutManager
    }

    override fun onListFailed(message: String) {
        binding.swipeRefresh.isRefreshing = false
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }

    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    override fun onItemClick(v: View, data: PokemonItem) {
        val detail = bundle?.let { Intent(activity, DetailActivity::class.java).putExtras(it) }
        startActivity(detail)
    }
}
package com.enpassion.twowaydatabindingkotlin.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.transaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.*
import com.enpassion.twowaydatabindingkotlin.R
import com.enpassion.twowaydatabindingkotlin.data.ToyEntry
import com.enpassion.twowaydatabindingkotlin.data.UIState
import com.enpassion.twowaydatabindingkotlin.databinding.FragmentListBinding
import com.enpassion.twowaydatabindingkotlin.viewmodel.MainViewModel
import org.jetbrains.anko.design.longSnackbar

const val CHOSEN_TOY = "chosenToy"

class ToyListFragment : androidx.fragment.app.Fragment(), ToyAdapter.ToyClickListener {

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)
    }
    private lateinit var mAdapter: ToyAdapter
    private lateinit var binding: FragmentListBinding
    private var mToyList: List<ToyEntry>? = null

    init {
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        //Set adapter, divider and default animator to the recycler view
        mAdapter = ToyAdapter(this)
        val dividerItemDecoration = DividerItemDecoration(
            requireActivity(), LinearLayoutManager.VERTICAL
        )
        with(binding.recycler) {
            addItemDecoration(dividerItemDecoration)
            itemAnimator = DefaultItemAnimator()
            adapter = mAdapter
        }

        //When fab clicked, open AddToyFragment
        binding.fab.setOnClickListener { openAddToyFrag(AddToyFragment()) }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        //Get the view model instance and pass it to the binding implementation
        binding.uiState = mViewModel.uiState

        //Claim list of toys from view model
        mViewModel.toyList?.observe(this, Observer { toyEntries ->
            if (toyEntries.isNullOrEmpty()) {
                mViewModel.uiState.set(UIState.EMPTY)
            } else {
                mViewModel.uiState.set(UIState.HAS_DATA)
                mAdapter.toyList = toyEntries
                mToyList = toyEntries
            }
        })

        //Attach an ItemTouchHelper for swipe-to-delete functionality
        val coordinator: CoordinatorLayout? = activity?.findViewById(R.id.main_coordinator)
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                //First take a backup of the toy to erase
                //If user is swiping an item, we can assume that list is not null
                val toyToErase = mToyList!![position]

                //Then delete the toy
                mViewModel.deleteToy(toyToErase)

                //Show a snack bar for undoing delete
                coordinator?.longSnackbar(R.string.toy_is_deleted, R.string.undo){
                    mViewModel.insertToy(toyToErase)
                }
            }
        }).attachToRecyclerView(binding.recycler)
    }

    override fun onToyClicked(chosenToy: ToyEntry) {
        //Pass chosen toy id to the AddToyFragment
        val args = Bundle()
        args.putParcelable(CHOSEN_TOY, chosenToy)
        val frag = AddToyFragment()
        frag.arguments = args

        //Open AddToyFragment in edit form
        openAddToyFrag(frag)
    }

    private fun openAddToyFrag(frag: AddToyFragment) {
        fragmentManager?.transaction {
            replace(R.id.main_container, frag)
            addToBackStack(null)
        }
    }
}
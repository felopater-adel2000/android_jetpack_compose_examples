package com.restart.jetpack_compose_examples.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.restart.jetpack_compose_examples.ScreenDirectionEventHandler
import com.restart.jetpack_compose_examples.databinding.FragmentListBinding
import com.restart.jetpack_compose_examples.datastore.SessionManager
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : BaseFragment() {
    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModel()

    private val x = MutableLiveData<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.apply {
            setContent {
                val viewState by viewModel.viewState.collectAsState()
                val sessionManager by viewModel.dataStore.data.collectAsState(SessionManager())
                ListScreen(
                    token = sessionManager.token,
                    viewState = viewState,
                    onAction = viewModel::onAction
                )

                ScreenDirectionEventHandler(
                    viewModel = viewModel,
                    navController = findNavController()
                )
            }
        }

        x.observeHere {

        }
    }
}


abstract class BaseFragment : Fragment() {

    fun <T> LiveData<T>.observeHere(observer: Observer<T>) {
        observe(viewLifecycleOwner, observer)
    }

}
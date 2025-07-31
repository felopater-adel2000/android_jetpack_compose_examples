package com.restart.jetpack_compose_examples.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.restart.jetpack_compose_examples.databinding.FragmentDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModel()

    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.apply {
            viewModel.onAction(DetailsAction.InitScreen(args.product))
            setContent {
                val viewState by viewModel.viewState.collectAsState()
                DetailsScreen(
                    viewState = viewState,
                    onAction = viewModel::onAction
                )
            }

        }
    }

}
package com.example.orderlite.presentation.listUnitsOM.unitOm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.orderlite.databinding.FragmentUnitOMBinding

const val SCREEN_MODE = "extra_mode"
const val MODE_ADD = "mode_add"
const val MODE_EDIT = "mode_edit"

class UnitOMFragment : Fragment() {
    private var screenMode: String? = null

    private lateinit var viewModel: UnitOMViewModel

    private lateinit var _binding:FragmentUnitOMBinding
    private val binding
    get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentUnitOMBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UnitOMViewModel::class.java]
        setOnClickListeners()

    }

    fun setOnClickListeners(){
        binding.btnSaveUnitOM.setOnClickListener {
            val name = binding.etName.text.toString()
            val shortName = binding.etShortName.text.toString()
            viewModel.addNewUnitOM(name,shortName)

        }
    }



    fun parseParams(){
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)){
            throw RuntimeException("Param Screen Mode is absent")
        }
        if (args.get(SCREEN_MODE)!= MODE_ADD&&args.get(SCREEN_MODE)!= MODE_EDIT){
            throw RuntimeException("Unknown Screen Mode")
        }
        screenMode = args.getString(SCREEN_MODE)
    }

    companion object {
        @JvmStatic
        fun newInstance(mode: String) =
            UnitOMFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, mode)
                    }
            }

    }
}
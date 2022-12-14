package com.example.orderlite.presentation.units_o_m.unit_o_m

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.orderlite.R
import com.example.orderlite.databinding.FragmentUnitOMBinding
import com.example.orderlite.presentation.FragmentNameInstaller


const val SCREEN_MODE = "extra_mode"
const val MODE_ADD = "mode_add"
const val MODE_EDIT = "mode_edit"
const val ITEM_ID = "item_id"
const val DEFAULT_ID: Int = 0

class UnitOMFragment : Fragment() {
    private var screenMode: String? = null
    private var unitOMId: Int = DEFAULT_ID

    private lateinit var viewModel: UnitOMViewModel
    private lateinit var fragmentNameInstaller:FragmentNameInstaller

    private lateinit var _binding: FragmentUnitOMBinding
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
        fragmentNameInstaller = FragmentNameInstaller
        fragmentNameInstaller.setName(R.string.unit_o_m_item)
        launchRightMode()
        setTextChangeListeners()
        observeViewModel()
        observeErrorInput()
    }

    private fun observeViewModel() {
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            parentFragmentManager.popBackStack()
        }
    }

    private fun observeErrorInput() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            if (it) binding.tilName.error = R.string.error_input_name.toString()
            else binding.tilName.error = null
        }
        viewModel.errorInputShortName.observe(viewLifecycleOwner) {
            if (it) binding.tilShortName.error = R.string.error_input_short_name.toString()
            else binding.tilShortName.error = null
        }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_ADD -> launchAddMode()
            MODE_EDIT -> launchEditMode()
        }
    }

    private fun launchAddMode() {
        setAddClickListener()
    }

    private fun launchEditMode() {
        binding.btnDeleteUnitOM.visibility = View.VISIBLE
        viewModel.getUnitOM(unitOMId)
        viewModel.unitOMItem.observe(viewLifecycleOwner) {
            binding.etName.setText(it.name)
            binding.etShortName.setText(it.shortName)
        }
        setEditClickListeners()
    }

    private fun setTextChangeListeners() {
        binding.etName.addTextChangedListener (
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.resetErrorInputName()
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            }
            )
        binding.etShortName.addTextChangedListener (
            object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.resetErrorInputShortName()
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            }
            )
    }

    private fun setEditClickListeners() {
        binding.btnSaveUnitOM.setOnClickListener {
            val name = binding.etName.text.toString()
            val shortName = binding.etShortName.text.toString()
            viewModel.editUnitOM(unitOMId, name, shortName)
        }
        binding.btnDeleteUnitOM.setOnClickListener {
            viewModel.deleteUnitOMItem(unitOMId)
        }
    }

    private fun setAddClickListener() {
        binding.btnSaveUnitOM.setOnClickListener {
            val name = binding.etName.text.toString()
            val shortName = binding.etShortName.text.toString()
            viewModel.addNewUnitOM(name, shortName)
        }
    }


    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param Screen Mode is absent")
        }
        if (args.getString(SCREEN_MODE) != MODE_ADD && args.getString(SCREEN_MODE) != MODE_EDIT) {
            throw RuntimeException("Unknown Screen Mode")
        }
        screenMode = args.getString(SCREEN_MODE)
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(ITEM_ID)) throw RuntimeException("UnitOMId is absent")
            else unitOMId = args.getInt(ITEM_ID)
        }
    }

    companion object {
        @JvmStatic
        fun newInstanceEditItem(unitOMId: Int) =
            UnitOMFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(ITEM_ID, unitOMId)
                }
            }

        @JvmStatic
        fun newInstanceAddItem(mode: String) =
            UnitOMFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, mode)
                }
            }
    }
}
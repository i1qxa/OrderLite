package com.example.orderlite.presentation.order_record.order_record_item

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.orderlite.data.orderRecord.OrderRecordRepositoryImpl
import com.example.orderlite.data.productItem.ProductItemRepositoryImpl
import com.example.orderlite.data.unitsOM.UnitsOMRepositoryImpl
import com.example.orderlite.domain.orderRecord.AddOrderRecordUseCase
import com.example.orderlite.domain.productItem.GetProductItemUseCase
import com.example.orderlite.domain.unitsOfMeasurement.GetListUnitsOMUseCase
import com.example.orderlite.domain.unitsOfMeasurement.GetUnitsOMUseCase

class OrderRecordItemViewModel(application: Application):AndroidViewModel(application){
    private var repositoryOrderRecord = OrderRecordRepositoryImpl(application)
    private var repositoryProductItem = ProductItemRepositoryImpl(application)
    private var repositoryUnitOM = UnitsOMRepositoryImpl(application)

    private var addOrderRecordUseCase = AddOrderRecordUseCase(repositoryOrderRecord)
    private var getProductItemUseCase = GetProductItemUseCase(repositoryProductItem)
    private var getUnitOMItemUseCase = GetUnitsOMUseCase(repositoryUnitOM)
    private var getListUnitOMUseCase = GetListUnitsOMUseCase(repositoryUnitOM)

}
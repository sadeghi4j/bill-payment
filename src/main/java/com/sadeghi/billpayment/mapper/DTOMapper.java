package com.sadeghi.billpayment.mapper;

import com.sadeghi.billpayment.entity.Bill;
import com.sadeghi.billpayment.entity.Payment;
import com.sadeghi.billpayment.model.BillDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DTOMapper {

    DTOMapper INSTANCE = Mappers.getMapper(DTOMapper.class);

    BillDto convert(Bill bill);
    Payment convertBillToPayment(Bill bill);

}
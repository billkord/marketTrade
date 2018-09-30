package com.market.trade.service;

import com.market.trade.converter.ConsumeMessageDtoToMessage;
import com.market.trade.dto.ConsumeMessageDTO;
import com.market.trade.exception.IllegalCurrencySymbol;
import com.market.trade.exception.InvalidTimestampException;
import com.market.trade.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessorService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ConsumeMessageDtoToMessage consumeMessageDtoToMessage;

    public void processMessage (ConsumeMessageDTO messageDTO) throws InvalidTimestampException, IllegalCurrencySymbol {
        Message message = consumeMessageDtoToMessage.apply(messageDTO);
        messageService.processAndSave(message);
    }
}

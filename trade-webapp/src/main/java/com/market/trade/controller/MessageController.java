package com.market.trade.controller;

import com.market.trade.dto.ConsumeMessageDTO;
import com.market.trade.exception.IllegalCurrencySymbol;
import com.market.trade.exception.InvalidTimestampException;
import com.market.trade.repository.MessageRepository;
import com.market.trade.service.PersonService;
import com.market.trade.service.ProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@BasePathAwareController
@RequestMapping("messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private ProcessorService processorService;

    @PostMapping(value = "consume")
    public ResponseEntity<?> schedulingPartitioningCheck(@RequestBody @Valid ConsumeMessageDTO consumeMessageDTO, Principal principal) {
        // validate user
        Long userId = consumeMessageDTO.getUserId();
        if (!personService.isValid(userId, principal)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
        }
        try {
            processorService.processMessage(consumeMessageDTO);
        } catch (InvalidTimestampException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Date format should be dd-MMM-yy hh:mm:ss");
        } catch (IllegalCurrencySymbol e1) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e1.getMessage() + " currency is not acceptable. Please contact for further information");
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "search/findTotal")
    public ResponseEntity<?> findTotal () {
        Long count = messageRepository.countAllValid();
        return ResponseEntity.ok(count);
    }

}

package com.watts.controller;

import com.watts.service.PropagationClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PropagationClientController {

    PropagationClientService propagationClientService;

    @GetMapping("/transaction/{sourceTxnId}/{message}")
    public String sendTransaction(@PathVariable String sourceTxnId, @PathVariable String message) {
        return propagationClientService.sendTransaction(sourceTxnId,message);
    }

}

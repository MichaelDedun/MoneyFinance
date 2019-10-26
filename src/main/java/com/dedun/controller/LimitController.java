package com.dedun.controller;


import com.dedun.converter.LimitConverter;
import com.dedun.dto.request.LimitRequest;
import com.dedun.dto.response.LimitResponse;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.service.LimitService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/limit")
public class LimitController {
    private final LimitService limitService;
    private final LimitConverter limitConverter;

    public LimitController(LimitService limitService, LimitConverter limitConverter) {
        this.limitService = limitService;
        this.limitConverter = limitConverter;
    }

    @PostMapping(value = "{id}")
    public LimitResponse createNewLimit(@AuthenticationPrincipal User user,
                                        @PathVariable(value = "id") int id, @RequestBody @Valid LimitRequest request) throws MoneyException {
        return limitConverter.from(limitService.saveLimit(id,user,request));
    }
}

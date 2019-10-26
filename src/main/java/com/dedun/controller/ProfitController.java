package com.dedun.controller;

import com.dedun.converter.ProfitConverter;
import com.dedun.dto.request.ProfitRequest;
import com.dedun.dto.response.ProfitResponse;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.service.ProfitService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profit")
public class ProfitController {
    private final ProfitConverter profitConverter;
    private final ProfitService profitService;

    public ProfitController(ProfitConverter profitConverter, ProfitService profitService) {
        this.profitConverter = profitConverter;
        this.profitService = profitService;
    }

    @PostMapping
    public ProfitResponse createNewProfit(@AuthenticationPrincipal User user,
                                          @RequestBody @Valid ProfitRequest request) throws MoneyException {
        return profitConverter.from(profitService.save(request,user));
    }

    @GetMapping(value = "all/{id}")
    public List<ProfitResponse> getAll(@PathVariable int id) {
        return profitService.getAll(id).stream().map(profitConverter::from).collect(Collectors.toList());
    }

    @GetMapping(value = "{id}")
    public ProfitResponse getProfit(@AuthenticationPrincipal User user,
                                    @PathVariable(value = "id") int id) throws MoneyException {
        return profitConverter.from(profitService.get(user,id));
    }

    @PutMapping(value = "{id}")
    public ProfitResponse edit(@AuthenticationPrincipal User user,
                               @PathVariable(value = "id") int id,
                               @RequestBody @Valid ProfitRequest profitRequest) throws MoneyException {
        return profitConverter.from(profitService.edit(profitRequest,user,id));
    }

    @PatchMapping(value = "{id}")
    public ProfitResponse updateState(@AuthenticationPrincipal User user,
                                      @PathVariable(value = "id") int id) throws MoneyException {
        return profitConverter.from(profitService.updateState(user,id));
    }

    @DeleteMapping(value = "{id}")
    public void deleteProfitById(@AuthenticationPrincipal User user,
                                 @PathVariable(value = "id") int id) throws MoneyException {
        profitService.delete(user, id);
    }
}

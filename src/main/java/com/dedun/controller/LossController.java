package com.dedun.controller;

import com.dedun.converter.LossConverter;
import com.dedun.dto.request.LossRequest;
import com.dedun.dto.response.LossResponse;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.service.LossService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loss")
public class LossController {
    private final LossService lossService;
    private final LossConverter lossConverter;

    public LossController(LossService lossService, LossConverter lossConverter) {
        this.lossService = lossService;
        this.lossConverter = lossConverter;
    }

    @PostMapping()
    public LossResponse create(@AuthenticationPrincipal User user,
                               @RequestBody @Valid LossRequest lossRequest) throws MoneyException {
        return lossConverter.from(lossService.saveLoss(lossRequest,user));
    }

    @GetMapping(value = "all/{id}")
    public List<LossResponse> getLosses(@PathVariable int id) {
        return lossService.getAll(id).stream().map(lossConverter::from).collect(Collectors.toList());
    }

    @GetMapping(value = "{id}")
    public LossResponse getLoss(@AuthenticationPrincipal User user,
                                @PathVariable(value = "id") int id) throws MoneyException {
        return lossConverter.from(lossService.get(user,id));
    }

    @PutMapping(value = "{id}")
    public LossResponse edit(@AuthenticationPrincipal User user,
                               @PathVariable(value = "id") int id,
                               @RequestBody @Valid LossRequest lossRequest) throws MoneyException {
        return lossConverter.from(lossService.edit(lossRequest,user,id));
    }

    @PatchMapping(value = "{id}")
    public LossResponse updateState(@AuthenticationPrincipal User user,
                                    @PathVariable(value = "id") int id) throws MoneyException {
        return lossConverter.from(lossService.updateState(user,id));
    }

    @DeleteMapping(value = "{id}")
    public void delete(@AuthenticationPrincipal User user,
                       @PathVariable(value = "id") int id) throws MoneyException {
        lossService.delete(user, id);
    }
}

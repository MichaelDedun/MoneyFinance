package com.dedun.controller;

import com.dedun.converter.WalletConverter;
import com.dedun.dto.request.WalletRequest;
import com.dedun.dto.response.WalletResponse;
import com.dedun.exception.MoneyException;
import com.dedun.model.User;
import com.dedun.service.WalletService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    private final WalletService walletService;
    private final WalletConverter walletConverter;

    public WalletController(WalletService walletService,
                            WalletConverter walletConverter) {
        this.walletService = walletService;
        this.walletConverter = walletConverter;
    }

    @PostMapping
    public WalletResponse createNewWallet(@AuthenticationPrincipal User user,
                                          @RequestBody @Valid WalletRequest walletRequest) {
        return walletConverter.from(walletService.save(walletRequest,user));
    }

    @GetMapping
    public List<WalletResponse> getWallets(@AuthenticationPrincipal User user) {
        return walletService.getAll(user.getId())
                .stream()
                .map(walletConverter::from).collect(Collectors.toList());
    }

    @GetMapping(value = "{id}")
    public WalletResponse getWalletById(@AuthenticationPrincipal User user,
                                        @PathVariable(value = "id") int id) throws MoneyException {
        return walletConverter.from(walletService.get(user,id));
    }

    @PutMapping(value = "{id}")
    public WalletResponse editWallet(@AuthenticationPrincipal User user,
                                   @PathVariable(value = "id") int id,
                                   @RequestBody @Valid WalletRequest walletRequest) throws MoneyException {
        return walletConverter.from(walletService.edit(walletRequest,user,id));
    }

    @PatchMapping(value = "{id}")
    public WalletResponse updateState(@AuthenticationPrincipal User user,
                                      @PathVariable(value = "id") int id) throws MoneyException {
        return walletConverter.from(walletService.updateState(user,id));
    }

    @DeleteMapping
    public void deleteAllWallets(@AuthenticationPrincipal User user) {
        walletService.deleteAll(user);
    }

    @DeleteMapping(value = "{id}")
    public void deleteWalletById(@AuthenticationPrincipal User user,
                                 @PathVariable(value = "id") int id) throws MoneyException {
        walletService.delete(user, id);
    }
}

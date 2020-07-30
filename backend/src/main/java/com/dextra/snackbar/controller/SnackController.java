package com.dextra.snackbar.controller;

import com.dextra.snackbar.controller.dto.SnackDTO;
import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.service.SnackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("snacks")
public class SnackController {

    private SnackService snackService;

    public SnackController(SnackService snackService) {
        this.snackService = snackService;
    }

    @GetMapping
    public ResponseEntity<List<SnackDTO>> listMenuSnacks() {
        List<Snack> menuSnacks = snackService.getMenuSnacks();
        List<SnackDTO> snackDTOS = SnackDTO.convertList(menuSnacks);

        return ResponseEntity.ok(snackDTOS);
    }
}

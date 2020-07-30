package com.dextra.snackbar.service;

import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.repository.SnackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackService {

    private SnackRepository snackRepository;

    public SnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    public List<Snack> getMenuSnacks() {
        return snackRepository.findByIsMenuItemTrue();
    }
}

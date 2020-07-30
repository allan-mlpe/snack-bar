package com.dextra.snackbar.service;

import com.dextra.snackbar.repository.SnackRepository;
import org.springframework.stereotype.Service;

@Service
public class SnackService {

    private SnackRepository snackRepository;

    public SnackService(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

}

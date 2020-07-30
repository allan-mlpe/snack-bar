package com.dextra.snackbar.controller.dto;

import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.model.SnackItem;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SnackDTO extends ApiResponse {
    private UUID id;

    private String description;

    private List<SnackItemDTO> snackItemDTOS;

    public SnackDTO() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SnackItemDTO> getSnackItemDTOS() {
        return snackItemDTOS;
    }

    public void setSnackItemDTOS(List<SnackItemDTO> snackItemDTOS) {
        this.snackItemDTOS = snackItemDTOS;
    }

    public static SnackDTO convert(Snack snack) {
        SnackDTO snackDTO = new SnackDTO();
        snackDTO.setId(snack.getId());
        snackDTO.setDescription(snack.getDescription());

        List<SnackItemDTO> snackItemDTOS = SnackItemDTO.convertList(snack.getSnackItems());

        snackDTO.setSnackItemDTOS(snackItemDTOS);

        return snackDTO;
    }

    public Snack toModel() {
        Snack snack = new Snack();

        snack.setId(this.id);
        snack.setDescription(this.description);

        List<SnackItem> snackItems = snackItemDTOS.stream()
                .map(snackItemDTO -> snackItemDTO.toModel())
                .collect(Collectors.toList());

        snack.setSnackItems(snackItems);

        return snack;
    }
}

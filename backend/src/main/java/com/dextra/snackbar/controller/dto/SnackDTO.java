package com.dextra.snackbar.controller.dto;

import com.dextra.snackbar.model.Snack;
import com.dextra.snackbar.model.SnackItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class SnackDTO extends ApiResponse {
    private UUID id;

    private String description;

    private List<SnackItemDTO> snackItems;

    @JsonProperty("menuItem")
    private Boolean isMenuItem;

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

    public List<SnackItemDTO> getSnackItems() {
        return snackItems;
    }

    public void setSnackItems(List<SnackItemDTO> snackItems) {
        this.snackItems = snackItems;
    }

    public Boolean isMenuItem() {
        return isMenuItem;
    }

    public void setIsMenuItem(Boolean custom) {
        isMenuItem = custom;
    }

    public static SnackDTO convert(Snack snack) {
        SnackDTO snackDTO = new SnackDTO();
        snackDTO.setId(snack.getId());
        snackDTO.setDescription(snack.getDescription());
        snackDTO.setIsMenuItem(snack.isMenuItem());

        List<SnackItemDTO> snackItemDTOS = SnackItemDTO.convertList(snack.getSnackItems());
        snackDTO.setSnackItems(snackItemDTOS);

        return snackDTO;
    }

    public static List<SnackDTO> convertList(List<Snack> snacks) {
        return snacks.stream()
                .map(snack -> SnackDTO.convert(snack))
                .collect(Collectors.toList());
    }

    public Snack toModel() {
        Snack snack = new Snack();

        snack.setId(this.id);
        snack.setDescription(this.description);
        snack.setMenuItem(this.isMenuItem);

        List<SnackItem> snackItems = this.snackItems.stream()
                .map(snackItemDTO -> snackItemDTO.toModel())
                .collect(Collectors.toList());
        snack.setSnackItems(snackItems);

        return snack;
    }
}

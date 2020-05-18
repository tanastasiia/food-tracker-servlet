package ua.training.model.dto;

public interface GenericDto<T, R> {
    R toEntity();
}

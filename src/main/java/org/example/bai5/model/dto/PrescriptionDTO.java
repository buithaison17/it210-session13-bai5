package org.example.bai5.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrescriptionDTO {
    private Long id;
    private String doctorName;
    private String patientName;
    private String description;
    @NotNull(message = "Số lượng thuốc không được trống")
    @Min(value = 1, message = "Số lượng thuốc phải lớn hơn 0")
    private Integer quantity;
}

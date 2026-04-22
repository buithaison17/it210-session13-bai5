package org.example.bai5.controller;

import jakarta.validation.Valid;
import org.example.bai5.model.dto.PrescriptionDTO;
import org.example.bai5.service.PrescriptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @GetMapping("/prescriptions")
    public String viewList(Model model, @RequestParam(name = "search", required = false) String search) {
        if (search == null || search.isEmpty()) {
            model.addAttribute("list", prescriptionService.findAll());
        } else {
            model.addAttribute("list", prescriptionService.findByPatientName(search));
        }
        return "prescriptions";
    }

    @GetMapping("/add-prescription")
    public String addPrescription(Model model) {
        model.addAttribute("prescription", new PrescriptionDTO());
        return "add_prescription";
    }

    @PostMapping("/add-prescription")
    public String savePrescription(
            @Valid @ModelAttribute("prescription") PrescriptionDTO prescription,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "add_prescription";
        }
        prescriptionService.save(prescription);
        return "redirect:/prescriptions";
    }
}

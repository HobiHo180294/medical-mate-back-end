package com.appointments.controller;

import com.appointments.dto.AppointmentResponseDto;
import com.appointments.dto.AvailableTimeSlotsRequestDto;
import com.appointments.dto.CreateAppointmentDto;
import com.appointments.service.AppointmentService;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public void createAppointment(@RequestBody CreateAppointmentDto dto) {
        appointmentService.createAppointment(dto);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByUserId(@PathVariable String clientId) {
        List<AppointmentResponseDto> allAppointmentsByClientId = appointmentService.getAllAppointmentsByClientId(clientId);
        return ResponseEntity.ok(allAppointmentsByClientId);
    }

    @GetMapping("/freeTimeslots")
    public ResponseEntity<Map<String, Boolean>> getFreeTimeSlotsForDepartmentAndSpecificDate(
            @RequestBody AvailableTimeSlotsRequestDto dto) {
        Map<String, Boolean> result = appointmentService.getTimeSlotsInformation(dto);
        return ResponseEntity.ok(result);
    }
}

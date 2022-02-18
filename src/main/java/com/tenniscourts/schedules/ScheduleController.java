package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/scheduler")
@Api(value = "Create scheduler for tennis court")
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;
    @PostMapping("/save")
   	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> addScheduleTennisCourt(CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    @GetMapping("/findDates")
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@RequestParam(value= "startDate") final LocalDate startDate,
    		@RequestParam(value= "endDate") final LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @GetMapping("/findById/{scheduleId}")
	@ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ScheduleDTO> findByScheduleId(@PathVariable("scheduleId") Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}

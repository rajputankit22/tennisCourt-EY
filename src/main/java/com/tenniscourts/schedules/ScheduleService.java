package com.tenniscourts.schedules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleMapper scheduleMapper;
    
    private final TennisCourtRepository tennisCourtRepository;

    public ScheduleDTO addSchedule(Long tennisCourtId, CreateScheduleRequestDTO createScheduleRequestDTO) {
    	TennisCourt tc = tennisCourtRepository.findbyId(tennisCourtId);
    	ScheduleDTO schedule = new ScheduleDTO();
    	schedule.setTennisCourt(tc);
    	scheduled.setTennisCourtId(tennisCourtId);
    	schedule.setStartDateTime(createScheduleRequestDTO.getStartDateTime());
    	 LocalDateTime endDate =ldt.minus(1, ChronoUnit.HOURS);
    	 schedule.setEndDateTime(endDate);
    	 return ScheduleMapper.map(scheduleRepository.saveAndFlush(ScheduleMapper.map(createScheduleRequestDTO)));
    }

    public List<ScheduleDTO> findSchedulesByDates(LocalDateTime startDate, LocalDateTime endDate) {
    	return scheduleRepository.findByByStartDateTimeAndEndDateTime(startDate,endDate).map(scheduleMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("schedule not found.");
        });
    }

    public ScheduleDTO findSchedule(Long scheduleId) {
    	return scheduleRepository.findById(scheduleId).map(scheduleMapper::map).orElseThrow(() -> {
            throw new EntityNotFoundException("schedule not found.");
        });
    }

    public List<ScheduleDTO> findSchedulesByTennisCourtId(Long tennisCourtId) {
        return scheduleMapper.map(scheduleRepository.findByTennisCourt_IdOrderByStartDateTime(tennisCourtId));
    }
}

package com.example.loa.Service;

import com.example.loa.Entity.Schedule;
import com.example.loa.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule init(){
        Schedule schedule = new Schedule();
        schedule.setValtan(false);
        schedule.setBiakiss(false);
        schedule.setKuke(false);
        schedule.setAbrel12(false);
        schedule.setAbrel34(false);
        schedule.setAbrel56(false);
        schedule.setAkkan(false);
        schedule.setKkayangel(false);
        schedule.setKhamen(false);
        schedule.setSanghatop(false);
        try{
            Schedule result = scheduleRepository.save(schedule);
            System.out.println(result);
            return result;
        }catch(Exception e){
            System.out.println(String.format("[Error] %s", e));
            return null;
        }
    }
}

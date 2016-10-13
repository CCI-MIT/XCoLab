package org.xcolab.service.contest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ContestSchedule;
import org.xcolab.service.contest.domain.contest.ContestDao;
import org.xcolab.service.contest.domain.contestschedule.ContestScheduleDao;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

@RestController
public class ContestScheduleController {

    @Autowired
    private ContestScheduleDao contestScheduleDao;

    @Autowired
    private ContestDao contestDao;

    @PostMapping(value = "/contestSchedules")
    public ContestSchedule createContestSchedule(@RequestBody ContestSchedule contestSchedule) {
        return this.contestScheduleDao.create(contestSchedule);
    }

    @GetMapping(value = "/contestSchedules/{contestScheduleId}")
    public ContestSchedule getContestSchedule(@PathVariable long contestScheduleId)
            throws NotFoundException {
        return contestScheduleDao.get(contestScheduleId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/contestSchedules/{contestScheduleId}/isUsed")
    public boolean isContestScheduleUsed(@PathVariable long contestScheduleId) {
        return contestDao.existsWithScheduleId(contestScheduleId);
    }

    @PutMapping(value = "/contestSchedules/{id_}")
    public boolean updateContestSchedule(@RequestBody ContestSchedule contestSchedule,
                                         @PathVariable long id_) throws NotFoundException {

        if (contestScheduleDao.exists(id_)) {
            return contestScheduleDao.update(contestSchedule);
        } else {
            throw new NotFoundException("No ContestSchedule with id " + id_);
        }
    }

    @RequestMapping(value = "/contestSchedules", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<ContestSchedule> getContestSchedules() {
        return contestScheduleDao.findByGiven();
    }

    @DeleteMapping(value = "/contestSchedules/{id_}")
    public boolean deleteContestSchedule(@PathVariable long id_)
            throws NotFoundException {
        if (contestScheduleDao.exists(id_)) {
            return contestScheduleDao.delete(id_);

        }
        throw new NotFoundException();
    }
}

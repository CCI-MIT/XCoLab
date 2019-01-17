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

import org.xcolab.client.contest.pojo.IContestSchedule;
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
    public IContestSchedule createContestSchedule(@RequestBody IContestSchedule contestSchedule) {
        return this.contestScheduleDao.create(contestSchedule);
    }

    @GetMapping(value = "/contestSchedules/{contestScheduleId}")
    public IContestSchedule getContestSchedule(@PathVariable long contestScheduleId)
            throws NotFoundException {
        return contestScheduleDao.get(contestScheduleId).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = "/contestSchedules/{contestScheduleId}/isUsed")
    public boolean isContestScheduleUsed(@PathVariable long contestScheduleId) {
        return contestDao.existsWithScheduleId(contestScheduleId);
    }

    @PutMapping(value = "/contestSchedules/{id}")
    public boolean updateContestSchedule(@RequestBody IContestSchedule contestSchedule,
                                         @PathVariable long id) throws NotFoundException {

        if (contestScheduleDao.exists(id)) {
            return contestScheduleDao.update(contestSchedule);
        } else {
            throw new NotFoundException("No ContestSchedule with id " + id);
        }
    }

    @RequestMapping(value = "/contestSchedules", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<IContestSchedule> getContestSchedules() {
        return contestScheduleDao.findByGiven();
    }

    @DeleteMapping(value = "/contestSchedules/{id}")
    public boolean deleteContestSchedule(@PathVariable long id)
            throws NotFoundException {
        if (contestScheduleDao.exists(id)) {
            return contestScheduleDao.delete(id);

        }
        throw new NotFoundException();
    }
}

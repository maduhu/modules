package org.motechproject.appointments.api.model.jobs;

import org.motechproject.appointments.api.EventKeys;
import org.motechproject.appointments.api.model.Visit;
import org.motechproject.model.MotechEvent;
import org.motechproject.model.RepeatingSchedulableJob;
import org.motechproject.scheduler.MotechSchedulerService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VisitReminderJob extends RepeatingSchedulableJob {

    public static final String SUBJECT = EventKeys.VISIT_REMINDER_EVENT_SUBJECT;

    private String externalId;
    private Date startDate;
    private Visit visit;

    public VisitReminderJob(String externalId, Date startDate, Visit visit) {
        super(createMotechEvent(externalId, visit),
                startDate,
                visit.reminder().endDate(),
                visit.reminder().repeatCount(),
                visit.reminder().intervalSeconds() * 1000);
        this.externalId = externalId;
        this.startDate = startDate;
        this.visit = visit;
    }

    private static MotechEvent createMotechEvent(String externalId, Visit visit) {
        return new MotechEvent(SUBJECT, getParameters(visit, externalId));
    }

    private static Map<String, Object> getParameters(Visit visit, String externalId) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put(EventKeys.EXTERNAL_ID_KEY, externalId);
        parameters.put(MotechSchedulerService.JOB_ID_KEY, visit.id());
        parameters.put(EventKeys.APPOINTMENT_ID, visit.appointment().id());
        return parameters;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VisitReminderJob))
            return false;
        VisitReminderJob that = (VisitReminderJob) obj;
        return that.externalId.equals(this.externalId)
                && this.startDate.equals(that.startDate)
                && this.visit.equals(that.visit);
    }
}
package com.sena.schedule.service;

import com.sena.schedule.model.reminder;
import com.sena.schedule.model.reminderLog;
import com.sena.schedule.model.scheduleDose;
import com.sena.schedule.repository.IReminder;
import com.sena.schedule.repository.IReminderLog;
import com.sena.schedule.repository.IScheduleDose;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class reminderSchedulerService {

    @Autowired
    private IReminder reminderRepo;

    @Autowired
    private emailService emailService;

    @Autowired
    private IReminderLog reminderLogRepo;

    @Autowired
    private IScheduleDose scheduleDoseRepo;

   // Ejecuta cada 5 minutos
    @Scheduled(fixedRate = 300_000)
    public void checkAndSendReminders() throws MessagingException {
        LocalDateTime now = LocalDateTime.now();
        List<reminder> reminders = reminderRepo.findAll();
        for (reminder r : reminders) {
            if (!r.getStatus() && r.getSendAt().isBefore(now.plusMinutes(1)) && r.getSendAt().isAfter(now.minusMinutes(5))) {
                String email = r.getDoseID().getPatient().getEmail();
                String subject = "Recordatorio de medicación";
                String body = "Hola " + r.getDoseID().getPatient().getName() +
                        ", es hora de tomar tu medicamento: " + r.getDoseID().getMedication().getName();
                emailService.emailSender(email, subject, body);
                r.setStatus(true);
                reminderRepo.save(r);
                
                // REGISTRO EN BITÁCORA (reminderLog)
                reminderLog log = new reminderLog();
                log.setReminderID(r.getReminderID());
                log.setPatientEmail(email);
                log.setMedicationName(r.getDoseID().getMedication().getName());
                log.setSentAt(now);
                reminderLogRepo.save(log);
            }
        }
    }

    @Scheduled(fixedRate = 300_000)
    public void notifyUnconfirmedDoses() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneHourAgo = now.minusHours(1);
        List<scheduleDose> pending = scheduleDoseRepo.findByConfirmationStatus(0);
        for (scheduleDose sd : pending) {
            if (sd.getStartDate().isBefore(oneHourAgo) && sd.getStartDate().isAfter(oneHourAgo.minusMinutes(5))) {
                String email = sd.getPatient().getEmail();
                String subject = "Advertencia: No se confirmó la toma";
                String body = "Hola " + sd.getPatient().getName() +
                              ", no confirmaste la toma de tu medicamento: " + sd.getMedication().getName() +
                              ". Por favor confirma si la realizaste.";
                try {
                    emailService.emailSender(email, subject, body);
                } catch (MessagingException e) {
                    // Opcional: loguear este evento de error
                    e.printStackTrace();
                }
                // Opcional: loguear este evento
            }
        }
    }
}
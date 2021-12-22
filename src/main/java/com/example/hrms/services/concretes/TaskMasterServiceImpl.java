package com.example.hrms.services.concretes;

import com.example.hrms.models.JobAdvertisement;
import com.example.hrms.models.TaskMaster;
import com.example.hrms.repositories.JobAdvertisementRepository;
import com.example.hrms.repositories.TaskMasterRepository;
import com.example.hrms.services.abtsracts.TaskMasterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskMasterServiceImpl implements TaskMasterService {
    
    private final TaskMasterRepository taskMasterRepository;
    private final JobAdvertisementRepository jobAdvertisementRepository;

    public TaskMasterServiceImpl(TaskMasterRepository taskMasterRepository,
                                 JobAdvertisementRepository jobAdvertisementRepository) {
        this.taskMasterRepository = taskMasterRepository;
        this.jobAdvertisementRepository = jobAdvertisementRepository;
    }
    
    @Override
    public TaskMaster saveTaskMaster(TaskMaster taskMaster) {
        return taskMasterRepository.save(taskMaster);
    }

    @Override
    public List<TaskMaster> getAllTaskMasters() {
        return taskMasterRepository.findAll();
    }

    @Override
    public TaskMaster updateTaskMaster(TaskMaster taskMaster, Long id) {
        Optional< TaskMaster > updatedTaskMaster = taskMasterRepository.findById(id);
        if (updatedTaskMaster.isPresent()) {
            updatedTaskMaster.get().setCompanyName(taskMaster.getCompanyName());
            updatedTaskMaster.get().setWebsite(taskMaster.getWebsite());
            updatedTaskMaster.get().setEmail(taskMaster.getEmail());
            updatedTaskMaster.get().setPassword(taskMaster.getPassword());
            updatedTaskMaster.get().setPhoneNumber(taskMaster.getPhoneNumber());
            taskMasterRepository.save(updatedTaskMaster.get());
            System.out.println(updatedTaskMaster.get());
            return updatedTaskMaster.get();
        }
        return null;
    }

    @Override
    public TaskMaster deleteTaskMaster(Long id) {
        Optional<TaskMaster> taskMaster = taskMasterRepository.findById(id);
        taskMasterRepository.deleteById(id);
        return taskMaster.get();
    }

    @Override
    public TaskMaster findByCompanyName(String companyName) {
        return taskMasterRepository.findTaskMasterBycompanyName(companyName);
    }

    @Override
    public TaskMaster findByEmail(String email) {
        return taskMasterRepository.findTaskMasterByemail(email);
    }

    @Override
    public TaskMaster findByWebSite(String webSite) {
        return taskMasterRepository.findTaskMasterBywebsite(webSite);
    }

    @Override
    public TaskMaster findByPhoneNumber(String phoneNumber) {
        return taskMasterRepository.findTaskMasterByphoneNumber(phoneNumber);
    }

    @Override
    public boolean validateEmailToCheckCommonDomain(String eMail,String companyName) {
        return eMail.contains(companyName);
    }

    @Override
    public boolean validateEmail() {
        return false;
    }

    @Override
    public TaskMaster findById(Long id) {
        return taskMasterRepository.findById(id).get();
    }

    @Override
    public TaskMaster publishJob(Long taskMasterId, Long jobId) {
        Optional<JobAdvertisement> job = jobAdvertisementRepository.findById(jobId);
        Optional<TaskMaster> taskMaster = taskMasterRepository.findById(taskMasterId);
        if(job.isPresent() && taskMaster.isPresent()) {
            job.get().setTask_master(taskMaster.get());
            jobAdvertisementRepository.save(job.get());
            taskMaster.get().getPublishedJobs().add(job.get());
            taskMasterRepository.save(taskMaster.get());
            return taskMaster.get();
        }
        return null;
    }


}

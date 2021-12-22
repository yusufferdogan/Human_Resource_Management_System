package com.example.hrms.services.abtsracts;

import com.example.hrms.models.TaskMaster;

import java.util.List;

public interface TaskMasterService {

    TaskMaster saveTaskMaster(TaskMaster taskMaster);

    List<TaskMaster> getAllTaskMasters();

    TaskMaster updateTaskMaster(TaskMaster taskMaster, Long id);

    TaskMaster deleteTaskMaster(Long id);

    TaskMaster findByCompanyName(String companyName);

    TaskMaster findByEmail(String eMailAddress);

    TaskMaster findByWebSite(String webSite);

    TaskMaster findByPhoneNumber(String phoneNumber);

    boolean validateEmailToCheckCommonDomain(String eMail,String companyName);

    boolean validateEmail();

    TaskMaster findById(Long id);

    TaskMaster publishJob(Long taskMasterId,Long jobId);
}

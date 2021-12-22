package com.example.hrms.repositories;

import com.example.hrms.models.TaskMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskMasterRepository extends JpaRepository<TaskMaster,Long> {

    TaskMaster findTaskMasterBycompanyName(String companyName);

    TaskMaster findTaskMasterByemail(String email);

    TaskMaster findTaskMasterBywebsite(String website);

    TaskMaster findTaskMasterByphoneNumber(String phone);
}

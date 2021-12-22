package com.example.hrms.controllers;

import com.example.hrms.core.utilities.results.ErrorResult;
import com.example.hrms.core.utilities.results.SuccessDataResult;
import com.example.hrms.models.TaskMaster;
import com.example.hrms.services.abtsracts.JobAdvertisementService;
import com.example.hrms.services.abtsracts.TaskMasterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task_masters")
public class TaskMasterController {

    private final TaskMasterService taskMasterService;
    private final JobAdvertisementService jobAdvertisementService;

    public TaskMasterController(TaskMasterService taskMasterService, JobAdvertisementService jobAdvertisementService) {
        this.taskMasterService = taskMasterService;
        this.jobAdvertisementService = jobAdvertisementService;
    }

    @GetMapping
    ResponseEntity<?> getAllTaskMasters(){
        SuccessDataResult<List<TaskMaster>> results =  new SuccessDataResult<>
                (this.taskMasterService.getAllTaskMasters(),"All TaskMasters Returned");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> addTaskMaster(@RequestBody TaskMaster taskMaster){
        System.out.println(taskMaster);
        if(taskMasterService.findByEmail(taskMaster.getEmail()) != null){
            return new ResponseEntity<>(new ErrorResult("Account with Email already Exist"), HttpStatus.NOT_ACCEPTABLE);
        }
        if(taskMasterService.validateEmailToCheckCommonDomain(taskMaster.getEmail(), taskMaster.getCompanyName())){
            return new ResponseEntity<>(new ErrorResult("Wrong Domain for Email"),
                    HttpStatus.NOT_ACCEPTABLE);
        }
        SuccessDataResult<TaskMaster> results =  new SuccessDataResult<>
                (this.taskMasterService.saveTaskMaster(taskMaster),"New TaskMaster Successfully enrolled");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTaskMaster(@PathVariable Long id, @RequestBody TaskMaster taskMaster){
        TaskMaster updatedTaskMaster = this.taskMasterService.updateTaskMaster(taskMaster,id);
        if(updatedTaskMaster == null){
            return new ResponseEntity<>(new ErrorResult("TaskMaster does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<TaskMaster> results =  new SuccessDataResult<>
                (updatedTaskMaster,"TaskMaster Updated");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTaskMaster(@PathVariable Long id){
        TaskMaster deleted = taskMasterService.deleteTaskMaster(id);
        if(deleted == null){
            return new ResponseEntity<>(new ErrorResult("TaskMaster does not exist"), HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<TaskMaster> results =  new SuccessDataResult<>
                (deleted,"TaskMaster deleted");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PostMapping("{id}/publish")
    ResponseEntity<?> publishJob(@PathVariable Long id,Long jobId){
        System.out.println(id+","+jobId);
        TaskMaster taskMaster = taskMasterService.publishJob(id,jobId);
        if(taskMaster == null){
            return new ResponseEntity<>(new ErrorResult("TaskMaster Or Job Advertisement does not exist"),
                    HttpStatus.NOT_FOUND);
        }
        SuccessDataResult<TaskMaster> results =  new SuccessDataResult<>
                (taskMaster,"TaskMaster has published job");
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}

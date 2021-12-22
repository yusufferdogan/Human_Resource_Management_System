package com.example.hrms.services.abtsracts;

import com.example.hrms.models.JobAdvertisement;

import java.util.List;

public interface JobAdvertisementService {

    JobAdvertisement saveJobAdvertisement(JobAdvertisement position);

    List<JobAdvertisement> getAllJobAdvertisements();

    JobAdvertisement updateJobAdvertisement(JobAdvertisement position, Long id);

    JobAdvertisement deleteJobAdvertisement(Long id);

    List<JobAdvertisement> getAllActiveJobAdvertisements();

    List<JobAdvertisement> getAllJobAdvertisementsOfCompany(String company);

    List<JobAdvertisement> getAllActiveJobAdvertisementsOfCompany(String company);

    JobAdvertisement findById(Long jobId);

    JobAdvertisement addCity(Long id,Long cityId);

    JobAdvertisement addPosition(Long id,Long positionId);
}

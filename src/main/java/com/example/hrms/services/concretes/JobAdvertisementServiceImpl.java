package com.example.hrms.services.concretes;

import com.example.hrms.models.City;
import com.example.hrms.models.JobAdvertisement;
import com.example.hrms.models.Position;
import com.example.hrms.repositories.CityRepository;
import com.example.hrms.repositories.JobAdvertisementRepository;
import com.example.hrms.repositories.PositionRepository;
import com.example.hrms.services.abtsracts.JobAdvertisementService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JobAdvertisementServiceImpl implements JobAdvertisementService {

    private final JobAdvertisementRepository repository;
    private final CityRepository cityRepository;
    private final PositionRepository positionRepository;

    public JobAdvertisementServiceImpl(JobAdvertisementRepository repository, CityRepository cityRepository, PositionRepository positionRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public JobAdvertisement saveJobAdvertisement(JobAdvertisement job) {
        return repository.save(job);
    }

    @Override
    public List<JobAdvertisement> getAllJobAdvertisements() {
        return repository.findAll();
    }

    @Override
    public JobAdvertisement updateJobAdvertisement(JobAdvertisement job, Long id) {
        Optional<JobAdvertisement> updatedJobAdvertisement = repository.findById(id);
        if (updatedJobAdvertisement.isPresent()) {
            updatedJobAdvertisement.get().setJobDescription(job.getJobDescription());
            updatedJobAdvertisement.get().setDeadline(job.getDeadline());
            updatedJobAdvertisement.get().setOpenPositionNumber(job.getOpenPositionNumber());
            repository.save(updatedJobAdvertisement.get());
            System.out.println(updatedJobAdvertisement.get());
            return updatedJobAdvertisement.get();
        }
        return null;
    }

    @Override
    public JobAdvertisement deleteJobAdvertisement(Long id) {
        Optional<JobAdvertisement> job = repository.findById(id);
        repository.deleteById(id);
        return job.get();
    }

    @Override
    public List<JobAdvertisement> getAllActiveJobAdvertisements() {
        List<JobAdvertisement> activeJobs = getAllJobAdvertisements();
        for (JobAdvertisement job : activeJobs) {
            if (job.getDeadline().isBefore(LocalDate.now())) {
                activeJobs.add(job);
            }
        }
        return activeJobs;
    }

    @Override
    public List<JobAdvertisement> getAllJobAdvertisementsOfCompany(String company) {
        List<JobAdvertisement> jobsOfCompany = getAllJobAdvertisements();
        for (JobAdvertisement job : jobsOfCompany) {
            if (job.getTask_master().getCompanyName().equals(company)) {
                jobsOfCompany.add(job);
            }
        }
        return jobsOfCompany;
    }

    @Override
    public List<JobAdvertisement> getAllActiveJobAdvertisementsOfCompany(String company) {
        List<JobAdvertisement> activeJobsOfCompany = getAllJobAdvertisements();
        for (JobAdvertisement job : activeJobsOfCompany) {
            if (job.getDeadline().isBefore(LocalDate.now())
                    && job.getTask_master().getCompanyName().equals(company)) {
                activeJobsOfCompany.add(job);
            }
        }
        return activeJobsOfCompany;
    }

    @Override
    public JobAdvertisement findById(Long jobId) {
        return repository.findById(jobId).get();
    }

    @Override
    public JobAdvertisement addCity(Long id, Long cityId) {
        Optional<JobAdvertisement> job = repository.findById(id);
        Optional<City> city = cityRepository.findById(cityId);
        if (job.isPresent() && city.isPresent()) {
            job.get().getCities().add(city.get());
            city.get().getCities_in_job_advertisement().add(job.get());
            repository.save(job.get());
            cityRepository.save(city.get());
            return job.get();
        }
        return null;
    }

    @Override
    public JobAdvertisement addPosition(Long id, Long positionId) {
        Optional<JobAdvertisement> job = repository.findById(id);
        Optional<Position> position = positionRepository.findById(positionId);
        if (job.isPresent() && position.isPresent()) {
            job.get().getPositions().add(position.get());
            position.get().getPositions_in_job_advertisement().add(job.get());
            repository.save(job.get());
            positionRepository.save(position.get());
            return job.get();
        }
        return null;
    }


}
